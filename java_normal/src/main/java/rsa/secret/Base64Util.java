package rsa.secret;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Base64Utils;

public class Base64Util {

	public static String encode(String content) {
		if(StringUtils.isNotBlank(content)) {
			return Base64Utils.encodeToString(content.getBytes());
		}
		return null;
	}
	
	public static String decode(String content) {
		if(StringUtils.isNotBlank(content)) {
			return new String(Base64Utils.decodeFromString(content));
		}
		return null;
	}
}