/*
 * Created on 19 thg 9 2016 ( Time 17:07:44 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.web.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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
import org.trams.web.common.Pager;
import org.trams.web.common.utils.DataUtils;
import org.trams.web.common.Login;
import org.trams.bean.jpa.NoticeEntity;

//--- Entities
import org.trams.bean.Notice;

//--- Services 
import org.trams.business.service.NoticeService;

/**
 * Spring MVC controller for 'Notice' management.
 */
@Controller
@RequestMapping("/notice")
public class NoticeController extends AbstractController {

	//--- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "notice";

	private static final Integer PAGE_SIZE   = 10;

	private static String nav = "notice";

	//--- JSP pages names ( View name in the MVC model )
	private static final String JSP_CREATE   = "notice/create";
	private static final String JSP_LIST   = "notice/list";
	private static final String JSP_EDIT   = "notice/edit";
	private static final String JSP_DETAIL   = "notice/detail";
	private static final String JSP_LOGIN   = "redirect:/login";
	private static final String MAIN_LIST_NAME   = "list";
	//--- Main entity service
	@Resource
    private NoticeService noticeService; // Injected by Spring
	//--- Other service(s)

	//--------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */
	public NoticeController() {
		super(NoticeController.class, MAIN_ENTITY_NAME );
		log("NoticeController created.");
	}

	@RequestMapping("/list")
	public String list(
			@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam(value="type",defaultValue="") String type,
			@RequestParam(value="key",defaultValue="") String key,
			@RequestParam(value="delete",defaultValue="0") Integer delete,
			HttpSession session,
			Model model) {
		if(key==null){
			key="";
		}
		if(type==null){
			type="";
		}
		if(delete>0){
			try {
				noticeService.delete(delete);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		Page<NoticeEntity> listPage = null;
		if(type == ""){
			listPage = noticeService.listPaging(page, PAGE_SIZE);
		}
		else if(type.equals("title")){
			listPage = noticeService.findByTitle("%"+key+"%", page, PAGE_SIZE);
		}
		model.addAttribute(MAIN_LIST_NAME, listPage);	
		model.addAttribute("key", key);
		model.addAttribute("type", type);
		model.addAttribute("activePage", nav);
		model.addAttribute("pagination_navigator", "/notice/list");
		new Pager<NoticeEntity>(listPage).setSetting(model, "type="+type+"&amp;key="+key+"&amp;");		
		return JSP_LIST;
	}

	
	@RequestMapping("/detail/{id}")
	public String detail(
	Model model, 
	HttpSession session,
	@PathVariable("id") Integer id) {
		Notice notice = noticeService.findById(id);
		model.addAttribute("notice", notice);
		model.addAttribute("activePage", nav);
		return JSP_DETAIL;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET ) 
	public String create(
			HttpSession session,
			Model model) {
		log("Action 'create'");
		model.addAttribute("activePage", nav);
		return JSP_CREATE;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST ) 
	public String create(
			HttpSession session,
			@ModelAttribute Notice notice,
			@RequestParam(value="notification",defaultValue="") Integer notification,
			Model model) {
		log("Action 'create'");
		try {
			if(notification == 1){
				
			}
			notice.setCreateDate( DataUtils.getNowDate() );
			notice.setUpdateDate( DataUtils.getNowDate() );
			noticeService.create(notice);
			return "redirect:/"+JSP_LIST;
		} catch(Exception e) {
			System.out.println(e);
			return JSP_CREATE;
		}
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String update(
	HttpSession session,
	@PathVariable("id") Integer id,
	Model model  ) {
		Notice notice = noticeService.findById(id);
		try {
			model.addAttribute("item", notice);
			model.addAttribute("activePage", nav);
			return JSP_EDIT;
		} catch(Exception e) {
			return JSP_LIST;
		}
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(
	@RequestParam(value="edit",defaultValue="0") Integer edit,
	@ModelAttribute Notice item,
	HttpSession session,
	@PathVariable("id") Integer id,
	Model model  ) {
		Notice notice = noticeService.findById(id);
		try {
			notice.setTitle( item.getTitle() );
			notice.setContent( item.getContent() );
			notice.setUpdateDate( DataUtils.getNowDate() );
			notice = noticeService.update(notice);
			model.addAttribute("item", notice);
		} catch(Exception e) {
			System.out.println(e);
		}
		return "redirect:/"+JSP_LIST;
	}
	
	

	@RequestMapping(value = "/delete/{id}")
	public String delete(
	Model model, 
	HttpSession session,
	@PathVariable("id") Integer id ) {
	noticeService.delete(id);
	return "redirect:/"+JSP_LIST;
	}

}