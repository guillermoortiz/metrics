curl -XPUT http://quickstart.cloudera:9200/metrics/_mapping/TRANSFERENCIAS_NACIONALES -d '{	
      "TRANSFERENCIAS_NACIONALES" : {
        "properties" : {
          "banco_Destino" : {
            "type" : "string",
            "index" : "not_analyzed"
          },
          "banco_Origen" : {
            "type" : "string",
            "index" : "not_analyzed"
          },
          "canal_OPERACION" : {
            "type" : "string",
            "index" : "not_analyzed"
          },
          "centro_ORDENANTE" : {
            "type" : "string",
            "index" : "not_analyzed"
          },
          "codigo_CENTRO" : {
            "type" : "string",
            "index" : "not_analyzed"
          },
          "codigo_CENTRO_DESTINO" : {
            "type" : "string",
            "index" : "not_analyzed"
          },
          "codigo_EMPRESA" : {
            "type" : "string",
            "index" : "not_analyzed"
          },
          "codigo_EMPRESA_DESTINO" : {
            "type" : "string",
            "index" : "not_analyzed"
          },
          "codigo_PAIS" : {
            "type" : "string",
            "index" : "not_analyzed"
          },
          "datos_P" : {
            "properties" : {
              "celda" : {
                "type" : "string",
                "index" : "not_analyzed"
              },
              "entidad" : {
                "type" : "string",
                "index" : "not_analyzed"
              },
              "sistema" : {
                "type" : "string",
                "index" : "not_analyzed"
              },
              "tabla" : {
                "type" : "string",
                "index" : "not_analyzed"
              }
            }
          },
          "datos_Q" : {
            "properties" : {
              "evento" : {
                "type" : "string",
                "index" : "not_analyzed"
              },
              "fecha_hora" : {
                "type" : "date",
                "format" : "dateOptionalTime"
              },
              "owner" : {
                "type" : "string",
                "index" : "not_analyzed"
              },
              "plan" : {
                "type" : "string",
                "index" : "not_analyzed"
              },
              "tabla" : {
                "type" : "string",
                "index" : "not_analyzed"
              }
            }
          },
          "empresa_ORDENANTE" : {
            "type" : "string",
            "index" : "not_analyzed"
          },
          "estado_PAGO" : {
            "type" : "string",
            "index" : "not_analyzed"
          },
          "importe" : {
            "type" : "double"
          },
          "indicador_INMEDIATEZ" : {
            "type" : "string",
            "index" : "not_analyzed"
          },
          "indicador_RESIDENTE" : {
            "type" : "string",
            "index" : "not_analyzed"
          },
          "localidad_Destino" : {
            "type" : "string",
            "index" : "not_analyzed"
          },
          "localidad_Origen" : {
            "type" : "string",
            "index" : "not_analyzed"
          },
          "medio_FINANCIERO" : {
            "type" : "string",
            "index" : "not_analyzed"
          },
          "moneda" : {
            "type" : "string",
            "index" : "not_analyzed"
          },
          "pago_DE_REMESA" : {
            "type" : "string",
            "index" : "not_analyzed"
          },
          "pais_Destino" : {
            "type" : "string",
            "index" : "not_analyzed"
          },
          "pais_Origen" : {
            "type" : "string",
            "index" : "not_analyzed"
          },
          "provincia_Destino" : {
            "type" : "string",
            "index" : "not_analyzed"
          },
          "provincia_Origen" : {
            "type" : "string",
            "index" : "not_analyzed"
          },
          "timesptamp_ALTA" : {
            "type" : "date",
            "format" : "dateOptionalTime"
          },
          "timestamp_MODIFICACION" : {
            "type" : "date",
            "format" : "dateOptionalTime"
          },
          "tipo_ORDEN" : {
            "type" : "string",
            "index" : "not_analyzed"
          },
          "usuario_ALTA" : {
            "type" : "string",
            "index" : "not_analyzed"
          },
          "usuario_MODIFICACION" : {
            "type" : "string",
            "index" : "not_analyzed"
          },
		  "coordenadasOrigen" : {
            "type" : "geo_point"            
          },
		  "coordenadasDestino" : {
            "type" : "geo_point"  			
          }
		}
    }
}'