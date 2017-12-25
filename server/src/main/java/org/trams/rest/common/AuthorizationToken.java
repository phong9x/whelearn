package org.trams.rest.common;

import org.trams.bean.User;
import org.trams.bean.UserItem;
import org.trams.web.common.utils.EncryptionUtils;


public class AuthorizationToken {
	public static void main(String[] args){
	}
	public static String generateTokenMobile(User user){
		UserItem u =new UserItem();
		u.setId(user.getId());
		u.setRole(0);
		return EncryptionUtils.jwtBuild(u);
	}
	
	public static UserItem convertToObject(String token){
		try {
			return EncryptionUtils.jwtParse(token, UserItem.class);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static boolean checkLogin(String token){
		try {
			UserItem u= EncryptionUtils.jwtParse(token, UserItem.class);
			if(u!=null){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		
	}
	

	
}
