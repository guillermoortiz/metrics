package com.produban.metrics.util;

public interface FMetrics {

	interface FULTALTA{
		// ULTALTA table
		
        // Offset from Q-Capture extra blank fields
		int OFFSET_ISRT = 50;
		int OFFSET_REPL = 0;
		int OFFSET_DLET = 0;
		int OFFSET_UKWN = 0;
		
		// Index of each TABLE value
		int IDXT_UCA_CODEMPRE = 1;
		int IDXT_UCA_CODSUC = 2;
		int IDXT_UCA_CONTRLOC = 3;
		int IDXT_UCA_TIPOP = 4;
		int IDXT_UCA_ULTALTA = 5;
		
		// Index of each USER value
		
		int IDXU_CodigoEmpresa = 0;
		int IDXU_CodigoSucursal = 1;
		int IDXU_NombreSucursal = 2;
		int IDXU_CodigoProducto = 3;
		int IDXU_Producto = 4;
		
        
	}
	
	interface FQCaptureMeta{
		
        // QCaptureMeta, number of fields it uses
        int OFFSET_numFields = 11;
	}
	
	interface FPL_EM_ORDEN{		
        // PL_EM_ORDEN table
		
		// Offset from Q-Capture extra blank fields
		int OFFSET_ISRT = 0;
		int OFFSET_REPL = 0;
		int OFFSET_DLET = 0;
		int OFFSET_UKWN = 0;
		
		
		// Index of each TABLE value
	
		int IDXT_EMPRESA_ORDENANTE = 6;
		int IDXT_CENTRO_ORDENANTE = 7;
		int IDXT_ESTADO_PAGO = 8;
		int IDXT_TIPO_ORDEN = 9; 
		int IDXT_CODIGO_PAIS = 10;
		int IDXT_INDICADOR_RESIDENTE = 11;   
		int IDXT_MEDIO_FINANCIERO = 12;
		int IDXT_INDICADOR_INMEDIATEZ = 13;            
		int IDXT_CODIGO_EMPRESA_DESTINO = 20;
		int IDXT_CODIGO_CENTRO_DESTINO = 21;
		int IDXT_CANAL_OPERACION = 22;            
		int IDXT_USUARIO_ALTA = 25;    
		int IDXT_TIMESPTAMP_ALTA = 26;
		int IDXT_USUARIO_MODIFICACION = 27;
		int IDXT_TIMESTAMP_MODIFICACION = 28;            
		int IDXT_PAGO_DE_REMESA = 37;
		
		// User requested data
		int IDXU_Importe = 0;
		int IDXU_Moneda = 1;
		int IDXU_Banco_Origen = 2;
		int IDXU_Localidad_Origen = 3;
		int IDXU_Provincia_Origen = 4;
		int IDXU_Pais_Origen = 5;
		int IDXU_Banco_Destino = 6;
		int IDXU_Localidad_Destino = 7;
		int IDXU_Provincia_Destino = 8;
		int IDXU_Pais_Destino = 9;
        
	}


	interface COMMONS{
        int INDEX_QUEUE_NAME = 3;
        
        // String to identify table
        String TULTALTA = "ULTALTA";
        String TDISPOSICION_EFECTIVO_CAJERO = "OB_DGO_CONTAB";
        String THH_TRANSF_EMIT = "HH_TRANSF_EMIT";
        String TOB_DGO_CONTAB = "OB_DGO_CONTAB";
        String TPAGOS_TPV = "OB_DGO_CONTAB";
        String TPL_EM_ORDEN = "PL_EM_ORDEN";
        
        // Name of the table to be stored
        String NULTALTA = "ULTALTA";
        String NDISPOSICION_EFECTIVO_CAJERO = "DISPOSICION_EFECTIVO_CAJERO";
        String NHH_TRANSF_EMIT = "TRANSFERENCIAS_INTERNACIONALES";
        String NOB_DGO_CONTAB = "PL_EM_ORDEN";
        String NPAGOS_TPV = "PAGOS_TPV";
        String NPL_EM_ORDEN = "TRANSFERENCIAS_NACIONALES";
        
        // Other constants
        String DATE_FORMAT = "yyyy-MM-dd-hh.mm.ss.SSS";
        char DECIMAL_SEPARATOR = ',';
        
        
	}
	
	interface FOB_DGO_CONTAB{
		
		// Offset from Q-Capture extra blank fields
		int OFFSET_ISRT = 0;
		int OFFSET_REPL = 0;
		int OFFSET_DLET = 0;
		int OFFSET_UKWN = 0;
		
		int IDXT_CODIGO_EMPRESA = 1;
		int IDXT_CODIGO_CENTRO = 2;
		int IDXT_PUESTO_FISICO = 3;
		int IDXT_IMPORTE_OPERACION_BANCARIA = 12;
		int IDXT_MONEDA = 13;
		int IDXT_FECHA_OPERACION = 14;
		int IDXT_USUARIO = 25;
		int IDXT_TIMESTAMP_EJECUCION = 26;
		
		
	}
	
}

