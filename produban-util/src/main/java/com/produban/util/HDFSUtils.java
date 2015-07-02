package com.produban.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.log4j.Logger;

/**
 * Utilities for HDFS.
 */
public final class HDFSUtils {

	/**
	 * Instance of the log object.
	 */
	private static final Logger LOG = Logger.getLogger(HDFSUtils.class);

	/**
	 * Private constructor, only static methods.
	 */
	private HDFSUtils() {
	}

	/**
	 * 
	 * @param path
	 * @return
	 */
	public static List<String> readHDFS(final String pathFile) {
		List<String> lines = new ArrayList<String>();
		try {
			Path path = new Path(pathFile);
			Configuration conf = new Configuration();
			FileSystem fileSystem = FileSystem.get(conf);

			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(fileSystem.open(path)));
			String line = bufferedReader.readLine();
			while (line != null) {
				lines.add(line);
				line = bufferedReader.readLine();
			}
		} catch (IOException e) {
			LOG.error("Error reading file " + pathFile, e);
		}
		return lines;
	}

	public static long lastModification(final String pathFile) {
		long modificationTime = 0l;
		try {
			Path path = new Path(pathFile);
			Configuration conf = new Configuration();
			FileSystem fileSystem = FileSystem.get(conf);

			FileStatus status = fileSystem.getFileStatus(path);
			modificationTime = status.getModificationTime();

		} catch (IOException e) {
			LOG.error("Error reading file " + pathFile, e);
		}
		return modificationTime;
	}

	public static List<String> readHDFS(final String pathFile,
			final String principal, final String ticketKerberosPath) {
		List<String> lines = new ArrayList<String>();
		try {
			Path path = new Path(pathFile);
			Configuration conf = new Configuration();
			conf.set("hadoop.security.authentication", "Kerberos");
			UserGroupInformation.setConfiguration(conf);
			UserGroupInformation.loginUserFromKeytab(principal,
					ticketKerberosPath);
			FileSystem fileSystem = FileSystem.get(conf);

			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(fileSystem.open(path)));
			String line = bufferedReader.readLine();
			while (line != null) {
				lines.add(line);
				line = bufferedReader.readLine();
			}
		} catch (IOException e) {
			LOG.error("Error reading file " + pathFile, e);
		}
		return lines;
	}

	public static long lastModification(final String pathFile,
			final String principal, final String ticketKerberosPath) {
		long modificationTime = 0l;
		try {
			Path path = new Path(pathFile);
			Configuration conf = new Configuration();
			conf.set("hadoop.security.authentication", "Kerberos");
			UserGroupInformation.setConfiguration(conf);
			UserGroupInformation.loginUserFromKeytab(principal,
					ticketKerberosPath);			
			// UserGroupInformation.loginUserFromKeytab("example_user@IBM.COM",
			// "/path/to/example_user.keytab");

			FileSystem fileSystem = FileSystem.get(conf);

			FileStatus status = fileSystem.getFileStatus(path);
			modificationTime = status.getModificationTime();

		} catch (IOException e) {
			LOG.error("Error reading file " + pathFile, e);
		}
		return modificationTime;
	}

}
