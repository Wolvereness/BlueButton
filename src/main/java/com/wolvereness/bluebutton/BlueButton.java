package com.wolvereness.bluebutton;

import java.io.IOException;
import java.util.Properties;

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
public final class BlueButton {
	/**
	 * This is the current version of BlueButton
	 */
	public static final Version version;

	static {
		final Properties properties = new Properties();
		try {
			properties.load(BlueButton.class.getClassLoader().getResourceAsStream("git.properties"));
			version = new Version(properties);
		} catch (final IOException e) {
			throw new Error(e);
		}
	}

	public static void main(final String[] args) throws Throwable {
		// TODO Auto-generated method stub
	}
}
