curl -XPUT http://quickstart.cloudera:9200/metrics/_mapping/APERTURA_CUENTAS_123 -d '{		
      "APERTURA_CUENTAS_123" : {
        "_timestamp":{
          	"enabled":true,
          	"store":true
          },
        "properties" : {                      
          "bancoOrigen" : {
            "type" : "string","index" : "not_analyzed"
          },
          "localidadOrigen" : {
            "type" : "string","index" : "not_analyzed"
          },
          "provinciaOrigen" : {
            "type" : "string","index" : "not_analyzed"
          },          
          "paisOrigen" : {
            "type" : "string","index" : "not_analyzed"
          },
          "coordenadasOrigen" : {
            "type" : "geo_point"
          },
          "CODIGO_EMPRESA" : {
            "type" : "string","index" : "not_analyzed"
          },
          "CODIGO_CENTRO" : {
            "type" : "string","index" : "not_analyzed"
          },
          "tipo_Cuenta" : {
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
                "format" : "yyyy-MM-dd HH:mm:ss"
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
          }              
       }
    }
}'