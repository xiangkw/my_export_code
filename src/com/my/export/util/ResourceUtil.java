package com.my.export.util;

import java.io.InputStream;
import java.net.URL;

public class ResourceUtil {

	public static final String PROTOCOL_FILE = "file:";

	public static final String PROTOCOL_CLASSPATH = "classpath*:";

	@SuppressWarnings("rawtypes")
	public static InputStream getResourceAsStream(String path, Class class1) {
		InputStream inputstream = class1.getResourceAsStream(path);
		return inputstream;
	}

	public static InputStream getResourceAsStream(String path) {
		String trip = path.startsWith("/") ? path.substring(1) : path;
		InputStream stream = null;

		if (trip != null && !"".equals(trip)) {
			stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(trip);

			if (stream == null) {
				stream = ResourceUtil.class.getClassLoader().getResourceAsStream(trip);
			}

			if (stream == null) {
				stream = ResourceUtil.class.getResourceAsStream(trip);
			}
			if (stream == null) {
				stream = ResourceUtil.class.getResourceAsStream(path);
			}
		}

		return stream;
	}

	public static URL getResource(String path) {
		String trip = path.startsWith("/") ? path.substring(1) : path;
		URL url = null;

		if (trip != null && !"".equals(trip)) {
			url = Thread.currentThread().getContextClassLoader().getResource(trip);

			if (url == null) {
				url = ResourceUtil.class.getClassLoader().getResource(trip);
			}

			if (url == null) {
				url = ResourceUtil.class.getResource(trip);
			}
		}

		return url;
	}

}
