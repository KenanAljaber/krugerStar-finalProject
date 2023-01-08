package com.minegocio.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonConverter {
	
	
	@Autowired
	private  ObjectMapper mapper;
	
	/**
	 * this method will take any type of objects and convert it into string json
	 * format
	 * 
	 * @param obj is the object we want to convert into json format
	 * @return a string as formatted as json
	 */
	public   String convertToJson(Object obj) {
		String json = "";
		try {
			json = mapper.writeValueAsString(obj);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Could not convert object to json");
		}
		return json;
	}

}
