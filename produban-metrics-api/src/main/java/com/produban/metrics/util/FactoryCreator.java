package com.produban.metrics.util;

import com.produban.metrics.entities.DISPOSICION_EFECTIVO_CAJERO;
import com.produban.metrics.entities.HH_TRANSF_EMIT;
import com.produban.metrics.entities.KF_CUENTA_APERT;
import com.produban.metrics.entities.Metrics;
import com.produban.metrics.entities.OB_DGO_CONTAB;
import com.produban.metrics.entities.PAGOS_TPV;
import com.produban.metrics.entities.PL_EM_ORDEN;
import com.produban.metrics.entities.ULTALTA;

public class FactoryCreator implements FMetrics {

	public static Metrics createMetric(final String[] produbanLine,
			final String[] line, final String[] fields) {
		Metrics lMetric = null;

		switch (produbanLine[COMMONS.INDEX_QUEUE_NAME]) {
		case COMMONS.TULTALTA:
			lMetric = createULTALTA(produbanLine, line, fields);
			break;
		case COMMONS.TPL_EM_ORDEN:
			lMetric = createPL_EM_ORDEN(produbanLine, line, fields);
			break;
		case COMMONS.TOB_DGO_CONTAB:
			boolean c1;
			boolean c2;
			boolean c3;
			boolean c4;

			c1 = (line[FOB_DGO_CONTAB.IDXT_CODIGO_SEL1] == FOB_DGO_CONTAB.VALUE_SEL1_TPV);
			c2 = (line[FOB_DGO_CONTAB.IDXT_CODIGO_SEL2] == FOB_DGO_CONTAB.VALUE_SEL2_TPV);
			c3 = (line[FOB_DGO_CONTAB.IDXT_CODIGO_SEL1] == FOB_DGO_CONTAB.VALUE_SEL1_CAJERO);
			c4 = (line[FOB_DGO_CONTAB.IDXT_CODIGO_SEL2] == FOB_DGO_CONTAB.VALUE_SEL2_CAJERO);
			if (c1 && c2) {
				// 009 y 008 TPV
				lMetric = createPAGOS_TPV(produbanLine, line, fields);
			} else if (c3 && c4) {
				// 049 y 001 EFECTIVO
				lMetric = createDISPOSICION_EFECTIVO_CAJERO(produbanLine, line,
						fields);
			} else {
				lMetric = createOB_DGO_CONTAB(produbanLine, line, fields);
			}
			break;
		case COMMONS.THH_TRANSF_EMIT:
			lMetric = createHH_TRANSF_EMIT(produbanLine, line, fields);
			break;

		case COMMONS.TKF_CUENTA_APERT:
			lMetric = createKF_CUENTA_APERT(produbanLine, line, fields);
			break;

		default:
			break;
		}

		return lMetric;

	}

	private static Metrics createDISPOSICION_EFECTIVO_CAJERO(
			String[] produbanLine, String[] line, String[] fields) {
		Metrics event = new DISPOSICION_EFECTIVO_CAJERO(produbanLine, line,
				fields);
		return event;
	}

	private static Metrics createPAGOS_TPV(String[] produbanLine,
			String[] line, String[] fields) {
		Metrics event = new PAGOS_TPV(produbanLine, line, fields);
		return event;
	}

	private static Metrics createOB_DGO_CONTAB(String[] produbanLine,
			String[] line, String[] fields) {
		Metrics event = new OB_DGO_CONTAB(produbanLine, line, fields);
		return event;
	}

	private static Metrics createHH_TRANSF_EMIT(String[] produbanLine,
			String[] line, String[] fields) {
		Metrics event = new HH_TRANSF_EMIT(produbanLine, line, fields);
		return event;
	}

	public static Metrics createULTALTA(final String[] produbanLine,
			final String[] line, final String[] fields) {
		Metrics event = new ULTALTA(produbanLine, line, fields);
		return event;
	}

	public static Metrics createPL_EM_ORDEN(final String[] produbanLine,
			final String[] line, final String[] fields) {
		Metrics event = new PL_EM_ORDEN(produbanLine, line, fields);
		return event;
	}

	public static Metrics createKF_CUENTA_APERT(final String[] produbanLine,
			final String[] line, final String[] fields) {
		Metrics event = new KF_CUENTA_APERT(produbanLine, line, fields);
		return event;
	}
}
