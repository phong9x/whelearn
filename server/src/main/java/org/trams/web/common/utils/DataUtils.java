package org.trams.web.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Timestamp;
import java.sql.Time;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.modelmapper.internal.util.Lists;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;




public class DataUtils {
	static CharsetEncoder asciiEncoder = Charset.forName("UTF-8").newEncoder();
	
	public static void main(String[] args) {
		System.out.println(getNowDate());
		 Calendar calSeoul = Calendar.getInstance();
		 calSeoul.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
		 System.out.println("Time in Tokyo: " + calSeoul.get(Calendar.HOUR_OF_DAY) + ":"
		        + calSeoul.get(Calendar.MINUTE));
		 String str =calSeoul.get(Calendar.YEAR)+"-"+calSeoul.get(Calendar.MONTH)+"-"+calSeoul.get(Calendar.DAY_OF_MONTH)+1+" "+ calSeoul.get(Calendar.HOUR_OF_DAY) + ":"
			        + calSeoul.get(Calendar.MINUTE);
		 System.out.println(str);
		 Date now = convertStringtoUtilDate(str, "yyyy-MM-dd HH:mm");
	}
	
	public static boolean isUTF8(String s){
	    try{
	        byte[]bytes = s.getBytes("UTF-8");
	    }catch(UnsupportedEncodingException e){
	        e.printStackTrace();
	        System.exit(-1);
	    }
	    return true;
	}
	
	
	public static boolean isPureAscii(String v) {
	    return asciiEncoder.canEncode(v);
	  }

	
	
	public static Integer parseInt(String str){
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return 0;
	}
	
