package cn.dails.logging.utils;

import com.google.gson.JsonObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class CommonTools {

	
	/************************hostName start*************************/
	private static String hostName;

	public static String getHostName() {
		if (hostName != null)
			return hostName;
		try {
			hostName = execReadToString("hostname").trim();
		} catch (IOException e) {
			hostName = "_unknown";
		}
		return hostName;
		/*
		 * try { hostName=InetAddress.getLocalHost().getHostName().toString(); }
		 * catch (UnknownHostException e) { hostName="_unknown"; } return
		 * hostName;
		 */
	}
	
	public static String execReadToString(String execCommand) throws IOException {
		Process proc = Runtime.getRuntime().exec(execCommand);
		try (InputStream stream = proc.getInputStream()) {
			try (@SuppressWarnings("resource")
			Scanner s = new Scanner(stream).useDelimiter("\\A")) {
				return s.hasNext() ? s.next().trim() : "";
			} finally {
				stream.close();
			}
		}
	}
	/**===================hostName end =====================================*/
	
	
	/**
	 * 获取本地ip
	 * @return
	 * @throws UnknownHostException
	 */
	public static String getLocalIp() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostAddress();
	}
	
	
	
	/**
	 * 获取异常信息
	 * @param e
	 * @return
	 */
	public static String getExceptionSimpleInfo(Throwable e) {
		if (e == null)
			return null;
//		JSONObject json = new JSONObject();
	      JsonObject json = new JsonObject();
		json.addProperty("id", System.identityHashCode(e));
		json.addProperty("class", e.getClass().getName());
		json.addProperty("msg", e.getMessage());
		if (e.getStackTrace() != null && e.getStackTrace().length > 0) {
			json.addProperty("topStack", e.getStackTrace()[0].toString());
		}
		return json.toString();
	}
	
	
	
	public static Method getMethod(JoinPoint point) throws Exception {
		MethodSignature signature = (MethodSignature) point.getSignature();
		final String methodName = point.getSignature().getName();
		Method method = signature.getMethod();
		if (method.getDeclaringClass().isInterface()) {
			method = point.getTarget().getClass().getDeclaredMethod(methodName, method.getParameterTypes());
		}
		return method;
	}
}
