package com.produban.metrics.util;

import com.produban.metrics.entities.PL_EM_ORDEN;
import com.produban.metrics.entities.ULTALTA;

public class Factory {
	public static ULTALTA createULTALTA(final String[] produbanLine, final String[] line){				
		ULTALTA event = new ULTALTA(produbanLine, line);
		return event;
	}	
	
	public static PL_EM_ORDEN createPL_EM_ORDEN(final String[] produbanLine, final String[] line){		
		PL_EM_ORDEN event = new PL_EM_ORDEN(produbanLine, line);
		return event;
	}
}
