package com.wolvereness.bluebutton.crash;

import static com.wolvereness.bluebutton.crash.Tokens.CAUSED_START;
import static com.wolvereness.bluebutton.crash.Tokens.DESCRIPTION_START;
import static com.wolvereness.bluebutton.crash.Tokens.DETAILS_START;
import static com.wolvereness.bluebutton.crash.Tokens.STACK_MORE_END;
import static com.wolvereness.bluebutton.crash.Tokens.STACK_MORE_START;
import static com.wolvereness.bluebutton.crash.Tokens.STACK_START;
import static com.wolvereness.bluebutton.crash.Tokens.START;
import static com.wolvereness.bluebutton.crash.Tokens.TIME_START;
import static com.wolvereness.bluebutton.crash.Tokens.WORLD;
import static com.wolvereness.bluebutton.crash.Tokens.WORLD_START;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.wolvereness.util.bluebutton.Splitter;
import com.wolvereness.util.bluebutton.StringMaker;

class ExceptionMessage implements Serializable, StringMaker.Appendable {
	private static final long serialVersionUID = -8052119166635775588L;
	private static final String[] TYPES = new String[] { "type", "message", "stack"};

	final String exceptionType;
	final String message;
	final List<StackTraceElement> stacks;

	ExceptionMessage(final List<String> description, final List<StackTraceElement> stacks) {
		this.stacks = ImmutableList.copyOf(stacks);
		final Iterator<String> it = description.iterator();
		final String exception = it.next();
		final int colon = exception.indexOf(':');
		if (colon != -1) {
			this.exceptionType = exception.substring(0, colon);
			final StringBuilder message = new StringBuilder(exception.substring(colon + 2));
			while(it.hasNext()) {
				message.append('\n').append(it.next());
			}
			this.message = message.toString();
		} else {
			this.exceptionType = exception;
			this.message = "";
		}
	}

	@Override
	public StringBuilder appendTo(final StringBuilder builder, final int indent) {
		return StringMaker.appendTo(
			TYPES,
			new Object[] {
				exceptionType,
				message,
				stacks
				},
			builder.append(ExceptionMessage.class.getName()),
			indent);
	}

	List<StackTraceElement> getStackTrace() {
		return stacks;
	}

	@Override
	public String toString() {
		return appendTo(new StringBuilder(), 1).toString();
	}

}

class Node implements Serializable, StringMaker.Appendable {
	private static final long serialVersionUID = -7456417988332538402L;
	private static final String[] TYPES = new String[] {"type"};

	private final Nodes type;

	Node(final Nodes type) {
		this.type = type;
	}

	@Override
	public StringBuilder appendTo(final StringBuilder builder, final int indent) {
		return StringMaker.appendTo(
			TYPES,
			new Object[] {
				type
				},
			builder.append(Node.class.getName()),
			indent);
	}

	@Override
	public String toString() {
		return appendTo(new StringBuilder(), 1).toString();
	}

}
//return StringMaker.appendTo(\n\t\t\t\t\t\tTYPES,\n\t\t\t\t\t\tnew Object[] {\n\t\t\t\t\t\t\t},\n\t\t\t\t\t\tsuper.appendTo(builder, indent).append('.').append(getClass().getName()),\n\t\t\t\t\t\tindent);

