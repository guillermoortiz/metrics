
package com.produban.metrics.entities;

import java.io.Serializable;

import com.produban.metrics.util.FMetrics;

public class DISPOSICION_EFECTIVO_CAJERO extends OB_DGO_CONTAB implements Serializable, FMetrics{

	/**
	 * 
	 */
	// User data, other table relevant data is part of super (OB_DGO_CONTAB)
	private String BANCO;
	private String PLAZA;
	private String PROVINCIA;
	
	private static final long serialVersionUID = -6695575859054083243L;

	// getters and setters
	public String getBANCO() {
		return BANCO;
	}

	public void setBANCO(String bANCO) {
		BANCO = bANCO;
	}

	public String getPLAZA() {
		return PLAZA;
	}

	public void setPLAZA(String pLAZA) {
		PLAZA = pLAZA;
	}

	public String getPROVINCIA() {
		return PROVINCIA;
	}

	public void setPROVINCIA(String pROVINCIA) {
		PROVINCIA = pROVINCIA;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	// Constructor
	public DISPOSICION_EFECTIVO_CAJERO(String[] produbanLine, String[] line, String[] fields) {
		// Table relevant data is on the super class
		super(produbanLine, line, fields);

		// Create proper ProdubanMeta with the name of the table
		String[] newProdubanLine = new String[3];
		newProdubanLine[0]= super.getDATOS_P().getEntidad();
		newProdubanLine[1]= super.getDATOS_P().getSistema();
		newProdubanLine[2]= super.getDATOS_P().getCelda();
		newProdubanLine[3]= COMMONS.NDISPOSICION_EFECTIVO_CAJERO;
		ProdubanMeta newPMeta = new ProdubanMeta(newProdubanLine);
		super.setDATOS_P(newPMeta);		

		// User data
		this.BANCO = line[FDISPOSICION_EFECTIVO_CAJERO.IDXU_BANCO];
		this.PLAZA = line[FDISPOSICION_EFECTIVO_CAJERO.IDXU_PLAZA];
		this.PROVINCIA = line[FDISPOSICION_EFECTIVO_CAJERO.IDXU_PROVINCIA];
		
	}	
}
