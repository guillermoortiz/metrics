package com.produban.metrics.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.produban.metrics.util.FMetrics;

public class PL_EM_ORDEN implements Metrics, FMetrics {

	/**
	 * 
	 */
	private static final long serialVersionUID = -791019680584419244L;
	
	// Data from Produban
	private ProdubanMeta DATOS_P;
	
	// Data from Q-Capture
	private QCaptureMeta DATOS_Q;
		
	// Table relevant data
	//   E5604_IDEMPORD, index 6 + DATOS_Q.numFields; (12)	
	private String EMPRESA_ORDENANTE;
	//   E5604_IDCENORD index 7 + DATOS_Q.numFields; (12)
	private String CENTRO_ORDENANTE;
	//   E5604_CESTADO, CODIGO index 8 + DATOS_Q.numFields; (12) 
	private String ESTADO_PAGO;
	//   E5604_TORDEN (SIP,SO,FWD,RETURN) index 9 + DATOS_Q.numFields; (12)
	private String TIPO_ORDEN; 
	//   E5604_CODPAIS index 10 + DATOS_Q.numFields; (12)
	private String CODIGO_PAIS;
	//   E5604_INDRES index 11 + DATOS_Q.numFields; (12)
	private String INDICADOR_RESIDENTE;   
	//   E5604_MEDIO index 12 + DATOS_Q.numFields; (12)
	private String MEDIO_FINANCIERO;
	//   E5604_IDINMEDI index 13 + DATOS_Q.numFields; (12)
	private String INDICADOR_INMEDIATEZ;            
	//   E5604_IDEMPRD index 20 + DATOS_Q.numFields; (12)
	private String CODIGO_EMPRESA_DESTINO;
	//   E5604_IDCENTD index 21 + DATOS_Q.numFields; (12)
	private String CODIGO_CENTRO_DESTINO;
	//   E5604_CANALOPE index 22 + DATOS_Q.numFields; (12)            
	private String CANAL_OPERACION;            
	//   E5604_USUALT index 25 + DATOS_Q.numFields; (12)
	private String USUARIO_ALTA;    
	//   E5604_TIMALTA index 26 + DATOS_Q.numFields; (12)
	private Timestamp TIMESPTAMP_ALTA;
	//   E5604_USUMOD index 27 + DATOS_Q.numFields; (12)
	private String USUARIO_MODIFICACION;
	//   E5604_TIMESMOD index 28 + DATOS_Q.numFields; (12)
	private Timestamp TIMESTAMP_MODIFICACION;            
	//   E5604_IMPPAG00 index 37 + DATOS_Q.numFields; (12)
	private String PAGO_DE_REMESA;
	
	// User requested data
	private BigDecimal Importe;
	private String Moneda;
	private String Banco_Origen;
	private String Localidad_Origen;
	private String Provincia_Origen;
	private String Pais_Origen;
	private String Banco_Destino;
	private String Localidad_Destino;
	private String Provincia_Destino;
	private String Pais_Destino;

