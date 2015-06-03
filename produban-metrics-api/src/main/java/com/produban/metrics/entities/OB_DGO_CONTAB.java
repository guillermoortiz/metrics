package com.produban.metrics.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.produban.metrics.util.FMetrics;

public class OB_DGO_CONTAB implements Serializable, FMetrics, Metrics{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2193182445803147338L;

	// Data from Produban
	private ProdubanMeta DATOS_P;
	
	// Data from Q-Capture
	private QCaptureMeta DATOS_Q;
	
	// Table relevant data
	private String CODIGO_EMPRESA;
	private String CODIGO_CENTRO;
	private String PUESTO_FISICO;
	private BigDecimal IMPORTE_OPERACION_BANCARIA;
	private String MONEDA;
	private Date FECHA_OPERACION;
	private String USUARIO;
	private Date TIMESTAMP_EJECUCION;
	
	// There is no user data as it will be part of inherited classes
	
	public String getCODIGO_EMPRESA() {
		return CODIGO_EMPRESA;
	}
	public void setCODIGO_EMPRESA(String cODIGO_EMPRESA) {
		CODIGO_EMPRESA = cODIGO_EMPRESA;
	}
	public String getCODIGO_CENTRO() {
		return CODIGO_CENTRO;
	}
	public void setCODIGO_CENTRO(String cODIGO_CENTRO) {
		CODIGO_CENTRO = cODIGO_CENTRO;
	}
	public String getPUESTO_FISICO() {
		return PUESTO_FISICO;
	}
	public void setPUESTO_FISICO(String pUESTO_FISICO) {
		PUESTO_FISICO = pUESTO_FISICO;
	}
	public BigDecimal getIMPORTE_OPERACION_BANCARIA() {
		return IMPORTE_OPERACION_BANCARIA;
	}
	public void setIMPORTE_OPERACION_BANCARIA(BigDecimal iMPORTE_OPERACION_BANCARIA) {
		IMPORTE_OPERACION_BANCARIA = iMPORTE_OPERACION_BANCARIA;
	}
	public String getMONEDA() {
		return MONEDA;
	}
	public void setMONEDA(String mONEDA) {
		MONEDA = mONEDA;
	}
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date getFECHA_OPERACION() {
		return FECHA_OPERACION;
	}
	public void setFECHA_OPERACION(Date fECHA_OPERACION) {
		FECHA_OPERACION = fECHA_OPERACION;
	}
	public String getUSUARIO() {
		return USUARIO;
	}
	public void setUSUARIO(String uSUARIO) {
		USUARIO = uSUARIO;
	}
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date getTIMESTAMP_EJECUCION() {
		return TIMESTAMP_EJECUCION;
	}
	public void setTIMESTAMP_EJECUCION(Date tIMESTAMP_EJECUCION) {
		TIMESTAMP_EJECUCION = tIMESTAMP_EJECUCION;
	}
	
	public ProdubanMeta getDATOS_P() {
		return DATOS_P;
	}
	public void setDATOS_P(ProdubanMeta dATOS_P) {
		DATOS_P = dATOS_P;
	}
	public QCaptureMeta getDATOS_Q() {
		return DATOS_Q;
	}
	public void setDATOS_Q(QCaptureMeta dATOS_Q) {
		DATOS_Q = dATOS_Q;
	}

	// Constructor
	public OB_DGO_CONTAB(String[] produbanLine, String[] line, String[] fields) {
		
		// Create Q-Capture metadata
		this.setDATOS_Q(new QCaptureMeta(line));
		// Create Produban metadata
		this.setDATOS_P(new ProdubanMeta(produbanLine));
		
		// Locate offset due to ISRT events blanks
		Integer offset=0;
		if (this.DATOS_Q.getEvento() == QCaptureMeta.tipo_evento.ISRT)
		{
			offset = FOB_DGO_CONTAB.OFFSET_ISRT;
		}
		else
		{
			offset = FOB_DGO_CONTAB.OFFSET_UKWN;
		}
		
		// Table relevant data
		this.CODIGO_EMPRESA=line[FQCaptureMeta.OFFSET_numFields+offset+FOB_DGO_CONTAB.IDXT_CODIGO_EMPRESA];
		this.CODIGO_CENTRO=line[FQCaptureMeta.OFFSET_numFields+offset+FOB_DGO_CONTAB.IDXT_CODIGO_CENTRO];		
		this.PUESTO_FISICO=line[FQCaptureMeta.OFFSET_numFields+offset+FOB_DGO_CONTAB.IDXT_PUESTO_FISICO];
		DecimalFormat df = new DecimalFormat();
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator(COMMONS.DECIMAL_SEPARATOR);		
		df.setDecimalFormatSymbols(dfs);
		String importe="";
		Number Nimporte;
		try {
			importe = line[FQCaptureMeta.OFFSET_numFields+offset+FOB_DGO_CONTAB.IDXT_IMPORTE_OPERACION_BANCARIA];
			Nimporte = df.parse(importe);
			this.IMPORTE_OPERACION_BANCARIA= new BigDecimal(Nimporte.toString());			
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		this.MONEDA=line[FQCaptureMeta.OFFSET_numFields+offset+FOB_DGO_CONTAB.IDXT_MONEDA];
		SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat(COMMONS.DATE_FORMAT);	
		try {
			String fecha;
			fecha = line[FQCaptureMeta.OFFSET_numFields+offset+FOB_DGO_CONTAB.IDXT_FECHA_OPERACION].substring(0, 23);
			this.FECHA_OPERACION = datetimeFormatter1.parse(fecha);			
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		this.USUARIO=line[FQCaptureMeta.OFFSET_numFields+offset+FOB_DGO_CONTAB.IDXT_USUARIO];
		try {
			String fecha;
			fecha = line[FQCaptureMeta.OFFSET_numFields+offset+FOB_DGO_CONTAB.IDXT_TIMESTAMP_EJECUCION].substring(0, 23);
			this.TIMESTAMP_EJECUCION = datetimeFormatter1.parse(fecha);
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return "OB_DGO_CONTAB [DATOS_P=" + DATOS_P + ", DATOS_Q=" + DATOS_Q
				+ ", CODIGO_EMPRESA=" + CODIGO_EMPRESA + ", CODIGO_CENTRO="
				+ CODIGO_CENTRO + ", PUESTO_FISICO=" + PUESTO_FISICO
				+ ", IMPORTE_OPERACION_BANCARIA=" + IMPORTE_OPERACION_BANCARIA
				+ ", MONEDA=" + MONEDA + ", FECHA_OPERACION=" + FECHA_OPERACION
				+ ", USUARIO=" + USUARIO + ", TIMESTAMP_EJECUCION="
				+ TIMESTAMP_EJECUCION + "]";
	}
}
