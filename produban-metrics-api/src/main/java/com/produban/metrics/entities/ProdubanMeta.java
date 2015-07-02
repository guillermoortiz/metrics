package com.produban.metrics.entities;

import java.io.Serializable;

import com.produban.metrics.util.FMetrics;

public class ProdubanMeta implements Serializable, FMetrics {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3347511061225457848L;
	
	private String Entidad;
	private String Sistema;
	private String Celda;	
	private String Tabla;
		
	public String getEntidad() {
		return Entidad;
	}
	public void setEntidad(String entidad) {
		Entidad = entidad;
	}
	public String getSistema() {
		return Sistema;
	}
	public void setSistema(String sistema) {
		Sistema = sistema;
	}
	public String getCelda() {
		return Celda;
	}
	public void setCelda(String celda) {
		Celda = celda;
	}
	public String getTabla() {
		return Tabla;
	}
	public void setTabla(String tabla) {
		Tabla = tabla;
	}
	
	public ProdubanMeta(String[] produbanLine) {

		// Produban metadata
		this.Entidad = produbanLine[FProdubanMeta.IDXT_Entidad];
		this.Sistema = produbanLine[FProdubanMeta.IDXT_Sistema];
		this.Celda = produbanLine[FProdubanMeta.IDXT_Celda];
		this.Tabla = produbanLine[FProdubanMeta.IDXT_Tabla];
	}
	
	@Override
	public String toString() {
		return "ProdubanMeta [Entidad=" + Entidad + ", Sistema=" + Sistema
				+ ", Celda=" + Celda + ", Tabla=" + Tabla + "]";
	}

	
	
}
