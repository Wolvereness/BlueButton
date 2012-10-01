package com.wolvereness.util.bluebutton;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * <pre>
 * This file is part of BlueButton.
 *
 * BlueButton is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * BlueButton is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with BlueButton.  If not, see < http://www.gnu.org/licenses/ >.
 * </pre>
 * @author Wolfe
 */
@SuppressWarnings("javadoc")
public class StringMaker {
	public interface Appendable {
		StringBuilder appendTo(StringBuilder builder, int indent);
	}

	public static StringBuilder appendTo(final boolean[] value, final StringBuilder builder, final int indent) {
		if (value == null)
			return builder.append("null");
		indent(builder.append("{\n"), indent);
		for (final boolean num : value) {
			indent(builder.append(num).append(",\n"), indent);
		}
		return (value.length > 0 ? builder.deleteCharAt /* the "," */(builder.length() - 2 - indent) : builder).append('}');
	}

	public static StringBuilder appendTo(final byte[] value, final StringBuilder builder, final int indent) {
		if (value == null)
			return builder.append("null");
		indent(builder.append("{\n"), indent);
		for (final byte num : value) {
			indent(builder.append(num).append(",\n"), indent);
		}
		return (value.length > 0 ? builder.deleteCharAt /* the "," */(builder.length() - 2 - indent) : builder).append('}');
	}

	public static StringBuilder appendTo(final char[] value, final StringBuilder builder, final int indent) {
		if (value == null)
			return builder.append("null");
		indent(builder.append("{\n"), indent);
		for (final char num : value) {
			indent(builder.append(num).append(",\n"), indent);
		}
		return (value.length > 0 ? builder.deleteCharAt /* the "," */(builder.length() - 2 - indent) : builder).append('}');
	}

	public static StringBuilder appendTo(final Collection<?> collection, final StringBuilder builder, final int indent) {
		if (collection == null)
			return builder.append("null");
		indent(builder.append("[\n"), indent);
		for (final Object obj : collection) {
			indent(appendTo(obj, builder, indent + 1).append(",\n"), indent);
		}
		return (collection.size() > 0 ? builder.deleteCharAt /* the "," */(builder.length() - 2 - indent) : builder).append(']');
	}

	public static StringBuilder appendTo(final double[] value, final StringBuilder builder, final int indent) {
		if (value == null)
			return builder.append("null");
		indent(builder.append("{\n"), indent);
		for (final double num : value) {
			indent(builder.append(num).append(",\n"), indent);
		}
		return (value.length > 0 ? builder.deleteCharAt /* the "," */(builder.length() - 2 - indent) : builder).append('}');
	}

	public static StringBuilder appendTo(final float[] value, final StringBuilder builder, final int indent) {
		if (value == null)
			return builder.append("null");
		indent(builder.append("{\n"), indent);
		for (final float num : value) {
			indent(builder.append(num).append(",\n"), indent);
		}
		return (value.length > 0 ? builder.deleteCharAt /* the "," */(builder.length() - 2 - indent) : builder).append('}');

	}

	public static StringBuilder appendTo(final int[] value, final StringBuilder builder, final int indent) {
		if (value == null)
			return builder.append("null");
		indent(builder.append("{\n"), indent);
		for (final int num : value) {
			indent(builder.append(num).append(",\n"), indent);
		}
		return (value.length > 0 ? builder.deleteCharAt /* the "," */(builder.length() - 2 - indent) : builder).append('}');
	}

	public static StringBuilder appendTo(final long[] value, final StringBuilder builder, final int indent) {
		if (value == null)
			return builder.append("null");
		indent(builder.append("{\n"), indent);
		for (final long num : value) {
			indent(builder.append(num).append(",\n"), indent);
		}
		return (value.length > 0 ? builder.deleteCharAt /* the "," */(builder.length() - 2 - indent) : builder).append('}');

	}

	public static StringBuilder appendTo(final Map<?,?> collection, final StringBuilder builder, final int indent) {
		if (collection == null)
			return builder.append("null");
		indent(builder.append("[\n"), indent);
		for (final Map.Entry<?, ?> obj : collection.entrySet()) {
			indent(appendTo(obj.getValue(), builder.append(obj.getKey()).append('='), indent + 1).append(",\n"), indent);
		}
		return (collection.size() > 0 ? builder.deleteCharAt /* the "," */(builder.length() - 2 - indent) : builder).append(']');
	}

	public static StringBuilder appendTo(final Object value, final StringBuilder builder, final int indent) {
		if (value instanceof Appendable)
			return ((Appendable) value).appendTo(builder, indent);
		else if (value instanceof Map)
			return appendTo((Map<?,?>) value, builder, indent);
		else if (value instanceof Collection)
			return appendTo((Collection<?>) value, builder, indent);
		else if (value != null && value.getClass().isArray()) {
			final Class<?> clazz = value.getClass().getComponentType();
			if (clazz.isPrimitive()) {
				if (clazz == boolean.class)
					return appendTo((boolean[]) value, builder, indent);
				else if (clazz == byte.class)
					return appendTo((byte[]) value, builder, indent);
				else if (clazz == short.class)
					return appendTo((short[]) value, builder, indent);
				else if (clazz == char.class)
					return appendTo((char[]) value, builder, indent);
				else if (clazz == int.class)
					return appendTo((int[]) value, builder, indent);
				else if (clazz == long.class)
					return appendTo((long[]) value, builder, indent);
				else if (clazz == float.class)
					return appendTo((float[]) value, builder, indent);
				else if (clazz == double.class) return appendTo((double[]) value, builder, indent);
				throw new AssertionError("No primitive type for " + clazz);
			} else
				return appendTo((Object[]) value, builder, indent);
		} else
			return builder.append(value);
	}

	public static StringBuilder appendTo(final Object[] array, final StringBuilder builder, final int indent) {
		if (array == null)
			return builder.append("null");
		indent(builder.append("{\n"), indent);
		for (final Object obj : array) {
			indent(appendTo(obj, builder, indent + 1).append(",\n"), indent);
		}
		return (array.length > 0 ? builder.deleteCharAt /* the "," */(builder.length() - 2 - indent) : builder).append('}');
	}

	public static StringBuilder appendTo(final short[] value, final StringBuilder builder, final int indent) {
		if (value == null)
			return builder.append("null");
		indent(builder.append("{\n"), indent);
		for (final short num : value) {
			indent(builder.append(num).append(",\n"), indent);
		}
		return (value.length > 0 ? builder.deleteCharAt /* the "," */(builder.length() - 2 - indent) : builder).append('}');
	}

	public static StringBuilder appendTo(final String[] names, final Object[] values, final StringBuilder builder, final int indent) {
		if (names.length != values.length)
			throw new IllegalArgumentException(Arrays.toString(names) + " does not have the same length as " + Arrays.toString(values));
		indent(builder.append("<\n"), indent);
		final int length = values.length;
		for (int n = 0; n < length; n++) {
			indent(appendTo(values[n], builder.append(names[n]).append('='), indent + 1).append(",\n"), indent);
		}
		return (length > 0 ? builder.deleteCharAt /* the "," */(builder.length() - 2 - indent) : builder).append('>');
	}

	private static StringBuilder indent(final StringBuilder builder, final int indent) {
		for (int i = 0; i < indent; i++) {
			builder.append('\t');
		}
		return builder;
	}
}
