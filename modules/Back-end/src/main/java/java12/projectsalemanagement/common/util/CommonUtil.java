package java12.projectsalemanagement.common.util;

import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonUtil {
	
	public static <T> String toJson(final ObjectMapper objectMapper, final T object) {
	    try {
	        if (null != objectMapper) {
	            return objectMapper.writeValueAsString(object);
	        }
	        return Jackson2ObjectMapperBuilder.json().build().writeValueAsString(object);
	    }
	    catch (JsonProcessingException ex) {
	        throw new IllegalArgumentException(ex.getMessage());
	    }
	}

}
