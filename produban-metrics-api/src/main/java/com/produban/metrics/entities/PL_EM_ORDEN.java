package com.produban.metrics.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PL_EM_ORDEN implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -791019680584419244L;
	
	// Data from Produban
	private ProdubanMeta DATOS_P;
	
	// Data from Q-Capture
	private QCaptureMeta DATOS_Q;
	
	
	
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
	private BigDecimal IMPORTE_PAGOS;
	//   E5604_CODMON index 38 + DATOS_Q.numFields; (12)
	private String CODIGO_MONEDA;
	//   E5604_INDREM index 43 + DATOS_Q.numFields; (12)
	private String PAGO_DE_REMESA;              

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
	public BigDecimal getIMPORTE_PAGOS() {
		return IMPORTE_PAGOS;
	}
	public void setIMPORTE_PAGOS(BigDecimal iMPORTE_PAGOS) {
		IMPORTE_PAGOS = iMPORTE_PAGOS;
	}
	public String getCODIGO_MONEDA() {
		return CODIGO_MONEDA;
	}
	public void setCODIGO_MONEDA(String cODIGO_MONEDA) {
		CODIGO_MONEDA = cODIGO_MONEDA;
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

	public PL_EM_ORDEN(String[] produbanLine, String[] line) {
		// Add QCaptureMeta.numFields for field offset		
		
		this.DATOS_Q = new QCaptureMeta(line);
		this.DATOS_P = new ProdubanMeta(produbanLine);
		
		Integer offset=0;
		
		
		// Insert events add some irrelevant fields we need to offset
		if (this.DATOS_Q.getEvento() == QCaptureMeta.tipo_evento.ISRT)
		{
			offset = 0;
		}
		else
		{
			offset = 0;
		}
		
		
		this.EMPRESA_ORDENANTE = line[QCaptureMeta.numFields+offset+6];
		this.CENTRO_ORDENANTE = line[QCaptureMeta.numFields+offset+7];
		this.ESTADO_PAGO = line[QCaptureMeta.numFields+offset+8];
		this.TIPO_ORDEN = line[QCaptureMeta.numFields+offset+9];
		this.CODIGO_PAIS  = line[QCaptureMeta.numFields+offset+offset+10]; 
		this.INDICADOR_RESIDENTE = line[QCaptureMeta.numFields+offset+11];
		this.MEDIO_FINANCIERO = line[QCaptureMeta.numFields+offset+12];
		this.INDICADOR_INMEDIATEZ = line[QCaptureMeta.numFields+offset+13];
		this.CODIGO_EMPRESA_DESTINO = line[QCaptureMeta.numFields+offset+20];
		this.CODIGO_CENTRO_DESTINO  = line[QCaptureMeta.numFields+offset+21];
		this.CANAL_OPERACION = line[QCaptureMeta.numFields+offset+22];
		this.USUARIO_ALTA = line[QCaptureMeta.numFields+offset+25];
		
		SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss.SSS");
		Date lFromDate1 = null;
		try {
			String fecha;
			fecha = line[QCaptureMeta.numFields+26].substring(0, 23);
			lFromDate1 = datetimeFormatter1.parse(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.TIMESPTAMP_ALTA = new Timestamp(lFromDate1.getTime());		
		this.USUARIO_MODIFICACION = line[QCaptureMeta.numFields+27];
		
		try {
			String fecha;
			fecha = line[QCaptureMeta.numFields+28].substring(0, 23);
			lFromDate1 = datetimeFormatter1.parse(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		this.TIMESTAMP_MODIFICACION  = new Timestamp(lFromDate1.getTime());
		
		try{
			this.IMPORTE_PAGOS  = new BigDecimal(line[QCaptureMeta.numFields+37]);
			this.CODIGO_MONEDA  = line[QCaptureMeta.numFields+38];
			this.PAGO_DE_REMESA  = line[QCaptureMeta.numFields+43];
		}catch (Exception e){
			e.printStackTrace();
		}

		
	}
	@Override
	public String toString() {
		return "PL_EM_ORDEN [DATOS_P=" + DATOS_P.toString() + ", DATOS_Q=" + DATOS_Q.toString()
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
				+ ", IMPORTE_PAGOS=" + IMPORTE_PAGOS + ", CODIGO_MONEDA="
				+ CODIGO_MONEDA + ", PAGO_DE_REMESA=" + PAGO_DE_REMESA + "]";
	}	

	


}
