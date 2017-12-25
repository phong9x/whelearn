/*
 * Created on 30 thg 8 2016 ( Time 15:36:53 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.rest.controller;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.trams.bean.UserItem;
import org.trams.bean.ProgramRegister;
import org.trams.bean.ProgramRegisterItem;
import org.trams.bean.Purchase;
import org.trams.bean.TimeLearn;
import org.trams.bean.jpa.ProgramRegisterEntity;
import org.trams.bean.jpa.PurchaseEntity;
import org.trams.bean.jpa.TimeLearnEntity;
import org.trams.business.service.ProgramRegisterService;
import org.trams.business.service.PurchaseService;
import org.trams.business.service.TimeLearnService;
import org.trams.business.service.mapping.TimeLearnServiceMapper;
import org.trams.rest.common.AbstractRestController;
import org.trams.rest.common.AuthorizationToken;
import org.trams.web.common.utils.DataUtils;
/**
 * Spring MVC controller for 'ProgramRegister' management.
 */
@RequestMapping("/programRegister")
@Controller
public class ProgramRegisterRestController extends AbstractRestController{

	@Resource
	private ProgramRegisterService programRegisterService;
	
	@Resource
	private TimeLearnService timeLearnService;
	
	@Resource
	private PurchaseService purchaseService;
	
	@Resource
	private TimeLearnServiceMapper timeLearnServiceMapper;
	
	@RequestMapping( value="/all",
			method = {RequestMethod.POST, RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> findAll(
	@RequestHeader(value="token") String token
	) {
		//UserItem user =AuthorizationToken.convertToObject(token);
		try {
			List<ProgramRegister> list = programRegisterService.findAll();
			return SUCCESS(list);
		} catch (Exception e) {
			return FAIL("HAVE ERROR: "+e);
		}
	} 
	
	@RequestMapping( value="/paging",
			method = {RequestMethod.POST, RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> findAllPaging(
	@RequestHeader(value="token",required=false) String token,
	@RequestBody String json
	) throws ParseException {
		UserItem user =AuthorizationToken.convertToObject(token);
		HashMap<String, Object> ret =new HashMap<String, Object>();
		try {
			JSONParser parser = new JSONParser();
			JSONObject jsonObj = (JSONObject) parser.parse(json);
			Integer page = DataUtils.parseInt(jsonObj.get("page"));
			
			Page<PurchaseEntity> list = purchaseService.listPagingByUserId(user.getId(), page, 10);
			if(list.getContent()!=null){
				List<ProgramRegisterItem> list2 = new ArrayList<ProgramRegisterItem>();
				for (PurchaseEntity i : list) {
					ProgramRegisterItem r = new ProgramRegisterItem();
					r.setTitle(i.getProgram().getTitle());
					r.setAddress(i.getProgram().getAddress());
					r.setVideo_url(i.getProgram().getVideoUrl());
					r.setImage_url(i.getProgram().getImageUrl());
					r.setCategoryName(i.getProgram().getCategoryName());
					r.setFee(i.getProgram().getFee());
					r.setProgramId(i.getProgram().getId());
					r.setRegisterId(i.getId());
					r.setRefund_fee(i.getRefundMoney());
					List<TimeLearnEntity> list_time_entity = timeLearnService.findByProgramId(i.getProgram().getId());
					List<String> list_time = new ArrayList<>();
					for (TimeLearnEntity j : list_time_entity) {
						Calendar c = Calendar.getInstance();
						c.setTime(j.getDateLearn());
						Integer day = c.get(Calendar.DAY_OF_WEEK);
						String str_day;
						if (day == 2) {
							str_day = "월";
						} else if (day == 3) {
							str_day = "화";
						} else if (day == 4) {
							str_day = "수";
						} else if (day == 5) {
							str_day = "목";
						} else if (day == 6) {
							str_day = "금";
						} else if (day == 7) {
							str_day = "토";
						} else {
							str_day = "일";
						}
						String time = DataUtils.convertDate_To_String(j.getDateLearn(), "MM/dd") 
								+ "("+str_day+") "
								+ DataUtils.convertDate_To_String(j.getFromTime(), "HH:mm") + "-"
								+ DataUtils.convertDate_To_String(j.getToTime(), "HH:mm");
						list_time.add(time);
					}
					r.setTimeLearn(list_time);
					list2.add(r);
				}
				
				ret.put("list", list2);
				ret.put("page", page);
				ret.put("totalPage", list.getTotalPages());
				return SUCCESS(ret);
			}else{
				return SUCCESS();
			}
		} catch (Exception e) {
			return FAIL("HAVE ERROR: "+e);
		}
		
	} 


	@RequestMapping( value="/item/{id}",
			method = {RequestMethod.POST, RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> findOne(
	@PathVariable("id") Integer id,
	@RequestHeader(value="token") String token
		) {
		//UserItem user =AuthorizationToken.convertToObject(token);
		try {
			ProgramRegister item= programRegisterService.findById(id);
			if(item!=null){
				return SUCCESS(item);
			}else{
				return SUCCESS();
			}
		} catch (Exception e) {
			return FAIL("HAVE ERROR: "+e);
		}
		
	}
	
	@RequestMapping( value="/create",
			method = {RequestMethod.POST, RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> create(
	@RequestHeader(value="token",required=false) String token,
	@RequestBody ProgramRegister item
	) {
	try{
			UserItem user =AuthorizationToken.convertToObject(token);
			item.setUserId(user.getId());
			item.setCreateDate(DataUtils.getNowDate());
			item.setUpdateDate(DataUtils.getNowDate());
			ProgramRegister create= programRegisterService.create(item);
			return SUCCESS(create);
		}catch(Exception e){
			System.out.println(e);
			return FAIL("HAVE ERROR: "+e);
		}
	}

	@RequestMapping( value="/edit",
			method = {RequestMethod.POST, RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> edit(
	@RequestHeader(value="token",required=false) String token,
	@RequestBody ProgramRegister item
	) {
	try{
			UserItem user =AuthorizationToken.convertToObject(token);
			ProgramRegister edit=programRegisterService.update(item);
			return SUCCESS(edit);
		}catch(Exception e){
			System.out.println(e);
			return FAIL("HAVE ERROR: "+e);
		}
	}

	@RequestMapping( value="/refund",
			method = {RequestMethod.POST, RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> refund(
	@RequestHeader(value="token",required=false) String token,
	@RequestBody(required = true) Purchase item
	) {
	try{
			Purchase pr = purchaseService.findById(item.getId());
			pr.setRefundMoney(1);
			pr.setRefundDate(DataUtils.getNowDate());
			Purchase edit=purchaseService.update(pr);
			HashMap<String, Object> ret = new HashMap<>();
			ret.put("id", edit.getId());
			ret.put("programId", edit.getProgramId());
			ret.put("userId", edit.getUserId());
			ret.put("refundMoney", edit.getRefundMoney());
			ret.put("createDate", edit.getCreateDate());
			ret.put("updateDate", edit.getUpdateDate());
			return SUCCESS(ret);
		}catch(Exception e){
			System.out.println(e);
			return FAIL("HAVE ERROR: "+e);
		}
	}
	
	@RequestMapping( value="/delete/{id}",
			method = {RequestMethod.POST, RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> delete(
	@RequestHeader(value="token",required=false) String token,
	@PathVariable("id") Integer id) {
		try{
				programRegisterService.delete(id);
				return SUCCESS();
		}catch(Exception e){
				System.out.println(e);
				return FAIL("HAVE ERROR: "+e);
		}
	}
	
}



