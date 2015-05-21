package com.produban.metrics.util;

import org.apache.commons.codec.binary.Hex;
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

		default:
			parseLine = new String[0];
			break;
		}

		return parseLine;
	}

	// Importe=188,09,Moneda=EUR,Banco Origen=BANCO SANTANDER SA,Localidad
	// Origen=ALCOBENDAS,Provincia Origen=MADRID,País Origen=ESPAÑA,Banco
	// Destino=BANQUE NATIONALE DE PARIS,Localidad Destino=BEOGRAD,Provincia
	// destino=,País destino=SERBIA
	public static String[] parsePlEmOrden(String[] line) {
		String[] extraParameters = new String[PLEMORDEN.EXTRA_PARAMS];

		String codBancoOrigen = line[PLEMORDEN.INDEX_BANCO_ORIGEN].replace(
				"\"", "");
		String codBancoDestino = line[PLEMORDEN.INDEX_BANCO_DESTINO].replace(
				"\"", "");
		String codSucursalOrigen = line[PLEMORDEN.INDEX_SUCURSAL_ORIGEN]
				.replace("\"", "");
		String codSucursalDestino = line[PLEMORDEN.INDEX_SUCURSAL_DESTINO]
				.replace("\"", "");

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
		String provinciaDestino = new String();
		String localidadDestino;

		String codBancoSucursal = codBancoDestino + codSucursalDestino;
		String bancoDestino = cache.get(codBancoDestino,
				K.CACHE.TABLE_ENTIDAD_CREDITO_ID);
		if (!StringUtils.isEmpty(bancoDestino)) {
			String[] recordOficinaDestino = cache.get(codBancoSucursal,
					K.CACHE.TABLE_OFICI_BANCARIA_ID).split("\\|");
			localidadDestino = recordOficinaDestino[0];
			provinciaDestino = cache.get(recordOficinaDestino[1],
					K.CACHE.TABLE_PROVINCIA_ID);
			paisDestino = cache.get(recordOficinaDestino[2],
					K.CACHE.TABLE_PAIS_ID);
		} else {
			bancoDestino = cache.get(codBancoDestino,
					K.CACHE.TABLE_ENT_CRED_EXT_ID);
			String[] recordOficinaDestino = cache.get(codBancoSucursal,
					K.CACHE.TABLE_OFI_ENT_EXT_ID).split("\\|");
			localidadDestino = recordOficinaDestino[0];
			paisDestino = cache.get(recordOficinaDestino[1],
					K.CACHE.TABLE_PAIS_ID);
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

		return extraParameters;
	}

	// Codigo Empresa=0049,Codigo sucursal=0015,Nombre sucursal=ALCOBENDAS
	// URBANA CONSTITUCION,Código producto=211,Producto=CREDITOS GENERALES
	public static String[] parseUltalta(final String[] line) {
		String[] extraParameters = new String[ULTALTA.EXTRA_PARAMS];
		String codEmpresa = line[ULTALTA.INDEX_COD_EMPRESA].replace("\"", "");
		String codSucursal = line[ULTALTA.INDEX_COD_SUCURSAL].replace("\"", "");
		String codProducto = line[ULTALTA.INDEX_COD_PRODUCTO].replace("\"", "");

		extraParameters[0] = line[ULTALTA.INDEX_COD_EMPRESA].replace("\"", "");
		extraParameters[1] = line[ULTALTA.INDEX_COD_SUCURSAL].replace("\"", "");
		extraParameters[2] = cache.get(codEmpresa + codSucursal,
				K.CACHE.TABLE_CENTRO_ID);
		extraParameters[3] = line[ULTALTA.INDEX_COD_PRODUCTO].replace("\"", "");
		extraParameters[4] = cache.get(codEmpresa + codProducto,
				K.CACHE.TABLE_EMPRESA_PRODUCT_ID);

		return extraParameters;
	}

	private static String[] parseObDgoContab(String[] line) {
		boolean tpv = line[5].contains(OBDGOCONTAB.CONS1_TPV)
				&& line[6].equals(OBDGOCONTAB.CONS2_TPV)
				&& line[47].equals(OBDGOCONTAB.CONS3_TPV)
				&& line[48].equals(OBDGOCONTAB.CONS4_TPV);

		String[] extraParam;
		if (tpv) {
			extraParam = parseObDgoContabTPV(line);
		} else {
			extraParam = parseObDgoContabEfectivo(line);
		}
		return extraParam;
	}

	// Importe=10,91,Moneda=EUR,TPV=0000304100,Comercio=ARROCERIA
	// BALEAR,Localidad=ALCOBENDAS,Provincia=MADRID,Pais=ESPAÑA
	public static String[] parseObDgoContabTPV(final String line[]) {
		String[] extraParameters = new String[OBDGOCONTAB_TPV.EXTRA_PARAMS];
		String importe = line[OBDGOCONTAB_TPV.INDEX_IMPORTE].replace("\"", "");
		String moneda = line[OBDGOCONTAB_TPV.INDEX_MONEDA].replace("\"", "");
		String dgoParam = line[OBDGOCONTAB_TPV.INDEX_DGO_PARAMS].replace("\"",
				"");
		String tpv = StringUtils.substring(dgoParam, 260, 270);
		String comercio = StringUtils.substring(dgoParam, 270, 295);
		String codLocalidad = String.valueOf(Integer.parseInt(
				StringUtils.substring(dgoParam, 295, 308), 16)); 

		String localidad = cache.get(codLocalidad, K.CACHE.TABLE_PLAZA_ID);
		String[] recordProvincia = cache.get(codLocalidad,
				K.CACHE.TABLE_PROVINCIA_ID).split("\\|");
		String provincia = recordProvincia[0];
		String pais = cache.get(recordProvincia[1], K.CACHE.TABLE_PAIS_ID);

		extraParameters[0] = importe;
		extraParameters[1] = moneda;
		extraParameters[2] = tpv;
		extraParameters[3] = comercio;
		extraParameters[4] = localidad;
		extraParameters[5] = provincia;
		extraParameters[6] = pais;

		return extraParameters;

	}

	// Importe=10,91,Moneda=EUR,Localidad=ALCOBENDAS,Provincia=MADRID,Pais=ESPAÑA
	public static String[] parseObDgoContabEfectivo(final String line[]) {
		String[] extraParameters = new String[OBDGOCONTAB_EFECTIVO.EXTRA_PARAMS];
		String importe = line[OBDGOCONTAB_EFECTIVO.INDEX_IMPORTE].replace("\"",
				"");
		String moneda = line[OBDGOCONTAB_EFECTIVO.INDEX_MONEDA].replace("\"",
				"");
		String dgoParam = line[OBDGOCONTAB_EFECTIVO.INDEX_DGO_PARAMS].replace(
				"\"", "");
		String banco = StringUtils.substring(dgoParam, 763, 793);
		String localidad = StringUtils.substring(dgoParam, 793, 808);

		String codProvincia = String.valueOf(Integer.parseInt(
				StringUtils.substring(dgoParam, 808, 810), 16));
		String[] recordProvincia = cache.get(codProvincia,
				K.CACHE.TABLE_PROVINCIA_ID).split("\\|");

		extraParameters[0] = importe;
		extraParameters[1] = moneda;
		extraParameters[2] = banco;
		extraParameters[3] = localidad;
		extraParameters[4] = recordProvincia[0];
		extraParameters[5] = recordProvincia[1];

		return extraParameters;

	}

	public static void main(String[] args) {

	}

	// String line =
	// "10|\"IBM\"|\"2015113\"|\"171646002851\"|\"ORN01\"|\"PL_EM_ORDEN\"|“ISRT\"|\"0000:1d8b:5f00:cad7:0002\"|\"0000:ced4:c014:705f:0001\"|\"2015-04-21-13.06.55\"|\"PLBALQDI\"|0000|\"0049\"|\"0015\"|\"754\"|\"QBBBBKC\"|\"001\"|\"0049\"|\"0001\"|\"PTD\"| \"0009\"|\"ES\"|\"S\"|\"CCC\"|\"INM\"|\"001\"|\"2015-04-10\"|\"0001-01-01\"|\"2015-04-13\"|\"2015-04-13\"|\"2015-04-13\"|\"BNPA\"|“BE01\"|\"RED\"|\" \"|\" \"|\"PLBALE1 \"|\"2015-04-10-09.31.04.455131\"|\"PLCHDV1 \"|\"2015-04-20-18.46.03.028611\"|\"2015-04-13\"|\"2015-04-10\"|\"SEPA\"|\"RCUR\"|\" \"|\"CORE\"|\"003\"|\"27 \"|188,09|\"EUR\"|\" \"|\"N\"|\"OAP\"|\"PMS\"|\" \"|\" \"|\" \"|\" \"|\" \"|\" \"|\" \"|\" \"|\" \"|\"0049\"|\"0015\"|\"754\"|\"QBBBBKC\"|\"001\"|\"0049\"|\"0001\"|\"DEV\"|\"D008\"|\"ES\"|\"S\"|\"CCC\"|\"INM\"|\"001\"|\"2015-04-10\"|\"0001-01-01\"|\"2015-04-13\"|\"2015-04-13\"|\"2015-04-13\"|\"0049\"|\"0015\"|\"RED\"|\" \"|\" \"|\"PLBALE1 \"|\"2015-04-10-09.31.04.455131\"|\"PLBALQD \"|\"2015-04-21-15.06.54.772523\"|\"2015-04-13\"|\"2015-04-10\"|\"SEPA\"|\"RCUR\"|\" \"|\"CORE\"|\"003\"|\"27 \"|188,09|\"EUR\"|\" \"|\"S\"|\"OAP\"|\"PMS\"|\" \"|\" \"|\" \"|\" \"|\" \"|\" \"|\" \"|\" \"|\" \"";
	// String[] result = FactoryParser.parsePlEmOrden(line.split("\\|"));
	// System.out.println("ok");

	// String line =
	// "10|\"IBM\"|\"2015114\"|\"132648118166\"|\"ORN01\"|\"ULTALTA\"|\"REPL\"|\"0000:1d96:0c9d:de31:0002\"|\"0000:ced8:6f4b:cfb4:0001\"|\"2015-04-24-11.26.47\"|\"CCB506SI\"|0001|\"0049\"|“0015\"|“211\"|\" 0555491 \"|\" \"|\"0036\"|\"5777\"|\"632\"|\"BBBJGGR\"|\" \"";
	// String[] result = FactoryParser.parseUltalta(line.split("\\|"));
	// System.out.println("ok");

}
