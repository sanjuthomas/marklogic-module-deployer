package org.sanju.ml.deployer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.sanju.ml.payload.LibraryPayload;
import org.sanju.ml.plugin.PropertyConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	private static final Logger logger = LoggerFactory.getLogger(LibraryDeployer.class);
	final DatabaseClient databaseClient;
	final List<LibraryPayload> payloads = new ArrayList<>();

	public LibraryDeployer(final DatabaseClient databaseClient, final Properties properties) throws IOException{
		this.databaseClient = databaseClient;
		final List<File> files = ModuleUtils.loadAssets(properties.getProperty(ModuleTypes.LIBRARIES.getSourceLocation()));
		for(final File file : files){
			this.payloads.add(new LibraryPayload(properties.getProperty(PropertyConstants.ML_LIBRARY_LOCATION_PROPERTY), file));
		}
	}

	@Override
	public void deploy(final LibraryPayload t) {

		logger.info("Deploying {} to database {}", t.getFile().getName(), this.databaseClient.getDatabase());
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
