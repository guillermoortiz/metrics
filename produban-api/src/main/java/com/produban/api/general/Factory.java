package com.produban.api.general;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.produban.api.manager.CacheManager;
import com.produban.api.manager.IndexerManager;

public final class Factory {

	private static ApplicationContext context;
	private static final Logger LOG = Logger.getLogger(Factory.class);

	private Factory() {
	}

	static synchronized ApplicationContext getContext() {

		if (context == null) {
			String[] locations = {"produban-manager.xml", "produban-cache.xml" , "produban-indexer.xml"};

			ArrayList<String> available = new ArrayList<String>();
			ClassLoader loader = Factory.class.getClassLoader();
			for (String location : locations) {
				if (loader.getResource(location) != null) {
					available.add(location);
				}
			}

			String[] availableLocations = new String[available.size()];
			for (int i = 0; i < available.size(); i++) {
				availableLocations[i] = available.get(i);
			}

			context = new ClassPathXmlApplicationContext(availableLocations);
		}

		return context;
	}
	
	public static CacheManager getCacheManager() {
		CacheManager bean = (CacheManager) getContext().getBean("cacheManager");
		return bean;
	}
	
	public static IndexerManager getIndexerManager() {
		IndexerManager bean = (IndexerManager) getContext().getBean("indexerManager");
		return bean;
	}

}
