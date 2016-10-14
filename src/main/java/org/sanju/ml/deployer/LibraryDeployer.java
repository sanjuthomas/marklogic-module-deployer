package org.sanju.ml.deployer;

import java.io.File;

import org.sanju.ml.payload.LibraryPayload;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.admin.ExtensionLibrariesManager;
import com.marklogic.client.io.FileHandle;
import com.marklogic.client.io.Format;

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
		final File file = t.getFile();
		elm.write(t.getLibraryPrefix() + file.getName(), new FileHandle(file).withFormat(Format.TEXT));
	}

}
