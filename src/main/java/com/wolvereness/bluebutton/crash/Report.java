package com.wolvereness.bluebutton.crash;

import static com.wolvereness.bluebutton.crash.Tokens.CAUSED_START;
import static com.wolvereness.bluebutton.crash.Tokens.DESCRIPTION_START;
import static com.wolvereness.bluebutton.crash.Tokens.DETAILS_START;
import static com.wolvereness.bluebutton.crash.Tokens.STACK_MORE_END;
import static com.wolvereness.bluebutton.crash.Tokens.STACK_MORE_START;
import static com.wolvereness.bluebutton.crash.Tokens.STACK_START;
import static com.wolvereness.bluebutton.crash.Tokens.START;
import static com.wolvereness.bluebutton.crash.Tokens.WORLD;
import static com.wolvereness.bluebutton.crash.Tokens.WORLD_START;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.wolvereness.util.bluebutton.Splitter;

class ExceptionMessage implements Serializable {
	private static final long serialVersionUID = -8052119166635775588L;

	final String exceptionType;
	final String message;
	final List<StackTraceElement> stacks;
	ExceptionMessage(final List<String> description, final List<StackTraceElement> stacks) {
		this.stacks = ImmutableList.copyOf(stacks);
		final Iterator<String> it = description.iterator();
		final String exception = it.next();
		final int colon = exception.indexOf(':');
		this.exceptionType = exception.substring(0, colon);
		final StringBuilder message = new StringBuilder(exception.substring(colon + 2));
		while(it.hasNext()) {
			message.append('\n').append(it.next());
		}
		this.message = message.toString();
	}

	List<StackTraceElement> getStackTrace() {
		return stacks;
	}

}

class Node {

}

enum Nodes {
	_NA {
		@Override public Node makeNode(final String line, final List<String> data) {
			return new Node() {
				// TODO Auto-generated method stub
			};
		}
	},
	CRAFTBUKKIT_INFORMATION {
		@Override public Node makeNode(final String line, final List<String> data) {
			return new Node() {
				// TODO Auto-generated method stub
			};
		}
	},
	IS_MODDED {
		@Override public Node makeNode(final String line, final List<String> data) {
			return new Node() {
				// TODO Auto-generated method stub
			};
		}
	},
	JAVA_VERSION {
		@Override public Node makeNode(final String line, final List<String> data) {
			return new Node() {
				// TODO Auto-generated method stub
			};
		}
	},
	JAVA_VM_VERSION {
		@Override public Node makeNode(final String line, final List<String> data) {
			return new Node() {
				// TODO Auto-generated method stub
			};
		}
	},
	JVM_FLAGS {
		@Override public Node makeNode(final String line, final List<String> data) {
			return new Node() {
				// TODO Auto-generated method stub
			};
		}
	},
	MEMORY {
		@Override public Node makeNode(final String line, final List<String> data) {
			return new Node() {
				// TODO Auto-generated method stub
			};
		}
	},
	MINECRAFT_VERSION {
		@Override public Node makeNode(final String line, final List<String> data) {
			return new Node() {
				// TODO Auto-generated method stub
			};
		}
	},
	OPERATING_SYSTEM {
		@Override public Node makeNode(final String line, final List<String> data) {
			return new Node() {
				// TODO Auto-generated method stub
			};
		}
	},
	PLAYER_COUNT {
		@Override public Node makeNode(final String line, final List<String> data) {
			return new Node() {
				// TODO Auto-generated method stub
			};
		}
	},
	PROFILER_POSITION {
		@Override public Node makeNode(final String line, final List<String> data) {
			return new Node() {
				// TODO Auto-generated method stub
			};
		}
	},
	TYPE {
		@Override public Node makeNode(final String line, final List<String> data) {
			return new Node() {
				// TODO Auto-generated method stub
			};
		}
	},
	WORLD {
		@Override public Node makeNode(final String line, final List<String> data) {
			return new Node() {
				// TODO Auto-generated method stub
			};
		}
	};

	public abstract Node makeNode(String line, List<String> data);
}

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
public class Report {
	private static final Pattern STACK_ELEMENT = Pattern.compile("^(\\tat )(([a-zA-Z]+\\.)+[A-Za-z]+)\\.([A-Za-z]+)\\((.*):(\\d+)\\)$");

