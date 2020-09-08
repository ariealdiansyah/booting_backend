package com.lawencon.booting.utility;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hibernate {

	
	public static List<Map<String, Object>> bMapperList(List<Object[]> listMapping, String... columns) throws Exception {

		if (listMapping.isEmpty())
			throw new Exception("Result is Empty");

		List<Map<String, Object>> listMap = new ArrayList<>();
		listMapping.forEach(valObj -> {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < valObj.length; i++) {
				map.put(columns[i], valObj[i]);
			}

			listMap.add(map);
		});

		return listMap;
	}

	
	public static List<Map<String, Object>> bMapper(List<Object> listMapping, String column) throws Exception {
		if (listMapping.isEmpty())
			throw new Exception("Result is Empty");

		List<Map<String, Object>> listMap = new ArrayList<>();

		listMapping.forEach(valObj -> {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(column, valObj);

			listMap.add(map);
		});

		return listMap;
	}

	
	public static <T> List<T> bMapperList(List<Object[]> listMapping, Class<T> clazz, String... columns)
			throws Exception {

		List<T> listResult = new ArrayList<>();

		listMapping.forEach(valDb -> {
			try {
				T newClass = clazz.newInstance();
				Method[] methods = clazz.getDeclaredMethods();
				List<Method> listMethod = new ArrayList<>(Arrays.asList(methods));
				getSuperMethod(clazz, listMethod);

				for (int i = 0; i < valDb.length; i++) {
					Object value = valDb[i];
					String mapping = columns[i];

					invokeMethod(newClass, listMethod, value, mapping);

				}

				listResult.add(newClass);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		return listResult;
	}

	
	public static <T> List<T> bMapper(List<Object> listMapping, Class<T> clazz, String column) throws Exception {
		List<T> listResult = new ArrayList<>();

		listMapping.forEach(valDb -> {
			try {
				T newClass = clazz.newInstance();
				Method[] methods = clazz.getDeclaredMethods();
				List<Method> listMethod = new ArrayList<>(Arrays.asList(methods));
				getSuperMethod(clazz, listMethod);

				invokeMethod(newClass, listMethod, valDb, column);

				listResult.add(newClass);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		return listResult;
	}

	private static <T> void getSuperMethod(Class<T> clazz, List<Method> listMethod) {
		Class<? super T> superClazz = clazz.getSuperclass();
		while (superClazz != null) {
			Method[] methodSuper = superClazz.getDeclaredMethods();
			listMethod.addAll(Arrays.asList(methodSuper));
			superClazz = superClazz.getSuperclass();
		}
	}

	private static <T> void invokeMethod(T newClass, List<Method> listMethod, Object value, String mapping)
			throws Exception {
		for (int j = 0; j < listMethod.size(); j++) {
			boolean isFound = false;
			Method m = listMethod.get(j);

			if (m.getName().startsWith("set")) {

				for (Parameter p : m.getParameters()) {

					if (p.getName().equalsIgnoreCase(mapping)) {

						isFound = true;
						m.invoke(newClass, value);
						break;
					}

					break;
				}
			}

			if (isFound)
				break;
		}
	}

}
