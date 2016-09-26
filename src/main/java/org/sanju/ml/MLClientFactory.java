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
public class MLClientFactory {

	private static final Map<String, DatabaseClient> clientMap = new HashMap<String, DatabaseClient>();

	public static final DatabaseClient getClient(
			final MLApplicationServer mlApplicationServer) {

		final DatabaseClient client = clientMap.get(mlApplicationServer.name());

		if (null == client) {
			final DatabaseClient databaseClient = DatabaseClientFactory
					.newClient(mlApplicationServer.getMlServer().getHost(),
							mlApplicationServer.getPort(), mlApplicationServer
									.getMlServer().getCredential()
									.getUsername(), mlApplicationServer
									.getMlServer().getCredential()
									.getPassword(), Authentication.DIGEST);
			clientMap.put(mlApplicationServer.name(), databaseClient);
		}

		return clientMap.get(mlApplicationServer.name());
	}

}
