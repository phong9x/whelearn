package org.trams.rest.common.notification;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import org.trams.web.common.ApplicationDefine;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PushFCM {
	
	public static void main(String[] args) {
		String API_KEY="AIzaSyCfb2CMHOhoEzz1JerxWM9A26CgtwJutvY";
		List<String> list_reg =new LinkedList<>();
		list_reg.add("fTfZO5D-8Hw:APA91bHUNUNLnvnoRcSzk_2WDMAua49VTqjRE_ZFGBVDT7n2h62xyF217Zl2uMREiUWyEKiB16Yun5Q37EEg23UK7Uacmg__VDrIkpOrPVYlMIga-38_ojg4W0W9rvqsCcaVoGjhzAhK");
		list_reg.add("c0YyGvBRtws:APA91bFdeR4hDNHF65hHTk_V6gRX-QLy6a3nabHGlV8Kc5MV9aFVutB4IwVVU08ORlto5h7VJR5cC4EFQ9KlRLE3XwVdCephUmp64IHPSdiISCLImeNnNR3JFmpQFXwRD1dnTK1TIb50");
		Notification n =new Notification();
		n.sendNotification(list_reg, "정산완료 되었을 때", "Program title 에 대한 정산이 완료되었습니다. 세부 사항을 확인해 주세요.", 1,
				ApplicationDefine.NOTIFICATION_FinishPayCourseNotification, 1);
	}
	public static void post(String API_KEY, Object c){
		try{
		// 1. URL
			URL url = new URL("https://fcm.googleapis.com/fcm/send");
					
		// 2. Open connection
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					
		// 3. Specify POST method
		conn.setRequestMethod("POST");
		
		// 4. Set the headers
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Authorization", "key="+API_KEY);
		
		conn.setDoOutput(true);

			// 5. Add JSON data into POST request body 
		
			//`5.1 Use Jackson object mapper to convert Contnet object into JSON
	    	ObjectMapper mapper = new ObjectMapper();
	    	
	    	// 5.2 Get connection output stream
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			
			// 5.3 Copy Content "JSON" into 
			mapper.writeValue(wr, c);
			System.out.println(mapper.writeValueAsString(c));

			// 5.4 Send the request
			wr.flush();
			
			// 5.5 close
			wr.close();
			 
			// 6. Get the response
			int responseCode = conn.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
	 
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
	 
			// 7. Print result
			System.out.println("Result: "+response.toString());
					
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	
	
	
}
