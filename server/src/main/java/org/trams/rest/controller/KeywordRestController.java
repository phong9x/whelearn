/*
 * Created on 23 thg 8 2016 ( Time 16:25:36 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.rest.controller;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
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
import org.trams.bean.UserKeyword;
import org.trams.bean.Keyword;
import org.trams.bean.jpa.KeywordEntity;
import org.trams.business.service.KeywordService;
import org.trams.rest.common.AbstractRestController;
import org.trams.rest.common.AuthorizationToken;
import org.trams.web.common.utils.DataUtils;
/**
 * Spring MVC controller for 'Keyword' management.
 */
@RequestMapping("/keyword")
@Controller
public class KeywordRestController extends AbstractRestController{

	@Resource
	private KeywordService keywordService;
	
		
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
			List<Keyword> list = keywordService.findAll();
			return SUCCESS(list);
		} catch (Exception e) {
			return FAIL("HAVE ERROR: "+e);
		}
	} 

	@RequestMapping( value="/rank",
			method = {RequestMethod.POST, RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> rank(
	@RequestHeader(value="token",required=false) String token,
	@RequestBody(required = true) String json
	) throws ParseException {
		UserItem user =AuthorizationToken.convertToObject(token);
		try {
			if(user.getId()!= null){
				List<UserKeyword> list = keywordService.listKeyWordByUserId(user.getId());
				
				return SUCCESS(list);
			}else{
				return SUCCESS();
			}
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
		//UserItem user =AuthorizationToken.convertToObject(token);
		HashMap<String, Object> ret =new HashMap<String, Object>();
		try {
			JSONParser parser = new JSONParser();
			JSONObject jsonObj = (JSONObject) parser.parse(json);
			Integer page = DataUtils.parseInt(jsonObj.get("page"));
			Page<KeywordEntity> list = keywordService.findAll(page);
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
			Keyword item= keywordService.findById(id);
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
	@RequestBody Keyword item
	) {
	try{
			item.setCreateDate(DataUtils.getNowDate());
			item.setUpdateDate(DataUtils.getNowDate());
			Keyword create= keywordService.create(item);
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
	@RequestBody Keyword item
	) {
	try{
			item.setUpdateDate(DataUtils.getNowDate());
			Keyword edit=keywordService.update(item);
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
				keywordService.delete(id);
				return SUCCESS();
		}catch(Exception e){
				System.out.println(e);
				return FAIL("HAVE ERROR: "+e);
		}
	}
	
}



