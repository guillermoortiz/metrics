package com.produban.bin;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.math3.util.Pair;
import org.apache.log4j.Logger;

import com.produban.api.general.Factory;
import com.produban.api.general.K;
import com.produban.api.manager.CacheManager;
import com.produban.util.HDFSUtils;

public class LoadCache {
	private static final Logger LOG = Logger.getLogger(LoadCache.class);

	private static final String PATH_GENERIC = "/pro/landing/tablas_generales/";
	private static final String PATH_DATE = "/fecha_carga=";
	private static final String PATH_PART = "/part-m-00000";

	/*
	 * private static final String PATH_CENTRO = PATH_GENERIC + "centro" +
	 * PATH_DATE; private static final String PATH_EMPRESA_PRODUCT =
	 * PATH_GENERIC + "empresa_product" + PATH_DATE; private static final String
	 * PATH_ENTIDAD_CREDITO = PATH_GENERIC + "entidad_credito" + PATH_DATE;
	 * private static final String PATH_ENT_CRED_EXT = PATH_GENERIC +
	 * "ent_cred_ext" + PATH_DATE; private static final String
	 * PATH_OFICI_BANCARIA = PATH_GENERIC + "ofici_bancaria" + PATH_DATE;
	 * private static final String PATH_OFI_ENT_EXT = PATH_GENERIC +
	 * "ofi_ent_ext" + PATH_DATE; private static final String PATH_PAIS =
	 * PATH_GENERIC + "pais" + PATH_DATE; private static final String PATH_PLAZA
	 * = PATH_GENERIC + "plaza" + PATH_DATE; private static final String
	 * PATH_PROVINCIA = PATH_GENERIC + "provincia" + PATH_DATE;
	 */
	private static final String PATH_PAIS = "/tmp/pais.tabla";
	private static final String PATH_PLAZA = "/pro/landing/tablas_generales/plaza/fecha_carga=20150625/part-m-00000";
	private static final String PATH_PROVINCIA = "/tmp/provincia.tabla";
	private static final String PATH_CENTRO = "/tmp/centro.tabla";
	private static final String PATH_EMPRESA_PRODUCT = "/tmp/empresa_product.tabla";
	private static final String PATH_ENTIDAD_CREDITO = "/tmp/entidad_credito.tabla";
	private static final String PATH_ENT_CRED_EXT = "/tmp/ent_cred_ext.tabla";
	private static final String PATH_OFICI_BANCARIA = "/tmp/ofici_bancaria.tabla";
	private static final String PATH_OFI_ENT_EXT = "/tmp/ofi_ent_ext.tabla";	
//	private static final String PATH_PAIS = "/pro/landing/tablas_generales/pais/fecha_carga=20150625/part-m-00000";
//	private static final String PATH_PLAZA = "/pro/landing/tablas_generales/plaza/fecha_carga=20150625/part-m-00000";
//	private static final String PATH_PROVINCIA = "/pro/landing/tablas_generales/provincia/fecha_carga=20150625/part-m-00000";
//	private static final String PATH_CENTRO = "/pro/landing/tablas_generales/centro/fecha_carga=20150625/part-m-00000";	
//	private static final String PATH_EMPRESA_PRODUCT = "/pro/landing/tablas_generales/empresa_product/fecha_carga=20150625/part-m-00000";
//	private static final String PATH_ENTIDAD_CREDITO = "/pro/landing/tablas_generales/entidad_credito/fecha_carga=20150625/part-m-00000";
//	private static final String PATH_ENT_CRED_EXT = "/pro/landing/tablas_generales/ent_cred_ext/fecha_carga=20150625/part-m-00000";
//	private static final String PATH_OFICI_BANCARIA = "/pro/landing/tablas_generales/ofici_bancaria/fecha_carga=20150625/part-m-00000";
//	private static final String PATH_OFI_ENT_EXT = "/pro/landing/tablas_generales/ofi_ent_ext/fecha_carga=20150625/part-m-00000";	
	private static final String PATH_COORDENADAS_NACIONALES = "/opt/metrics/conf/locations/localCities.csv";
	private static final String PATH_COORDENADAS_INTERNACIONALES = "/opt/metrics/conf/locations/internationalCities.csv";

//	 private static final String PATH_COORDENADAS_NACIONALES =
//	 "c:\\usr\\metrics\\conf\\locations\\localCities.csv";
//
//	 private static final String PATH_COORDENADAS_INTERNACIONALES =
//	 "c:\\usr\\metrics\\conf\\locations\\internationalCities.csv";