	public static Integer parseInt(Object obj){
		try {
			return Integer.parseInt(obj.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return 0;
	}
	public static Long parseLong(Object obj){
		try {
			return Long.parseLong(obj.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return (long) 0;
	}
	
	public static String toString(Object str){
		try {
			return String.valueOf(str);
		} catch (Exception e) {
			System.out.println(e.toString());
			return "";
		}
	
	}
	
	public static String getStringFromArray(String[] str, int index){
		try {
			return String.valueOf(str[index]);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return "";
	}
	
	public static Date getNowDate(){
		Calendar calSeoul = Calendar.getInstance();
		calSeoul.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
		String str =calSeoul.get(Calendar.YEAR)+"-"+(calSeoul.get(Calendar.MONTH)+1)+"-"+calSeoul.get(Calendar.DAY_OF_MONTH)+" "+ calSeoul.get(Calendar.HOUR_OF_DAY) + ":"
			        + calSeoul.get(Calendar.MINUTE);
		Date now = convertStringtoUtilDate(str, "yyyy-MM-dd HH:mm");
	    return now;
	}
	
	public static String getPresentTimeString(String formatDate){
		java.util.Date utilDate = new java.util.Date();
		SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
	    return sdf.format(utilDate.getTime());
	}
	
	public static Date getDate(String date,String formatDate){
	    DateFormat df = new SimpleDateFormat(formatDate); 
	    Date startDate;
	    try {
	        startDate = df.parse(date);
	        return startDate;
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    
	    return null;
	}
	
	public static java.sql.Date convertStringtoSqlDate(String date,String formatDate){
		SimpleDateFormat format = new SimpleDateFormat(formatDate);
		java.util.Date parsed = null;
		try {
			parsed = format.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
	    return sqlDate;
	}
	
	public static java.util.Date convertStringtoUtilDate(String date,String formatDate){
		SimpleDateFormat format = new SimpleDateFormat(formatDate);
		java.util.Date parsed = null;
		try {
			parsed = format.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return parsed;
	}
	
	/**
	 * 
	 * @param email
	 * @return
	 */
	public static String getFindEmail(String email){
		try {
			String[] e = email.split("@");
			int lenght = e[0].length();
			String rex = e[0].substring(lenght-3, lenght);
			return email.replaceAll(rex, "***");
			
		} catch (Exception e) {
		}
		return "";
	}
	/**
	 * 
	 * @param email
	 * @return
	 */
	public static String getHiddentString(String str,int hiddneNumber){
		try {
			int lenght = str.length();
			String rex = str.substring(lenght-hiddneNumber, lenght);
			return str.replaceAll(rex, "***");
			
		} catch (Exception e) {
		}
		return "";
	}
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	  public static String getMD5(String input) {
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            byte[] messageDigest = md.digest(input.getBytes());
	            BigInteger number = new BigInteger(1, messageDigest);
	            String hashtext = number.toString(16);
	            // Now we need to zero pad it if you actually want the full 32 chars.
	            while (hashtext.length() < 32) {
	                hashtext = "0" + hashtext;
	            }
	            return hashtext;
	        }
	        catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException(e);
	        }
	    }
	  
	  /**
	   * 
	   * @param format
	   * @return
	   */
	  public static Date convertToDate(String value,String format){
		  
			SimpleDateFormat formatter = new SimpleDateFormat(format);

			try {

				Date date = formatter.parse(value);
				return date;

			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			return null;
	  }
	  
		/**
		 * Generate autt code
		 * @param email
		 * @param password
		 * @return
		 */
		public static String getAuthPhoneCode(String srt,String str2){
			
			String str = srt+ "ML^#09X#" + str2;
			try {
					String hexStr = getMD5(str);
		            return hexStr.substring(3,9).toUpperCase();			
			} catch (Exception e) {
			}
			return "";
		}
		

		public static Cookie createCookie(String cookieName, String cookieValue) {
		    Cookie cookie = new Cookie(cookieName, cookieValue);
		    final int expiryTime = 60 * 60 * 24;  // 24h in seconds
		    cookie.setPath("/");
		    cookie.setMaxAge(expiryTime);
		    cookie.setSecure(true);
		    return cookie;
		}
		
		 public static String convertStringListToJson(List<Integer> list){
			 JSONArray listData = new JSONArray();
			 if(list != null && list.size() > 0){
				 for (int i = 0; i < list.size(); i++) {
					 listData.add(list.get(i));
				 }
			 }
			 return listData.toJSONString();
		 }
		 
		
		 public static Long convertFromTime(Date date){
			 try {
				return date.getTime();
			} catch (Exception e) {
			}
			 
			 return new Long(0);
		 }
		 public static String formatPhoneNumber(String str){
			 String temp="";
			 Integer a= (Integer)str.length()/4;
			 Integer b= str.length()-(a)*4;
			 int index=0;
			 for (int i = a; i >=0; i--) {
				 if(i==a){
					 temp=str.substring(str.length()-(index*4+4), str.length()-index*4)+temp;
				 }else if(i==0){
					 temp=str.substring(0, b)+"-"+temp;
				 }else{
					 temp=str.substring(str.length()-(index*4+4), str.length()-index*4)+"-"+temp;
				 }
				 index++;
				 
			}
			 
			 return temp;
		 }
		 
		 public static Integer diffirentTwoDate(Date beforeDate,Date afterDate){
			 try {
				 long diff = afterDate.getTime()-beforeDate.getTime();
				 
				 Integer diffDays = Math.round(DataUtils.parseLong(diff) / (24 * 60 * 60 * 1000));
				return diffDays;
			} catch (Exception e) {
			}
			 
			 return 0;
		 }
		 
		 public static String getStringRandom(Integer length){
				try{
					return RandomStringUtils.randomNumeric(length).toUpperCase();

				} catch (Exception e) {
					System.out.println(e);
				}
				return "";
			}

		 public static Integer getTotalPage(Integer totalElements, Integer size){
				try {
					return (int) Math.ceil((double) totalElements/ (double) size);
				} catch (Exception e) {
					return 0;
				}
			 }
		 public static String convertDate_To_String(Date date, String formatDate){
			    Format format = new SimpleDateFormat(formatDate);
			    return format.format(date);
			}
}
