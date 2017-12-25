package org.trams.web.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.trams.rest.common.AbstractRestController;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.mysql.fabric.Response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by phong on 4/11/16.
 */
public class ServletUtils extends AbstractRestController{

    public static void response(HttpServletResponse response, int code, String message) throws IOException {
        response.setStatus(code);
        response.setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        JSONObject js =new JSONObject();
        js.put("status", 100);
        js.put("mesage", message);
        js.put("data", null);
        System.out.println(js.toString());
        response.getWriter().write(js.toString());
    }



}
