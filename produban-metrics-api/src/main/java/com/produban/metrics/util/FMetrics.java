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
		int IDXU_Coordenadas = 5;
		
        
	}
	
	interface FQCaptureMeta{
		
        // QCaptureMeta, number of fields it uses
        int OFFSET_numFields = 11;
        
        int IDXT_datestr = 2;
        int IDXT_hourstr = 3;
        int IDXT_owner = 4;
		int IDXT_tabla = 5;
		int IDXT_evento = 6;
		int IDXT_plan = 10;
	}
	
	interface FPL_EM_ORDEN{		
        // PL_EM_ORDEN table
		
		// Offset from Q-Capture extra blank fields
		int OFFSET_ISRT = 51;
		int OFFSET_REPL = 0;
		int OFFSET_DLET = 0;
		int OFFSET_UKWN = 0;
		
		
		// Index of each TABLE value
	
		int IDXT_CODIGO_EMPRESA = 1;
		int IDXT_CODIGO_CENTRO = 2;
		int IDXT_EMPRESA_ORDENANTE = 6;
		int IDXT_CENTRO_ORDENANTE = 7;
		int IDXT_ESTADO_PAGO = 8;
		int IDXT_TIPO_ORDEN = 60; // (+12 de los de QCapture). Segun la excel era el 9 
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
		int IDXU_COORDENADA_ORIGEN = 10;
		int IDXU_COORDENADA_DESTINO = 11;
		
		// Loop increments for Banco, Localidad, Provincia, Destino iterations
		int INC_UserData = 4;
		int IDXITERA_Banco_Destino = 0;
		int IDXITERA_Localidad_Destino = 1;
		int IDXITERA_Provincia_Destino = 2;
		int IDXITERA_Pais_Destino = 3;
        
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
        String NHH_DATOS_BANCOS = "TRANSFERENCIAS_INTERNACIONALES_DATOS";
        String NOB_DGO_CONTAB = "PL_EM_ORDEN";
        String NPAGOS_TPV = "PAGOS_TPV";
        String NPL_EM_ORDEN = "TRANSFERENCIAS_NACIONALES";
        
        // Other constants
        String DATE_FORMAT = "yyyy-MM-dd-hh.mm.ss.SSS";
        String DATE_FORMAT2 = "yyyy-MM-dd";
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
	
		int IDXT_CODIGO_SEL1 = 36;
		int IDXT_CODIGO_SEL2 = 37;
		
		String VALUE_SEL1_TPV = "009";
		String VALUE_SEL2_TPV = "008";
		String VALUE_SEL1_CAJERO = "049";
		String VALUE_SEL2_CAJERO = "001";
		
	}
	
	interface FPAGOS_TPV {
		
		// Offset from Q-Capture extra blank fields
		int OFFSET_ISRT = 29;
		int OFFSET_REPL = 0;
		int OFFSET_DLET = 0;
		int OFFSET_UKWN = 0;
		
		int IDXU_Importe = 0;
		int IDXU_Moneda = 1;
		int IDXU_TPV = 2;
		int IDXU_Comercio = 3;
		int IDXU_Localidad = 4;
		int IDXU_Provincia = 5;
		int IDXU_Pais = 6;
		int IDXU_COORDENADAS = 7;
	}
	
	interface FDISPOSICION_EFECTIVO_CAJERO {
		
		// Offset from Q-Capture extra blank fields
		int OFFSET_ISRT = 29;
		int OFFSET_REPL = 0;
		int OFFSET_DLET = 0;
		int OFFSET_UKWN = 0;

		
		int IDXU_BANCO = 0;
		int IDXU_PLAZA = 1;
		int IDXU_PROVINCIA = 2;
		
	}
	interface FHH_DATOS_BANCOS {
		int LENGTH_HH_DATOS_BANCOS = 30;
		int LENGTH_EXTRA_FIELDS = 5;
		
		int IDXU_Banco_destino = 0;
		int IDXU_Localidad_destino = 1;
		int IDXU_Provincia_destino = 2;
		int IDXU_Pais_destino = 3;
		int IDXU_COORDENADA_DESTINO = 4;
	}
	
	
	interface FHH_TRANSF_EMIT {
		int LENGTH_HH_TRANSF = 90;
		int LENGTH_EXTRA_FIELDS = 7;
		
		// Offset from Q-Capture extra blank fields
		int OFFSET_ISRT = 39;
		int OFFSET_REPL = 39;
		int OFFSET_DLET = 0;
		int OFFSET_UKWN = 0;
		
		// Interesting table data
		int IDXT_CODIGO_EMPRESA = 1;	
		int IDXT_CODIGO_CENTRO = 2;
		int IDXT_CODIGO_PRODUCTO = 3;
		int IDXT_INDICADOR_PETICION = 8;		  // (IMP.PAGO O IMP.CONTRAVALOR)
		int IDXT_IMPORTE_DIVISA_PAGO = 9;
		int IDXT_CODIGO_MONEDA_IMPORTE_ORDENADO = 10;
		int IDXT_IMPORTE_CONTRAVALOR = 11;
		int IDXT_CODIGO_MONEDA_IMPORTE_CONTRAVALOR = 12;
		//private BigDecimal IMPORTE_ORDENADO; -> Importe, dato de usuario 13
		//private String CODIGO_MONEDA_ORDEN; -> Moneda, dato de usuario 14
		int IDXT_GASTOS_EMISION  = 16; // (O-ORD./B-BENEF/C=COMIS ORD GTS BENEF/G=GTS ORD COMIS BENEF)
		int IDXT_INDIC_GASTOS_FUERA_PAIS_EMISOR_POR_CTA = 17; //  DE: OUR-ORD/BEN-BENEF/SHR-REPARTIR
		int IDXT_PROCEDENCIA_TRANSFERENCIA = 18; //(MARCAJE, PERIODICA, ETC)
		int IDXT_FECHA_ALTA_TRANSFERENCIA = 21;
		int IDXT_FECHA_VALOR_ABONO_TRANSFERENCIA = 22;
		int IDXT_FECHA_VALOR_ADEUDO_TRANSFERENCIA = 23;
		int IDXT_HHG_TIMESTAMP = 24;
		int IDXT_BANCO_RECEPTOR  = 27;
		int IDXT_OFICINA_RECEPTORA = 28;		  // DEL PAGO
		int IDXT_CODIGO_PAIS_DESTINO = 29;
		int IDXT_INDICADOR_RESIDENCIA_BENEFICIARIO = 30;	  
		int IDXT_CODIGO_CANAL = 32;// EN BANCA ELECTRONICA
		int IDXT_CODIGO_USUARIO_ULTIMA_MODIFICACION = 37;
		int IDXT_FECHA_ULTIMA_MODIFICACION = 39;

		// User data
		int IDXU_Importe = 0;
		int IDXU_Moneda = 1;
		int IDXU_Banco_origen = 2;
		int IDXU_Localidad_origen = 3;
		int IDXU_Provincia_origen = 4;
		int IDXU_Pais_origen = 5;
		int IDXU_COORDENADAS_ORIGEN = 6;	
		
	}
	
	interface FProdubanMeta {
		int IDXT_Entidad = 0;
		int IDXT_Sistema = 1;
		int IDXT_Celda = 2;
		int IDXT_Tabla = 3;
		
	}
	
	
}

