package com.produban.metrics.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.produban.api.general.Factory;
import com.produban.api.general.K;
import com.produban.api.manager.CacheManager;

/**
 * Class to parse codes in the messages with the final value through a cache.
 * 
 * @author ortizg1
 *
 */
public class FactoryParser implements KMetrics {

	// Singleton where query the final values
	public static CacheManager cache = Factory.getCacheManager();

	public static String[] parser(final String[] produbanLine,
			final String[] line) {
		String[] parseLine;
		String type = produbanLine[COMMONS.INDEX_QUEUE_NAME];

		switch (type) {
		case ULTALTA.ULTALTA:
			parseLine = parseUltalta(line);
			break;

		case PLEMORDEN.PL_EM_ORDEN:
			parseLine = parsePlEmOrden(line);
			break;

		case OBDGOCONTAB.OB_DGO_CONTAB:
			parseLine = parseObDgoContab(line);
			break;

		case HH_DATOS_BANCOS.HH_DATOS_BANCOS:
			parseLine = parseHhDatosBancos(line);
			break;

		case HH_TRANSF_EMIT.HH_TRANSF_EMIT:
			parseLine = parseHhTransfEmit(line);
			break;

		default:
			parseLine = new String[0];
			break;
		}

		return parseLine;
	}

	// Importe=188,09,Moneda=EUR,Banco Origen=BANCO SANTANDER SA,Localidad
	// Origen=ALCOBENDAS,Provincia Origen=MADRID,Pais Origen=ESPAA,Banco
	// Destino=BANQUE NATIONALE DE PARIS,Localidad Destino=BEOGRAD,Provincia
	// destino=,Pais destino=SERBIA
	private static String[] parsePlEmOrden(String[] line) {
		String[] extraParameters = new String[PLEMORDEN.EXTRA_PARAMS];

		String codBancoOrigen = line[PLEMORDEN.INDEX_BANCO_ORIGEN];
		String codBancoDestino = line[PLEMORDEN.INDEX_BANCO_DESTINO];
		String codSucursalOrigen = line[PLEMORDEN.INDEX_SUCURSAL_ORIGEN];
		String codSucursalDestino = line[PLEMORDEN.INDEX_SUCURSAL_DESTINO];

		String bancoOrigen = cache.get(codBancoOrigen,
				K.CACHE.TABLE_ENTIDAD_CREDITO_ID);
		String codProvinciaOrigen = codBancoOrigen + codSucursalOrigen;
		String[] recordOficina = cache.get(codProvinciaOrigen,
				K.CACHE.TABLE_OFICI_BANCARIA_ID).split("\\|");
		String localidadOrigen = recordOficina[0];
		String provinciaOrigen = cache.get(recordOficina[1],
				K.CACHE.TABLE_PROVINCIA_ID);
		String paisOrigen = cache.get(recordOficina[2], K.CACHE.TABLE_PAIS_ID);

		String paisDestino;
		String localidadDestino;
		String provinciaDestino = new String();
		String coordenadaOrigen;
		String coordenadaDestino;

		String codBancoSucursal = codBancoDestino + codSucursalDestino;
		String bancoDestino = cache.get(codBancoDestino,
				K.CACHE.TABLE_ENTIDAD_CREDITO_ID);
		if (!StringUtils.isEmpty(bancoDestino)) {
			String[] recordOficinaDestino = cache.get(codBancoSucursal,
					K.CACHE.TABLE_OFICI_BANCARIA_ID).split("\\|");
			localidadDestino = recordOficinaDestino[0];
			provinciaDestino = cache.get(recordOficinaDestino[1],
					K.CACHE.TABLE_PROVINCIA_ID).split("\\|")[0];
			paisDestino = cache.get(recordOficinaDestino[2],
					K.CACHE.TABLE_PAIS_ID);			
			coordenadaDestino = cache.get(localidadDestino,
					K.CACHE.TABLE_COORDENADAS_NACIONALES_ID);

		} else {
			bancoDestino = cache.get(codBancoDestino,
					K.CACHE.TABLE_ENT_CRED_EXT_ID);
			String[] recordOficinaDestino = cache.get(codBancoSucursal,
					K.CACHE.TABLE_OFI_ENT_EXT_ID).split("\\|");
			localidadDestino = recordOficinaDestino[0];
			paisDestino = cache.get(recordOficinaDestino[1],
					K.CACHE.TABLE_PAIS_ID);			
			coordenadaDestino = cache.get(localidadDestino, K.CACHE.TABLE_COORDENADAS_INTERNACIONALES_ID);
			
		}

		coordenadaOrigen = cache.get(localidadOrigen,
				K.CACHE.TABLE_COORDENADAS_NACIONALES_ID);
		if (StringUtils.isEmpty(coordenadaDestino)) {
			coordenadaDestino = "";
		}
		if (StringUtils.isEmpty(coordenadaOrigen)) {
			coordenadaOrigen = "";
		}

		extraParameters[0] = line[PLEMORDEN.INDEX_IMPORTE];
		extraParameters[1] = line[PLEMORDEN.INDEX_MONEDA];
		extraParameters[2] = bancoOrigen;
		extraParameters[3] = localidadOrigen;
		extraParameters[4] = provinciaOrigen;
		extraParameters[5] = paisOrigen;
		extraParameters[6] = bancoDestino;
		extraParameters[7] = localidadDestino;
		extraParameters[8] = provinciaDestino;
		extraParameters[9] = paisDestino;
		extraParameters[10] = coordenadaOrigen;
		extraParameters[11] = coordenadaDestino;

		return extraParameters;
	}
	
