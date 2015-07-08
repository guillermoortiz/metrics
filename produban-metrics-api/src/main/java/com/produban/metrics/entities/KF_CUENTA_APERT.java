package com.produban.metrics.entities;

import com.produban.metrics.util.FMetrics;

public class KF_CUENTA_APERT implements Metrics, FMetrics {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6924987942419790118L;

	// Data from Produban
	private ProdubanMeta DATOS_P;

	// Data from Q-Capture
	private QCaptureMeta DATOS_Q;

	// User requested data
	private String CODIGO_EMPRESA;
	private String CODIGO_CENTRO;
	private String tipo_Cuenta;

	private String bancoOrigen;
	private String localidadOrigen;
	private String provinciaOrigen;
	private String paisOrigen;
	private String coordenadasOrigen;	

	@Override
	public ProdubanMeta getDATOS_P() {
		// TODO Auto-generated method stub
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

	public String getTipo_Cuenta() {
		return tipo_Cuenta;
	}

	public String getBancoOrigen() {
		return bancoOrigen;
	}

	public void setBancoOrigen(String bancoOrigen) {
		this.bancoOrigen = bancoOrigen;
	}

	public String getLocalidadOrigen() {
		return localidadOrigen;
	}

	public void setLocalidadOrigen(String localidadOrigen) {
		this.localidadOrigen = localidadOrigen;
	}

	public String getProvinciaOrigen() {
		return provinciaOrigen;
	}

	public void setProvinciaOrigen(String provinciaOrigen) {
		this.provinciaOrigen = provinciaOrigen;
	}

	public String getPaisOrigen() {
		return paisOrigen;
	}

	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}

	public String getCoordenadasOrigen() {
		return coordenadasOrigen;
	}

	public void setCoordenadasOrigen(String coordenadasOrigen) {
		this.coordenadasOrigen = coordenadasOrigen;
	}

	public KF_CUENTA_APERT(String[] produbanLine, String[] line, String[] fields) {

		int offset = 0;
		int codigo1_cuenta = 0;
		int codigo2_cuenta = 0;

		// Q-Capture metadata
		this.DATOS_Q = new QCaptureMeta(line);
		// Produban metadata
		this.DATOS_P = new ProdubanMeta(produbanLine);
		// Put table name from constants
		this.DATOS_P.setTabla(COMMONS.NKF_CUENTA_APERT);

		if (this.DATOS_Q.getEvento() == QCaptureMeta.tipo_evento.ISRT) {
			offset = FKF_CUENTA_APERT.OFFSET_ISRT;
		} else {
			offset = FPL_EM_ORDEN.OFFSET_UKWN;
		}

		this.CODIGO_EMPRESA = line[FQCaptureMeta.OFFSET_numFields + offset
				+ FKF_CUENTA_APERT.IDXT_CODIGO_EMPRESA];
		this.CODIGO_CENTRO = line[FQCaptureMeta.OFFSET_numFields + offset
				+ FKF_CUENTA_APERT.IDXT_CODIGO_CENTRO];

		try {
			codigo1_cuenta = Integer
					.parseInt(line[FQCaptureMeta.OFFSET_numFields + offset
							+ FKF_CUENTA_APERT.IDXT_CODIGO_CUENTA_1]);
			codigo2_cuenta = Integer
					.parseInt(line[FQCaptureMeta.OFFSET_numFields + offset
							+ FKF_CUENTA_APERT.IDXT_CODIGO_CUENTA_2]);
		} catch (Exception e) {
		} finally {
			if (codigo1_cuenta == FKF_CUENTA_APERT.CODIGO1_CUENTA_AHORRO
					&& codigo2_cuenta == FKF_CUENTA_APERT.CODIGO2_CUENTA_AHORRO) {
				this.tipo_Cuenta = FKF_CUENTA_APERT.TIPO_CUENTA_AHORRO;
			} else if (codigo1_cuenta == FKF_CUENTA_APERT.CODIGO1_CUENTA_VISTA
					&& codigo2_cuenta == FKF_CUENTA_APERT.CODIGO2_CUENTA_VISTA) {
				this.tipo_Cuenta = FKF_CUENTA_APERT.TIPO_CUENTA_VISTA;
			} else {
				this.tipo_Cuenta = FKF_CUENTA_APERT.TIPO_CUENTA_OTRO;
			}
		}

		
		// BancoOrigen, localidadOrigen, provinciaOrigen, PaisOrigen, CoordenadasOrigen
		this.bancoOrigen=fields[FKF_CUENTA_APERT.IDX_BANCO_ORIGEN];
		this.localidadOrigen=fields[FKF_CUENTA_APERT.IDX_LOCALIDAD_ORIGEN];
		this.provinciaOrigen=fields[FKF_CUENTA_APERT.IDX_PROVINCIA_ORIGEN];
		this.paisOrigen=fields[FKF_CUENTA_APERT.IDX_PAIS_ORIGEN];
		this.coordenadasOrigen=fields[FKF_CUENTA_APERT.IDX_COORDENADAS_ORIGEN];		
	}


	@Override
	public String toString() {
		return "KF_CUENTA_APERT [DATOS_P=" + DATOS_P + ", DATOS_Q=" + DATOS_Q
				+ ", CODIGO_EMPRESA=" + CODIGO_EMPRESA + ", CODIGO_CENTRO="
				+ CODIGO_CENTRO + ", tipo_Cuenta=" + tipo_Cuenta
				+ ", bancoOrigen=" + bancoOrigen + ", localidadOrigen="
				+ localidadOrigen + ", provinciaOrigen=" + provinciaOrigen
				+ ", paisOrigen=" + paisOrigen + ", coordenadasOrigen="
				+ coordenadasOrigen + "]";
	}

	public void setTipo_Cuenta(String tipo_Cuenta) {
		this.tipo_Cuenta = tipo_Cuenta;
	}

}