	static StackTraceElement toStackTraceElement(final String element) {
		final Matcher matcher = STACK_ELEMENT.matcher(element);
		if (!matcher.matches())
			throw new IllegalArgumentException("Invalid stack trace element: " + element);
		try {
			return new StackTraceElement(matcher.group(2), matcher.group(4), matcher.group(5), Integer.parseInt(matcher.group(6)));
		} catch (final NumberFormatException ex) {
			throw new IllegalArgumentException("Invalid stack trace element: " + element, ex);
		}
	}

	final ImmutableMap<Nodes, Node> nodes;

	public Report(final String text) {
		final ArrayList<String> lines;
		{ // _LINES_
			int i;
			if ((i = text.indexOf('\r')) != -1) {
				if (text.startsWith("\r\n", i)) { // StartsWith prevents OOB exception
					lines = Splitter.splitAll(text, "\r\n");
				} else {
					lines = Splitter.splitAll(text, '\r');
				}
			} else {
				lines = Splitter.splitAll(text, '\n');
			}
		} // _LINES_

		final ImmutableMap.Builder<Nodes, Node> nodes = ImmutableMap.builder();
		final ListIterator<String> it = lines.listIterator(lines.size() - 1);

		{ // _DETAILS_
			int count = 0; // Number of lines with no "- " for sub-listing
			while (true) {
				String entry = it.previous();
				if (entry.startsWith(START)) {
					if (entry.startsWith(WORLD, 2)) {
						continue; // Process this at end
					}

					final int colon = entry.indexOf(':');
					Nodes type = Nodes.valueOf(entry.substring(2, colon).toUpperCase().replace(' ', '_'));
					if (type == null) {
						type = Nodes._NA;
					}
					final List<String> subList = lines.subList(it.nextIndex(), it.nextIndex() + count);
					final Node node = type.makeNode(entry.substring(colon + 1), subList);
					nodes.put(type, node);

					count = 0; // Reset count, as it is used for sub-listing
				} else if (entry.equals(DETAILS_START)) {
					{
						final ArrayList<String> worlds = new ArrayList<String>();
						while (it.hasNext()) {
							entry = it.next();
							if (entry.startsWith(WORLD_START)) {
								worlds.add(entry);
							}
						}
						nodes.put(Nodes.WORLD, Nodes.WORLD.makeNode(null, worlds));
					}

					while (!it.previous().equals(DETAILS_START)) {}
					if (!it.previous().equals("")) // NOTE_0- blank line preceding details check, see NOTE_0- below
						throw new IllegalStateException("Expected blank line in " + lines.toString());
					while (!it.previous().startsWith(DESCRIPTION_START)) {}
					it.next(); // Description_start
					it.next(); // Blank line
					break; // We're done in the loop
				} else {
					count++;
				}
			}
		} // _DETAILS_

		{ // _STACK_
			final LinkedList<ExceptionMessage> exceptions = new LinkedList<ExceptionMessage>();
			final List<StackTraceElement> stacks = new ArrayList<StackTraceElement>();
			final List<String> description = new ArrayList<String>();
			description.add(it.next()); // Initialize; first line is always first exception
			for (String entry = it.next(); !entry.equals(DETAILS_START); entry = it.next()) {
				// Read in all the exception information.
				// Apocalypse if the formating changes...
				if (entry.startsWith(STACK_START)) {
					// Normal stack element
					stacks.add(toStackTraceElement(entry));
				} else if (entry.startsWith(STACK_MORE_START)) {
					// "... n more" final line
					final ExceptionMessage previous = exceptions.getLast();
					final List<StackTraceElement> previousTrace = previous.getStackTrace();
					entry = entry.substring(STACK_MORE_START.length(), entry.length() - STACK_MORE_END.length());
					final int stackCount = Integer.valueOf(entry);
					stacks.addAll(previousTrace.subList(previousTrace.size() - stackCount, previousTrace.size()));
					exceptions.add(new ExceptionMessage(description, stacks));

					// Reset our counters
					description.clear();
					stacks.clear();
				} else if (entry.startsWith(CAUSED_START)) {
					// Finish old exception
					exceptions.add(new ExceptionMessage(description, stacks));
					description.clear();
					stacks.clear();

					// New exception
					description.add(entry.substring(CAUSED_START.length()));
				} else {
					// Random description information
					description.add(entry);
				}
			}
			description.remove(description.size() - 1); // NOTE_0- There will be a blank line here, see NOTE_0- above
			exceptions.add(new ExceptionMessage(description, stacks));
		} // _STACK_

		while (!it.previous().startsWith(DESCRIPTION_START)) {} // This puts us on the line before the "description:"

		// TODO: add description
		// TODO: add timestamp
		// TODO: add joke

		this.nodes = nodes.build();
	}
}
