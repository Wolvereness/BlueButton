package com.wolvereness.bluebutton.crash;

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
class Tokens {
	public static final String CAUSED_START;
	public static final String DESCRIPTION_START;
	public static final String DETAILS_START;
	public static final String STACK_MORE_END;
	public static final String STACK_MORE_START;
	public static final String STACK_START;
	public static final String START;
	public static final String WORLD;
	public static final String WORLD_START;

	static {
		DETAILS_START = "Relevant Details:";
		WORLD_START = "- World ";
		WORLD = WORLD_START.substring(2);
		START = "- ";
		STACK_START = "\tat ";
		STACK_MORE_START = "\t... ";
		STACK_MORE_END = " more";
		CAUSED_START = "Caused by: ";
		DESCRIPTION_START = "Description: ";
	}
}
