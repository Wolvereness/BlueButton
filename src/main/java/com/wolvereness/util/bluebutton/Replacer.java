package com.wolvereness.util.bluebutton;

import java.util.Arrays;

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
public class Replacer implements Comparable<Replacer> {
	/**
	 * This is required to make {@link Replacer}s; it provides a variable replacement value.
	 */
	public interface Handler {
		/**
		 * @param scope this is provided by the replacement method when performing replacements.
		 * @return the String to replace the respective handler
		 */
		String getValue(Object...scope);
	}

	/**
	 * @param string The string to replace in
	 * @param replacers This is the set of replaces to use. Order matters!
	 * 		</br><i>Note: the array will not be modified</i>
	 * @param scope This is the 'scope' to provide to the handlers for the replacers
	 * 		</br><i>Note: the array will not be modified</i>
	 * @return A string representing all occurrences of the replacers replaced with appropriate values from the assigned {@link Handler}s,
	 * 		or null if string is null and replaces is empty.
	 * @throws IllegalArgumentException if a replacer is null or does not have the same starting character
	 * @throws NullPointerException if replacer is null
	 * @throws NullPointerException string is null and replacers is not empty
	 */
	public static String makeReplacements(final String string, final Replacer[] replacers, final Object...scope) {
		if (replacers.length == 0)
			return string;
		if (replacers[0] == null)
			throw new IllegalArgumentException("Replacer(s) were null " + Arrays.toString(replacers));

		final char header = replacers[0].header;
		int p = 0, i = string.indexOf(header);
		if (i == -1)
			return string; // Short logic for no replacement

		for (final Replacer replacer : replacers) {
			// Fail-fast
			if (replacer == null)
				throw new IllegalArgumentException("Replacer(s) were null " + Arrays.toString(replacers));
			if (replacer.header != header)
				throw new IllegalArgumentException(replacer + " does not match " + replacers[0]);
		}

		final StringBuilder out = new StringBuilder(string.length() + (string.length() >> 3));
		findNext: do {
			// Copy pre-sequence
			out.append(string, p, i++);
			for (final Replacer replacer : replacers) {
				if (string.startsWith(replacer.token, i)) {
					// We have our match
					out.append(replacer.handler.getValue(scope));
					i += replacer.token.length();
					continue findNext;
				}
			}
			// No match, recover skipped character
			out.append(header);
		} while ((i = string.indexOf(header, p = i)) != -1);

		// Final sequence
		return out.append(string, p, string.length()).toString();
	}

	/**
	 * This method uses an efficient algorithm for creating a new string using a character token while minimizing object creations.
	 * @param string String to replace in
	 * @param c String token to replace
	 * @param replacement CharSequence to replace with
	 * @return A string representing each occurrence of specified character replaced with given replacement
	 * @throws NullPointerException if replacement or base string are null
	 */
	public static String replaceAll(final String string, final char c, final CharSequence replacement) {
		if (replacement.length() == 1)
			return string.replace(c, replacement.charAt(0)); // Delegate for single character replacement
		int p = 0, i = string.indexOf(c);
		if (i == -1)
			return string; // Short logic for no replacement

		final StringBuilder out = new StringBuilder(replacement.length() > 1 ? string.length() + (string.length() >> 3) : string.length());
		do {
			// Copy pre-sequence and then replacement
			out.append(string, p, i).append(replacement);
		} while ((i = string.indexOf(c, p = i + 1)) != -1);

		// Final sequence
		return out.append(string, p, string.length()).toString();
	}

	/**
	 * This method uses an efficient algorithm for creating a new string using a literal replacement while minimizing object creations.
	 * @param string String to replace in
	 * @param value String token to replace
	 * @param replacement CharSequence to replace with
	 * @return A string representing each occurrence of the literal value replaced with given replacement
	 * @throws IllegalArgumentException if value is null or 0-length
	 * @throws NullPointerException if replacement or base string are null
	 */
	public static String replaceAllLiteral(final String string, final String value, final CharSequence replacement) {
		if (value == null || value.length() == 0)
			throw new IllegalArgumentException("Cannot replace occurences of empty search string");

		if (value.length() == 1)
			return replaceAll(string, value.charAt(0), replacement); // Delegate for single character replacement
		int p = 0, i = string.indexOf(value);
		if (i == -1)
			return string; // Short logic for no replacement

		final int valueLength = value.length();
		final StringBuilder out = new StringBuilder(replacement.length() > valueLength ? string.length() + (string.length() >> 3) : string.length());
		do {
			// Copy pre-sequence and then replacement
			out.append(string, p, i).append(replacement);
		} while ((i = string.indexOf(value, p = i + valueLength)) != -1);

		// Final sequence
		return out.append(string, p, string.length()).toString();
	}

	private final Handler handler;
	private final char header;
	private final boolean low;
	private final String token;

	/**
	 * @param token Token that will be searched
	 * @param handler The handler to use when making replacements. The handler will interpret the value to insert in place of the token.
	 * @param lowPriority This changes the natural ordering. True indicates it will always proceed an object marked false, and vice-versa.
	 * @throws IllegalArgumentException if token is null, token is empty, or handler is null
	 */
	public Replacer(final String token, final Handler handler, final boolean lowPriority) {
		if (token == null || token.length() == 0)
			throw new IllegalArgumentException("Token cannot be empty");
		if (handler == null)
			throw new IllegalArgumentException("Handler cannot be empty");
		this.token = token.substring(1);
		this.header = token.charAt(0);
		this.low = lowPriority;
		this.handler = handler;
	}

	public int compareTo(final Replacer that) {
		if (this.header != that.header)
			throw new IllegalArgumentException("Cannot compare " + this + " and " + that);
		if (this.low == that.low)
			return this.token.compareTo(that.token);
		return this.low ? 1 : -1;
	}

	@Override
	public String toString() {
		return getClass().getName() + "(" + token + " on " + header + (low ? '<' : '>');
	}
}
