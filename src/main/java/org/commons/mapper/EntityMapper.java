package org.commons.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class EntityMapper {

	public static <T> T jsonToEntity(String jsonString, Class<T> clazz) {
		try {
			System.out.println(jsonString);
			ObjectMapper objectMapper = initObjectMapper();
			return objectMapper.readValue(jsonString, clazz);
		} catch (Exception e) {
			System.out.println("something went wrong. cant parse json");
			System.out.println(e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static <T> String entityToJson(T t) {
		try {
			ObjectMapper objectMapper = initObjectMapper();
			return objectMapper.writeValueAsString(t);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static ObjectMapper initObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return objectMapper;
	}
}
