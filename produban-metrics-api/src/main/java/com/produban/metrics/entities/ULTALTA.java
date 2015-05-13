package com.produban.metrics.entities;

import java.io.Serializable;

// boton derecho, source, generar toString y generar set y get
// ULTALTA HH_TRANSF_EMIT PL_EM_ORDEN HH_DATOS_BANCOS OB_DGO_OPERAB_1 OB_DGO_OPERAB_2 OB_DGO_CONTAB


public class ULTALTA implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6063117346759975633L;
	
	// Data from Produban
	private ProdubanMeta DATOS_P;

	
	private QCaptureMeta DATOS_Q;
	// Data from Q-Capture	
	private String UCA_CODEMPRE;
	// EMPRESA	
	private String UCA_CODSUC;
	// CODIGO DE CENTRO
	private String UCA_TIPOP;
	// TIPO DE PRODUCTO
	private String UCA_ULTALTA;
	// ULTIMA CLAVE DE CUENTA DADA DE ALTA
	private String UCA_CONTRLOC;
	// ULTIMA CLAVE DE CUENTA DADA DE ALTA PARA LA  APLICACION DE CAMARA
	
	
	public QCaptureMeta getDATOS_Q() {
		return DATOS_Q;
	}
	public void setDATOS_Q(QCaptureMeta dATOS_Q) {
		DATOS_Q = dATOS_Q;
	}
	public String getUCA_CODEMPRE() {
		return UCA_CODEMPRE;
	}
	public void setUCA_CODEMPRE(String uCA_CODEMPRE) {
		UCA_CODEMPRE = uCA_CODEMPRE;
	}
	public String getUCA_CODSUC() {
		return UCA_CODSUC;
	}
	public void setUCA_CODSUC(String uCA_CODSUC) {
		UCA_CODSUC = uCA_CODSUC;
	}
	public String getUCA_TIPOP() {
		return UCA_TIPOP;
	}
	public void setUCA_TIPOP(String uCA_TIPOP) {
		UCA_TIPOP = uCA_TIPOP;
	}
	public String getUCA_ULTALTA() {
		return UCA_ULTALTA;
	}
	public void setUCA_ULTALTA(String uCA_ULTALTA) {
		UCA_ULTALTA = uCA_ULTALTA;
	}
	public String getUCA_CONTRLOC() {
		return UCA_CONTRLOC;
	}
	public void setUCA_CONTRLOC(String uCA_CONTRLOC) {
		UCA_CONTRLOC = uCA_CONTRLOC;
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
	

	public ULTALTA() {
		super();
	}
	
	public ULTALTA(String[] line, String[] line2) {

		
		this.DATOS_P = new ProdubanMeta(line);
		
		this.DATOS_Q = new QCaptureMeta(line2);
		
		this.UCA_CODEMPRE = line[QCaptureMeta.numFields+1];
		this.UCA_CODSUC = line[QCaptureMeta.numFields+2];
		this.UCA_CONTRLOC = line[QCaptureMeta.numFields+3];
		this.UCA_TIPOP = line[QCaptureMeta.numFields+4];
		this.UCA_ULTALTA  = line[QCaptureMeta.numFields+5];
		
	}
	@Override
	public String toString() {
		return "ULTALTA [DATOS_P=" + DATOS_P.toString() + ", DATOS_Q=" + DATOS_Q.toString()				
				+ ", UCA_CODEMPRE=" + UCA_CODEMPRE + ", UCA_CODSUC="
				+ UCA_CODSUC + ", UCA_TIPOP=" + UCA_TIPOP + ", UCA_ULTALTA="
				+ UCA_ULTALTA + ", UCA_CONTRLOC=" + UCA_CONTRLOC + "]";
	}

	


}
