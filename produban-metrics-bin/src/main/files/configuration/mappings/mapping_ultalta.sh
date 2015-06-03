curl -XPUT http://quickstart.cloudera:9200/metrics/_mapping/ULTALTA -d '{		
      "ULTALTA" : {
        "properties" : {
          "codigoEmpresa" : {
            "type" : "string","index" : "not_analyzed"
          },
          "codigoProducto" : {
            "type" : "string","index" : "not_analyzed"
          },
          "codigoSucursal" : {
            "type" : "string","index" : "not_analyzed"
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
          "nombreSucursal" : {
            "type" : "string","index" : "not_analyzed"
          },
          "producto" : {
            "type" : "string","index" : "not_analyzed"
          },
          "uca_CODEMPRE" : {
            "type" : "string","index" : "not_analyzed"
          },
          "uca_CODSUC" : {
            "type" : "string","index" : "not_analyzed"
          },
          "uca_CONTRLOC" : {
            "type" : "string","index" : "not_analyzed"
          },
          "uca_TIPOP" : {
            "type" : "string","index" : "not_analyzed"
          },
          "uca_ULTALTA" : {
            "type" : "string","index" : "not_analyzed"
        }        
      }
    }
}'