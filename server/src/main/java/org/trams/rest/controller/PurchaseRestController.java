/*
 * Created on 23 thg 8 2016 ( Time 16:25:36 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.rest.controller;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
import org.trams.bean.CouponUse;
import org.trams.bean.ProgramRegister;
import org.trams.bean.Purchase;
import org.trams.bean.jpa.PurchaseEntity;
import org.trams.business.service.CouponUseService;
import org.trams.business.service.ProgramRegisterService;
import org.trams.business.service.PurchaseService;
import org.trams.rest.common.AbstractRestController;
import org.trams.rest.common.AuthorizationToken;
import org.trams.web.common.utils.ConstantUtils;
import org.trams.web.common.utils.DataUtils;
/**
 * Spring MVC controller for 'Purchase' management.
 */
@RequestMapping("/purchase")
@Controller
public class PurchaseRestController extends AbstractRestController{

	@Resource
	private PurchaseService purchaseService;
	
	@Resource
	private CouponUseService couponUseService;
	
	@Resource
	private ProgramRegisterService programRegisterService;
		
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
			List<Purchase> list = purchaseService.findAll();
			return SUCCESS(list);
		} catch (Exception e) {
			return FAIL("HAVE ERROR: "+e);
		}
	} 
	
	@RequestMapping( value="/create",
			method = {RequestMethod.POST, RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> payment(
	@RequestHeader(value="token",required=false) String token,
	@RequestBody Purchase item
	) {
	try{
		if(item.getTotalMoney() == 0){
			UserItem user = AuthorizationToken.convertToObject(token);
			item.setRefundMoney(0);
			item.setOrderProgram(0);
			item.setUserId(user.getId());
			item.setCreateDate(DataUtils.getNowDate());
			item.setUpdateDate(DataUtils.getNowDate());
			Purchase create= purchaseService.create(item);
			return SUCCESS("Process success!");
		}else{
			UserItem user = AuthorizationToken.convertToObject(token);
			item.setRefundMoney(0);
			item.setOrderProgram(1);
			item.setUserId(user.getId());
			item.setCreateDate(DataUtils.getNowDate());
			item.setUpdateDate(DataUtils.getNowDate());
			Purchase create= purchaseService.create(item);
			String url = ConstantUtils.getConfig("domain")+"payment?order_id="+create.getId();
			return SUCCESS(url,"Process success!");
		}
		
			
		}catch(Exception e){
			System.out.println(e);
			return FAIL("HAVE ERROR: "+e);
		}
	}
	
	@RequestMapping( value="/test",
			method = {RequestMethod.POST, RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> test(
	@RequestHeader(value="token",required=false) String token,
	@RequestBody Purchase item
	) {
	try{
			UserItem user = AuthorizationToken.convertToObject(token);
			item.setRefundMoney(0);
			item.setOrderProgram(1);
			item.setUserId(user.getId());
			item.setCreateDate(DataUtils.getNowDate());
			item.setUpdateDate(DataUtils.getNowDate());
			Purchase create= purchaseService.create(item);
			String url = ConstantUtils.getConfig("domain")+"payment?order_id="+create.getId();
			return SUCCESS(url,"Process success!");
		}catch(Exception e){
			System.out.println(e);
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
		//UserItem user =AuthorizationToken.convertToObject(token);
		HashMap<String, Object> ret =new HashMap<String, Object>();
		try {
			JSONParser parser = new JSONParser();
			JSONObject jsonObj = (JSONObject) parser.parse(json);
			Integer page = DataUtils.parseInt(jsonObj.get("page"));
			Page<PurchaseEntity> list = purchaseService.findAll(page);
			if(list.getContent()!=null){
				ret.put("list", list.getContent());
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
			Purchase item= purchaseService.findById(id);
			if(item!=null){
				return SUCCESS(item);
			}else{
				return SUCCESS();
			}
		} catch (Exception e) {
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
	@RequestBody Purchase item
	) {
	try{
			item.setUpdateDate(DataUtils.getNowDate());
			Purchase edit=purchaseService.update(item);
			return SUCCESS(edit);
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
				purchaseService.delete(id);
				return SUCCESS();
		}catch(Exception e){
				System.out.println(e);
				return FAIL("HAVE ERROR: "+e);
		}
	}
	
	@RequestMapping( value="/remove/{id}",
			method = {RequestMethod.POST, RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> update_delete(
	@RequestHeader(value="token",required=false) String token,
	@PathVariable("id") Integer id) {
		try{
			purchaseService.update_isDelete(id);
			return SUCCESS();
		}catch(Exception e){
			System.out.println(e);
			return FAIL("HAVE ERROR: "+e);
		}
	}
}


