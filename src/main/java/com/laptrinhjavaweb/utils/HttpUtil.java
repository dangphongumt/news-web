package com.laptrinhjavaweb.utils;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil {
	
	private String value;
	
	public HttpUtil(String value) {
		this.value = value;
	}
	
	public <T> T toModel(Class<T> tClass) {
		try {
			return new ObjectMapper().readValue(value, tClass);
		} catch (Exception e) {
			System.err.println("error in utils "+e.getMessage());
		}
		return null;
	}
	
	public static HttpUtil of (BufferedReader reader) {
		StringBuilder sb = new StringBuilder();
		try {
			String line;
			while((line=reader.readLine()) != null) {//chuyển request thành json
				sb.append(line);
			}
		} catch (IOException e) {
			System.err.println("error in utils "+e.getMessage());
		}
		
		
		return new HttpUtil(sb.toString());//return contructer
	}
}
