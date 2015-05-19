package com.produban.manager;

import org.apache.commons.math3.util.Pair;

import com.produban.api.manager.IndexerManager;
import com.produban.indexer.api.Indexer;

public class IndexerManagerImpl implements IndexerManager {
	Indexer indexer;

	@Override
	public void indexDocument(String document, String documentType) {
		indexer.indexJson(document, documentType);
	}

	@Override
	public void indexDocuments(String[] documents, String documentType) {
		indexer.indexJsons(documentType, documents);

	}

	public Indexer getIndexer() {
		return indexer;
	}

	public void setIndexer(Indexer indexer) {
		this.indexer = indexer;
	}

	@Override
	public void indexDocuments(Pair<String, String>[] documents) {
		indexer.indexJsons(documents);

	}

	@Override
	public void indexWithId(String jsonToIndex, String id, String docType) {
		indexer.indexWithId(jsonToIndex, id, docType);

	}

}
