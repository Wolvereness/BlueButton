package com.wolvereness.bluebutton;

import java.io.UnsupportedEncodingException;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.lang3.exception.ContextedRuntimeException;

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
public class Version {

	private static String getProperty(final Properties properties, final String property) {
		final Object obj = properties.get(property);
		if (obj == null)
			throw new NullPointerException(property);
		return obj.toString();
	}

	private final String branch;
	private final String buildTime;
	private final String buildUserEmail;
	private final String buildUserName;
	private final String commitId;
	private final String commitMessageFull;
	private final String commitMessageShort;
	private final String commitTime;
	private final String commitUserEmail;
	private final String commitUserName;
	private final String describe;

	/**
	 * @param properties The properties to load from
	 * @throws UnsupportedEncodingException If UTF8 isn't supported
	 */
	public Version(final Properties properties) throws UnsupportedEncodingException
	{
		try {
			this.branch = getProperty(properties, "git.branch");
			this.buildTime = getProperty(properties, "git.build.time");
			this.buildUserEmail = getProperty(properties, "git.build.user.email");
			this.buildUserName = getProperty(properties, "git.build.user.name");
			this.commitId = getProperty(properties, "git.commit.id");
			this.commitMessageFull = getProperty(properties, "git.commit.message.full");
			this.commitMessageShort = getProperty(properties, "git.commit.message.short");
			this.commitTime = getProperty(properties, "git.commit.time");
			this.commitUserEmail = getProperty(properties, "git.commit.user.email");
			this.commitUserName = getProperty(properties, "git.commit.user.name");
			this.describe = getProperty(properties, "git.commit.id.describe");
		} catch (final Throwable t) {
			final ContextedRuntimeException ex = new ContextedRuntimeException("Failed to read properties", t);
			for (final Entry<Object, Object> entry : properties.entrySet()) {
				ex.addContextValue(entry.getKey() == null ? null : entry.getKey().toString(), entry.getValue());
			}
			throw ex;
		}
	}

	/**
	 * @return the branch
	 */
	public String getBranch() {
		return branch;
	}

	/**
	 * @return the buildTime
	 */
	public String getBuildTime() {
		return buildTime;
	}

	/**
	 * @return the buildUserEmail
	 */
	public String getBuildUserEmail() {
		return buildUserEmail;
	}

	/**
	 * @return the buildUserName
	 */
	public String getBuildUserName() {
		return buildUserName;
	}

	/**
	 * @return the commitId
	 */
	public String getCommitId() {
		return commitId;
	}

	/**
	 * @return the commitMessageFull
	 */
	public String getCommitMessageFull() {
		return commitMessageFull;
	}

	/**
	 * @return the commitMessageShort
	 */
	public String getCommitMessageShort() {
		return commitMessageShort;
	}

	/**
	 * @return the commitTime
	 */
	public String getCommitTime() {
		return commitTime;
	}

	/**
	 * @return the commitUserEmail
	 */
	public String getCommitUserEmail() {
		return commitUserEmail;
	}

	/**
	 * @return the commitUserName
	 */
	public String getCommitUserName() {
		return commitUserName;
	}

	/**
	 * @return the describe
	 */
	public String getDescribe() {
		return describe;
	}

	@Override
	public String toString() {
		return String.format(
			"Version [\n" +
				"\tbranch=%s,\n" +
				"\tbuildTime=%s,\n" +
				"\tbuildUserEmail=%s,\n" +
				"\tbuildUserName=%s,\n" +
				"\tcommitId=%s,\n" +
				"\tcommitMessageFull=%s,\n" +
				"\tcommitMessageShort=%s,\n" +
				"\tcommitTime=%s,\n" +
				"\tcommitUserEmail=%s,\n" +
				"\tcommitUserName=%s,\n" +
				"\tdescribe=%s\n" +
				"\t]",
			branch,
			buildTime,
			buildUserEmail,
			buildUserName,
			commitId,
			commitMessageFull,
			commitMessageShort,
			commitTime,
			commitUserEmail,
			commitUserName,
			describe);
	}
}
