package org.sanju.ml;

import java.util.HashMap;
import java.util.Map;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.DatabaseClientFactory.Authentication;

/**
 *
 * @author Sanju Thomas
 *
 */
public class ConnectionManager {

	private static final Map<String, DatabaseClient> clientMap = new HashMap<>();

	public static final DatabaseClient getClient(
			final ApplicationServer mlApplicationServer) {

		final DatabaseClient client = clientMap.get(mlApplicationServer.name());

		if (null == client) {
			final DatabaseClient databaseClient = DatabaseClientFactory
					.newClient(mlApplicationServer.getMlServer().getHost(),
							mlApplicationServer.getPort(),
							mlApplicationServer.getModuleDatabase(),
							mlApplicationServer.getMlServer().getCredential().getUsername(),
							mlApplicationServer.getMlServer().getCredential().getPassword(),
							Authentication.DIGEST);

			clientMap.put(mlApplicationServer.name(), databaseClient);
		}

		return clientMap.get(mlApplicationServer.name());
	}

	public static void close(final DatabaseClient client){
		client.release();
		clientMap.remove(name(client));
	}

	private static String name(final DatabaseClient client){

		final StringBuilder builder = new StringBuilder();
		builder.append(client.getHost());
		builder.append(":");
		builder.append(client.getPort());
		return builder.toString();
	}

}