	// Codigo Empresa=0049,Codigo sucursal=0015,Nombre sucursal=ALCOBENDAS
	// URBANA CONSTITUCION,Cdigo producto=211,Producto=CREDITOS GENERALES,CoordenadasOrigen=3324,
	private static String[] parseUltalta(final String[] line) {
		String[] extraParameters = new String[ULTALTA.EXTRA_PARAMS];
		String codEmpresa = line[ULTALTA.INDEX_COD_EMPRESA];
		String codSucursal = line[ULTALTA.INDEX_COD_SUCURSAL];
		String codProducto = line[ULTALTA.INDEX_COD_PRODUCTO];
		String[] centroLocalidad = cache.get(codEmpresa + codSucursal,
				K.CACHE.TABLE_CENTRO_ID).split("\\|");
		
		extraParameters[0] = line[ULTALTA.INDEX_COD_EMPRESA];
		extraParameters[1] = line[ULTALTA.INDEX_COD_SUCURSAL];
		extraParameters[2] = centroLocalidad[0];
		
		extraParameters[3] = line[ULTALTA.INDEX_COD_PRODUCTO];
		extraParameters[4] = cache.get(codEmpresa + codProducto,
				K.CACHE.TABLE_EMPRESA_PRODUCT_ID);
		extraParameters[5] = cache.get(centroLocalidad[1],
				K.CACHE.TABLE_COORDENADAS_NACIONALES_ID);
		
		return extraParameters;
	}

	private static String[] parseObDgoContab(String[] line) {
		boolean tpv = line[OBDGOCONTAB.INDEX_CONS1]
				.contains(OBDGOCONTAB.CONS1_TPV);

		String[] extraParam;
		if (tpv) {
			extraParam = parseObDgoContabTPV(line);
		} else {
			extraParam = parseObDgoContabEfectivo(line);
		}
		return extraParam;
	}

	// Importe=10,91,Moneda=EUR,TPV=0000304100,Comercio=ARROCERIA
	// BALEAR,Localidad=ALCOBENDAS,Provincia=MADRID,Pais=ESPAA
	private static String[] parseObDgoContabTPV(final String line[]) {
		String[] extraParameters = new String[OBDGOCONTAB_TPV.EXTRA_PARAMS];
		String importe = line[OBDGOCONTAB_TPV.INDEX_IMPORTE];
		String moneda = line[OBDGOCONTAB_TPV.INDEX_MONEDA];
		String dgoParam = line[OBDGOCONTAB_TPV.INDEX_DGO_PARAMS];
		String tpv = StringUtils.substring(dgoParam, 260, 270);
		String comercio = StringUtils.substring(dgoParam, 270, 295);
		String codLocalidad = String.valueOf(Integer.parseInt(
				StringUtils.substring(dgoParam, 295, 308), 16));

		String localidad = cache.get(codLocalidad, K.CACHE.TABLE_PLAZA_ID);
		String[] recordProvincia = cache.get(codLocalidad,
				K.CACHE.TABLE_PROVINCIA_ID).split("\\|");
		String provincia = recordProvincia[0];
		String pais = cache.get(recordProvincia[1], K.CACHE.TABLE_PAIS_ID);

		String coordenadaTPV = cache.get(localidad,
				K.CACHE.TABLE_COORDENADAS_NACIONALES_ID);
		if (StringUtils.isEmpty(coordenadaTPV)) {
			coordenadaTPV = "";
		}

		extraParameters[0] = importe;
		extraParameters[1] = moneda;
		extraParameters[2] = tpv;
		extraParameters[3] = comercio;
		extraParameters[4] = localidad;
		extraParameters[5] = provincia;
		extraParameters[6] = pais;
		extraParameters[7] = coordenadaTPV;

		return extraParameters;

	}