enum Nodes implements Serializable {
	_NA {
		@Override public Node makeNode(final String line, final List<String> data) {
			return new Node(this) {
				private static final long serialVersionUID = 1806744531754775486L;
				// TODO Auto-generated method stub
				private final String[] TYPES = new String[] {};

				@Override public StringBuilder appendTo(final StringBuilder builder, final int indent) {
					// TODO Auto-generated method stub
					return StringMaker.appendTo(
						TYPES,
						new Object[] {
							},
						super.appendTo(builder, indent).append(getClass().getName()),
						indent);
				}
			};
		}
	},
	CRAFTBUKKIT_INFORMATION {
		@Override public Node makeNode(final String line, final List<String> data) {
			return new Node(this) {
				private static final long serialVersionUID = -1238140494055183276L;
				// TODO Auto-generated method stub
				private final String[] TYPES = new String[] {};

				@Override public StringBuilder appendTo(final StringBuilder builder, final int indent) {
					// TODO Auto-generated method stub
					return StringMaker.appendTo(
						TYPES,
						new Object[] {
							},
						super.appendTo(builder, indent).append(getClass().getName()),
						indent);
				}
			};
		}
	},
	IS_MODDED {
		@Override public Node makeNode(final String line, final List<String> data) {
			return new Node(this) {
				private static final long serialVersionUID = -2978008995834097857L;
				// TODO Auto-generated method stub
				private final String[] TYPES = new String[] {};

				@Override public StringBuilder appendTo(final StringBuilder builder, final int indent) {
					// TODO Auto-generated method stub
					return StringMaker.appendTo(
						TYPES,
						new Object[] {
							},
						super.appendTo(builder, indent).append(getClass().getName()),
						indent);
				}
			};
		}
	},
	JAVA_VERSION {
		@Override public Node makeNode(final String line, final List<String> data) {
			return new Node(this) {
				private static final long serialVersionUID = 6469440502730283729L;
				// TODO Auto-generated method stub
				private final String[] TYPES = new String[] {};

				@Override public StringBuilder appendTo(final StringBuilder builder, final int indent) {
					// TODO Auto-generated method stub
					return StringMaker.appendTo(
						TYPES,
						new Object[] {
							},
						super.appendTo(builder, indent).append(getClass().getName()),
						indent);
				}
			};
		}
	},
	JAVA_VM_VERSION {
		@Override public Node makeNode(final String line, final List<String> data) {
			return new Node(this) {
				private static final long serialVersionUID = -3031947030091611359L;
				// TODO Auto-generated method stub
				private final String[] TYPES = new String[] {};

				@Override public StringBuilder appendTo(final StringBuilder builder, final int indent) {
					// TODO Auto-generated method stub
					return StringMaker.appendTo(
						TYPES,
						new Object[] {
							},
						super.appendTo(builder, indent).append(getClass().getName()),
						indent);
				}
			};
		}
	},
	JVM_FLAGS {
		@Override public Node makeNode(final String line, final List<String> data) {
			return new Node(this) {
				private static final long serialVersionUID = -7789346901121607201L;
				// TODO Auto-generated method stub
				private final String[] TYPES = new String[] {};

				@Override public StringBuilder appendTo(final StringBuilder builder, final int indent) {
					// TODO Auto-generated method stub
					return StringMaker.appendTo(
						TYPES,
						new Object[] {
							},
						super.appendTo(builder, indent).append(getClass().getName()),
						indent);
				}
			};
		}
	},
	MEMORY {
		@Override public Node makeNode(final String line, final List<String> data) {
			return new Node(this) {
				private static final long serialVersionUID = -5483966477648362518L;
				// TODO Auto-generated method stub
				private final String[] TYPES = new String[] {};

				@Override public StringBuilder appendTo(final StringBuilder builder, final int indent) {
					// TODO Auto-generated method stub
					return StringMaker.appendTo(
						TYPES,
						new Object[] {
							},
						super.appendTo(builder, indent).append(getClass().getName()),
						indent);
				}
			};
		}
	},
	MINECRAFT_VERSION {
		@Override public Node makeNode(final String line, final List<String> data) {
			return new Node(this) {
				private static final long serialVersionUID = 6272160377152734479L;
				// TODO Auto-generated method stub
				private final String[] TYPES = new String[] {};

				@Override public StringBuilder appendTo(final StringBuilder builder, final int indent) {
					// TODO Auto-generated method stub
					return StringMaker.appendTo(
						TYPES,
						new Object[] {
							},
						super.appendTo(builder, indent).append(getClass().getName()),
						indent);
				}
			};
		}
	},
	OPERATING_SYSTEM {
		@Override public Node makeNode(final String line, final List<String> data) {
			return new Node(this) {
				private static final long serialVersionUID = -3913076311178282768L;
				// TODO Auto-generated method stub
				private final String[] TYPES = new String[] {};

				@Override public StringBuilder appendTo(final StringBuilder builder, final int indent) {
					// TODO Auto-generated method stub
					return StringMaker.appendTo(
						TYPES,
						new Object[] {
							},
						super.appendTo(builder, indent).append(getClass().getName()),
						indent);
				}
			};
		}
	},
	PLAYER_COUNT {
		@Override public Node makeNode(final String line, final List<String> data) {
			return new Node(this) {
				private static final long serialVersionUID = -2655651225284524999L;
				// TODO Auto-generated method stub
				private final String[] TYPES = new String[] {};

				@Override public StringBuilder appendTo(final StringBuilder builder, final int indent) {
					// TODO Auto-generated method stub
					return StringMaker.appendTo(
						TYPES,
						new Object[] {
							},
						super.appendTo(builder, indent).append(getClass().getName()),
						indent);
				}
			};
		}
	},
	PROFILER_POSITION {
		@Override public Node makeNode(final String line, final List<String> data) {
			return new Node(this) {
				private static final long serialVersionUID = 5133277576735422736L;
				// TODO Auto-generated method stub
				private final String[] TYPES = new String[] {};

				@Override public StringBuilder appendTo(final StringBuilder builder, final int indent) {
					// TODO Auto-generated method stub
					return StringMaker.appendTo(
						TYPES,
						new Object[] {
							},
						super.appendTo(builder, indent).append(getClass().getName()),
						indent);
				}
			};
		}
	},
	TYPE {
		@Override public Node makeNode(final String line, final List<String> data) {
			return new Node(this) {
				private static final long serialVersionUID = 8823694713117783921L;
				// TODO Auto-generated method stub
				private final String[] TYPES = new String[] {};

				@Override public StringBuilder appendTo(final StringBuilder builder, final int indent) {
					// TODO Auto-generated method stub
					return StringMaker.appendTo(
						TYPES,
						new Object[] {
							},
						super.appendTo(builder, indent).append(getClass().getName()),
						indent);
				}
			};
		}
	},
	WORLD {
		@Override public Node makeNode(final String line, final List<String> data) {
			return new Node(this) {
				private static final long serialVersionUID = -2700511998935256883L;
				// TODO Auto-generated method stub
				private final String[] TYPES = new String[] {};

				@Override public StringBuilder appendTo(final StringBuilder builder, final int indent) {
					// TODO Auto-generated method stub
					return StringMaker.appendTo(
						TYPES,
						new Object[] {
							},
						super.appendTo(builder, indent).append(getClass().getName()),
						indent);
				}
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
public class Report implements Serializable, StringMaker.Appendable {
	private static final long serialVersionUID = 3476171284348297887L;
	private static final Pattern STACK_ELEMENT = Pattern.compile("^(\\tat )(([a-zA-Z_\\$]+\\.)+[A-Za-z_\\$]+)\\.([A-Za-z_\\$<>]+)\\(((.*):(\\d+)|Unknown Source)\\)$");
	private static final String[] TYPES = new String[] {"description","fun","nodes","exceptions","time",};

	static StackTraceElement toStackTraceElement(final String element) {
		final Matcher matcher = STACK_ELEMENT.matcher(element);
		if (!matcher.matches())
			throw new IllegalArgumentException("Invalid stack trace element: " + element);
		if (matcher.group(6) == null) return new StackTraceElement(matcher.group(2), matcher.group(4), null, -2);
		else
			return new StackTraceElement(matcher.group(2), matcher.group(4), matcher.group(6), Integer.parseInt(matcher.group(7)));
	}

	final String description;
	final ImmutableList<ExceptionMessage> exceptions;
	final String fun;
	final ImmutableMap<Nodes, Node> nodes;
	final Date time;

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
					if (description.size() != 0) {
						exceptions.add(new ExceptionMessage(description, stacks));
						description.clear();
						stacks.clear();
					}

					// New exception
					description.add(entry.substring(CAUSED_START.length()));
				} else {
					// Random description information
					description.add(entry);
				}
			}
			description.remove(description.size() - 1); // NOTE_0- There will be a blank line here, see NOTE_0- above
			if (description.size() != 0) {
				exceptions.add(new ExceptionMessage(description, stacks));
			}

			this.exceptions = ImmutableList.copyOf(exceptions);
		} // _STACK_

		while (!it.previous().startsWith(DESCRIPTION_START)) {} // This puts us on the line before the "description:"
		it.next(); // Push iterator for description_start to hit twice

		this.description = it.previous().substring(DESCRIPTION_START.length());

		{ // _TIMESTAMP_
			final String timeStamp = it.previous().substring(TIME_START.length());
			Date time = null;
			try {
				time = (Date) DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.parseObject(timeStamp);
			} catch (final ParseException ex) {
				try {
					time = new SimpleDateFormat().parse(timeStamp);
				} catch (final ParseException e) {
				}
			}
			this.time = time == null ? new Date(0l) : time;
		} // _TIMESTAMP_

		it.previous(); // Blank line after joke
		this.fun = it.previous();

		this.nodes = nodes.build();
	}

	@Override
	public StringBuilder appendTo(final StringBuilder builder, final int indent) {
		builder.append(Report.class.getName());
		return StringMaker.appendTo(
			TYPES,
			new Object[] {
				description,
				fun,
				nodes,
				exceptions,
				DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(time),
				},
			builder,
			indent);
	}

	@Override
	public String toString() {
		return appendTo(new StringBuilder(), 1).toString();
	}
}
