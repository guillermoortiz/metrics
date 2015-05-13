package com.produban.metrics.entities;


import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class QCaptureMeta implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6910222250421531608L;

	public static final int numFields = 11;
	
	private String owner;
	private String tabla;
	private String evento;
	private Date fecha_hora;
	private String plan;
	
	
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getTabla() {
		return tabla;
	}
	public void setTabla(String tabla) {
		this.tabla = tabla;
	}
	public String getEvento() {
		return evento;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	}
	public Date getFecha_hora() {
		return fecha_hora;
	}
	public void setFecha_hora(Date fecha_hora) {
		this.fecha_hora = fecha_hora;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	
				
	public QCaptureMeta(String[] line) {

		
		String dateStr = line[2];
		String hourStr = line[3];
		
		Calendar cal  = new GregorianCalendar();
		
		Integer valor = Integer.parseInt(dateStr.substring(0,4));
		cal.set(Calendar.YEAR,valor);
				
		valor = Integer.parseInt(dateStr.substring(4,7));
		cal.set(Calendar.DAY_OF_YEAR,valor);
		
		valor = Integer.parseInt(hourStr.substring(0,2));
		cal.set(Calendar.HOUR, valor);
		
		valor = Integer.parseInt(hourStr.substring(2,4));
		cal.set(Calendar.MINUTE , valor);
		
		valor = Integer.parseInt(hourStr.substring(4,6));
		cal.set(Calendar.SECOND , valor);
		
		valor = Integer.parseInt(hourStr.substring(6,9));
		cal.set(Calendar.MILLISECOND , valor);
		
		this.fecha_hora = cal.getTime();
				
		this.owner = line[4];
		this.tabla = line[5];
		this.evento = line[6];
		this.plan = line[10];

	}
	@Override
	public String toString() {
		return "QCaptureMeta [owner=" + owner + ", tabla=" + tabla
				+ ", evento=" + evento + ", fecha_hora=" + fecha_hora
				+ ", plan=" + plan + "]";
	}
	
	
}