	private static final String FILE_CENTRO = "/opt/metrics/conf/loadCache/CENTRO";
	private static final String FILE_EMPRESA_PRODUCT = "/opt/metrics/conf/loadCache/EMPRESA_PRODUCT";
	private static final String FILE_ENTIDAD_CREDITO = "/opt/metrics/conf/loadCache/ENTIDAD_CREDITO";
	private static final String FILE_ENT_CRED_EXT = "/opt/metrics/conf/loadCache/ENT_CRED_EXT";
	private static final String FILE_OFICI_BANCARIA = "/opt/metrics/conf/loadCache/OFICI_BANCARIA";
	private static final String FILE_OFI_ENT_EXT = "/opt/metrics/conf/loadCache/OFI_ENT_EXT";
	private static final String FILE_PAIS = "/opt/metrics/conf/loadCache/PAIS";
	private static final String FILE_PLAZA = "/opt/metrics/conf/loadCache/PLAZA";
	private static final String FILE_PROVINCIA = "/opt/metrics/conf/loadCache/PROVINCIA";
	private static final String FILE_COORDENADAS_NACIONALES = "/opt/metrics/conf/loadCache/COORDENADAS_NACIONALES";
	private static final String FILE_COORDENADAS_INTERNACIONALES = "/opt/metrics/conf/loadCache/COORDENADAS_INTERNACIONALES";

	private String principal;
	private String pathKeytab;

	enum Tables {
		CENTRO, EMPRESA_PRODUCT, ENTIDAD_CREDITO, ENT_CRED_EXT, OFICI_BANCARIA, OFI_ENT_EXT, PAIS, PLAZA, PROVINCIA, COORDENADAS_NACIONALES, COORDENADAS_INTERNACIONALES
	}

	public LoadCache(String principal, String path) {
		this.principal = principal;
		this.pathKeytab = path;
	}

	private List<String> readLines(final String pathHFFS, final String fileConf)
			throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date today = new Date();
		String yesterday = sdf.format(new Date(
				today.getTime() - 24 * 3600 * 1000l));

		String finalPath = pathHFFS; // + yesterday + PATH_PART;

		List<String> lines = Collections.emptyList();
		File file = new File(fileConf);

//		long lastModification = HDFSUtils.lastModification(finalPath,
//				principal, pathKeytab);
		long lastModification = HDFSUtils.lastModification(finalPath);
		boolean older = FileUtils.isFileOlder(file, lastModification);

		if (!file.exists() || older) {
			//lines = HDFSUtils.readHDFS(finalPath, principal, pathKeytab);
			lines = HDFSUtils.readHDFS(finalPath);
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
			int[] indCenter = { 0, 1 };
			int[] valueCenter = { 4, 12 };
			lines = readLines(PATH_CENTRO, FILE_CENTRO);
			filterLines = filterList(lines, K.CACHE.TABLE_CENTRO_ID, indCenter,
					valueCenter);
			break;
		case EMPRESA_PRODUCT:
			// TODO revisar el codigo 10, no se si es el correcta para 0049211
			// -> creditos generales
			int[] indEmpPro = { 0, 1 };
			lines = readLines(PATH_EMPRESA_PRODUCT, FILE_EMPRESA_PRODUCT);
			filterLines = filterList(lines, K.CACHE.TABLE_EMPRESA_PRODUCT_ID,
					indEmpPro, 10);
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

			// key: OB:codBanco+codSucursal
			// value: nombrePoblacion|codProvincia|codPais
			int[] keyOB = { 0, 1 };
			int[] valueOB = { 2, 13, 14 };
			lines = readLines(PATH_OFICI_BANCARIA, FILE_OFICI_BANCARIA);
			filterLines = filterList(lines, K.CACHE.TABLE_OFICI_BANCARIA_ID,
					keyOB, valueOB);
			break;

		case OFI_ENT_EXT:
			// key: OEE:codBanco+codSucursal
			// value: nombrePoblacion|codPais
			int[] keyOEE = { 0, 1 };
			int[] valueOEE = { 5, 13 };
			lines = readLines(PATH_OFI_ENT_EXT, FILE_OFI_ENT_EXT);
			filterLines = filterList(lines, K.CACHE.TABLE_OFI_ENT_EXT_ID,
					keyOEE, valueOEE);
			break;

		case PAIS:
			// key: P:codPais
			// value: pais
			lines = readLines(PATH_PAIS, FILE_PAIS);
			filterLines = filterList(lines, K.CACHE.TABLE_PAIS_ID, 0, 2);
			break;

		// TODO Revisar porque parece que los registros de HDFS vienen mal.
		// case PLAZA:
		// lines = readLines(PATH_PLAZA, FILE_PLAZA);
		// filterLines = filterList(lines, K.CACHE.TABLE_PLAZA_ID, 0, 3);
		// break;

		case PROVINCIA:
			// key: PV:codPais+codProvincia
			// value: provincia
			int[] keyPV = { 0 };
			int[] valuePV = { 3, 10 };
			lines = readLines(PATH_PROVINCIA, FILE_PROVINCIA);
			filterLines = filterList(lines, K.CACHE.TABLE_PROVINCIA_ID, keyPV,
					valuePV);
			break;

		// TODO Now, this files are loaded all times. Fix and check if it's
		// necessary to load.
		case COORDENADAS_NACIONALES:
			// key: COE:NombreMunicipio
			// value: latitud,longitud
			int keyC = 0;
			int valueC = 1;
			File alreadyLoad = new File(FILE_COORDENADAS_NACIONALES);
			if (!alreadyLoad.exists()) {
				lines = FileUtils.readLines(new File(
						PATH_COORDENADAS_NACIONALES));
				filterLines = filterListCoor(lines,
						K.CACHE.TABLE_COORDENADAS_NACIONALES_ID, keyC, valueC);
				FileUtils.writeStringToFile(alreadyLoad, "");
			}
			break;

		case COORDENADAS_INTERNACIONALES:
			// key: COE:NombreMunicipioInternacional
			// value: latitud,longitud
			int keyCI = 0;
			int valueCI = 1;
			File alreadyLoadInt = new File(FILE_COORDENADAS_INTERNACIONALES);
			if (!alreadyLoadInt.exists()) {
				lines = FileUtils.readLines(new File(
						PATH_COORDENADAS_INTERNACIONALES));
				filterLines = filterListCoor(lines,
						K.CACHE.TABLE_COORDENADAS_INTERNACIONALES_ID, keyCI,
						valueCI);
				FileUtils.writeStringToFile(alreadyLoadInt, "");
			}
			break;

		default:
			break;
		}

