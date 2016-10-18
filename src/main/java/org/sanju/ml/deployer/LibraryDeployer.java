package org.sanju.ml.deployer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.sanju.ml.payload.LibraryPayload;
import org.sanju.ml.plugin.PropertyConstants;

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
	final List<LibraryPayload> payloads = new ArrayList<>();

	public LibraryDeployer(final DatabaseClient databaseClient, final Properties properties){
		this.databaseClient = databaseClient;
		final List<File> files = ModuleUtils.loadAssets(properties.getProperty(ModuleTypes.LIBRARIES.getSourceLocation()));
		for(final File file : files){
			this.payloads.add(new LibraryPayload(PropertyConstants.ML_LIBRARY_LOCATION_PROPERTY, file));
		}
	}

	@Override
	public void deploy(final LibraryPayload t) {

		final ExtensionLibrariesManager elm = this.databaseClient.newServerConfigManager().newExtensionLibrariesManager();
		final File file = t.getFile();
		elm.write(t.getLibraryPrefix() + file.getName(), new FileHandle(file).withFormat(Format.TEXT));
	}

	@Override
	public void deploy() {

		for(final LibraryPayload payload : this.payloads){
			this.deploy(payload);
		}
	}

}
