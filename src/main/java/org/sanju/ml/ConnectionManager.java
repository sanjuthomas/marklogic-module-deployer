package org.sanju.ml;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.DatabaseClientFactory.Authentication;

/**
 * MarkLogic connection manager class. This class provide one and only one connection per application server.
 * Once the connection is created for an application server, its cached and all the subsequent request is served with same connection.
 *
 * @author Sanju Thomas
 *
 */
public final class ConnectionManager {

	/**
	 * Constructor is marked private to avoid instance creation.
	 */
	private ConnectionManager(){}

	private static final Logger logger = LoggerFactory.getLogger(ConnectionManager.class);
	private static final Map<String, DatabaseClient> clientMap = new HashMap<>();

	/**
	 * Construct connection for a given application server if its not available in the cache.
	 * If the connection is available in the cache, the same connection is returned to the client.
	 *
	 * @param mlApplicationServer
	 * @return databaseClient
	 */
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

	/**
	 * Release the given connection and remove it from the cache.
	 *
	 * @param client
	 */
	public static void close(final DatabaseClient client){
		if(null != client){
			client.release();
			clientMap.remove(name(client));
		}
	}

	/**
	 * Construct the name of database client in the host:port format.
	 *
	 * @param client
	 * @return name
	 */
	private static String name(final DatabaseClient client){

		final StringBuilder builder = new StringBuilder();
		builder.append(client.getHost());
		builder.append(":");
		builder.append(client.getPort());
		return builder.toString();
	}

}
