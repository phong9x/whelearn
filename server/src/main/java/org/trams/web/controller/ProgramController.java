/*
 * Created on 1 thg 9 2016 ( Time 11:28:16 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.web.controller;

import java.util.LinkedList;
import java.util.List;
import java.sql.Time;
import java.text.Normalizer.Form;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.mail.Multipart;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.ModelAttribute;


//--- Common classes
import org.trams.web.common.AbstractController;
import org.trams.web.common.ApplicationDefine;
import org.trams.web.common.Pager;
import org.trams.web.common.utils.ConstantUtils;
import org.trams.web.common.utils.DataUtils;
import org.trams.web.common.utils.FileUtils;
import org.trams.web.common.Login;
import org.trams.bean.jpa.CommentEntity;
import org.trams.bean.jpa.ContentEntity;
import org.trams.bean.jpa.ProgramEntity;
import org.trams.bean.jpa.QuestionAnswerEntity;
import org.trams.bean.jpa.TimeLearnEntity;
import org.trams.bean.Category;
import org.trams.bean.KeywordProgram;
//--- Entities
import org.trams.bean.Program;
import org.trams.bean.ProgramCalendar;
import org.trams.bean.ProgramCalendarSimple;
import org.trams.bean.ProgramSimple;
import org.trams.bean.QuestionAnswer;
import org.trams.bean.QuestionAnswerItem;
import org.trams.bean.TimeLearn;
import org.trams.bean.User;
import org.trams.bean.UserItem;
import org.trams.business.service.CategoryService;
import org.trams.business.service.CommentService;
import org.trams.business.service.KeywordProgramService;
//--- Services 
import org.trams.business.service.ProgramService;
import org.trams.business.service.QuestionAnswerService;
import org.trams.business.service.TimeLearnService;
import org.trams.business.service.UserService;


/**
 * Spring MVC controller for 'Program' management.
 */
@Controller
@RequestMapping("/program")
public class ProgramController extends AbstractController {

	//--- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "program";
	private static final String MAIN_LIST_NAME = "list";

	private static final String TOTAL_PAGE   = "pages";

	private static final String CURRENT_PAGE   = "pageNumber";
	private static final String LIST_PAGES   = "listPages";

	private static final Integer PAGE_SIZE   = 10;

	private static String nav = "program";

	//--- JSP pages names ( View name in the MVC model )
	private static final String JSP_CREATE   = "program/create";
	private static final String JSP_MAP   = "program/map";
	private static final String JSP_LIST   = "program";
	private static final String JSP_COMMENT_LIST   = "program/comment";
	private static final String JSP_POPUP_LIST   = "program/popup/list";
	private static final String JSP_QA_LIST   = "program/qa";
	private static final String JSP_INDEX   = "program";
	private static final String JSP_EDIT   = "program/edit";
	private static final String JSP_DETAIL   = "program/detail";
	private static final String JSP_LOGIN   = "redirect:/login";

	//--- Main entity service
	@Resource
    private ProgramService programService; // Injected by Spring
	//--- Other service(s)
	@Resource
    private UserService userService; // Injected by Spring
	
	@Resource
    private CategoryService categoryService;
	
	@Resource
    private TimeLearnService timeLearnService;

	@Resource
    private KeywordProgramService keywordProgramService;
	
	@Resource
    private CommentService commentService;
	
	@Resource
    private QuestionAnswerService questionAnswerService;

	@Resource
	private ServletContext servletContext;
	//--------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */
	public ProgramController() {
		super(ProgramController.class, MAIN_ENTITY_NAME );
		log("ProgramController created.");
	}

