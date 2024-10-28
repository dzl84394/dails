package cn.dails.base.utils;

import org.springframework.util.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.Random;
import java.util.UUID;

public class StrUtils {

	public static final String _BASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	public static final String _NUM_ONLY = "0123456789";
	public static final String _CHAR_ONLY = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	public static final String _CASE_ONLY = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String _UNCASE_ONLY = "abcdefghijklmnopqrstuvwxyz";

	/**
	 * 随机码
	 * 
	 * @param size 长度
	 * @return
	 */
	public static String randomCode(int size) {
		return randomCode(size, _BASE);
	}

	public static String randomCode(int size, String sources) {
		if (sources == null || sources.length() == 0) {
			sources = _BASE;
		}
		int codesLen = sources.length();
		Random rand = new Random();
		StringBuilder str = new StringBuilder(size);
		for (int i = 0; i < size; i++) {
			str.append(sources.charAt(rand.nextInt(codesLen - 1)));
		}
		return str.toString();
	}

	/**
	 * uuid去掉-，32个字符串 不去-是36位
	 * 
	 * @return
	 */
	public static String generateUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");

	}

	/**
	 * js数字长度是17， 时间13位，随机数4位 随机数用redis累加来代替
	 * 
	 */
	public static String timeId() {
		return System.currentTimeMillis() + "" + (int) (Math.random() * 2000 + 1000);
	}

	/**
	 * 备注： 雪花id，百度的id，美团的id，都好麻烦呀<br/>
	 * hascode可能会重合<br/>
	 * 图片的流形式的md5和写完之后的md5不一样 <br/>
	 * md5如果首位是0，可能少一位，所以得转换2进制
	 * 
	 */

	public static String getMD5(String str) {
		// 生成一个MD5加密计算摘要
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			return toHexString(md.digest());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		// 计算md5函数

	}

	private static String toHexString(byte[] bytes) {
		Formatter formatter = new Formatter();
		for (byte b : bytes) {
			formatter.format("%02x", b);
		}

		String res = formatter.toString();
		formatter.close();

		return res;
	}

	/**
	 * 两种都一样
	 * 
	 * @param s
	 * @return
	 */
	public static final String getMd5(String s) {
		char[] hexDigits = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
				'F' };

		try {
			byte[] btInput = s.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char[] str = new char[j * 2];
			int k = 0;

			for (int i = 0; i < j; ++i) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 15];
				str[k++] = hexDigits[byte0 & 15];
			}

			return (new String(str)).toLowerCase();
		} catch (Exception var10) {
			var10.printStackTrace();
			return null;
		}
	}
	/**
	 * 3个方法
	 * @param string
	 * @return
	 */
	public static String md5(String string) {
		String md5 = DigestUtils.md5DigestAsHex(string.getBytes());
		return md5;
	}

//	public static void main(String[] args) {
//
//		String aString = getMD5("abcd");
//		String bString = getMd5("abcd");
////    	String aString = generateUUID();
////    	String aString = randomCode(5,_NUM_ONLY);
//		System.out.println(aString + ":" + bString);
//	}
}
