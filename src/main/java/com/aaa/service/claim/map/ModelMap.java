package com.aaa.service.claim.map;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Component
public class ModelMap<T> {
	
	public T map(T obj1, Class<T> cls) throws JsonProcessingException {
		ObjectMapper map = new ObjectMapper();
		map.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		map.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
		String json = map.writeValueAsString(obj1);
		return (T) map.readValue(json, cls);
	}
	
	public List<?> mapList(T obj1, Class<T> cls) throws JsonProcessingException {
		ObjectMapper map = new ObjectMapper();
		map.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		map.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
		String json = map.writeValueAsString(obj1);
		return map.readValue(json, map.getTypeFactory().constructCollectionType(List.class, cls));
	}
}
