package com.produban.metrics.entities;


import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.produban.metrics.util.FMetrics.FQCaptureMeta;

public class QCaptureMeta implements Serializable {
	
	/**
	 * 
	 */	
	private static final long serialVersionUID = 6910222250421531608L;

	
	public enum tipo_evento { ISRT, REPL, DLET, UKWN };
	
	private String owner;
	private String tabla;
	private tipo_evento evento;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
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
	public tipo_evento getEvento() {
		return evento;
	}
	public void setEvento(tipo_evento evento) {
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

		
		String dateStr = line[FQCaptureMeta.IDXT_datestr];
		String hourStr = line[FQCaptureMeta.IDXT_hourstr];
		
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
				
		this.owner = line[FQCaptureMeta.IDXT_owner];
		this.tabla = line[FQCaptureMeta.IDXT_tabla];
		switch (line[6].toUpperCase()){
		case "ISRT":
			this.evento = QCaptureMeta.tipo_evento.ISRT;
			break;
		case "REPL":
			this.evento = QCaptureMeta.tipo_evento.REPL;
			break;
		case "DLET":
			this.evento = QCaptureMeta.tipo_evento.DLET;
			break;
		default:
			this.evento = QCaptureMeta.tipo_evento.UKWN;			
			break;
		}
		
		this.plan = line[FQCaptureMeta.IDXT_plan];

	}
	@Override
	public String toString() {
		
		String lEvento="";
		
		switch (this.evento){
		case ISRT:
			lEvento="ISRT";
			break;
		case REPL:
			lEvento="REPL";
			break;
		case DLET:
			lEvento="DLET";
			break;
		default:
			lEvento="UKWN";			
			break;
		}
		
		return "QCaptureMeta [owner=" + owner + ", tabla=" + tabla
				+ ", evento=" + lEvento + ", fecha_hora=" + fecha_hora
				+ ", plan=" + plan + "]";
	}
	
	
	
}
