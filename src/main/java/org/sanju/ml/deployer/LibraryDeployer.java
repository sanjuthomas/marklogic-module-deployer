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
 * Deployer implementation to deploy all library files to MarkLogic module database.
 *
 * @author Sanju Thomas
 *
 */
public class LibraryDeployer implements Deployer<LibraryPayload>{

	private static final Logger logger = LoggerFactory.getLogger(LibraryDeployer.class);
	private final DatabaseClient databaseClient;
	private final List<LibraryPayload> payloads = new ArrayList<>();
	private final String libraryLocation;

	/**
	 *
	 * @param databaseClient
	 * @param properties
	 * @throws IOException
	 * @see {@link DatabaseClient}
	 * @see {@link Properties}
	 * @see {@link IOException}
	 */
	public LibraryDeployer(final DatabaseClient databaseClient, final Properties properties) throws IOException{
		this.databaseClient = databaseClient;
		this.libraryLocation = properties.getProperty(ModuleTypes.LIBRARIES.getSourceLocation());
		final List<File> files = ModuleUtils.loadAssets(properties.getProperty(ModuleTypes.LIBRARIES.getSourceLocation()));
		files.forEach(file -> this.payloads.add(new LibraryPayload(properties.getProperty(PropertyConstants.ML_LIBRARY_LOCATION_PROPERTY), file)));
		
	}

	/**
	 * Deploy a given instance of the LibraryPayload into MarkLogic module database.
	 *
	 * @param libraryPayload
	 * @see {@link LibraryPayload}
	 */
	@Override
	public void deploy(final LibraryPayload t) {
		logger.info("Deploying {} ", t.getFile().getName());

		final String relative = new File(this.libraryLocation).toURI().relativize(t.getFile().toURI()).getPath();
		final ExtensionLibrariesManager elm = this.databaseClient.newServerConfigManager().newExtensionLibrariesManager();
		final File file = t.getFile();
		elm.write(t.getLibraryPrefix() + relative, new FileHandle(file).withFormat(Format.TEXT));
	}

	/**
	 * Deploy all the instances of the LibraryPayload into MarkLogic module database.
	 * See the constructor of the this class to see how the LibraryPayload instance are created.
	 *
	 */
	@Override
	public void deploy() {
		payloads.forEach(payload -> this.deploy(payload));
	}

}
