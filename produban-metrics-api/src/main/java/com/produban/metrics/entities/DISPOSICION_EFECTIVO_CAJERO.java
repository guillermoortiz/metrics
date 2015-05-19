package com.produban.metrics.entities;

public class DISPOSICION_EFECTIVO_CAJERO extends OB_DGO_CONTAB{

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

	/**
	 * 
	 */
	private String BANCO;
	private String PLAZA;
	private String PROVINCIA;
	
	private static final long serialVersionUID = -6695575859054083243L;

	public DISPOSICION_EFECTIVO_CAJERO(String[] produbanLine, String[] line) {
		super(produbanLine, line);

		String[] newProdubanLine = new String[3];
		Integer offset=0;
				
		
		newProdubanLine[0]= super.getDATOS_P().getEntidad();
		newProdubanLine[1]= super.getDATOS_P().getSistema();
		newProdubanLine[2]= super.getDATOS_P().getCelda();
		newProdubanLine[3]="DISPOSICION_EFECTIVO_CAJERO";
		
		ProdubanMeta newPMeta = new ProdubanMeta(newProdubanLine);
		
		super.setDATOS_P(newPMeta);		

		// Insert events add some irrelevant fields we need to offset
		if (super.getDATOS_Q().getEvento() == QCaptureMeta.tipo_evento.ISRT)
		{
			offset = 29;
		}
		else
		{
			offset = 0;
		}
			
		// Atributos del DGO, se pasa ya parseado y enriquecido si es necesario 
		// Disposiciones de efectivo en cajero
		// 763:792 Banco -> campo numFields+offset+28+0
		// 793:807 Plaza -> campo numFields+offset+28+1
		// 808:809 Provincia -> campo numFields+offset+28+2
		this.BANCO = line[QCaptureMeta.numFields+offset+28];
		this.PLAZA = line[QCaptureMeta.numFields+offset+28+1];
		this.PROVINCIA = line[QCaptureMeta.numFields+offset+28+2];
		
		
	}
	
}
