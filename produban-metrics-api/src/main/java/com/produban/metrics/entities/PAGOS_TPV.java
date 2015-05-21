package com.produban.metrics.entities;

import java.io.Serializable;

public class PAGOS_TPV extends OB_DGO_CONTAB implements Serializable {



	@Override
	public String toString() {
		return "PAGOS_TPV [IDENTIFICADOR_TPV=" + IDENTIFICADOR_TPV
				+ ", NOMBRE_COMERCIO=" + NOMBRE_COMERCIO + ", LOCALIDAD="
				+ LOCALIDAD + ", toString()=" + super.toString() + "]";
	}

	/**
	 * 
	 */
	//Compras realizadas en TPV
	//260:269: Identificador del TPV         
	//270:294: Nombre del comercio
	//295:307: Localidad
	private String IDENTIFICADOR_TPV;
	private String NOMBRE_COMERCIO;
	private String LOCALIDAD;
	

	
	public String getIDENTIFICADOR_TPV() {
		return IDENTIFICADOR_TPV;
	}

	public void setIDENTIFICADOR_TPV(String iDENTIFICADOR_TPV) {
		IDENTIFICADOR_TPV = iDENTIFICADOR_TPV;
	}

	public String getNOMBRE_COMERCIO() {
		return NOMBRE_COMERCIO;
	}

	public void setNOMBRE_COMERCIO(String nOMBRE_COMERCIO) {
		NOMBRE_COMERCIO = nOMBRE_COMERCIO;
	}

	public String getLOCALIDAD() {
		return LOCALIDAD;
	}

	public void setLOCALIDAD(String lOCALIDAD) {
		LOCALIDAD = lOCALIDAD;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = -6695575859054083243L;

	public PAGOS_TPV(String[] produbanLine, String[] line) {
		super(produbanLine, line);

		String[] newProdubanLine = new String[3];
		Integer offset=0;
				
		newProdubanLine[0]= super.getDATOS_P().getEntidad();
		newProdubanLine[1]= super.getDATOS_P().getSistema();
		newProdubanLine[2]= super.getDATOS_P().getCelda();
		newProdubanLine[3]="PAGOS_TPV";
		
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
			
		
		//Atributos del DGO, se pasa ya parseado y enriquecido si es necesario
		//Compras realizadas en TPV
		//260:269: Identificador del TPV = numFields+ offset+ 28 +0         
		//270:294: Nombre del comercionum = Fields+ offset+ 28 +0
		//295:307: Localidad = numFields+ offset+ 28 +0
		this.IDENTIFICADOR_TPV=line[QCaptureMeta.numFields+offset+28].substring(260, 269);
		this.NOMBRE_COMERCIO=line[QCaptureMeta.numFields+offset+28].substring(270, 294);
		this.LOCALIDAD=line[QCaptureMeta.numFields+offset+28].substring(295, 307);
				
		
	}

	
}