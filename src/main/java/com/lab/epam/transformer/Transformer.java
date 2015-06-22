package com.lab.epam.transformer;

import com.lab.epam.md5.MD5Creator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class Transformer<T> {

	private Class<T> clazz;
	PreparedStatement ps;

	public Transformer(Class<T> clazz) {
		this.clazz = clazz;
	}

	public String getTableName() {
		String tableName = null;
		try {
			Annotation annotation = clazz.getAnnotation(Table.class);
			tableName = annotation.annotationType().getMethod("value")
					.invoke(annotation).toString();

		} catch (IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			e.printStackTrace();
		}
		return tableName;
	}

	public Map<String, Object> getObjectColumns(T object) {
		Map<String, Object> columns = new HashMap<>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				Annotation annotation = field.getAnnotation(Column.class);
				String key = annotation.annotationType().getMethod("value")
						.invoke(annotation).toString();
				columns.put(key, field.get(object));
			} catch (IllegalAccessException | InvocationTargetException
					| NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		return columns;
	}

	public List<String> setRowInDB(T object) throws SQLException {
		Map<String, Object> objectColumns = getObjectColumns(object);
		String values = "";
		String field = "";
		for (Entry<String, Object> entry : objectColumns.entrySet()) {
			if(entry.getValue() == null){
				continue;
			}
			if (entry.getKey().equals("password")) {
				entry.setValue(MD5Creator.getMD5(entry.getValue().toString()
						+ objectColumns.get("login").toString()));
			}
			field += entry.getKey() + ",";
			if (entry.getValue().getClass() == String.class || entry.getValue().getClass() == Date.class) {
				values += "'" + entry.getValue() + "',";
			} else {
				values += entry.getValue() + ",";
			}
		}
		String substring = field.substring(0, field.length() - 1);
		field = substring;
		substring = values.substring(0, values.length() - 1);
		values = substring;
		 List<String> list = new ArrayList<>(); 
		 list.add(field);
		 list.add(values);
		 
		return list;
	}

	public List<T> rowToObject(ResultSet rs) {
		T object = null;
		List<T> list = new ArrayList<T>();
		try {
			while (rs.next()) {
				try {
					object = clazz.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
				Field[] fields = clazz.getDeclaredFields();
				for (Field field : fields) {
					try {
						field.setAccessible(true);
						Annotation annotation = field
								.getAnnotation(Column.class);
						String columnName = annotation.annotationType()
								.getMethod("value").invoke(annotation)
								.toString();
						field.set(object, rs.getObject(columnName));
					} catch (IllegalAccessException | InvocationTargetException
							| NoSuchMethodException | SQLException e) {
						e.printStackTrace();
					}
				}
				list.add(object);
			}
		} catch (SecurityException | IllegalArgumentException | SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
