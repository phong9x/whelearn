/*
 * Created on 13 thg 9 2016 ( Time 14:33:55 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.web.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.sql.Update;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.ModelAttribute;

//--- Common classes
import org.trams.web.common.AbstractController;
import org.trams.web.common.ApplicationDefine;
import org.trams.web.common.Pager;
import org.trams.web.common.utils.DataUtils;
import org.trams.web.common.Login;
import org.trams.bean.jpa.ContentEntity;
import org.trams.bean.jpa.ContentsRecomEntity;

//--- Entities
import org.trams.bean.ContentsRecom;
import org.trams.bean.Content;
import org.trams.bean.ContentRelated;
//--- Services 
import org.trams.business.service.ContentsRecomService;
import org.trams.business.service.SettingService;
import org.trams.rest.common.notification.Notification;
import org.trams.business.service.ContentService;

/**
 * Spring MVC controller for 'ContentsRecom' management.
 */
@Controller
@RequestMapping("/contentsRecom")
public class ContentsRecomController extends AbstractController {

	// --- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "contentsRecom";

	private static final Integer PAGE_SIZE = 10;

	private static String nav = "contentsRecom";

	// --- JSP pages names ( View name in the MVC model )
	private static final String JSP_CREATE = "contentsRecom/create";
	private static final String JSP_LIST = "contentsRecom/list";
	private static final String JSP_EDIT = "contentsRecom/edit";
	private static final String JSP_DETAIL = "contentsRecom/detail";
	private static final String JSP_LOGIN = "redirect:/login";
	private static final String MAIN_LIST_NAME = "list";
	// --- Main entity service
	@Resource
	private ContentsRecomService contentsRecomService; // Injected by Spring
	// --- Other service(s)
	@Resource
	private ContentService contentService; // Injected by Spring

	@Resource
	private SettingService settingService;

