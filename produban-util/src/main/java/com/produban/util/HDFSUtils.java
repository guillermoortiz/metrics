package com.produban.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
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
	 * Constant for buffer size.
	 */
	public static final int BUFFER_SIZE = 1024;

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
			e.printStackTrace();
		}
		return lines;
	}

	

	public static void main(String[] args) throws URISyntaxException {
		List<String> file = HDFSUtils
		//		.readHDFS("/tmp/b.txt");
		 .readHDFS("/user/ingesta/PRO/landing/PAIS/part-m-00000");

	}

}
