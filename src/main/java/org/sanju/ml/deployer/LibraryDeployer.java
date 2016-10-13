package org.sanju.ml.deployer;

import org.sanju.ml.payload.LibraryPayload;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.admin.ExtensionLibrariesManager;

/**
 *
 * @author Sanju Thomas
 *
 */
public class LibraryDeployer implements Deployer<LibraryPayload>{


	final DatabaseClient databaseClient;

	public LibraryDeployer(final DatabaseClient databaseClient){
		this.databaseClient = databaseClient;
	}

	@Override
	public void deploy(final LibraryPayload t) {

		final ExtensionLibrariesManager elm = this.databaseClient.newServerConfigManager().newExtensionLibrariesManager();

	}

}