		return filterLines;

	}

	private List<Pair<String, String>> filterListCoor(final List<String> lines,
			final String prefixId, final int key, final int value) {
		List<Pair<String, String>> filterLine = new ArrayList<>();

		for (String line : lines) {
			String[] splits = StringUtils.split(line, '|');
			Pair<String, String> pair = new Pair<String, String>(prefixId
					+ splits[key].trim(), splits[value].trim());
			filterLine.add(pair);

			if (LOG.isDebugEnabled()) {
				LOG.debug("key:" + pair.getKey() + " value:" + pair.getValue());
			}
		}
		return filterLine;
	}

	private List<Pair<String, String>> filterList(final List<String> lines,
			final String prefixId, final int key, final int value) {
		List<Pair<String, String>> filterLine = new ArrayList<>();

		for (String line : lines) {
			try {
				String[] splits = StringUtils.split(line, '\001');
				Pair<String, String> pair = new Pair<String, String>(prefixId
						+ splits[key].trim(), splits[value].trim());
				filterLine.add(pair);

				if (LOG.isDebugEnabled()) {
					LOG.debug("key:" + pair.getKey() + " value:"
							+ pair.getValue());
				}
			} catch (Exception e) {
				LOG.error("Error parsing line :" + line, e);
			}
		}
		return filterLine;
	}

	private List<Pair<String, String>> filterList(final List<String> lines,
			final String prefixId, final int[] keys, final int[] values) {
		List<Pair<String, String>> filterLine = new ArrayList<>();

		for (String line : lines) {
			try {
				String[] splits = StringUtils.split(line, '\001');
				String keyFinal = "";
				for (int indexKey : keys) {
					keyFinal += splits[indexKey].trim();
				}

				String valueFinal = "";
				for (int indexValue : values) {
					valueFinal += splits[indexValue].trim() + '|';
				}

				Pair<String, String> pair = new Pair<String, String>(prefixId
						+ keyFinal, valueFinal);
				filterLine.add(pair);

				if (LOG.isDebugEnabled()) {
					LOG.debug("key:" + pair.getKey() + " value:"
							+ pair.getValue());
				}
			} catch (Exception e) {
				LOG.error("Error parsing line :" + line, e);
			}
		}
		return filterLine;
	}

	private List<Pair<String, String>> filterList(final List<String> lines,
			final String prefixId, final int[] keys, final int value) {
		List<Pair<String, String>> filterLine = new ArrayList<>();

		for (String line : lines) {
			try {
				String[] splits = StringUtils.split(line, '\001');
				String keyFinal = "";
				for (int indexKey : keys) {
					keyFinal += splits[indexKey].trim();
				}

				Pair<String, String> pair = new Pair<String, String>(prefixId
						+ keyFinal.trim(), splits[value].trim());
				filterLine.add(pair);

				if (LOG.isDebugEnabled()) {
					LOG.debug("key:" + pair.getKey() + " value:"
							+ pair.getValue());
				}
			} catch (Exception e) {
				LOG.error("Error parsing line :" + line, e);
			}
		}
		return filterLine;
	}

	public static void main(String[] args) throws IOException {
		
		if (args.length != 2) {
			System.out
					.println("It's neccesary two parameter, principal and path of the keytab.");
			System.exit(-1);
		}

		String principal = args[0];
		String path = args[1];

		LoadCache loadCache = new LoadCache(principal, path);
		CacheManager cacheManager = Factory.getCacheManager();

		for (Tables table : Tables.values()) {
			LOG.info("Reading from HDFS table:" + table);
			List<Pair<String, String>> filterLines = loadCache
					.filterTables(table);
			LOG.info("Table name:" + table + " number records:"
					+ filterLines.size());
			cacheManager.setBulkLoad(filterLines);
		}
	}
}
