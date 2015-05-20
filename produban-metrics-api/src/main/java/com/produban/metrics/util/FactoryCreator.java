package com.produban.metrics.util;

import com.produban.metrics.entities.Metrics;
import com.produban.metrics.entities.PL_EM_ORDEN;
import com.produban.metrics.entities.ULTALTA;

public class FactoryCreator implements FMetrics {
	
	public static Metrics createMetric(final String[] produbanLine, final String[] line, final String[] fields){
		Metrics lMetric = null;
		
		switch (produbanLine[COMMONS.INDEX_QUEUE_NAME])
				{
		case COMMONS.TULTALTA:
			lMetric = createULTALTA(produbanLine, line, fields);
			break;
		case COMMONS.TPL_EM_ORDEN:
			lMetric = createPL_EM_ORDEN(produbanLine, line, fields);
			break;
		case COMMONS.TOB_DGO_CONTAB:
			//COMMONS.TDISPOSICION_EFECTIVO_CAJERO:
			//COMMONS.TPAGOS_TPV:
			lMetric = createOB_DGO_CONTAB(produbanLine, line, fields);
			break;
		case COMMONS.THH_TRANSF_EMIT:
			lMetric = createHH_TRANSF_EMIT(produbanLine, line, fields);
			break;		
				
		default:
			break;
				}
		
		return lMetric;
		
	}
	
	private static Metrics createOB_DGO_CONTAB(String[] produbanLine,
			String[] line, String[] fields) {
		return null;
	}

	private static Metrics createHH_TRANSF_EMIT(String[] produbanLine, String[] line, String[] fields) {

		return null;
	}

	public static ULTALTA createULTALTA(final String[] produbanLine, final String[] line, final String[] fields){				
		ULTALTA event = new ULTALTA(produbanLine, line, fields);
		return event;
	}	
	
	public static PL_EM_ORDEN createPL_EM_ORDEN(final String[] produbanLine, final String[] line, final String[] fields){		
		PL_EM_ORDEN event = new PL_EM_ORDEN(produbanLine, line, fields);
		return event;
	}
}
