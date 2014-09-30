package com.github.onsdigital.search;

import java.io.IOException;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.client.Client;


/**
 * Starts an {@link EmbeddedElasticSearchServer} when a client requested
 * 
 * @author Bren
 *
 */
public class ElasticSearchServer {

	private static Client client;
	private static EmbeddedElasticSearchServer server;

	private ElasticSearchServer() {

	}

	public static Client getClient() throws ElasticsearchException, IOException {
		if (client == null) {
			synchronized (ElasticSearchServer.class) {
				if (client == null) {
					startEmbeddedServer();
					client = server.getClient();
				}
			}
		}
		return client;
	}

	private static void startEmbeddedServer() throws ElasticsearchException,
			IOException {
		server = new EmbeddedElasticSearchServer("ONSNode");
		Runtime.getRuntime().addShutdownHook(new ShutDownNodeThread());
	}

	static class ShutDownNodeThread extends Thread {
		@Override
		public void run() {
			client.close();
			server.shutdown();
		}
	}

}
