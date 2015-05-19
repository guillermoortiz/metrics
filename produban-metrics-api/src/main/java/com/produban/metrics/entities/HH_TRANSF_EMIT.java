package com.produban.metrics.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HH_TRANSF_EMIT implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1754638693393220961L;
	
	// Data from Produban
	private ProdubanMeta DATOS_P;
	
	// Data from Q-Capture
	private QCaptureMeta DATOS_Q;
	
	private String CODIGO_EMPRESA;	
	private String CODIGO_CENTRO;
	private String CODIGO_PRODUCTO;
	  // (IMP.PAGO O IMP.CONTRAVALOR)
	private String INDICADOR_PETICION;
	private BigDecimal IMPORTE_DIVISA_PAGO;
	private String CODIGO_MONEDA_IMPORTE_ORDENADO;
	private BigDecimal IMPORTE_CONTRAVALOR;
	private String CODIGO_MONEDA_IMPORTE_CONTRAVALOR;
	private BigDecimal IMPORTE_ORDENADO;
	private String CODIGO_MONEDA_ORDEN;
	  // (O-ORD./B-BENEF/C=COMIS ORD GTS BENEF/G=GTS ORD COMIS BENEF)
	private String GASTOS_EMISION;
	  //  DE: OUR-ORD/BEN-BENEF/SHR-REPARTIR
	private String INDIC_GASTOS_FUERA_PAIS_EMISOR_POR_CTA;
	  //(MARCAJE, PERIODICA, ETC)
	private String PROCEDENCIA_TRANSFERENCIA;
	private Date FECHA_ALTA_TRANSFERENCIA;
	private Date FECHA_VALOR_ABONO_TRANSFERENCIA;
	private Date FECHA_VALOR_ADEUDO_TRANSFERENCIA;
	private Date HHG_TIMESTAMP;
	private String BANCO_RECEPTOR;
	private String OFICINA_RECEPTORA;
	  // DEL PAGO
	private String CODIGO_PAIS_DESTINO;
	private String INDICADOR_RESIDENCIA_BENEFICIARIO;
	  // EN BANCA ELECTRONICA
	private String CODIGO_CANAL;
	private String CODIGO_USUARIO_ULTIMA_MODIFICACION;
	private Date  FECHA_ULTIMA_MODIFICACION;

	

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
	public String getCODIGO_PRODUCTO() {
		return CODIGO_PRODUCTO;
	}
	public void setCODIGO_PRODUCTO(String cODIGO_PRODUCTO) {
		CODIGO_PRODUCTO = cODIGO_PRODUCTO;
	}
	public String getINDICADOR_PETICION() {
		return INDICADOR_PETICION;
	}
	public void setINDICADOR_PETICION(String iNDICADOR_PETICION) {
		INDICADOR_PETICION = iNDICADOR_PETICION;
	}
	public BigDecimal getIMPORTE_DIVISA_PAGO() {
		return IMPORTE_DIVISA_PAGO;
	}
	public void setIMPORTE_DIVISA_PAGO(BigDecimal iMPORTE_DIVISA_PAGO) {
		IMPORTE_DIVISA_PAGO = iMPORTE_DIVISA_PAGO;
	}
	public String getCODIGO_MONEDA_IMPORTE_ORDENADO() {
		return CODIGO_MONEDA_IMPORTE_ORDENADO;
	}
	public void setCODIGO_MONEDA_IMPORTE_ORDENADO(
			String cODIGO_MONEDA_IMPORTE_ORDENADO) {
		CODIGO_MONEDA_IMPORTE_ORDENADO = cODIGO_MONEDA_IMPORTE_ORDENADO;
	}
	public BigDecimal getIMPORTE_CONTRAVALOR() {
		return IMPORTE_CONTRAVALOR;
	}
	public void setIMPORTE_CONTRAVALOR(BigDecimal iMPORTE_CONTRAVALOR) {
		IMPORTE_CONTRAVALOR = iMPORTE_CONTRAVALOR;
	}
	public String getCODIGO_MONEDA_IMPORTE_CONTRAVALOR() {
		return CODIGO_MONEDA_IMPORTE_CONTRAVALOR;
	}
	public void setCODIGO_MONEDA_IMPORTE_CONTRAVALOR(
			String cODIGO_MONEDA_IMPORTE_CONTRAVALOR) {
		CODIGO_MONEDA_IMPORTE_CONTRAVALOR = cODIGO_MONEDA_IMPORTE_CONTRAVALOR;
	}
	public BigDecimal getIMPORTE_ORDENADO() {
		return IMPORTE_ORDENADO;
	}
	public void setIMPORTE_ORDENADO(BigDecimal iMPORTE_ORDENADO) {
		IMPORTE_ORDENADO = iMPORTE_ORDENADO;
	}
	public String getCODIGO_MONEDA_ORDEN() {
		return CODIGO_MONEDA_ORDEN;
	}
	public void setCODIGO_MONEDA_ORDEN(String cODIGO_MONEDA_ORDEN) {
		CODIGO_MONEDA_ORDEN = cODIGO_MONEDA_ORDEN;
	}
	public String getGASTOS_EMISION() {
		return GASTOS_EMISION;
	}
	public void setGASTOS_EMISION(String gASTOS_EMISION) {
		GASTOS_EMISION = gASTOS_EMISION;
	}
	public String getINDIC_GASTOS_FUERA_PAIS_EMISOR_POR_CTA() {
		return INDIC_GASTOS_FUERA_PAIS_EMISOR_POR_CTA;
	}
	public void setINDIC_GASTOS_FUERA_PAIS_EMISOR_POR_CTA(
			String iNDIC_GASTOS_FUERA_PAIS_EMISOR_POR_CTA) {
		INDIC_GASTOS_FUERA_PAIS_EMISOR_POR_CTA = iNDIC_GASTOS_FUERA_PAIS_EMISOR_POR_CTA;
	}
	public String getPROCEDENCIA_TRANSFERENCIA() {
		return PROCEDENCIA_TRANSFERENCIA;
	}
	public void setPROCEDENCIA_TRANSFERENCIA(String pROCEDENCIA_TRANSFERENCIA) {
		PROCEDENCIA_TRANSFERENCIA = pROCEDENCIA_TRANSFERENCIA;
	}
	public Date getFECHA_ALTA_TRANSFERENCIA() {
		return FECHA_ALTA_TRANSFERENCIA;
	}
	public void setFECHA_ALTA_TRANSFERENCIA(Date fECHA_ALTA_TRANSFERENCIA) {
		FECHA_ALTA_TRANSFERENCIA = fECHA_ALTA_TRANSFERENCIA;
	}
	public Date getFECHA_VALOR_ABONO_TRANSFERENCIA() {
		return FECHA_VALOR_ABONO_TRANSFERENCIA;
	}
	public void setFECHA_VALOR_ABONO_TRANSFERENCIA(
			Date fECHA_VALOR_ABONO_TRANSFERENCIA) {
		FECHA_VALOR_ABONO_TRANSFERENCIA = fECHA_VALOR_ABONO_TRANSFERENCIA;
	}
	public Date getFECHA_VALOR_ADEUDO_TRANSFERENCIA() {
		return FECHA_VALOR_ADEUDO_TRANSFERENCIA;
	}
	public void setFECHA_VALOR_ADEUDO_TRANSFERENCIA(
			Date fECHA_VALOR_ADEUDO_TRANSFERENCIA) {
		FECHA_VALOR_ADEUDO_TRANSFERENCIA = fECHA_VALOR_ADEUDO_TRANSFERENCIA;
	}
	public Date getHHG_TIMESTAMP() {
		return HHG_TIMESTAMP;
	}
	public void setHHG_TIMESTAMP(Date hHG_TIMESTAMP) {
		HHG_TIMESTAMP = hHG_TIMESTAMP;
	}
	public String getBANCO_RECEPTOR() {
		return BANCO_RECEPTOR;
	}
	public void setBANCO_RECEPTOR(String bANCO_RECEPTOR) {
		BANCO_RECEPTOR = bANCO_RECEPTOR;
	}
	public String getOFICINA_RECEPTORA() {
		return OFICINA_RECEPTORA;
	}
	public void setOFICINA_RECEPTORA(String oFICINA_RECEPTORA) {
		OFICINA_RECEPTORA = oFICINA_RECEPTORA;
	}
	public String getCODIGO_PAIS_DESTINO() {
		return CODIGO_PAIS_DESTINO;
	}
	public void setCODIGO_PAIS_DESTINO(String cODIGO_PAIS_DESTINO) {
		CODIGO_PAIS_DESTINO = cODIGO_PAIS_DESTINO;
	}
	public String getINDICADOR_RESIDENCIA_BENEFICIARIO() {
		return INDICADOR_RESIDENCIA_BENEFICIARIO;
	}
	public void setINDICADOR_RESIDENCIA_BENEFICIARIO(
			String iNDICADOR_RESIDENCIA_BENEFICIARIO) {
		INDICADOR_RESIDENCIA_BENEFICIARIO = iNDICADOR_RESIDENCIA_BENEFICIARIO;
	}
	public String getCODIGO_CANAL() {
		return CODIGO_CANAL;
	}
	public void setCODIGO_CANAL(String cODIGO_CANAL) {
		CODIGO_CANAL = cODIGO_CANAL;
	}
	public String getCODIGO_USUARIO_ULTIMA_MODIFICACION() {
		return CODIGO_USUARIO_ULTIMA_MODIFICACION;
	}
	public void setCODIGO_USUARIO_ULTIMA_MODIFICACION(
			String cODIGO_USUARIO_ULTIMA_MODIFICACION) {
		CODIGO_USUARIO_ULTIMA_MODIFICACION = cODIGO_USUARIO_ULTIMA_MODIFICACION;
	}
	public Date getFECHA_ULTIMA_MODIFICACION() {
		return FECHA_ULTIMA_MODIFICACION;
	}
	public void setFECHA_ULTIMA_MODIFICACION(Date fECHA_ULTIMA_MODIFICACION) {
		FECHA_ULTIMA_MODIFICACION = fECHA_ULTIMA_MODIFICACION;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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


	public HH_TRANSF_EMIT(String[] produbanLine, String[] line) {
		// Add QCaptureMeta.numFields for field offset		
				
		this.DATOS_Q = new QCaptureMeta(line);
		
		Integer offset=0;
		
		
		// Insert events add some irrelevant fields we need to offset
		if (this.DATOS_Q.getEvento() == QCaptureMeta.tipo_evento.ISRT)
		{
			offset = 40;
		}
		else
		{
			offset = 0;
		}
		
		this.DATOS_P = new ProdubanMeta(produbanLine);
		
		this.CODIGO_EMPRESA=line[QCaptureMeta.numFields+offset+1];
		this.CODIGO_CENTRO=line[QCaptureMeta.numFields+offset+2];
		this.CODIGO_PRODUCTO=line[QCaptureMeta.numFields+offset+3];
		  // (IMP.PAGO O IMP.CONTRAVALOR)
		this.INDICADOR_PETICION=line[QCaptureMeta.numFields+offset+8];
		
		DecimalFormat df = new DecimalFormat();
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		
		dfs.setDecimalSeparator(',');		
		df.setDecimalFormatSymbols(dfs);
		String importe="";
		Number Nimporte;
		
	
		try {
			importe = line[QCaptureMeta.numFields+offset+9];
			Nimporte = df.parse(importe);
			this.IMPORTE_DIVISA_PAGO = new BigDecimal(Nimporte.toString());			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

				
		this.CODIGO_MONEDA_IMPORTE_ORDENADO=line[QCaptureMeta.numFields+offset+10];
		
		try {
			importe = line[QCaptureMeta.numFields+offset+11];
			Nimporte = df.parse(importe);
			this.IMPORTE_CONTRAVALOR = new BigDecimal(Nimporte.toString());			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.CODIGO_MONEDA_IMPORTE_CONTRAVALOR=line[QCaptureMeta.numFields+offset+12];
		
		try {
			importe = line[QCaptureMeta.numFields+offset+13];
			Nimporte = df.parse(importe);
			this.IMPORTE_ORDENADO = new BigDecimal(Nimporte.toString());			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.CODIGO_MONEDA_ORDEN=line[QCaptureMeta.numFields+offset+14];
		  // (O-ORD./B-BENEF/C=COMIS ORD GTS BENEF/G=GTS ORD COMIS BENEF)
		this.GASTOS_EMISION=line[QCaptureMeta.numFields+offset+16];
		  //  DE: OUR-ORD/BEN-BENEF/SHR-REPARTIR
		this.INDIC_GASTOS_FUERA_PAIS_EMISOR_POR_CTA=line[QCaptureMeta.numFields+offset+17];
		  //(MARCAJE, PERIODICA, ETC)
		this.PROCEDENCIA_TRANSFERENCIA=line[QCaptureMeta.numFields+offset+18];
		
		SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss.SSS");
		
		try {
			String fecha;
			fecha = line[QCaptureMeta.numFields+offset+21].substring(0, 23);
			this.FECHA_ALTA_TRANSFERENCIA = datetimeFormatter1.parse(fecha);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	
		
		try {
			String fecha;
			fecha = line[QCaptureMeta.numFields+offset+22].substring(0, 23);
			this.FECHA_VALOR_ABONO_TRANSFERENCIA=datetimeFormatter1.parse(fecha);
			

		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		try {
			String fecha;
			fecha = line[QCaptureMeta.numFields+offset+23].substring(0, 23);
			this.FECHA_VALOR_ADEUDO_TRANSFERENCIA = datetimeFormatter1.parse(fecha);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		

		try {
			String fecha;
			fecha = line[QCaptureMeta.numFields+offset+24].substring(0, 23);
			this.HHG_TIMESTAMP = datetimeFormatter1.parse(fecha);
			

		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		this.BANCO_RECEPTOR=line[QCaptureMeta.numFields+offset+27];
		this.OFICINA_RECEPTORA=line[QCaptureMeta.numFields+offset+28];
		  // DEL PAGO

		this.CODIGO_PAIS_DESTINO=line[QCaptureMeta.numFields+offset+29];
		this.INDICADOR_RESIDENCIA_BENEFICIARIO=line[QCaptureMeta.numFields+offset+30];
		  // EN BANCA ELECTRONICA
		this.CODIGO_CANAL=line[QCaptureMeta.numFields+offset+32];
		this.CODIGO_USUARIO_ULTIMA_MODIFICACION=line[QCaptureMeta.numFields+offset+37];
		
		try {
			String fecha;
			fecha = line[QCaptureMeta.numFields+offset+38].substring(0, 23);
			this.FECHA_ULTIMA_MODIFICACION= datetimeFormatter1.parse(fecha);
			

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.DATOS_P.setTabla("TRANSFERENCIAS_INTERNACIONALES");
		
	}
	@Override
	public String toString() {
		return "HH_TRANSF_EMIT [DATOS_P=" + DATOS_P.toString() + ", DATOS_Q=" + DATOS_Q.toString()
				+ ", CODIGO_EMPRESA=" + CODIGO_EMPRESA + ", CODIGO_CENTRO="
				+ CODIGO_CENTRO + ", CODIGO_PRODUCTO=" + CODIGO_PRODUCTO
				+ ", INDICADOR_PETICION=" + INDICADOR_PETICION
				+ ", IMPORTE_DIVISA_PAGO=" + IMPORTE_DIVISA_PAGO
				+ ", CODIGO_MONEDA_IMPORTE_ORDENADO="
				+ CODIGO_MONEDA_IMPORTE_ORDENADO + ", IMPORTE_CONTRAVALOR="
				+ IMPORTE_CONTRAVALOR + ", CODIGO_MONEDA_IMPORTE_CONTRAVALOR="
				+ CODIGO_MONEDA_IMPORTE_CONTRAVALOR + ", IMPORTE_ORDENADO="
				+ IMPORTE_ORDENADO + ", CODIGO_MONEDA_ORDEN="
				+ CODIGO_MONEDA_ORDEN + ", GASTOS_EMISION=" + GASTOS_EMISION
				+ ", INDIC_GASTOS_FUERA_PAIS_EMISOR_POR_CTA="
				+ INDIC_GASTOS_FUERA_PAIS_EMISOR_POR_CTA
				+ ", PROCEDENCIA_TRANSFERENCIA=" + PROCEDENCIA_TRANSFERENCIA
				+ ", FECHA_ALTA_TRANSFERENCIA=" + FECHA_ALTA_TRANSFERENCIA
				+ ", FECHA_VALOR_ABONO_TRANSFERENCIA="
				+ FECHA_VALOR_ABONO_TRANSFERENCIA
				+ ", FECHA_VALOR_ADEUDO_TRANSFERENCIA="
				+ FECHA_VALOR_ADEUDO_TRANSFERENCIA + ", HHG_TIMESTAMP="
				+ HHG_TIMESTAMP + ", BANCO_RECEPTOR=" + BANCO_RECEPTOR
				+ ", OFICINA_RECEPTORA=" + OFICINA_RECEPTORA
				+ ", CODIGO_PAIS_DESTINO=" + CODIGO_PAIS_DESTINO
				+ ", INDICADOR_RESIDENCIA_BENEFICIARIO="
				+ INDICADOR_RESIDENCIA_BENEFICIARIO + ", CODIGO_CANAL="
				+ CODIGO_CANAL + ", CODIGO_USUARIO_ULTIMA_MODIFICACION="
				+ CODIGO_USUARIO_ULTIMA_MODIFICACION
				+ ", FECHA_ULTIMA_MODIFICACION=" + FECHA_ULTIMA_MODIFICACION
				+ "]";
	}

}
 