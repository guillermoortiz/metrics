package com.produban.metrics.util;

import java.util.Arrays;

import com.produban.api.general.Factory;
import com.produban.api.general.K;
import com.produban.api.manager.CacheManager;

/**
 * Class to parse codes in the messages with the final value through a cache.
 * @author ortizg1
 *
 */
public class FactoryParser {
	public static int INDEX_QUEUE_NAME = 3;
	public static final String ULTALTA = "ULTALTA";

	private static final String PL_EM_ORDEN = "PL_EM_ORDEN";
	private static final int PL_EM_ORDEN_COD_PAIS = 21;

	//Singleton where query the final values
	public static CacheManager cache = Factory.getCacheManager();

	
	public static String[] parser(final String[] produbanLine,
			final String[] line) {
		String[] parseLine = new String[line.length];
		String type = produbanLine[INDEX_QUEUE_NAME];

		switch (type) {
		case ULTALTA:
			parseLine = parseUltalta(line);
			break;

		case PL_EM_ORDEN:
			parseLine = parsePlEmOrden(line);
			break;

		default:
			break;
		}

		return parseLine;
	}

	private static String[] parsePlEmOrden(String[] line) {
		String[] copyLine = Arrays.copyOf(line, line.length);
		
		//TODO
		copyLine[PL_EM_ORDEN_COD_PAIS] = cache.get(line[PL_EM_ORDEN_COD_PAIS],
				K.CACHE.TABLE_PAIS_ID);

		return copyLine;	
	}

	private static String[] parseUltalta(String[] line) {
		String[] copyLine = Arrays.copyOf(line, line.length);

		//TODO
		
		return copyLine;
	}

}
