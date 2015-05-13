package com.produban.bin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.math3.util.Pair;

import com.produban.api.general.Factory;
import com.produban.api.general.K;
import com.produban.api.manager.CacheManager;
import com.produban.util.HDFSUtils;

public class LoadCache {

	public static final String PATH_CENTRO = "/user/ingesta/PRO/landing/CENTRO/part-m-00000";
	public static final String PATH_EMPRESA_PRODUCT = "/user/ingesta/PRO/landing/EMPRESA_PRODUCT/part-m-00000";
	public static final String PATH_ENTIDAD_CREDITO = "/user/ingesta/PRO/landing/ENTIDAD_CREDITO/part-m-00000";
	public static final String PATH_ENT_CRED_EXT = "/user/ingesta/PRO/landing/ENT_CRED_EXT/part-m-00000";
	public static final String PATH_HH_DATOS_BANCOS = "/user/ingesta/PRO/landing/HH_DATOS_BANCOS/part-m-00000";
	public static final String PATH_HH_TRANSF_EMIT = "/user/ingesta/PRO/landing/HH_TRANSF_EMIT/part-m-00000";
	public static final String PATH_OFICI_BANCARIA = "/user/ingesta/PRO/landing/OFICI_BANCARIA/part-m-00000";
	public static final String PATH_OFI_ENT_EXT = "/user/ingesta/PRO/landing/OFI_ENT_EXT/part-m-00000";
	public static final String PATH_PAIS = "/user/ingesta/PRO/landing/PAIS/part-m-00000";
	public static final String PATH_PLAZA = "/user/ingesta/PRO/landing/PLAZA/part-m-00000";
	public static final String PATH_PROVINCIA = "/user/ingesta/PRO/landing/PROVINCIA/part-m-00000";

	enum Tables {
		CENTRO, EMPRESA_PRODUCT, ENTIDAD_CREDITO, ENT_CRED_EXT, HH_DATOS_BANCOS, HH_TRANSF_EMIT, OFICI_BANCARIA, OFI_ENT_EXT, PAIS, PLAZA, PROVINCIA
	}

	private List<Pair<String, String>> filterTables(final Tables table) {
		List<Pair<String, String>> filterLines = new ArrayList<>();
		List<String> lines;

		switch (table) {
		case CENTRO:
			break;
		case EMPRESA_PRODUCT:

			break;
		case ENTIDAD_CREDITO:
			lines = HDFSUtils.readHDFS(PATH_ENTIDAD_CREDITO);
			filterLines = filterList(lines, K.CACHE.TABLE_ENTIDAD_CREDITO_ID, 0, 7);
			break;
		case ENT_CRED_EXT:
			lines = HDFSUtils.readHDFS(PATH_ENT_CRED_EXT);
			filterLines = filterList(lines, K.CACHE.TABLE_ENT_CRED_EXT_ID, 0, 2);

			break;
		case HH_DATOS_BANCOS:

			break;
		case HH_TRANSF_EMIT:

			break;

		case OFICI_BANCARIA:

			break;

		case OFI_ENT_EXT:

			break;

		case PAIS:
			lines = HDFSUtils.readHDFS(PATH_PAIS);
			filterLines = filterList(lines, K.CACHE.TABLE_PAIS_ID, 0, 2);
			break;

		case PLAZA:

			break;

		case PROVINCIA:
			lines = HDFSUtils.readHDFS(PATH_PROVINCIA);
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

	public static void main(String[] args) {
		LoadCache loadCache = new LoadCache();
		CacheManager cacheManager = Factory.getCacheManager();
		

		for (Tables table : Tables.values()) {
			System.out.println("Reading from HDFS table:" + table); 
			List<Pair<String, String>> filterLines = loadCache
					.filterTables(table);
			System.out.println("Table name:" + table + " number records:" + filterLines.size());
			cacheManager.setBulkLoad(filterLines);
		}
	}
}
