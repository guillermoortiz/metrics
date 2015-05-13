package com.produban.manager;

import com.produban.api.general.Factory;
import com.produban.api.manager.IndexerManager;
import com.produban.indexer.api.Indexer;

public class IndexerManagerImpl implements IndexerManager {
	Indexer indexer;

	@Override
	public void indexDocument(String document, String documentType) {
		indexer.indexJson(document, documentType);		
	}
	
	
	public Indexer getIndexer() {
		return indexer;
	}

	public void setIndexer(Indexer indexer) {
		this.indexer = indexer;
	}
}
