package com.produban.metrics.entities;

import com.produban.metrics.util.FMetrics;

// boton derecho, source, generar toString y generar set y get
// ULTALTA HH_TRANSF_EMIT PL_EM_ORDEN HH_DATOS_BANCOS OB_DGO_OPERAB_1 OB_DGO_OPERAB_2 OB_DGO_CONTAB


public class ULTALTA implements Metrics, FMetrics {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6063117346759975633L;
	
	// Data from Produban
	private ProdubanMeta DATOS_P;

	// Data from Q-Capture	
	private QCaptureMeta DATOS_Q;
	
	// Table relevant data
	// EMPRESA	
	private String UCA_CODEMPRE;
	// CODIGO DE CENTRO
	private String UCA_CODSUC;
	// TIPO DE PRODUCTO
	private String UCA_TIPOP;
	// ULTIMA CLAVE DE CUENTA DADA DE ALTA
	private String UCA_ULTALTA;
	// ULTIMA CLAVE DE CUENTA DADA DE ALTA PARA LA  APLICACION DE CAMARA
	private String UCA_CONTRLOC;

	// User requested data
	private String CodigoEmpresa;
	private String CodigoSucursal;	
	private String NombreSucursal;
	private String CodigoProducto;
	private String Producto;
	
	
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
	
	public String getCodigoEmpresa() {
		return CodigoEmpresa;
	}
	public void setCodigoEmpresa(String codigoEmpresa) {
		CodigoEmpresa = codigoEmpresa;
	}
	public String getCodigoSucursal() {
		return CodigoSucursal;
	}
	public void setCodigoSucursal(String codigoSucursal) {
		CodigoSucursal = codigoSucursal;
	}
	public String getNombreSucursal() {
		return NombreSucursal;
	}
	public void setNombreSucursal(String nombreSucursal) {
		NombreSucursal = nombreSucursal;
	}
	public String getCodigoProducto() {
		return CodigoProducto;
	}
	public void setCodigoProducto(String codigoProducto) {
		CodigoProducto = codigoProducto;
	}
	public String getProducto() {
		return Producto;
	}
	public void setProducto(String producto) {
		Producto = producto;
	}
		
	public ULTALTA(String[] produbanLine, String[] line, String[] fields) {

		Integer offset=0;
		
		// Data relevant to Q-Capture
		this.DATOS_Q = new QCaptureMeta(line);
		
		// Produban relevant data
		this.DATOS_P = new ProdubanMeta(produbanLine);
		
		// Put table name
		this.DATOS_P.setTabla(COMMONS.NULTALTA);
				
		// Insert events add some irrelevant fields we need to offset
		if (this.DATOS_Q.getEvento() == QCaptureMeta.tipo_evento.ISRT)
		{
			offset = FULTALTA.OFFSET_ISRT;
		}
		else
		{
			offset = FULTALTA.OFFSET_UKWN;
		}
		
		// Table relevant data
		this.UCA_CODEMPRE = line[FQCaptureMeta.OFFSET_numFields+offset+FULTALTA.IDXT_UCA_CODEMPRE];
		this.UCA_CODSUC = line[FQCaptureMeta.OFFSET_numFields+offset+FULTALTA.IDXT_UCA_CODSUC];
		this.UCA_CONTRLOC = line[FQCaptureMeta.OFFSET_numFields+offset+FULTALTA.IDXT_UCA_CONTRLOC];
		this.UCA_TIPOP = line[FQCaptureMeta.OFFSET_numFields+offset+FULTALTA.IDXT_UCA_TIPOP];
		this.UCA_ULTALTA  = line[FQCaptureMeta.OFFSET_numFields+offset+FULTALTA.IDXT_UCA_ULTALTA];
		
		// User requested data
		this.CodigoEmpresa=fields[FULTALTA.IDXU_CodigoEmpresa];
		this.CodigoSucursal=fields[FULTALTA.IDXU_CodigoSucursal];
		this.NombreSucursal=fields[FULTALTA.IDXU_NombreSucursal];
		this.CodigoProducto=fields[FULTALTA.IDXU_CodigoProducto];
		this.Producto=fields[FULTALTA.IDXU_Producto];
		
	}

	@Override
	public String toString() {
		return "ULTALTA [DATOS_P=" + DATOS_P + ", DATOS_Q=" + DATOS_Q
				+ ", UCA_CODEMPRE=" + UCA_CODEMPRE + ", UCA_CODSUC="
				+ UCA_CODSUC + ", UCA_TIPOP=" + UCA_TIPOP + ", UCA_ULTALTA="
				+ UCA_ULTALTA + ", UCA_CONTRLOC=" + UCA_CONTRLOC
				+ ", CodigoEmpresa=" + CodigoEmpresa + ", CodigoSucursal="
				+ CodigoSucursal + ", NombreSucursal=" + NombreSucursal
				+ ", CodigoProducto=" + CodigoProducto + ", Producto="
				+ Producto + "]";
	}
	


}
