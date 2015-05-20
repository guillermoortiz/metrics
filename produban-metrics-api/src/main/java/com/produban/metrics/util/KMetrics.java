package com.produban.metrics.util;

public interface KMetrics {
	
	interface ULTALTA{
		// Ultalta
		String ULTALTA = "ULTALTA";
		int INDEX_COD_EMPRESA = 12;
		int INDEX_COD_PRODUCTO = 14;
		int INDEX_COD_SUCURSAL = 13;		
		int EXTRA_PARAMS = 5;
	}
	
	interface OBDGOCONTAB{
		String OB_DGO_CONTAB = "OB_DGO_CONTAB";
		
		String CONS1_TPV = "OB_DGO_CONTAB";
		String CONS2_TPV = "\"ISRT\"";
		String CONS3_TPV = "\"049\"";
		String CONS4_TPV = "\"001\"";
	}
	
	interface OBDGOCONTAB_TPV{		
		int EXTRA_PARAMS = 7;
		int INDEX_IMPORTE = 51;
		int INDEX_MONEDA = 52;
		int INDEX_DGO_PARAMS = 67;
		
	}
	
	interface OBDGOCONTAB_EFECTIVO{		
		int EXTRA_PARAMS = 6;
		int INDEX_IMPORTE = 51;
		int INDEX_MONEDA = 52;
		int INDEX_DGO_PARAMS = 67;		
	}
	
	interface PLEMORDEN{
		// PlEmOrden	
		String PL_EM_ORDEN = "PL_EM_ORDEN";
		int INDEX_IMPORTE = 99;
		int INDEX_MONEDA = 100;
		int INDEX_BANCO_ORIGEN = 12;
		int INDEX_SUCURSAL_ORIGEN = 13;
		int INDEX_BANCO_DESTINO = 31;
		int INDEX_SUCURSAL_DESTINO = 32;
		int EXTRA_PARAMS = 10;
	}
	
	interface COMMONS{
		int INDEX_QUEUE_NAME = 3;
	}
}