	public QCaptureMeta getDATOS_Q() {
		return DATOS_Q;
	}
	public void setDATOS_Q(QCaptureMeta dATOS_Q) {
		DATOS_Q = dATOS_Q;
	}
	public String getEMPRESA_ORDENANTE() {
		return EMPRESA_ORDENANTE;
	}
	public void setEMPRESA_ORDENANTE(String eMPRESA_ORDENANTE) {
		EMPRESA_ORDENANTE = eMPRESA_ORDENANTE;
	}
	public String getCENTRO_ORDENANTE() {
		return CENTRO_ORDENANTE;
	}
	public void setCENTRO_ORDENANTE(String cENTRO_ORDENANTE) {
		CENTRO_ORDENANTE = cENTRO_ORDENANTE;
	}
	public String getESTADO_PAGO() {
		return ESTADO_PAGO;
	}
	public void setESTADO_PAGO(String eSTADO_PAGO) {
		ESTADO_PAGO = eSTADO_PAGO;
	}
	public String getTIPO_ORDEN() {
		return TIPO_ORDEN;
	}
	public void setTIPO_ORDEN(String tIPO_ORDEN) {
		TIPO_ORDEN = tIPO_ORDEN;
	}
	public String getCODIGO_PAIS() {
		return CODIGO_PAIS;
	}
	public void setCODIGO_PAIS(String cODIGO_PAIS) {
		CODIGO_PAIS = cODIGO_PAIS;
	}
	public String getINDICADOR_RESIDENTE() {
		return INDICADOR_RESIDENTE;
	}
	public void setINDICADOR_RESIDENTE(String iNDICADOR_RESIDENTE) {
		INDICADOR_RESIDENTE = iNDICADOR_RESIDENTE;
	}
	public String getMEDIO_FINANCIERO() {
		return MEDIO_FINANCIERO;
	}
	public void setMEDIO_FINANCIERO(String mEDIO_FINANCIERO) {
		MEDIO_FINANCIERO = mEDIO_FINANCIERO;
	}
	public String getINDICADOR_INMEDIATEZ() {
		return INDICADOR_INMEDIATEZ;
	}
	public void setINDICADOR_INMEDIATEZ(String iNDICADOR_INMEDIATEZ) {
		INDICADOR_INMEDIATEZ = iNDICADOR_INMEDIATEZ;
	}
	public String getCODIGO_EMPRESA_DESTINO() {
		return CODIGO_EMPRESA_DESTINO;
	}
	public void setCODIGO_EMPRESA_DESTINO(String cODIGO_EMPRESA_DESTINO) {
		CODIGO_EMPRESA_DESTINO = cODIGO_EMPRESA_DESTINO;
	}
	public String getCODIGO_CENTRO_DESTINO() {
		return CODIGO_CENTRO_DESTINO;
	}
	public void setCODIGO_CENTRO_DESTINO(String cODIGO_CENTRO_DESTINO) {
		CODIGO_CENTRO_DESTINO = cODIGO_CENTRO_DESTINO;
	}
	public String getCANAL_OPERACION() {
		return CANAL_OPERACION;
	}
	public void setCANAL_OPERACION(String cANAL_OPERACION) {
		CANAL_OPERACION = cANAL_OPERACION;
	}
	public String getUSUARIO_ALTA() {
		return USUARIO_ALTA;
	}
	public void setUSUARIO_ALTA(String uSUARIO_ALTA) {
		USUARIO_ALTA = uSUARIO_ALTA;
	}
	public Timestamp getTIMESPTAMP_ALTA() {
		return TIMESPTAMP_ALTA;
	}
	public void setTIMESPTAMP_ALTA(Timestamp tIMESPTAMP_ALTA) {
		TIMESPTAMP_ALTA = tIMESPTAMP_ALTA;
	}
	public String getUSUARIO_MODIFICACION() {
		return USUARIO_MODIFICACION;
	}
	public void setUSUARIO_MODIFICACION(String uSUARIO_MODIFICACION) {
		USUARIO_MODIFICACION = uSUARIO_MODIFICACION;
	}
	public Timestamp getTIMESTAMP_MODIFICACION() {
		return TIMESTAMP_MODIFICACION;
	}
	public void setTIMESTAMP_MODIFICACION(Timestamp tIMESTAMP_MODIFICACION) {
		TIMESTAMP_MODIFICACION = tIMESTAMP_MODIFICACION;
	}
	
	public String getPAGO_DE_REMESA() {
		return PAGO_DE_REMESA;
	}
	public void setPAGO_DE_REMESA(String pAGO_DE_REMESA) {
		PAGO_DE_REMESA = pAGO_DE_REMESA;
	}
	
