package com.produban.metrics.util;

public interface KMetrics {

	interface ULTALTA {
		// Ultalta
		String ULTALTA = "ULTALTA";
		int INDEX_COD_EMPRESA = 12;
		int INDEX_COD_PRODUCTO = 14;
		int INDEX_COD_SUCURSAL = 13;
		int EXTRA_PARAMS = 6;		
	}

	interface OBDGOCONTAB {
		String OB_DGO_CONTAB = "OB_DGO_CONTAB";
			
		int INDEX_CONS1 = 47;
		int INDEX_CONS2 = 48;
		
		String CONS1_TPV = "009";
		String CONS2_TPV = "008";
		
		String CONS1_CAJERO = "049";
		String CONS2_CAJERO = "001";
	}

	interface OBDGOCONTAB_TPV {
		int EXTRA_PARAMS = 8;
		int INDEX_IMPORTE = 51;
		int INDEX_MONEDA = 52;
		int INDEX_DGO_PARAMS = 67;
		int INDEX_CHECK_TYPE_ORDEN = 5;
	}

	interface HH_TRANSF_EMIT {
		String HH_TRANSF_EMIT = "HH_TRANSF_EMIT";
		int INDEX_IMPORTE = 59;
		int INDEX_MONEDA = 60;
		int INDEX_BANCO_ORIGEN = 51;
		int INDEX_SUCURSAL_ORIGEN = 52;
		int INDEX_CHECK1 = 53;
		int INDEX_CHECK2 = 54;
		int INDEX_CHECK_TYPE_ORDEN = 5;
		int INDEX_CONS1 = 68;

	}

	interface HH_DATOS_BANCOS {		
		String HH_DATOS_BANCOS = "HH_DATOS_BANCOS";
		int INDEX_CHECK_TYPE_ORDEN = 5;
		int INDEX_CHECK_TYPE = 6;
		int INDEX_CHECK1 = 21;
		int INDEX_CHECK2 = 22;
		int INDEX_CHECK3 = 23;
		int INDEX_CHECK4 = 24;
		int INDEX_CHECK_B = 25;
		int INDEX_BANCO_DESTINO = 26;
		int INDEX_SUCURSAL_DESTINO = 27;

	}

	interface OBDGOCONTAB_EFECTIVO {
		int EXTRA_PARAMS = 7;
		int INDEX_IMPORTE = 51;
		int INDEX_MONEDA = 52;
		int INDEX_DGO_PARAMS = 67;		
	}

	interface PLEMORDEN {
		// PlEmOrden
		String PL_EM_ORDEN = "PL_EM_ORDEN";		
		int INDEX_IMPORTE = 99;
		int INDEX_MONEDA = 100;
		int INDEX_BANCO_ORIGEN = 63;
		int INDEX_SUCURSAL_ORIGEN = 64;
		int INDEX_BANCO_DESTINO = 82;
		int INDEX_SUCURSAL_DESTINO = 83;
		int EXTRA_PARAMS = 12;		
		
		int INDEX_CHECK1 = 10;
		int INDEX_CHECK_999 = 71;
		String CHECK1_VALUE_999 = "0009";
	}

	interface COMMONS {
		int INDEX_QUEUE_NAME = 3;
	}

}
