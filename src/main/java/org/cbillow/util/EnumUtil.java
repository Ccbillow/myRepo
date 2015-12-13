package org.cbillow.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnumUtil {
	private final static Logger logger = LoggerFactory.getLogger(EnumUtil.class);

	public interface ValuedEnum {
		int value();

		String extValue();
	}

	@SuppressWarnings("unchecked")
	public static <E extends Enum<E> & ValuedEnum> E getEnum(Class<E> clazz, int value) {
		try {
			for (E e : (E[]) clazz.getMethod("values").invoke(null))
				if (e.value() == value)
					return e;
		} catch (Exception e) {
			logger.error("getEnum exception", e);
			throw new IllegalArgumentException(clazz + " Can not convert from value: " + value);
		}

		throw new IllegalArgumentException(clazz + " Can not convert from value: " + value);
	}

	@SuppressWarnings("unchecked")
	public static <E extends Enum<E> & ValuedEnum> boolean isEnum(Class<E> clazz, int value) {
		try {
			for (E e : (E[]) clazz.getMethod("values").invoke(null))
				if (e.value() == value)
					return true;
		} catch (Exception e) {
			return false;
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	public static <E extends Enum<E> & ValuedEnum> E getEnum(Class<E> clazz, String extValue) {
		try {
			for (E e : (E[]) clazz.getMethod("values").invoke(null))
				if (e.extValue().equals(extValue))
					return e;
		} catch (Exception e) {
			logger.error("getEnum exception", e);
			throw new IllegalArgumentException(clazz + " Can not convert from extValue: " + extValue);
		}

		throw new IllegalArgumentException(clazz + " Can not convert from extValue: " + extValue);
	}

	@SuppressWarnings("unchecked")
	public static <E extends Enum<E> & ValuedEnum> boolean isEnum(Class<E> clazz, String extValue) {
		try {
			for (E e : (E[]) clazz.getMethod("values").invoke(null))
				if (e.extValue().equals(extValue))
					return true;
		} catch (Exception e) {
			return false;
		}

		return false;
	}

	/** 用于HQL查询时引用枚举常量，通过 Query.setProperties 方法将枚举值引入 */
	public static <E extends Enum<E>> Map<String, E> getNameMap(Class<E> clazz) {
		return getNameMap(clazz, "");
	}

	@SuppressWarnings("unchecked")
	public static <E extends Enum<E>> Map<String, E> getNameMap(Class<E> clazz, String prefix) {
		if (prefix == null)
			prefix = "";

		Map<String, E> map = new HashMap<String, E>();
		try {
			for (E e : (E[]) clazz.getMethod("values").invoke(null))
				map.put(prefix + e.name(), e);
		} catch (Exception e) {
		}
		return map;
	}
}
