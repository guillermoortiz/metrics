package com.produban.indexer.api;

/**
 * 
 * Interface that any class has to have to index documents.
 * 
 */
public interface Indexer {

	/**
	 * Method to index an JSON to a indexer.
	 * 
	 * @param jsonToIndex
	 *            json to index.
	 */
	public abstract void indexJson(String jsonToIndex);

	
	/**
	 * 
	 * Method to index an JSON to a indexer.
	 * 
	 * @param jsonToIndex json to index
	 * @param indexName name of the index
	 * @param docType type of the document
	 */
	public abstract void indexJson(final String jsonToIndex, final String indexName, final String docType);
	
	
	/**
	 * 
	 * Method to index an JSON to a indexer.
	 * 
	 * @param jsonToIndex json to index	 
	 * @param docType type of the document
	 */
	public abstract void indexJson(final String jsonToIndex, final String docType);
	
	
	/**
	 * Method to index a set of JSON in indexer.
	 * 
	 * @param jsonsToIndex
	 *            list of JSONs
	 */
	public abstract void indexJsons(String[] jsonsToIndex);

}