package com.produban.metrics.entities;

import java.io.Serializable;

import com.produban.metrics.util.FMetrics;

public class PAGOS_TPV extends OB_DGO_CONTAB implements Serializable, FMetrics {

	// User data
	private String Importe;
	private String Moneda;
	private String TPV;
	private String Comercio;
	private String Localidad;
	private String Provincia;
	private String Pais;
	
	public String getImporte() {
		return Importe;
	}

	public void setImporte(String importe) {
		Importe = importe;
	}

	public String getMoneda() {
		return Moneda;
	}

	public void setMoneda(String moneda) {
		Moneda = moneda;
	}

	public String getTPV() {
		return TPV;
	}

	public void setTPV(String tPV) {
		TPV = tPV;
	}

	public String getComercio() {
		return Comercio;
	}

	public void setComercio(String comercio) {
		Comercio = comercio;
	}

	public String getLocalidad() {
		return Localidad;
	}

	public void setLocalidad(String localidad) {
		Localidad = localidad;
	}

	public String getProvincia() {
		return Provincia;
	}

	public void setProvincia(String provincia) {
		Provincia = provincia;
	}

	public String getPais() {
		return Pais;
	}

	public void setPais(String pais) {
		Pais = pais;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = -6695575859054083243L;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public PAGOS_TPV(String[] produbanLine, String[] line, String[] fields) {
		super(produbanLine, line, fields);

		String[] newProdubanLine = new String[3];
						
		newProdubanLine[0]= super.getDATOS_P().getEntidad();
		newProdubanLine[1]= super.getDATOS_P().getSistema();
		newProdubanLine[2]= super.getDATOS_P().getCelda();
		newProdubanLine[3]= COMMONS.NPAGOS_TPV;
		
		ProdubanMeta newPMeta = new ProdubanMeta(newProdubanLine);
		
		super.setDATOS_P(newPMeta);		
		
		// User data
		this.Importe = fields[FPAGOS_TPV.IDXU_Importe];
		this.Moneda = fields[FPAGOS_TPV.IDXU_Moneda];
		this.TPV = fields[FPAGOS_TPV.IDXU_TPV];
		this.Comercio = fields[FPAGOS_TPV.IDXU_Comercio];
		this.Localidad = fields[FPAGOS_TPV.IDXU_Localidad];
		this.Provincia = fields[FPAGOS_TPV.IDXU_Provincia];
		this.Pais = fields[FPAGOS_TPV.IDXU_Pais];
	}	
}