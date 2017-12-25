package org.trams.web.common.utils;
import java.io.IOException;
import java.util.Properties;
public class ConstantUtils {
	 private static final String NOTFOUND = "not found";
	    private static Properties props;

	    public static String getConfig(String key) {
	        try {
	            if (props == null) {
	                props = new Properties();
	                props.load(ConstantUtils.class.getClassLoader().getResourceAsStream("META-INF/config/database.properties"));
	            }
	            return props.getProperty(key, NOTFOUND);
	        } catch (Exception e) {
	        	e.printStackTrace();
	            return NOTFOUND;
	        }
	    }
}
