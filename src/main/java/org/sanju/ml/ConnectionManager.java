package org.sanju.ml;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.DatabaseClientFactory.Authentication;

/**
 *
 * @author Sanju Thomas
 *
 */
public class ConnectionManager {


	private static final Logger logger = LoggerFactory.getLogger(ConnectionManager.class);
	private static final Map<String, DatabaseClient> clientMap = new HashMap<>();

	public static final DatabaseClient getClient(
			final ApplicationServer mlApplicationServer) {

		final DatabaseClient client = clientMap.get(mlApplicationServer.name());

		if (null == client) {
			logger.info("Creating a new connection for port {} ", mlApplicationServer.getPort());
			final DatabaseClient databaseClient = DatabaseClientFactory
					.newClient(mlApplicationServer.getMlServer().getHost(),
							mlApplicationServer.getPort(),
							mlApplicationServer.getMlServer().getCredential().getUsername(),
							mlApplicationServer.getMlServer().getCredential().getPassword(),
							Authentication.DIGEST);

			clientMap.put(mlApplicationServer.name(), databaseClient);
		}else{
			logger.info("Returning client from the cache.");
		}

		return clientMap.get(mlApplicationServer.name());
	}

	public static void close(final DatabaseClient client){
		if(null != client){
			client.release();
			clientMap.remove(name(client));
		}
	}

	private static String name(final DatabaseClient client){

		final StringBuilder builder = new StringBuilder();
		builder.append(client.getHost());
		builder.append(":");
		builder.append(client.getPort());
		return builder.toString();
	}

}
