package com.produban.metrics.util;

import java.io.Serializable;
import java.security.KeyManagementException;

import com.produban.metrics.entities.DISPOSICION_EFECTIVO_CAJERO;
import com.produban.metrics.entities.HH_TRANSF_EMIT;
import com.produban.metrics.entities.OB_DGO_CONTAB;
import com.produban.metrics.entities.PAGOS_TPV;
import com.produban.metrics.entities.PL_EM_ORDEN;
import com.produban.metrics.entities.QCaptureMeta;
import com.produban.metrics.entities.ULTALTA;

public class FactoryCreator implements KMetrics{	
	
	public static Serializable create(final String[] topic, final String[] line, final String[] extraData){
		Serializable object;
		String type = topic[COMMONS.INDEX_QUEUE_NAME];
		
		switch (type) {
		case ULTALTA.ULTALTA:
			object = parseUltalta(topic, line, extraData);
			break;

		case PL_EM_ORDEN.PL_EM_ORDEN:
			object = parsePlEmOrden(topic, line, extraData);
			break;

		default:
			break;
		}
		return object;
	}
	
	
	public static ULTALTA createULTALTA(final String[] produbanLine, final String[] line){				
		ULTALTA event = new ULTALTA(produbanLine, line);
		return event;
	}	
	
	public static PL_EM_ORDEN createPL_EM_ORDEN(final String[] produbanLine, final String[] line, final String[] fields){		
		PL_EM_ORDEN event = new PL_EM_ORDEN(produbanLine, line, fields);
		return event;
	}
	public static HH_TRANSF_EMIT createHH_TRANSF_EMIT(final String[] produbanLine, final String[] line){		
		HH_TRANSF_EMIT event = new HH_TRANSF_EMIT(produbanLine, line);
		return event;
	}
	
	
	public static OB_DGO_CONTAB createOB_DGO_CONTAB(final String[] produbanLine, final String[] line){		
		
		OB_DGO_CONTAB event = null;
		
		if (line[QCaptureMeta.numFields+7].trim()=="ISRT") {
			if ((line[QCaptureMeta.numFields+48].trim()=="009")&&
					(line[QCaptureMeta.numFields+49].trim()=="008")){
				//Compra realizada por PTV
				event = new PAGOS_TPV(produbanLine,line);
				
			} else if ((line[QCaptureMeta.numFields+48].trim()=="049")&&
					(line[QCaptureMeta.numFields+49].trim()=="001")){
				//Disposición en efectivo en cajero				
				event = new DISPOSICION_EFECTIVO_CAJERO(produbanLine,line);
			} else {
				
				event = new OB_DGO_CONTAB(produbanLine, line);
				
			}
		}
		
		return event;
	}
	
}
