package com.produban.api.manager;

import org.apache.commons.math3.util.Pair;


/**
 * Interface with the basic operations for cache.
 * @author ortizg1
 *
 */
public interface IndexerManager {
	public abstract void indexWithId(final String jsonToIndex,
			final String id, final String docType);
	public abstract void indexDocument(final String document, final String documentType);
	public abstract void indexDocuments(String[] documents, String documentType); 
	public abstract void indexDocuments(Pair<String, String>[] documents);
}
