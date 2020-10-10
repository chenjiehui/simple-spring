package com.simple.spring.utils;


import java.util.HashMap;
import java.util.Map;

public abstract class ClassUtils {

	/** The package separator character: '.' */
	private static final char PACKAGE_SEPARATOR = '.';

	/** The path separator character: '/' */
	private static final char PATH_SEPARATOR = '/';

	/**
	 * Map with primitive wrapper type as key and corresponding primitive
	 * type as value, for example: Integer.class -> int.class.
	 */
	private static final Map<Class<?>, Class<?>> wrapperToPrimitiveTypeMap = new HashMap<Class<?>, Class<?>>(8);

	/**
	 * Map with primitive type as key and corresponding wrapper
	 * type as value, for example: int.class -> Integer.class.
	 */
	private static final Map<Class<?>, Class<?>> primitiveTypeToWrapperMap = new HashMap<Class<?>, Class<?>>(8);

	static {
		wrapperToPrimitiveTypeMap.put(Boolean.class, boolean.class);
		wrapperToPrimitiveTypeMap.put(Byte.class, byte.class);
		wrapperToPrimitiveTypeMap.put(Character.class, char.class);
		wrapperToPrimitiveTypeMap.put(Double.class, double.class);
		wrapperToPrimitiveTypeMap.put(Float.class, float.class);
		wrapperToPrimitiveTypeMap.put(Integer.class, int.class);
		wrapperToPrimitiveTypeMap.put(Long.class, long.class);
		wrapperToPrimitiveTypeMap.put(Short.class, short.class);

		for (Map.Entry<Class<?>, Class<?>> entry : wrapperToPrimitiveTypeMap.entrySet()) {
			primitiveTypeToWrapperMap.put(entry.getValue(), entry.getKey());

		}

	}

	public static ClassLoader getDefaultClassLoader() {
		ClassLoader cl = null;
		try {
			cl = Thread.currentThread().getContextClassLoader();
		}
		catch (Throwable ex) {
			// Cannot access thread context ClassLoader - falling back...
		}
		if (cl == null) {
			// No thread context class loader -> use class loader of this class.
			cl = ClassUtils.class.getClassLoader();
			if (cl == null) {
				// getClassLoader() returning null indicates the bootstrap ClassLoader
				try {
					cl = ClassLoader.getSystemClassLoader();
				}
				catch (Throwable ex) {
					// Cannot access system ClassLoader - oh well, maybe the caller can live with null...
				}
			}
		}
		return cl;
	}

    public static <T> boolean isAssignableValue(Class<T> requiredType, Object value) {
		Assert.notNull(requiredType, "Type must not be null");
		return (value != null ? isAssignable(requiredType, value.getClass()) : !requiredType.isPrimitive());
    }

	public static boolean isAssignable(Class<?> lhsType, Class<?> rhsType) {
		Assert.notNull(lhsType, "Left-hand side type must not be null");
		Assert.notNull(rhsType, "Right-hand side type must not be null");
		if (lhsType.isAssignableFrom(rhsType)) {
			return true;
		}
		if (lhsType.isPrimitive()) {
			Class<?> resolvedPrimitive = wrapperToPrimitiveTypeMap.get(rhsType);
			return resolvedPrimitive != null && lhsType.equals(resolvedPrimitive);
		}
		else {
			Class<?> resolvedWrapper = primitiveTypeToWrapperMap.get(rhsType);
			return resolvedWrapper != null && lhsType.isAssignableFrom(resolvedWrapper);
		}
	}


	public static String convertClassNameToResourcePath(String className) {
		Assert.notNull(className, "Class name must not be null");
		return className.replace(PACKAGE_SEPARATOR, PATH_SEPARATOR);
	}

	public static String convertResourcePathToClassName(String resourcePath) {
		Assert.notNull(resourcePath, "Resource path must not be null");
		return resourcePath.replace(PATH_SEPARATOR, PACKAGE_SEPARATOR);
	}
}
