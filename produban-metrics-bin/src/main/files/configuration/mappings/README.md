Before creating the mapping, you should clean all ES and create the index metrics. 
These commands delete all the content of ElasticSearch

curl -XDELETE 'http://localhost:9200/metrics/'
curl -XPUT 'http://localhost:9200/metrics/'
