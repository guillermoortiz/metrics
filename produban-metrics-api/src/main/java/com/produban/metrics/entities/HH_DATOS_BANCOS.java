package com.produban.metrics.entities;

import java.io.Serializable;

import com.produban.metrics.util.FMetrics;
import com.produban.metrics.util.FMetrics.COMMONS;
import com.produban.metrics.util.FMetrics.FHH_TRANSF_EMIT;
import com.produban.metrics.util.FMetrics.FQCaptureMeta;

public class HH_DATOS_BANCOS implements Serializable, FMetrics {
	private String Banco_destino;
	private String Localidad_destino;
	private String Provincia_destino;
	private String Pais_destino;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1754638693393220961L;

	// Data from Produban
	private ProdubanMeta DATOS_P;

	// Data from Q-Capture
	private QCaptureMeta DATOS_Q;

	public HH_DATOS_BANCOS(String[] produbanLine, String[] line, String[] fields) {

		// Create Q-Capture metadata
		this.DATOS_Q = new QCaptureMeta(line);

		// Insert events add some irrelevant fields we need to offset
		Integer offset = 0;
		if (this.DATOS_Q.getEvento().equals(QCaptureMeta.tipo_evento.ISRT)) {
			offset = FHH_TRANSF_EMIT.OFFSET_ISRT;
		} else {
			offset = FHH_TRANSF_EMIT.OFFSET_UKWN;
		}

		// Create Produban metatada
		this.DATOS_P = new ProdubanMeta(produbanLine);
		// Set table name form constants
		this.DATOS_P.setTabla(COMMONS.NHH_DATOS_BANCOS);

		this.Banco_destino = fields[FHH_DATOS_BANCOS.IDXU_Banco_destino];
		this.Localidad_destino = fields[FHH_DATOS_BANCOS.IDXU_Localidad_destino];
		this.Provincia_destino = fields[FHH_DATOS_BANCOS.IDXU_Provincia_destino];
		this.Pais_destino = fields[FHH_DATOS_BANCOS.IDXU_Pais_destino];
	}

	public String getBanco_destino() {
		return Banco_destino;
	}

	public void setBanco_destino(String banco_destino) {
		Banco_destino = banco_destino;
	}

	public String getLocalidad_destino() {
		return Localidad_destino;
	}

	public void setLocalidad_destino(String localidad_destino) {
		Localidad_destino = localidad_destino;
	}

	public String getProvincia_destino() {
		return Provincia_destino;
	}

	public void setProvincia_destino(String provincia_destino) {
		Provincia_destino = provincia_destino;
	}

	public String getPais_destino() {
		return Pais_destino;
	}

	public void setPais_destino(String pais_destino) {
		Pais_destino = pais_destino;
	}

	@Override
	public String toString() {
		return "HH_DATOS_BANCOS [Banco_destino=" + Banco_destino
				+ ", Localidad_destino=" + Localidad_destino
				+ ", Provincia_destino=" + Provincia_destino
				+ ", Pais_destino=" + Pais_destino + ", DATOS_P=" + DATOS_P
				+ ", DATOS_Q=" + DATOS_Q + "]";
	}
}