	@RequestMapping(value= "" , method = RequestMethod.GET)
	public String list(
			@RequestParam (value="year", defaultValue="") Integer year,
			@RequestParam (value="month", defaultValue="") Integer month,
			HttpSession session,
			Model model) {
		Calendar current =Calendar.getInstance();
		current.setTime(new Date());
		if(year == null){
			year = current.get(Calendar.YEAR);
		}else{
			current.set(Calendar.YEAR, year);
		}
		if(month == null){
			month = current.get(Calendar.MONTH)+1;
		}else{
			current.set(Calendar.MONTH, month-1);
		}
		
		List<TimeLearnEntity> list = null;
		UserItem u = Login.getUserLogin(session);
		if(u.getType() == ApplicationDefine.USER_ADMIN){
			list = timeLearnService.findByMontYear(month, year);
		}else if(u.getType() == ApplicationDefine.USER_PROCEDURE){
			list = timeLearnService.findByMontYearAndUserId(month, year, u.getId());
		}
		List<ProgramCalendarSimple> calendar = new ArrayList<>();
		List<String> list_color = new ArrayList<>();
		list_color.add("red");
		list_color.add("grey");
		list_color.add("yellow");
		list_color.add("green");
		list_color.add("blue");
		list_color.add("purple");
		list_color.add("black");
		int index =0;
		if (list != null) {
			for (TimeLearnEntity i : list) {
				ProgramCalendarSimple pc= new ProgramCalendarSimple();
				pc.setTitle("["+i.getProgram().getCategoryName()+"]"+i.getProgram().getTitle());
				pc.setDay(i.getDateLearn().getDate());
				pc.setMonth(i.getDateLearn().getMonth());
				pc.setYear(i.getDateLearn().getYear()+1900);
				pc.setId(i.getProgram().getId());
				pc.setColor(list_color.get(index));
				calendar.add(pc);
				index++;
				if(index == 7){
					index=0;
				}
			}
		}
		Integer next_month = month;
		Integer next_year = year;
		Integer pre_month = month;
		Integer pre_year = year;
		if(month == 12){
			next_month = 1 ;
			next_year=year+1;
			pre_month = 11;
			pre_year = year;
		}else if(month == 1 ){
			next_month = 2 ;
			next_year=year;
			pre_month = 12;
			pre_year = year-1;
		}else{
			next_month = month +1 ;
			next_year= year;
			pre_month = month -1 ;
			pre_year = year;
		}
		model.addAttribute("next_year", next_year);
		model.addAttribute("next_month",next_month);
		model.addAttribute("domain",ConstantUtils.getConfig("domain"));
		model.addAttribute("pre_year", pre_year);
		model.addAttribute("pre_month", pre_month);
		
		model.addAttribute("date", current.getTime());
		model.addAttribute("list", calendar);
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("activePage", "program_calendar");
		return JSP_INDEX;
	}
	
	
	@RequestMapping("/popup/list")
	public String popup(
			@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam(value="type",defaultValue="") String type,
			@RequestParam(value="key",defaultValue="") String key,
			@RequestParam(value="number",defaultValue="1") String number,
			HttpSession session,
			Model model) {
		if(key==null){
			key="";
		}
		if(type==null){
			type="";
		}
		Page<ProgramEntity> listPage = null;
		if(type == ""){
			listPage = programService.listPaging(page, PAGE_SIZE);
		}
		else if(type.equals("title")){
			listPage = programService.findByTitle(key, page, PAGE_SIZE);
		}
		else if(type.equals("category")){
			listPage = programService.findByCategoryName(key, page, PAGE_SIZE);
		}
		model.addAttribute(MAIN_LIST_NAME, listPage);	
		model.addAttribute("key", key);
		model.addAttribute("type", type);
		model.addAttribute("number", number);
		model.addAttribute("activePage", nav);
		model.addAttribute("pagination_navigator", "/program/popup/list");
		new Pager<ProgramEntity>(listPage).setSetting(model, "type="+type+"&amp;key="+key+"&amp;number="+number+"&amp;");		
		return JSP_POPUP_LIST;
	}

