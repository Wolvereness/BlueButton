package com.wolvereness.util.bluebutton;

import java.util.ArrayList;

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
public class Splitter {

	/**
	 * This method uses an efficient algorithm for creating a list of sub strings using the character token as a delimiter.
	 * @param string String to split
	 * @param c Character token to use as a delimiter
	 * @return An ArrayList of the substrings delimited by the token
	 * @throws NullPointerException if base string is null
	 */
	public static ArrayList<String> splitAll(final String string, final char c) {
		int p = 0, i = string.indexOf(c);
		if (i == -1) {
			// No match short
			final ArrayList<String> out = new ArrayList<String>(1);
			out.add(string);
			return out;
		}

		final ArrayList<String> out = new ArrayList<String>();

		do {
			out.add(string.substring(p, i));
		} while ((i = string.indexOf(c, p = i + 1)) != -1);

		// Final delimited string
		out.add(string.substring(p));
		return out;
	}


	/**
	 * This method uses an efficient algorithm for creating a list of sub strings using the string token as a delimiter.
	 * @param string String to split
	 * @param token String token to use as a delimiter
	 * @return An ArrayList of the substrings delimited by the token
	 * @throws NullPointerException if base string is null
	 * @throws IllegalArgumentException if token is null
	 */
	public static ArrayList<String> splitAll(final String string, final String token) {
		if (token == null)
			throw new IllegalArgumentException("Cannot split on occurences of null search string");

		final int length = token.length();
		if (length == 0) {
			// Split all characters into strings
			final int stringLength = string.length();
			final ArrayList<String> out = new ArrayList<String>(stringLength);
			int i = 0;
			while (i < stringLength) {
				out.add(string.substring(i, ++i));
			}
			return out;
		}
		if (length == 1) // Delegate to other method for more efficient split
			return splitAll(string, token.charAt(0));

		int p = 0, i = string.indexOf(token);
		if (i == -1) {
			// No match short
			final ArrayList<String> out = new ArrayList<String>(1);
			out.add(string);
			return out;
		}

		final ArrayList<String> out = new ArrayList<String>();
		do {
			out.add(string.substring(p, i));
		} while ((i = string.indexOf(token, p = i + length)) != -1);

		// Final delimited string
		out.add(string.substring(p));
		return out;
	}

	private Splitter() {}
}
