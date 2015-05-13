package com.produban.metrics.entities;

import java.io.Serializable;

public class ProdubanMeta implements Serializable {

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

		this.Entidad = produbanLine[0];
		this.Sistema = produbanLine[1];
		this.Celda = produbanLine[2];
		this.Tabla = produbanLine[3];
	}
	@Override
	public String toString() {
		return "ProdubanMeta [Entidad=" + Entidad + ", Sistema=" + Sistema
				+ ", Celda=" + Celda + ", Tabla=" + Tabla + "]";
	}

	
	
}
