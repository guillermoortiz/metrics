package com.produban.metrics.util;

import com.esotericsoftware.minlog.Log;

/**
 * Class to filter message. It checks the minimum requisites to be a valid
 * message.
 * 
 * @author ortizg1
 *
 */
public class FilterMetrics implements KMetrics {

	private static final int INDEX_CHECK_TYPE_ORDEN = 5;
	private static final int INDEX_CHECK_TYPE_OP = 6;
	private static final String REPL = "REPL";
	private static final String ISRT = "ISRT";
	private static final String IND = "IND ";
	private static final String B = "B";

	/**
	 * 
	 * @param tableName
	 *            name of the table
	 * @param splitLine
	 *            Array of the Strings with the different fields of the message
	 * @return a boolean telling if the message is right
	 */
	public static boolean filter(final String tableName,
			final String[] splitLine) {
		boolean filter = false;

		try {

			switch (tableName) {

			case ULTALTA.ULTALTA:
				filter = filterUltalta(splitLine);
				break;

			case PLEMORDEN.PL_EM_ORDEN:
				filter = filterPlEmOrden(splitLine);
				break;

			case OBDGOCONTAB.OB_DGO_CONTAB:
				filter = filterObDgoContab(splitLine);
				break;

			case HH_DATOS_BANCOS.HH_DATOS_BANCOS:
				filter = filterHhDatosBancos(splitLine);
				break;

			case HH_TRANSF_EMIT.HH_TRANSF_EMIT:
				filter = filterHhTransfEmit(splitLine);
				break;

			default:
				filter = false;
				break;
			}
		} catch (Exception ex) {
			Log.error("Error trying to validate line,  it's not a valid line", ex);
			filter = false;
		}

		return filter;
	}

	private static boolean filterObDgoContab(String[] splitLine) {
		boolean opType = splitLine[INDEX_CHECK_TYPE_ORDEN]
				.equals(KMetrics.OBDGOCONTAB.OB_DGO_CONTAB)
				&& splitLine[INDEX_CHECK_TYPE_OP].equals(ISRT);

		boolean tpv = splitLine[KMetrics.OBDGOCONTAB.INDEX_CONS1]
				.equals(KMetrics.OBDGOCONTAB.CONS1_TPV)
				&& splitLine[KMetrics.OBDGOCONTAB.INDEX_CONS2]
						.equals(KMetrics.OBDGOCONTAB.CONS2_TPV);

		boolean cajero = splitLine[INDEX_CHECK_TYPE_ORDEN]
				.equals(KMetrics.OBDGOCONTAB.CONS1_CAJERO)
				&& splitLine[INDEX_CHECK_TYPE_ORDEN]
						.equals(KMetrics.OBDGOCONTAB.CONS2_CAJERO);

		return opType && (tpv || cajero);
	}

	private static boolean filterHhDatosBancos(String[] splitLine) {
		return splitLine[INDEX_CHECK_TYPE_ORDEN]
				.equals(KMetrics.HH_DATOS_BANCOS.HH_DATOS_BANCOS)
				&& splitLine[INDEX_CHECK_TYPE_OP].equals(ISRT)
				&& splitLine[KMetrics.HH_DATOS_BANCOS.INDEX_CHECK_B].equals(B);
	}

	private static boolean filterHhTransfEmit(String[] splitLine) {
		return splitLine[INDEX_CHECK_TYPE_ORDEN]
				.equals(KMetrics.HH_TRANSF_EMIT.HH_TRANSF_EMIT)
				&& splitLine[INDEX_CHECK_TYPE_OP].equals(ISRT);
				//&& splitLine[KMetrics.HH_TRANSF_EMIT.INDEX_CONS1]
					//	.equals(IND);
	}

	private static boolean filterPlEmOrden(String[] splitLine) {
		return splitLine[INDEX_CHECK_TYPE_ORDEN]
				.equals(KMetrics.PLEMORDEN.PL_EM_ORDEN)
				&& splitLine[INDEX_CHECK_TYPE_OP].equals(ISRT)
				&& splitLine[KMetrics.PLEMORDEN.INDEX_CHECK_999]
						.equals(KMetrics.PLEMORDEN.CHECK1_VALUE_999);
	}

	private static boolean filterUltalta(String[] splitLine) {
		return splitLine[INDEX_CHECK_TYPE_ORDEN].equals(ULTALTA.ULTALTA)
				&& splitLine[INDEX_CHECK_TYPE_OP].equals(REPL);
	}
}
