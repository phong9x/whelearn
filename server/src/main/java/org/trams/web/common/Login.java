package org.trams.web.common;

import javax.servlet.http.HttpSession;

import org.trams.bean.UserItem;


public class Login {
	
	public static void  saveUser(UserItem user,HttpSession session) {
		session.setAttribute("user", user);
	}
	
	public static String checkUserLogin(HttpSession session) {
		if (session.getAttribute("user") != null && session.getAttribute("user")!="" ) {
			return "1";
		} 
			return "0";
	}
	
	public static UserItem  getUserLogin(HttpSession session) {
		UserItem user =(UserItem) session.getAttribute("user");
		return user;
	}

	public  static void   removeUserLogin(HttpSession session) {
		session.setAttribute("user",null);
		
	}
	
	public static String checkAdminLogin(HttpSession session) {
		if (session.getAttribute("admin") != null && session.getAttribute("admin")!="") {
			return "1";
		} 
			return "0";
	}
	
	public static void  saveAdmin(UserItem user,HttpSession session) {
		session.setAttribute("admin", user);
	}
	
	public static UserItem  getAdminLogin(HttpSession session) {
		UserItem user =(UserItem) session.getAttribute("admin");
		return user;
	}
	
	public  static void   removeAdminLogin(HttpSession session) {
		session.setAttribute("admin",null);
		
	}
}