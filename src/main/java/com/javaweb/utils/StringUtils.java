package com.javaweb.utils;

public class StringUtils {
	public static boolean isNotBlank(String value) {
		if (value != null && !value.isEmpty())
			return true;
		return false;
	}
	public static boolean isNumber(String value) {
		try {
			Long number = Long.parseLong(value);
		}
		catch (Exception e) {
			return false;
		}
		return true;
	}
}