	// --------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */
	public ContentsRecomController() {
		super(ContentsRecomController.class, MAIN_ENTITY_NAME);
		log("ContentsRecomController created.");
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list_get(
			@RequestParam(value = "realtiveId1", defaultValue = "") Integer realtiveId1,
			@RequestParam(value = "realtiveId2", defaultValue = "") Integer realtiveId2,
			@RequestParam(value = "realtiveId3", defaultValue = "") Integer realtiveId3,
			@RequestParam(value = "delete", defaultValue = "0") Integer delete, HttpSession session, Model model) {
		Page<ContentsRecomEntity> page_list = contentsRecomService.listPaging(1, 3);
		if (page_list != null) {
			List<ContentsRecomEntity> list = page_list.getContent();
			
			for (ContentsRecomEntity contentsRecomEntity : list) {
				if(contentsRecomEntity.getId() ==1){
					model.addAttribute("content1", contentsRecomEntity.getContent());
				}
				if(contentsRecomEntity.getId() ==2){
					model.addAttribute("content2", contentsRecomEntity.getContent());
				}
				if(contentsRecomEntity.getId() ==3){
					model.addAttribute("content3", contentsRecomEntity.getContent());
				}
				
			}
			
		}
		model.addAttribute("activePage", "content");
		return JSP_LIST;
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String list_post(@RequestParam(value = "realtiveId1", defaultValue = "") Integer realtiveId1,
			@RequestParam(value = "realtiveId2", defaultValue = "") Integer realtiveId2,
			@RequestParam(value = "realtiveId3", defaultValue = "") Integer realtiveId3,
			@RequestParam(value = "delete", defaultValue = "0") Integer delete, HttpSession session, Model model) {
		// Page<ContentsRecomEntity> page_list =
		// contentsRecomService.listPaging(1, 10);
		// List<Integer> list_id = new ArrayList<>();
		// if(page_list != null){
		// List<ContentsRecomEntity> list = page_list.getContent();
		// for (ContentsRecomEntity contentsRecomEntity : list) {
		// list_id.add(contentsRecomEntity.getContent().getId());
		// }
		// }
		// contentsRecomService.deleteAll();
		try {
			if (realtiveId1 != null) {
				if (realtiveId1 != 0) {
					ContentsRecom cr = contentsRecomService.findById(1);
					if (cr != null) {
						cr.setContentId(realtiveId1);
						cr.setUpdateDate(DataUtils.getNowDate());
						cr = contentsRecomService.update(cr);
					} else {
						cr = new ContentsRecom();
						cr.setId(1);
						cr.setContentId(realtiveId1);
						cr.setCreateDate(DataUtils.getNowDate());
						cr.setUpdateDate(DataUtils.getNowDate());
						cr = contentsRecomService.create(cr);
					}
					ContentEntity ce = contentService.findOne(cr.getContentId());
					Notification n = new Notification();
					List<String> list_regid = settingService.getFCMTokenBy_UploadContentNotification();
					n.sendNotification(list_regid, "추천 컨텐츠 업로드 알림", ce.getTitle() + " 이 등록되었습니다. 확인해 주세요!", ce.getId(),
							ApplicationDefine.NOTIFICATION_UploadContent, 1);
				} else {
					contentsRecomService.delete(1);
				}
			}

			if (realtiveId2 != null) {
				if (realtiveId2 != 0) {
					ContentsRecom cr = contentsRecomService.findById(2);
					if (cr != null) {
						cr.setContentId(realtiveId2);
						cr.setUpdateDate(DataUtils.getNowDate());
						cr = contentsRecomService.update(cr);
					} else {
						cr = new ContentsRecom();
						cr.setId(2);
						cr.setContentId(realtiveId2);
						cr.setCreateDate(DataUtils.getNowDate());
						cr.setUpdateDate(DataUtils.getNowDate());
						cr = contentsRecomService.create(cr);
					}
					ContentEntity ce = contentService.findOne(cr.getContentId());
					Notification n = new Notification();
					List<String> list_regid = settingService.getFCMTokenBy_UploadContentNotification();
					n.sendNotification(list_regid, "추천 컨텐츠 업로드 알림", ce.getTitle() + " 이 등록되었습니다. 확인해 주세요!", ce.getId(),
							ApplicationDefine.NOTIFICATION_UploadContent, 1);
				} else {
					contentsRecomService.delete(2);
				}
			}

			if (realtiveId3 != null) {
				if (realtiveId3 != 0) {
					ContentsRecom cr = contentsRecomService.findById(3);
					if (cr != null) {
						cr.setContentId(realtiveId3);
						cr.setUpdateDate(DataUtils.getNowDate());
						cr = contentsRecomService.update(cr);
					} else {
						cr = new ContentsRecom();
						cr.setId(3);
						cr.setContentId(realtiveId3);
						cr.setCreateDate(DataUtils.getNowDate());
						cr.setUpdateDate(DataUtils.getNowDate());
						cr = contentsRecomService.create(cr);
					}
					ContentEntity ce = contentService.findOne(cr.getContentId());
					Notification n = new Notification();
					List<String> list_regid = settingService.getFCMTokenBy_UploadContentNotification();
					n.sendNotification(list_regid, "추천 컨텐츠 업로드 알림", ce.getTitle() + " 이 등록되었습니다. 확인해 주세요!", ce.getId(),
							ApplicationDefine.NOTIFICATION_UploadContent, 1);
				} else {
					contentsRecomService.delete(3);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/" + JSP_LIST;
	}

	@RequestMapping("/detail/{id}")
	public String detail(Model model, HttpSession session, @PathVariable("id") Integer id) {
		ContentsRecom contentsRecom = contentsRecomService.findById(id);
		model.addAttribute("contentsRecom", contentsRecom);
		model.addAttribute("activePage", nav);
		return JSP_DETAIL;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(HttpSession session, Model model) {
		log("Action 'create'");
		model.addAttribute("activePage", nav);
		return JSP_CREATE;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(HttpSession session, @ModelAttribute ContentsRecom contentsRecom, Model model) {
		log("Action 'create'");
		try {
			contentsRecom.setCreateDate(DataUtils.getNowDate());
			contentsRecom.setUpdateDate(DataUtils.getNowDate());
			contentsRecomService.create(contentsRecom);
			return "redirect:/" + JSP_LIST;
		} catch (Exception e) {
			System.out.println(e);
			return JSP_CREATE;
		}
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String update(HttpSession session, @PathVariable("id") Integer id, Model model) {
		ContentsRecom contentsRecom = contentsRecomService.findById(id);
		try {
			model.addAttribute("item", contentsRecom);
			model.addAttribute("activePage", nav);
			return JSP_EDIT;
		} catch (Exception e) {
			return JSP_LIST;
		}
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(@RequestParam(value = "edit", defaultValue = "0") Integer edit,
			@ModelAttribute ContentsRecom item, HttpSession session, @PathVariable("id") Integer id, Model model) {
		ContentsRecom contentsRecom = contentsRecomService.findById(id);
		try {
			contentsRecom.setUpdateDate(DataUtils.getNowDate());
			contentsRecom = contentsRecomService.update(contentsRecom);
			model.addAttribute("item", contentsRecom);
		} catch (Exception e) {
			System.out.println(e);
		}
		return "redirect:/" + JSP_EDIT + "/" + id;
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(Model model, HttpSession session, @PathVariable("id") Integer id) {
		contentsRecomService.delete(id);
		return "redirect:/" + JSP_LIST;
	}

}