package com.produban.bin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.math3.util.Pair;

import com.produban.api.general.Factory;
import com.produban.api.general.K;
import com.produban.api.manager.CacheManager;
import com.produban.util.HDFSUtils;

public class LoadCache {

	private static final String PATH_CENTRO = "/user/ingesta/PRO/landing/CENTRO/part-m-00000";
	private static final String PATH_EMPRESA_PRODUCT = "/user/ingesta/PRO/landing/EMPRESA_PRODUCT/part-m-00000";
	private static final String PATH_ENTIDAD_CREDITO = "/user/ingesta/PRO/landing/ENTIDAD_CREDITO/part-m-00000";
	private static final String PATH_ENT_CRED_EXT = "/user/ingesta/PRO/landing/ENT_CRED_EXT/part-m-00000";	
	private static final String PATH_OFICI_BANCARIA = "/user/ingesta/PRO/landing/OFICI_BANCARIA/part-m-00000";
	private static final String PATH_OFI_ENT_EXT = "/user/ingesta/PRO/landing/OFI_ENT_EXT/part-m-00000";
	private static final String PATH_PAIS = "/user/ingesta/PRO/landing/PAIS/part-m-00000";
	private static final String PATH_PLAZA = "/user/ingesta/PRO/landing/PLAZA/part-m-00000";
	private static final String PATH_PROVINCIA = "/user/ingesta/PRO/landing/PROVINCIA/part-m-00000";

	private static final String FILE_CENTRO = "/usr/metrics/conf/loadCache/CENTRO";
	private static final String FILE_EMPRESA_PRODUCT = "/usr/metrics/conf/loadCache/EMPRESA_PRODUCT";
	private static final String FILE_ENTIDAD_CREDITO = "/usr/metrics/conf/loadCache/ENTIDAD_CREDITO";
	private static final String FILE_ENT_CRED_EXT = "/usr/metrics/conf/loadCache/ENT_CRED_EXT";	
	private static final String FILE_OFICI_BANCARIA = "/usr/metrics/conf/loadCache/OFICI_BANCARIA";
	private static final String FILE_OFI_ENT_EXT = "/usr/metrics/conf/loadCache/OFI_ENT_EXT";
	private static final String FILE_PAIS = "/usr/metrics/conf/loadCache/PAIS";
	private static final String FILE_PLAZA = "/usr/metrics/conf/loadCache/PLAZA";
	private static final String FILE_PROVINCIA = "/usr/metrics/conf/loadCache/PROVINCIA";

	enum Tables {
		CENTRO, EMPRESA_PRODUCT, ENTIDAD_CREDITO, ENT_CRED_EXT, HH_DATOS_BANCOS, HH_TRANSF_EMIT, OFICI_BANCARIA, OFI_ENT_EXT, PAIS, PLAZA, PROVINCIA
	}

	private List<String> readLines(final String pathHFFS, final String fileConf)
			throws IOException {
		List<String> lines = Collections.emptyList();
		File file = new File(fileConf);

		long lastModification = HDFSUtils.lastModification(pathHFFS);
		boolean older = FileUtils.isFileOlder(file, lastModification);

		if (!file.exists() || older) {
			lines = HDFSUtils.readHDFS(pathHFFS);
			FileUtils.writeStringToFile(file, Long.toString(lastModification));
		}
		return lines;
	}

	private List<Pair<String, String>> filterTables(final Tables table)
			throws IOException {
		List<Pair<String, String>> filterLines = new ArrayList<>();
		List<String> lines;

		switch (table) {
		case CENTRO:
			lines = readLines(PATH_CENTRO, FILE_CENTRO);
			// filterLines = filterList(lines, K.CACHE.TABLE_CENTRO_ID, x, x);
			break;
		case EMPRESA_PRODUCT:
			lines = readLines(PATH_EMPRESA_PRODUCT, FILE_EMPRESA_PRODUCT);
			// filterLines = filterList(lines, K.CACHE.TABLE_EMPRESA_PRODUCT_ID,
			// x, x);
			break;
		case ENTIDAD_CREDITO:
			lines = readLines(PATH_ENTIDAD_CREDITO, FILE_ENTIDAD_CREDITO);
			filterLines = filterList(lines, K.CACHE.TABLE_ENTIDAD_CREDITO_ID,
					0, 7);
			break;
		case ENT_CRED_EXT:
			lines = readLines(PATH_ENT_CRED_EXT, FILE_ENT_CRED_EXT);
			filterLines = filterList(lines, K.CACHE.TABLE_ENT_CRED_EXT_ID, 0, 2);

			break;		

		case OFICI_BANCARIA:
			int[] indOfiBank = { 0, 1 };
			lines = readLines(PATH_OFICI_BANCARIA, FILE_OFICI_BANCARIA);
			filterLines = filterList(lines, K.CACHE.TABLE_OFICI_BANCARIA_ID,
					indOfiBank, 2);
			break;

		case OFI_ENT_EXT:
			int[] indOfiExt = { 0, 1 };
			lines = readLines(PATH_OFI_ENT_EXT, FILE_OFI_ENT_EXT);
			filterLines = filterList(lines, K.CACHE.TABLE_OFI_ENT_EXT_ID,
					indOfiExt, 5);
			break;

		case PAIS:
			lines = readLines(PATH_PAIS, FILE_PAIS);
			filterLines = filterList(lines, K.CACHE.TABLE_PAIS_ID, 0, 2);
			break;

		case PLAZA:
			lines = readLines(PATH_PLAZA, FILE_PLAZA);
			filterLines = filterList(lines, K.CACHE.TABLE_PLAZA_ID, 0, 3);
			break;

		case PROVINCIA:
			lines = readLines(PATH_PROVINCIA, FILE_PROVINCIA);
			filterLines = filterList(lines, K.CACHE.TABLE_PROVINCIA_ID, 0, 3);
			break;

		default:
			break;
		}

		return filterLines;

	}

	private List<Pair<String, String>> filterList(final List<String> lines,
			final String prefixId, final int key, final int value) {
		List<Pair<String, String>> filterLine = new ArrayList<>();

		for (String line : lines) {
			String[] splits = StringUtils.split(line, '|');
			Pair<String, String> pair = new Pair<String, String>(prefixId
					+ splits[key].trim(), splits[value].trim());
			filterLine.add(pair);
		}
		return filterLine;
	}

	private List<Pair<String, String>> filterList(final List<String> lines,
			final String prefixId, final int[] keys, final int value) {
		List<Pair<String, String>> filterLine = new ArrayList<>();

		for (String line : lines) {
			String[] splits = StringUtils.split(line, '|');
			String keyFinal = "";
			for (int indexKey : keys) {
				keyFinal += splits[indexKey].trim();
			}

			Pair<String, String> pair = new Pair<String, String>(prefixId
					+ keyFinal.trim(), splits[value].trim());
			filterLine.add(pair);
		}
		return filterLine;
	}

	public static void main(String[] args) throws IOException {
		LoadCache loadCache = new LoadCache();
		CacheManager cacheManager = Factory.getCacheManager();

		for (Tables table : Tables.values()) {
			System.out.println("Reading from HDFS table:" + table);
			List<Pair<String, String>> filterLines = loadCache
					.filterTables(table);
			System.out.println("Table name:" + table + " number records:"
					+ filterLines.size());
			cacheManager.setBulkLoad(filterLines);
		}
	}
}
