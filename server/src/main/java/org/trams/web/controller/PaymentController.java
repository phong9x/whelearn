/*
 * Created on 22 thg 8 2016 ( Time 15:01:35 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.trams.bean.BankInfo;
import org.trams.bean.CouponUse;
import org.trams.bean.Program;
import org.trams.bean.Purchase;
import org.trams.bean.User;
import org.trams.bean.jpa.ProgramEntity;
import org.trams.business.service.BankInfoService;
import org.trams.business.service.CouponUseService;
import org.trams.business.service.ProgramRegisterService;
//--- Entities
import org.trams.business.service.ProgramService;
import org.trams.business.service.PurchaseService;
//--- Services
import org.trams.business.service.UserService;
import org.trams.rest.common.notification.Notification;
//--- Common classes
import org.trams.web.common.AbstractController;
import org.trams.web.common.ApplicationDefine;
import org.trams.web.common.utils.ConstantUtils;
import org.trams.web.common.utils.DataUtils;

@Controller
@RequestMapping("/payment")
public class PaymentController extends AbstractController {

	// --- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "main";

	// --- JSP pages names ( View name in the MVC model )

	private static final String JSP_POPUP = "payment";
	private static final String JSP_RESULT = "payment/result";
	private static final String JSP_NOTI = "payment/noti";
	private static final String JSP_NEXT = "payment/next";

	private static final String JSP_TEST = "payment/test";
	private static final String JSP_RESULT_TEST = "payment/result/test";
	
	
//	private static final String MID = "whelearnwh"; // whelearn MID
	private static final String MID = "INIpayTest"; // test MID

	private static final int PAYMENT_FINISH = 0;
	private static final int PAYMENT_IN_PROGRESS = 1;
	private static final int PAYMENT_NEED_MONEY_TRANSFER = 2;

	// Resource
	@Resource
	private UserService userService;

	@Resource
	private ProgramService programService;

	@Resource
	private PurchaseService purchaseService;

	@Resource
	private CouponUseService couponUseService;

	@Resource
	private ProgramRegisterService programRegisterService;

	@Resource
	private BankInfoService bankInfoService;

	// --------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */
	public PaymentController() {
		super(PaymentController.class, MAIN_ENTITY_NAME);
		log("UserController created.");
	}

	@RequestMapping(value = "")
	public String popup(@RequestParam(value = "order_id", defaultValue = "") Integer order_id, HttpSession session,
			Model model) throws Exception {

		log("Action 'create'");

		model.addAttribute("mid", MID);

		try {
			// 인증
			Purchase purchase = purchaseService.findById(order_id);
			Program program = programService.findById(purchase.getProgramId());
			User user = userService.findById(purchase.getUserId());
			model.addAttribute("program_title", program.getTitle());
			model.addAttribute("order_id", order_id);
			model.addAttribute("price", purchase.getTotalMoney());
			model.addAttribute("url_base", ConstantUtils.getConfig("domain"));
			model.addAttribute("user", user);
			System.out.println("Create order success");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return JSP_POPUP;
	}

	@RequestMapping(value = "/next_card")
	public String paymentNextCard(HttpSession session, HttpServletRequest request, Model model) throws Exception {
		log("Action 'payment Next card'");

		boolean success = false;
		int state = PAYMENT_IN_PROGRESS;

		request.setCharacterEncoding("euc-kr");
		Enumeration<String> paramNames = request.getParameterNames();
        
        if (paramNames != null) {
        	log(" Request body:  ");
            while (paramNames.hasMoreElements()) {
            	String paramName = paramNames.nextElement();
            	String value = request.getParameter(paramName);
            	log("  [ " + paramName + ": " + value + " ]");
            }
        }
        
		String P_STATUS = request.getParameter("P_STATUS");
		String P_RMESG1 = request.getParameter("P_RMESG1");
		String P_TID = request.getParameter("P_TID");
		String P_REQ_URL = request.getParameter("P_REQ_URL");
		String P_NOTI = request.getParameter("P_NOTI");
		String[] P_NOTI_S = P_NOTI.split("\\|");
		int P_OID = Integer.parseInt(P_NOTI_S[0]);
		String phone = P_NOTI_S[1];
		String message = P_RMESG1;
//		log("P_NOTI:"+P_NOTI);
//		log("P_OID:"+P_OID);
//		log("phone:"+phone);
		try {
			if (P_STATUS.equals("00")) // success
			{
				P_REQ_URL = P_REQ_URL + "?P_TID=" + P_TID + "&P_MID=" + MID;
				HashMap<String, String> map = requestApproval(P_REQ_URL);
				if (map != null && map.size() > 0) {
					P_STATUS = map.get("P_STATUS");
					if (P_STATUS.equals("00")) {
						P_OID = Integer.parseInt(map.get("P_OID"));
						P_TID = map.get("P_TID");
						String P_UNAME = map.get("P_UNAME");
						success = paymentSuccessProcess(P_OID, P_TID, P_UNAME, phone);
						if (!success) {
							log("need cancel payment, tid: " + P_TID);
							System.out.println("need cancel payment, tid: " + P_TID);
						} else {
							state = PAYMENT_FINISH;
						}
					} else {
						message = map.get("P_RMESG1");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!success) {
			purchaseService.delete(P_OID);
		}
		model.addAttribute("P_STATUS", P_STATUS);
		model.addAttribute("success", success);
		model.addAttribute("state", state);
		model.addAttribute("message", message);

		return JSP_RESULT;
	}

	private HashMap<String, String> requestApproval(String reqUrl) {
		log("requestApproval");
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(reqUrl);
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
		HashMap<String, String> map = new HashMap<String, String>();

		try {
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				log("Method failed: " + method.getStatusLine());
			} else {
				// Read the response body.
				byte[] responseBody = method.getResponseBody(); // 승인결과 파싱
				String[] values = new String(responseBody, "EUC-KR").split("&");

				for (int x = 0; x < values.length; x++) {
					log(values[x]); // 승인결과 출력

					// 승인결과를 파싱값 잘라 hashmap에 저장
					int i = values[x].indexOf("=");
					String key1 = values[x].substring(0, i).trim();
					String value1 = values[x].substring(i + 1).trim();

					map.put(key1, value1);
				}
			}
		} catch (HttpException e) {
			log("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			log("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
		return map;
	}

	private boolean paymentSuccessProcess(Integer order_id, String tid, String fullname, String phone) {
		log("paymentSuccessProcess");
		boolean success = false;

		try {
			Purchase purchase = purchaseService.findById(order_id);
			if (purchase.getOrderProgram() != PAYMENT_FINISH) {
				purchase.setOrderProgram(PAYMENT_FINISH);
				purchase.setTid(tid);
				purchase.setFullname(fullname);
				purchase.setPhone(phone);
				purchase = purchaseService.update(purchase);
				if (purchase != null) {
					programService.updateTotalMoney(purchase.getProgramId());
				}
				CouponUse c1 = new CouponUse();
				c1.setCouponId(5);
				c1.setCreateDate(DataUtils.getNowDate());
				Calendar ca = Calendar.getInstance();
				ca.setTime(DataUtils.getNowDate());
				ca.add(Calendar.DAY_OF_YEAR, 90);
				c1.setDeadline(ca.getTime());
				c1.setStatusUse(0);
				c1.setUpdateDate(DataUtils.getNowDate());
				c1.setUserId(purchase.getUserId());
				CouponUse cu = couponUseService.create(c1);
				List<String> list_reg = new ArrayList<>();
				ProgramEntity p = programService.findByIdEntity(purchase.getProgramId());
				list_reg.add(p.getUser().getFcmToken());
				Notification n = new Notification();
				n.sendNotification(list_reg, "정산완료 되었을 때", p.getTitle()+"에 대한 정산이 완료되었습니다. 세부 사항을 확인해 주세요.", purchase.getProgramId(),
						ApplicationDefine.NOTIFICATION_FinishPayCourseNotification, 1);
				success = true;
			}

		} catch (Exception e) {
			log("paymentSuccessProcess Fail");
			e.printStackTrace();
			return success;
		}

		return success;
	}

	@RequestMapping(value = "/return_bank")
	public String paymentReturnBank(@RequestParam(value = "P_OID", defaultValue = "") Integer P_OID,
			HttpSession session, HttpServletRequest request, Model model) throws Exception {
		log("Action 'payment return bank'");
		Purchase purchase = purchaseService.findById(P_OID);
		boolean success = false;
		int state = PAYMENT_IN_PROGRESS;

		if (purchase != null) {
			state = purchase.getOrderProgram();
			if (state == PAYMENT_FINISH) {
				success = true;
			}else{
				purchaseService.delete(P_OID);
			}
		}
		
		System.out.println("success:" + success);
		System.out.println("state:" + state);
		model.addAttribute("success", success);
		model.addAttribute("state", state);

		return JSP_RESULT;
	}

	@RequestMapping(value = "/noti_bank")
	public String paymentNotiBank(HttpSession session, HttpServletRequest request, Model model) throws Exception {
		log("Action 'payment noti bank'");
		request.setCharacterEncoding("euc-kr");

		String P_STATUS = request.getParameter("P_STATUS");
		String P_TID = request.getParameter("P_TID");
		//int P_OID = Integer.parseInt(request.getParameter("P_OID"));
		String P_TYPE = request.getParameter("P_TYPE");
		
		String P_NOTI = request.getParameter("P_NOTI");
		String[] P_NOTI_S = P_NOTI.split("\\|");
		int P_OID = Integer.parseInt(P_NOTI_S[0]);
		String phone = P_NOTI_S[1];
		
		String result = "OK";
		Purchase purchase = purchaseService.findByTid(P_TID);

		if (purchase == null) {
			if ((P_TYPE.equals("BANK") && P_STATUS.equals("00")) || (P_TYPE.equals("VBANK") && P_STATUS.equals("02"))) {
				String P_UNAME = request.getParameter("P_UNAME");
				boolean success = paymentSuccessProcess(P_OID, P_TID, P_UNAME, phone);
				if (!success) {
					result = "FAIL";
					log("need cancel payment, tid: " + P_TID);
					System.out.println("need cancel payment, tid: " + P_TID);
				}
			} else if (P_TYPE.equals("VBANK") && !P_STATUS.equals("02")) {
				log("ignore account info noti, tid: " + P_TID);
				System.out.println("ignore account info noti, tid: " + P_TID);
			} else {
				purchaseService.delete(P_OID);
			}
		}

		model.addAttribute("result", result);

		return JSP_NOTI;
	}

	@RequestMapping(value = "/next_vbank")
	public String paymentNextVBank(HttpSession session, HttpServletRequest request, Model model) throws Exception {
		log("Action 'payment Next vbank'");

		boolean success = false;
		int state = PAYMENT_IN_PROGRESS;

		request.setCharacterEncoding("euc-kr");

		String P_STATUS = request.getParameter("P_STATUS");
		String P_RMESG1 = request.getParameter("P_RMESG1");
		String P_TID = request.getParameter("P_TID");
		String P_REQ_URL = request.getParameter("P_REQ_URL");
		String P_NOTI = request.getParameter("P_NOTI");
		String[] P_NOTI_S = P_NOTI.split("\\|");
		int P_OID = Integer.parseInt(P_NOTI_S[0]);

		String message = P_RMESG1;

		try {
			if (P_STATUS.equals("00")) // success
			{
				P_REQ_URL = P_REQ_URL + "?P_TID=" + P_TID + "&P_MID=" + MID;
				HashMap<String, String> map = requestApproval(P_REQ_URL);
				if (map != null && map.size() > 0) {
					P_STATUS = map.get("P_STATUS");
					if (P_STATUS.equals("00")) {
						P_OID = Integer.parseInt(map.get("P_OID"));
						P_TID = map.get("P_TID");
						Purchase purchase = purchaseService.findById(P_OID);

						if (purchase != null) {
							state = purchase.getOrderProgram();
							if (state != PAYMENT_FINISH) {
								String P_VACT_NUM = map.get("P_VACT_NUM");
								String P_VACT_DATE = map.get("P_VACT_DATE");
								String P_VACT_TIME = map.get("P_VACT_TIME");
								String P_VACT_NAME = map.get("P_VACT_NAME");
								String P_VACT_BANK_CODE = map.get("P_VACT_BANK_CODE");

								purchase.setOrderProgram(PAYMENT_NEED_MONEY_TRANSFER);
								purchase.setAccountInfo(P_VACT_NUM + "|" + P_VACT_DATE + "|" + P_VACT_TIME + "|"
										+ P_VACT_NAME + "|" + P_VACT_BANK_CODE + "|" + P_TID);
								purchase = purchaseService.update(purchase);
								state = purchase.getOrderProgram();
								model.addAttribute("P_VACT_NUM", P_VACT_NUM);
								model.addAttribute("P_VACT_DATE", P_VACT_DATE);
								model.addAttribute("P_VACT_TIME", P_VACT_TIME);
								model.addAttribute("P_VACT_NAME", P_VACT_NAME);
								model.addAttribute("P_VACT_BANK_CODE", P_VACT_BANK_CODE);
								String bankName = P_VACT_BANK_CODE;
								BankInfo bank = bankInfoService.findByBankCode(P_VACT_BANK_CODE);
								if (bank != null) {
									bankName = bank.getBankName();
								}
								model.addAttribute("BANK_NAME", bankName);

								success = true;
							} else {
								log("need cancel payment, tid: " + P_TID);
								System.out.println("need cancel payment, tid: " + P_TID);
							}
						}
					} else {
						message = map.get("P_RMESG1");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!success) {
			purchaseService.delete(P_OID);
		}
		System.out.println("success:" + success);
		System.out.println("state:" + state);
		
		model.addAttribute("P_STATUS", P_STATUS);
		model.addAttribute("success", success);
		model.addAttribute("state", state);
		model.addAttribute("message", message);

		return JSP_NEXT;
	}

	@RequestMapping(value = "/test")
	public String popup_test(@RequestParam(value = "order_id", defaultValue = "") Integer order_id, HttpSession session,
			Model model) throws Exception {

		log("Action 'create'");

		model.addAttribute("url_base", ConstantUtils.getConfig("domain"));
		System.out.println("Create order success");

		return JSP_TEST;
	}
	
	@RequestMapping(value = "/result/test")
	public String result_test(@RequestParam(value = "order_id", defaultValue = "") Integer order_id, HttpSession session,
			Model model) throws Exception {

		log("Action 'create'");

		model.addAttribute("url_base", ConstantUtils.getConfig("domain"));
		System.out.println("Create order success");

		return JSP_RESULT_TEST;
	}
	//
	// @RequestMapping(value = "/result")
	// public String result(
	// @RequestParam(value = "order_id", defaultValue = "") Integer order_id,
	// HttpSession session, HttpServletRequest request, Model model) throws
	// Exception {
	// log("Action 'create'");
	// String addr = request.getRemoteAddr().toString();
	// System.out.println("order_id:"+order_id);
	// // 이니시스에서 받은 value
	// String P_TID = request.getParameter("P_TID") + "";
	// String P_MID = request.getParameter("P_MID") + "";
	// String P_AUTH_DT = request.getParameter("P_AUTH_DT") + "";
	// String P_STATUS = request.getParameter("P_STATUS") + "";
	// String P_TYPE = request.getParameter("P_TYPE") + "";
	// String P_OID = request.getParameter("P_OID") + "";
	// String P_FN_CD1 = request.getParameter("P_FN_CD1") + "";
	// String P_FN_CD2 = request.getParameter("P_FN_CD2") + "";
	// String P_FN_NM = request.getParameter("P_FN_NM") + "";
	// String P_UNAME = request.getParameter("P_UNAME") + "";
	// String P_AMT = request.getParameter("P_AMT") + "";
	// String P_RMESG1 = request.getParameter("P_RMESG1") + "";
	// String P_RMESG2 = request.getParameter("P_RMESG2") + "";
	// String P_NOTI = request.getParameter("P_NOTI") + "";
	// String P_AUTH_NO = request.getParameter("P_AUTH_NO") + "";
	// System.out.println("P_OID:" + P_OID);
	// System.out.println("P_STATUS:" + P_STATUS);
	// model.addAttribute("P_STATUS", P_STATUS);
	// model.addAttribute("success", 0);
	// /***********************************************************************************
	// * 결제처리에 관한 로그 기록 위에서 상점 데이터베이스에 등록 성공유무에 따라서 성공시에는 "OK"를 이니시스로 실패시는
	// * "FAIL" 을 리턴하셔야합니다. 아래 조건에 데이터베이스 성공시 받는 FLAG 변수를 넣으세요 (주의) OK를 리턴하지
	// * 않으시면 이니시스 지불 서버는 "OK"를 수신할때까지 계속 재전송을 시도합니다 기타 다른 형태의
	// * out.println(response.write)는 하지 않으시기 바랍니다
	// ***********************************************************************************/
	// try {
	// if (P_STATUS.equals("00")) // 입금통보 "02" 가 아니면(가상계좌 채번 : 00 또는 01
	// {
	// model.addAttribute("success", 1);
	//
	// } else {
	// purchaseService.delete(order_id);
	// model.addAttribute("success", 0);
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// return JSP_RESULT;
	// }
	//
	// @RequestMapping(value = "/test")
	// public String test(
	// @RequestParam(value = "order_id", defaultValue = "") Integer order_id,
	// HttpSession session, Model model)
	// throws Exception {
	// log("Action 'create'");
	// String mid = "testoid"; // 가맹점 ID(가맹점 수정후 고정)
	// try {
	// // 인증
	// Purchase p = purchaseService.findById(order_id);
	// Program program = programService.findById(p.getProgramId());
	//
	// model.addAttribute("program_title", program.getTitle());
	// model.addAttribute("order_id", order_id);
	// model.addAttribute("price", p.getTotalMoney());
	//
	// model.addAttribute("store_name", "whelearn");
	// model.addAttribute("mid", mid);
	// model.addAttribute("product_name", "whelearn");
	//
	// System.out.println("Create order success");
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// return JSP_TEST;
	// }
	//
	// @RequestMapping(value = "/result/test")
	// public String test(HttpSession session, HttpServletRequest request, Model
	// model) throws Exception {
	// log("Action 'create'");
	//
	// return JSP_RESULT_TEST;
	// }
}