	public ProdubanMeta getDATOS_P() {
		return DATOS_P;
	}
	public void setDATOS_P(ProdubanMeta dATOS_P) {
		DATOS_P = dATOS_P;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public BigDecimal getImporte() {
		return Importe;
	}
	public void setImporte(BigDecimal importe) {
		Importe = importe;
	}
	public String getMoneda() {
		return Moneda;
	}
	public void setMoneda(String moneda) {
		Moneda = moneda;
	}
	public String getBanco_Origen() {
		return Banco_Origen;
	}
	public void setBanco_Origen(String banco_Origen) {
		Banco_Origen = banco_Origen;
	}
	public String getLocalidad_Origen() {
		return Localidad_Origen;
	}
	public void setLocalidad_Origen(String localidad_Origen) {
		Localidad_Origen = localidad_Origen;
	}
	public String getProvincia_Origen() {
		return Provincia_Origen;
	}
	public void setProvincia_Origen(String provincia_Origen) {
		Provincia_Origen = provincia_Origen;
	}
	public String getPais_Origen() {
		return Pais_Origen;
	}
	public void setPais_Origen(String pais_Origen) {
		Pais_Origen = pais_Origen;
	}
	public String getBanco_Destino() {
		return Banco_Destino;
	}
	public void setBanco_Destino(String banco_Destino) {
		Banco_Destino = banco_Destino;
	}
	public String getLocalidad_Destino() {
		return Localidad_Destino;
	}
	public void setLocalidad_Destino(String localidad_Destino) {
		Localidad_Destino = localidad_Destino;
	}
	public String getProvincia_Destino() {
		return Provincia_Destino;
	}
	public void setProvincia_Destino(String provincia_Destino) {
		Provincia_Destino = provincia_Destino;
	}
	public String getPais_Destino() {
		return Pais_Destino;
	}
	public void setPais_Destino(String pais_Destino) {
		Pais_Destino = pais_Destino;
	}


	public PL_EM_ORDEN(String[] produbanLine, String[] line, String[] fields) {
		
		// Local variables
		
		// For money conversions
		DecimalFormat df = new DecimalFormat();
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		String Simporte="";
		Number Nimporte;
		
		// Offset to add in case off ISRT that adds a series of "|" at the begining
		Integer offset=0;
		
		// For Gregorian date formatting
		SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat(COMMONS.DATE_FORMAT);
		Date lFromDate1 = null;
		
		// Data relevant to Q-Capture
		this.DATOS_Q = new QCaptureMeta(line);
		
		// Data relevant to Produban (Entidad, Sistema, Celda, Tabla) 
		this.DATOS_P = new ProdubanMeta(produbanLine);
		
		this.DATOS_P.setTabla(COMMONS.NPL_EM_ORDEN);
				
		// Insert events add some irrelevant fields we need to offset
		if (this.DATOS_Q.getEvento() == QCaptureMeta.tipo_evento.ISRT)
		{
			offset = FPL_EM_ORDEN.OFFSET_ISRT;
		}
		else
		{
			offset = FPL_EM_ORDEN.OFFSET_UKWN;
		}
		
		// Table relevant data
		this.EMPRESA_ORDENANTE = line[FQCaptureMeta.OFFSET_numFields+offset+FPL_EM_ORDEN.IDXT_EMPRESA_ORDENANTE];
		this.CENTRO_ORDENANTE = line[FQCaptureMeta.OFFSET_numFields+offset+FPL_EM_ORDEN.IDXT_CENTRO_ORDENANTE];
		this.ESTADO_PAGO = line[FQCaptureMeta.OFFSET_numFields+offset+FPL_EM_ORDEN.IDXT_ESTADO_PAGO];
		this.TIPO_ORDEN = line[FQCaptureMeta.OFFSET_numFields+offset+FPL_EM_ORDEN.IDXT_TIPO_ORDEN];
		this.CODIGO_PAIS  = line[FQCaptureMeta.OFFSET_numFields+offset+offset+FPL_EM_ORDEN.IDXT_CODIGO_PAIS]; 
		this.INDICADOR_RESIDENTE = line[FQCaptureMeta.OFFSET_numFields+offset+FPL_EM_ORDEN.IDXT_INDICADOR_RESIDENTE];
		this.MEDIO_FINANCIERO = line[FQCaptureMeta.OFFSET_numFields+offset+FPL_EM_ORDEN.IDXT_MEDIO_FINANCIERO];
		this.INDICADOR_INMEDIATEZ = line[FQCaptureMeta.OFFSET_numFields+offset+FPL_EM_ORDEN.IDXT_INDICADOR_INMEDIATEZ];
		this.CODIGO_EMPRESA_DESTINO = line[FQCaptureMeta.OFFSET_numFields+offset+FPL_EM_ORDEN.IDXT_CODIGO_EMPRESA_DESTINO];
		this.CODIGO_CENTRO_DESTINO  = line[FQCaptureMeta.OFFSET_numFields+offset+FPL_EM_ORDEN.IDXT_CANAL_OPERACION];
		this.CANAL_OPERACION = line[FQCaptureMeta.OFFSET_numFields+offset+FPL_EM_ORDEN.IDXT_CANAL_OPERACION];
		this.USUARIO_ALTA = line[FQCaptureMeta.OFFSET_numFields+offset+FPL_EM_ORDEN.IDXT_USUARIO_ALTA];
		
		
		try {
			String fecha;
			fecha = line[FQCaptureMeta.OFFSET_numFields+FPL_EM_ORDEN.IDXT_TIMESPTAMP_ALTA].substring(0, 23);
			lFromDate1 = datetimeFormatter1.parse(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.TIMESPTAMP_ALTA = new Timestamp(lFromDate1.getTime());		
		this.USUARIO_MODIFICACION = line[FQCaptureMeta.OFFSET_numFields+FPL_EM_ORDEN.IDXT_USUARIO_ALTA];
		
		try {
			String fecha;
			fecha = line[FQCaptureMeta.OFFSET_numFields+FPL_EM_ORDEN.IDXT_TIMESTAMP_MODIFICACION].substring(0, 23);
			lFromDate1 = datetimeFormatter1.parse(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		this.TIMESTAMP_MODIFICACION  = new Timestamp(lFromDate1.getTime());

		this.PAGO_DE_REMESA  = line[FQCaptureMeta.OFFSET_numFields+FPL_EM_ORDEN.IDXT_PAGO_DE_REMESA];
		
		// "," has been sent as decimal separator
		
		dfs.setDecimalSeparator(COMMONS.DECIMAL_SEPARATOR);		
		df.setDecimalFormatSymbols(dfs);			
		try {
			Simporte = fields[FPL_EM_ORDEN.IDXU_Importe];
			Nimporte = df.parse(Simporte);
			this.Importe  = new BigDecimal(Nimporte.toString());			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// User requested data
		this.Moneda=fields[FPL_EM_ORDEN.IDXU_Moneda];
		this.Banco_Origen=fields[FPL_EM_ORDEN.IDXU_Banco_Origen];
		this.Localidad_Origen=fields[FPL_EM_ORDEN.IDXU_Localidad_Origen];
		this.Provincia_Origen=fields[FPL_EM_ORDEN.IDXU_Localidad_Destino];
		this.Pais_Origen=fields[FPL_EM_ORDEN.IDXU_Pais_Origen];
		this.Banco_Destino=fields[FPL_EM_ORDEN.IDXU_Pais_Destino];
		this.Localidad_Destino=fields[FPL_EM_ORDEN.IDXU_Localidad_Destino];
		this.Provincia_Destino=fields[FPL_EM_ORDEN.IDXU_Provincia_Destino];
		this.Pais_Destino=fields[FPL_EM_ORDEN.IDXU_Pais_Destino];
		
	}
	@Override
	public String toString() {
		return "PL_EM_ORDEN [DATOS_P=" + DATOS_P + ", DATOS_Q=" + DATOS_Q
				+ ", EMPRESA_ORDENANTE=" + EMPRESA_ORDENANTE
				+ ", CENTRO_ORDENANTE=" + CENTRO_ORDENANTE + ", ESTADO_PAGO="
				+ ESTADO_PAGO + ", TIPO_ORDEN=" + TIPO_ORDEN + ", CODIGO_PAIS="
				+ CODIGO_PAIS + ", INDICADOR_RESIDENTE=" + INDICADOR_RESIDENTE
				+ ", MEDIO_FINANCIERO=" + MEDIO_FINANCIERO
				+ ", INDICADOR_INMEDIATEZ=" + INDICADOR_INMEDIATEZ
				+ ", CODIGO_EMPRESA_DESTINO=" + CODIGO_EMPRESA_DESTINO
				+ ", CODIGO_CENTRO_DESTINO=" + CODIGO_CENTRO_DESTINO
				+ ", CANAL_OPERACION=" + CANAL_OPERACION + ", USUARIO_ALTA="
				+ USUARIO_ALTA + ", TIMESPTAMP_ALTA=" + TIMESPTAMP_ALTA
				+ ", USUARIO_MODIFICACION=" + USUARIO_MODIFICACION
				+ ", TIMESTAMP_MODIFICACION=" + TIMESTAMP_MODIFICACION
				+ ", PAGO_DE_REMESA=" + PAGO_DE_REMESA + ", Importe=" + Importe
				+ ", Moneda=" + Moneda + ", Banco_Origen=" + Banco_Origen
				+ ", Localidad_Origen=" + Localidad_Origen
				+ ", Provincia_Origen=" + Provincia_Origen + ", Pais_Origen="
				+ Pais_Origen + ", Banco_Destino=" + Banco_Destino
				+ ", Localidad_Destino=" + Localidad_Destino
				+ ", Provincia_Destino=" + Provincia_Destino
				+ ", Pais_Destino=" + Pais_Destino + "]";
	}
}