	@RequestMapping("/comment/{id}")
	public String list(
			@PathVariable("id") Integer id,
			@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam(value="delete",defaultValue="0") Integer delete,
			HttpSession session,
			Model model) {
		if(delete>0){
			try {
				commentService.delete(delete);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		Program p = programService.findById(id);
		double point_average = commentService.averagePointByProgramIdAndType(id, 2);
		int point_int = (int)point_average;
		float result=0 ;
		double over = point_average - point_int;
		if(over > 0.25 && over <= 0.75){
			result =  ((float)point_int + (float)0.5);
		}else if(over > 0.75){
			result = point_int + 1;
		}else{
			result=point_int;
		}
		p.setPoint(result);
		p =programService.update(p);
		Page<CommentEntity> listPage = null;
		listPage=commentService.listPagingByTypeAndThreadId(2, id, page, 20);
		model.addAttribute(MAIN_LIST_NAME, listPage);	
		model.addAttribute("p", p);
		model.addAttribute("activePage", nav);
		model.addAttribute("pagination_navigator", "/program/comment/"+id);
		new Pager<CommentEntity>(listPage).setSetting(model, "");		
		return JSP_COMMENT_LIST;
	}
	
	@RequestMapping("/qa/{id}")
	public String qa_list(
			@PathVariable("id") Integer id,
			@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam(value="delete_question",defaultValue="0") Integer delete_question,
			@RequestParam(value="delete_answer",defaultValue="0") Integer delete_answer,
			@RequestParam(value="question_id",defaultValue="0") Integer question_id,
			@RequestParam(value="answer",defaultValue="") String answer,
			HttpSession session,
			Model model) {
		if(delete_question>0){
			try {
				questionAnswerService.delete(delete_question);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		if(delete_answer>0){
			try {
				QuestionAnswer qa = questionAnswerService.findById(delete_answer);
				qa.setAnswer("");
				qa.setAnswerDate(null);
				qa.setAnswerUserId(null);
				questionAnswerService.update(qa);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}else{
			if(question_id >0 && answer!= null){
				QuestionAnswer qa = questionAnswerService.findById(question_id);
				qa.setAnswer(answer);
				qa.setAnswerDate(DataUtils.getNowDate());
				UserItem u = Login.getUserLogin(session);
				qa.setAnswerUserId(u.getId());
				qa.setUpdateDate(DataUtils.getNowDate());
				questionAnswerService.update(qa);
			}
		}
		
		List<QuestionAnswerItem> list = null;
		list = questionAnswerService.listByProgramId(id, page, 20);
		Integer total = questionAnswerService.totalByProgramId(id);
		model.addAttribute(MAIN_LIST_NAME, list);	
		model.addAttribute("activePage", nav);
		model.addAttribute("pagination_navigator", "/user/list");
		model.addAttribute("totalPage", DataUtils.getTotalPage(total, PAGE_SIZE));
		return JSP_QA_LIST;
	}
	
	
	
	@RequestMapping(value = "/create", method = RequestMethod.GET ) 
	public String create(
			HttpSession session,
			Model model) {
		log("Action 'create'");
		List<Category> list_category = categoryService.findAll();
		model.addAttribute("list_category", list_category);
		model.addAttribute("activePage", nav);
		return JSP_CREATE;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST ) 
	public String create(
			HttpSession session,
			@ModelAttribute Program program,
			@RequestParam (value="dateLearn", defaultValue="") String[] dateLearn,
			@RequestParam (value="from", defaultValue="") String[] from,
			@RequestParam (value="to", defaultValue="") String[] to,
			@RequestParam (value="image") MultipartFile  image,
			@RequestParam (value="deadLine_date", defaultValue="" ) String  deadLine_date,
			@RequestParam (value="deadLine_time", defaultValue="") String  deadLine_time,
			
			@ModelAttribute KeywordProgram keywordProgram,
			Model model) {
		log("Action 'create'");
		try {
			try {
				String[] category = program.getCategoryName().split("%");
				program.setCategoryName(category[1]);
				program.setCategoryId(DataUtils.parseInt(category[0]));
			} catch (Exception e) {
				System.out.println(e);
			}
			if(image != null && image.getSize() >0){
				String path = FileUtils.saveFileOrigin(image,"upload", servletContext);
				program.setImageUrl(path);
				System.out.println(image.getOriginalFilename());
				System.out.println(image.getName());
				program.setImageName(image.getOriginalFilename());
				program.setVideoUrl("");
			}else{
				program.setImageName(null);
				program.setImageUrl("");
			}
			try {
				Date deadLine = DateUtils.parseDate(deadLine_date, "yyyy/MM/dd");
				String[] dateLineTime = deadLine_time.split(":");
				deadLine.setHours(DataUtils.parseInt(dateLineTime[0]));
				deadLine.setMinutes(DataUtils.parseInt(dateLineTime[1]));
				program.setDeadLine(deadLine);
			} catch (Exception e) {
				System.out.println(e);
			}
			program.setMoneyPaid((long)0);
			program.setTotalMoney((long)0);
			program.setDatePayment(null);
			program.setStatusPayment(0);
			program.setPoint((float)0);
			program.setIsLike(0);
			program.setLikeNumber(0);
			program.setUpdateDate( DataUtils.getNowDate() );
			program.setCreateDate( DataUtils.getNowDate() );
			program.setIsDelete(0);
			program.setHearingFaculty(1);
			if(program.getIncludeDrinking() == null){
				program.setIncludeDrinking(0);
			}
			if(program.getIncludeFee() == null){
				program.setIncludeFee(0);
			}
			if(program.getIncludeLearningBook() == null){
				program.setIncludeLearningBook(0);
			}
			if(program.getIncludeMaterialFee() == null){
				program.setIncludeMaterialFee(0);
			}
			if(program.getIncludeParking() == null){
				program.setIncludeParking(0);
			}
			if(program.getIncludeWifi() == null){
				program.setIncludeWifi(0);
			}
			Program p =programService.create(program);
			if(p !=null){
				if(dateLearn.length > 0 ){
					try {
						int index = 1;
						for (int j = 0; j < dateLearn.length; j++) {
							if(dateLearn[j] != null && from[j] != null && to[j] != null){
							TimeLearn tl = new TimeLearn();
							tl.setCreateDate(DataUtils.getNowDate());
							tl.setDateLearn(DateUtils.parseDate(dateLearn[j], "yyyy/MM/dd"));
							tl.setFromTime(DateUtils.parseDate(from[j], "HH:mm"));
							tl.setProgramId(p.getId());
							tl.setToTime(DateUtils.parseDate(to[j], "HH:mm"));
							tl.setUpdateDate(DataUtils.getNowDate());
							if(index==1){
								tl.setOpeningDay(1);
							}else{
								tl.setOpeningDay(0);
							}
							index=0;
							timeLearnService.create(tl);
							}
						}
						
					} catch (Exception e) {
						e.printStackTrace();
						
					}
					
				}
				keywordProgram.setProgramId(p.getId());
				keywordProgramService.create(keywordProgram);
			}
			return "redirect:/"+JSP_LIST;
		} catch(Exception e) {
			e.printStackTrace();
			return "redirect:/"+JSP_CREATE;
		}
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String update(
	HttpSession session,
	@PathVariable("id") Integer id,
	@RequestParam (value="delete", defaultValue="0") Integer  delete,
	Model model  ) {
		try {
			if(delete > 0){
				programService.update_isDelete(id);
				return "redirect:/"+JSP_INDEX;
			}else{
				Program p = programService.findById(id);
				List<TimeLearnEntity> list_time_learn = timeLearnService.findByProgramId(id);
				KeywordProgram kp = keywordProgramService.findById(id);
				User u = userService.findById(p.getUserId());
				List<Category> list_category = categoryService.findAll();
				model.addAttribute("list_category", list_category);
				model.addAttribute("u", u);
				model.addAttribute("kp", kp);
				model.addAttribute("list_time", list_time_learn);
				model.addAttribute("p", p);
				model.addAttribute("activePage", nav);
				return JSP_EDIT;
			}
			
		} catch(Exception e) {
			return JSP_LIST;
		}
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST ) 
	public String edit(
			HttpSession session,
			@ModelAttribute Program item,
			@RequestParam (value="changeTimeLearn", defaultValue="0") Integer changeTimeLearn,
			@RequestParam (value="dateLearn", defaultValue="") String[] dateLearn,
			@RequestParam (value="from", defaultValue="") String[] from,
			@RequestParam (value="to", defaultValue="") String[] to,
			@RequestParam (value="image") MultipartFile  image,
			@RequestParam (value="deadLine_date", defaultValue="" ) String  deadLine_date,
			@RequestParam (value="deadLine_time", defaultValue="") String  deadLine_time,
			@PathVariable("id") Integer id,
			@ModelAttribute KeywordProgram keywordProgram,
			Model model) {
		log("Action 'create'");
		Program program = programService.findById(id);
		try {
			try {
				String[] category = item.getCategoryName().split("%");
				program.setCategoryName(category[1]);
				program.setCategoryId(DataUtils.parseInt(category[0]));
			} catch (Exception e) {
				System.out.println(e);
			}
			if(image != null && image.getSize() >0){
				String path = FileUtils.saveFileOrigin(image,"upload", servletContext);
				program.setImageUrl(path);
				System.out.println(image.getOriginalFilename());
				program.setImageName(image.getOriginalFilename());
				program.setVideoUrl("");
			}else{
				program.setVideoUrl(item.getVideoUrl());
			}
			try {
				Date deadLine = DateUtils.parseDate(deadLine_date, "yyyy/MM/dd");
				String[] dateLineTime = deadLine_time.split(":");
				deadLine.setHours(DataUtils.parseInt(dateLineTime[0]));
				deadLine.setMinutes(DataUtils.parseInt(dateLineTime[1]));
				program.setDeadLine(deadLine);
			} catch (Exception e) {
				System.out.println(e);
			}
			program.setPlace(item.getPlace());
			program.setTitle( item.getTitle() );
			program.setSummary( item.getSummary() );
			program.setContent( item.getContent() );
			program.setIntroduceTeacher( item.getIntroduceTeacher() );
			program.setIntroduceProgram( item.getIntroduceProgram() );
			program.setIntroduceStudyProgram( item.getIntroduceStudyProgram() );
			program.setFee( item.getFee() );
			program.setAddress( item.getAddress() );
			program.setAddressGuide( item.getAddressGuide() );
			program.setTotalPeople( item.getTotalPeople() );
			
			program.setTotalTime( item.getTotalTime() );
			
			if(item.getHearingFaculty() == null){
				program.setHearingFaculty(0);
			}else{
				program.setHearingFaculty( item.getHearingFaculty() );	
			}
			
			if(item.getIncludeDrinking() == null){
				program.setIncludeDrinking(0);
			}else{
				program.setIncludeDrinking(item.getIncludeDrinking());
			}
			
			if(item.getIncludeFee() == null){
				program.setIncludeFee(0);
			}else{
				program.setIncludeFee(item.getIncludeFee());
			}
			
			if(item.getIncludeLearningBook() == null){
				program.setIncludeLearningBook(0);
			}else{
				program.setIncludeLearningBook(item.getIncludeLearningBook());
			}
			
			if(item.getIncludeMaterialFee() == null){
				program.setIncludeMaterialFee(0);
			}else{
				program.setIncludeMaterialFee(item.getIncludeMaterialFee());
			}
			
			if(item.getIncludeParking() == null){
				program.setIncludeParking(0);
			}else{
				program.setIncludeParking(item.getIncludeParking());
			}
			
			if(item.getIncludeWifi() == null){
				program.setIncludeWifi(0);
			}else{
				program.setIncludeWifi(item.getIncludeWifi());
			}
			
			program.setLatitude( item.getLatitude());
			program.setLongitude(item.getLongitude() );
			program.setUpdateDate( DataUtils.getNowDate() );
			program.setUserId(item.getUserId());
			Program p = programService.update(program);
			
			timeLearnService.deleteByProgramId(program.getId());
			try {
				int index = 1;
				for (int j = 0; j < dateLearn.length; j++) {
					if(dateLearn[j] != null && from[j] != null && to[j] != null){
					TimeLearn tl = new TimeLearn();
					tl.setCreateDate(DataUtils.getNowDate());
					tl.setDateLearn(DateUtils.parseDate(dateLearn[j], "yyyy/MM/dd"));
					tl.setFromTime(DateUtils.parseDate(from[j], "HH:mm"));
					tl.setProgramId(p.getId());
					tl.setToTime(DateUtils.parseDate(to[j], "HH:mm"));
					tl.setUpdateDate(DataUtils.getNowDate());
					if(index==1){
						tl.setOpeningDay(1);
					}
					index=0;
					timeLearnService.create(tl);
					}
				}
				
			} catch (Exception e) {
				System.out.println(e);
			}
					
			KeywordProgram kp = new KeywordProgram();
			try {
				kp=keywordProgramService.findByProgramId(p.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(kp == null){
				kp = new KeywordProgram();
			}
			kp.setAge(keywordProgram.getAge());
			kp.setArea(keywordProgram.getArea());
			kp.setGenitive(keywordProgram.getGenitive());
			kp.setExperience(keywordProgram.getExperience());
			kp.setFeeStudy(keywordProgram.getFeeStudy());
			kp.setGender(keywordProgram.getGender());
			kp.setPlaceStudy(keywordProgram.getPlaceStudy());
			kp.setSizeClass(keywordProgram.getSizeClass());
			kp.setStudyMode(keywordProgram.getStudyMode());
			kp.setTime1(keywordProgram.getTime1());
			kp.setTime2(keywordProgram.getTime2());
			kp.setUpdateDate(DataUtils.getNowDate());
			if(kp.getProgramId() == null){
				kp.setProgramId(p.getId());
				keywordProgramService.create(kp);
			}else{
				keywordProgramService.update(kp);
			}
			
			return "redirect:/"+JSP_LIST;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/"+JSP_EDIT+"/"+id;
	}
	
	
	@RequestMapping(value = "/delete/{id}")
	public String delete(
	Model model, 
	HttpSession session,
	@PathVariable("id") Integer id ) {
	programService.delete(id);
	return "redirect:/"+JSP_LIST;
	}

}
