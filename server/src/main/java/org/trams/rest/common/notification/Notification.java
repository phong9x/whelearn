package org.trams.rest.common.notification;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.trams.rest.common.notification.PushFCM;
import org.trams.web.common.utils.JsonUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;

public class Notification {
	@Autowired
	static
	ServletContext servletContext;
	
	private static String API_KEY = "AIzaSyA3BFz5i4lnY4maXU2TXwHbUz0V-B7TkJA";
	/**
	 * 
	 * @param token
	 * @param message
	 * @return
	 */
	public static String sendNotification(List<String> regId, String title, String content,Integer id, Integer type, Integer isShow){
		try {
			FCMData f = new FCMData();
			f.createData(title, content, id, type, isShow);
			f.setRegistration_ids(regId);
			PushFCM.post(API_KEY, f);
			System.out.println(JsonUtils.toString(f));
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	return "";
}	
}
