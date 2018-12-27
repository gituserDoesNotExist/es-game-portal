package org.commons.mapper;

import java.io.IOException;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

public class RequestExtractor {

	public static String readBody(HttpServletRequest request) throws IOException {
		Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
		if (scanner.hasNext()) {
			String body = scanner.useDelimiter("\\A").next();
			scanner.close();
			return body;
		}
		scanner.close();
		throw new RuntimeException("No value found");
	}

	public static long retrieveId(HttpServletRequest request) {
		String pathInfo = request.getPathInfo();
		if (pathInfo.startsWith("/")) {
			pathInfo = pathInfo.substring(1);
		}
		return Long.parseLong(pathInfo);
	}

}