	// Importe=10,91,Moneda=EUR,Localidad=ALCOBENDAS,Provincia=MADRID,Pais=ESPAA
	private static String[] parseObDgoContabEfectivo(final String line[]) {
		String[] extraParameters = new String[OBDGOCONTAB_EFECTIVO.EXTRA_PARAMS];
		String importe = line[OBDGOCONTAB_EFECTIVO.INDEX_IMPORTE];
		String moneda = line[OBDGOCONTAB_EFECTIVO.INDEX_MONEDA];
		String dgoParam = line[OBDGOCONTAB_EFECTIVO.INDEX_DGO_PARAMS];
		String banco = StringUtils.substring(dgoParam, 763, 793);
		String localidad = StringUtils.substring(dgoParam, 793, 808);

		String codProvincia = String.valueOf(Integer.parseInt(
				StringUtils.substring(dgoParam, 808, 810), 16));
		String[] recordProvincia = cache.get(codProvincia,
				K.CACHE.TABLE_PROVINCIA_ID).split("\\|");

		String coordenadaTPV = cache.get(localidad,
				K.CACHE.TABLE_COORDENADAS_NACIONALES_ID);
		if (StringUtils.isEmpty(coordenadaTPV)) {
			coordenadaTPV = "";
		}

		extraParameters[0] = importe;
		extraParameters[1] = moneda;
		extraParameters[2] = banco;
		extraParameters[3] = localidad;
		extraParameters[4] = recordProvincia[0];
		extraParameters[5] = recordProvincia[1];
		extraParameters[6] = coordenadaTPV;

		return extraParameters;

	}

	// Importe=11,00,Moneda=GBP,Banco origen=BANCO SANTANDER SA,Localidad
	// origen=ALCOBENDAS,Provincia origen=MADRID,Pais origen=ESPAA,Banco
	// destino=BANQUE NATIONALE DE PARIS,Localidad desttino=BEOGRAD,Provincia
	// destino=,Pais destino=SERBIA
	private static String[] parseHhTransfEmit(final String line[]) {
		List<String> extraParameters = new ArrayList<String>();

		String importe = line[HH_TRANSF_EMIT.INDEX_IMPORTE];
		String moneda = line[HH_TRANSF_EMIT.INDEX_MONEDA];
		String codBancoOrigen = line[HH_TRANSF_EMIT.INDEX_BANCO_ORIGEN];
		String codSucursalOrigen = line[HH_TRANSF_EMIT.INDEX_SUCURSAL_ORIGEN];

		String bancoOrigen = cache.get(codBancoOrigen,
				K.CACHE.TABLE_ENTIDAD_CREDITO_ID);
		String codProvinciaOrigen = codBancoOrigen + codSucursalOrigen;
		String[] recordOficina = cache.get(codProvinciaOrigen,
				K.CACHE.TABLE_OFICI_BANCARIA_ID).split("\\|");
		String localidadOrigen = recordOficina[0];
		String provinciaOrigen = cache.get(recordOficina[1],
				K.CACHE.TABLE_PROVINCIA_ID).split("\\|")[0];
		String paisOrigen = cache.get(recordOficina[2], K.CACHE.TABLE_PAIS_ID);
		String coordenadaOrigen = cache.get(localidadOrigen,
				K.CACHE.TABLE_COORDENADAS_NACIONALES_ID);
		if (StringUtils.isEmpty(coordenadaOrigen)) {
			coordenadaOrigen = "";
		}
		
		extraParameters.add(importe);
		extraParameters.add(moneda);
		extraParameters.add(bancoOrigen);
		extraParameters.add(localidadOrigen);
		extraParameters.add(provinciaOrigen);
		extraParameters.add(paisOrigen);
		extraParameters.add(coordenadaOrigen);

		return extraParameters.toArray(new String[extraParameters.size()]);

	}

	// Importe=11,00,Moneda=GBP,Banco origen=BANCO SANTANDER SA,Localidad
	// origen=ALCOBENDAS,Provincia origen=MADRID,Pais origen=ESPAA,Banco
	// destino=BANQUE NATIONALE DE PARIS,Localidad desttino=BEOGRAD,Provincia
	// destino=,Pais destino=SERBIA
	private static String[] parseHhDatosBancos(final String line[]) {
		List<String> extraParameters = new ArrayList<>();

		String codBancoDestino = line[HH_DATOS_BANCOS.INDEX_BANCO_DESTINO];
		String codSucursalDestino = line[HH_DATOS_BANCOS.INDEX_SUCURSAL_DESTINO];
		String codBancoSucursal = codBancoDestino + codSucursalDestino;

		String bancoDestino = cache.get(codBancoDestino,
				K.CACHE.TABLE_ENT_CRED_EXT_ID);
		String[] recordOficinaDestino = cache.get(codBancoSucursal,
				K.CACHE.TABLE_OFI_ENT_EXT_ID).split("\\|");
		String localidadDestino = recordOficinaDestino[0];
		String paisDestino = cache.get(recordOficinaDestino[1],
				K.CACHE.TABLE_PAIS_ID);
		String coordenadaDestino = cache.get(localidadDestino,
				K.CACHE.TABLE_COORDENADAS_INTERNACIONALES_ID);
		if (StringUtils.isEmpty(coordenadaDestino)) {
			coordenadaDestino = "";
		}
		extraParameters.add(bancoDestino);
		extraParameters.add(localidadDestino);
		extraParameters.add("");
		extraParameters.add(paisDestino);
		extraParameters.add(coordenadaDestino);

		return extraParameters.toArray(new String[extraParameters.size()]);

	}
}
