curl -XPUT http://quickstart.cloudera:9200/metrics/_mapping/TRANSFERENCIAS_INTERNACIONALES -d '{
	"TRANSFERENCIAS_INTERNACIONALES" : {
        "properties" : {
          "banco_RECEPTOR" : {
            "type" : "string","index" : "not_analyzed"
          },
          "banco_origen" : {
            "type" : "string","index" : "not_analyzed"
          },
          "codigo_CANAL" : {
            "type" : "string","index" : "not_analyzed"
          },
          "codigo_CENTRO" : {
            "type" : "string","index" : "not_analyzed"
          },
          "codigo_EMPRESA" : {
            "type" : "string","index" : "not_analyzed"
          },
          "codigo_MONEDA_IMPORTE_CONTRAVALOR" : {
            "type" : "string","index" : "not_analyzed"
          },
          "codigo_MONEDA_IMPORTE_ORDENADO" : {
            "type" : "string","index" : "not_analyzed"
          },
          "codigo_PAIS_DESTINO" : {
            "type" : "string","index" : "not_analyzed"
          },
          "codigo_PRODUCTO" : {
            "type" : "string","index" : "not_analyzed"
          },
          "codigo_USUARIO_ULTIMA_MODIFICACION" : {
            "type" : "string","index" : "not_analyzed"
          },
          "coordenadasOrigen" : {
            "type" : "geo_point"
          },
          "datos_P" : {
            "properties" : {
              "celda" : {
                "type" : "string","index" : "not_analyzed"
              },
              "entidad" : {
                "type" : "string","index" : "not_analyzed"
              },
              "sistema" : {
                "type" : "string","index" : "not_analyzed"
              },
              "tabla" : {
                "type" : "string","index" : "not_analyzed"
              }
            }
          },
          "datos_Q" : {
            "properties" : {
              "evento" : {
                "type" : "string","index" : "not_analyzed"
              },
              "fecha_hora" : {
                "type" : "date",
                "format" : "dateOptionalTime"
              },
              "owner" : {
                 "type" : "string","index" : "not_analyzed"
              },
              "plan" : {
                "type" : "string","index" : "not_analyzed"
              },
              "tabla" : {
                "type" : "string","index" : "not_analyzed"
              }
            }
          },
          "fecha_ALTA_TRANSFERENCIA" : {
            "type" : "date",
            "format" : "dateOptionalTime"
          },
          "fecha_ULTIMA_MODIFICACION" : {
            "type" : "long"
          },
          "fecha_VALOR_ABONO_TRANSFERENCIA" : {
            "type" : "date",
            "format" : "dateOptionalTime"
          },
          "fecha_VALOR_ADEUDO_TRANSFERENCIA" : {
            "type" : "date",
            "format" : "dateOptionalTime"
          },
          "gastos_EMISION" : {
            "type" : "string","index" : "not_analyzed"
          },
          "hh_datos_bancos" : {
            "properties" : {
              "banco_destino" : {
                "type" : "string","index" : "not_analyzed"
              },
              "coordenadaDestino" : {
                "type" : "geo_point"
              },
              "datos_P" : {
                "properties" : {
                  "celda" : {
                    "type" : "string","index" : "not_analyzed"
                  },
                  "entidad" : {
                    "type" : "string","index" : "not_analyzed"
                  },
                  "sistema" : {
                    "type" : "string","index" : "not_analyzed"
                  },
                  "tabla" : {
                    "type" : "string","index" : "not_analyzed"
                  }
                }
              },
              "datos_Q" : {
                "properties" : {
                  "evento" : {
                    "type" : "string","index" : "not_analyzed"
                  },
                  "fecha_hora" : {
                    "type" : "date",
                    "format" : "dateOptionalTime"
                  },
                  "owner" : {
                    "type" : "string","index" : "not_analyzed"
                  },
                  "plan" : {
                    "type" : "string","index" : "not_analyzed"
                  },
                  "tabla" : {
                    "type" : "string","index" : "not_analyzed"
                  }
                }
              },
              "localidad_destino" : {
                "type" : "string","index" : "not_analyzed"
              },
              "pais_destino" : {
                "type" : "string","index" : "not_analyzed"
              },
              "provincia_destino" : {
                "type" : "string","index" : "not_analyzed"
              }
            }
          },
          "hhg_TIMESTAMP" : {
            "type" : "date",
            "format" : "dateOptionalTime"
          },
          "importe" : {
            "type" : "long"
          },
          "importe_CONTRAVALOR" : {
            "type" : "long"
          },
          "importe_DIVISA_PAGO" : {
            "type" : "long"
          },
          "indic_GASTOS_FUERA_PAIS_EMISOR_POR_CTA" : {
            "type" : "string","index" : "not_analyzed"
          },
          "indicador_PETICION" : {
            "type" : "string","index" : "not_analyzed"
          },
          "indicador_RESIDENCIA_BENEFICIARIO" : {
            "type" : "string","index" : "not_analyzed"
          },
          "localidad_origen" : {
            "type" : "string","index" : "not_analyzed"
          },
          "moneda" : {
            "type" : "string","index" : "not_analyzed"
          },
          "oficina_RECEPTORA" : {
            "type" : "string","index" : "not_analyzed"
          },
          "pais_origen" : {
            "type" : "string","index" : "not_analyzed"
          },
          "procedencia_TRANSFERENCIA" : {
            "type" : "string","index" : "not_analyzed"
          },
          "provincia_origen" : {
            "type" : "string","index" : "not_analyzed"
          }
        }
      }
    }'