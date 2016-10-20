package org.sanju.ml.deployer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.sanju.ml.payload.RestExtensionPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.admin.ExtensionMetadata;
import com.marklogic.client.admin.MethodType;
import com.marklogic.client.admin.ResourceExtensionsManager;
import com.marklogic.client.admin.ResourceExtensionsManager.MethodParameters;
import com.marklogic.client.io.FileHandle;

/**
 * Deployer implementation to deploy all rest extension files to MarkLogic module database.
 *
 * @author Sanju Thomas
 *
 */
public class RestExtensionDeployer implements Deployer<RestExtensionPayload>{

	private static final Logger logger = LoggerFactory.getLogger(RestExtensionDeployer.class);
	private final DatabaseClient databaseClient;
	private final List<RestExtensionPayload> payloads = new ArrayList<>();

	/**
	 * Create an instance of the RestExtensionDeployer class.
	 *
	 * @param databaseClient
	 * @param properties
	 * @throws IOException
	 * @see {@link DatabaseClient}
	 * @see {@link Properties}
	 * @see {@link IOException}
	 */
	public RestExtensionDeployer(final DatabaseClient databaseClient, final Properties properties) throws IOException{
		this.databaseClient = databaseClient;
		final List<File> files = ModuleUtils.loadAssets(properties.getProperty(ModuleTypes.REST_EXT.getSourceLocation()));
		for(final File file : files){
			this.payloads.add(new RestExtensionPayload(file));
		}
	}

	/**
	 * Deploy a given instance of the RestExtensionPayload into MarkLogic module database.
	 *
	 * @param restExtensionPayload
	 */
	@Override
	public void deploy(final RestExtensionPayload t) {
		logger.info("Deploying {} ", t.getFile().getName());
		final ResourceExtensionsManager resourceExtensionsManager = this.databaseClient.newServerConfigManager().newResourceExtensionsManager();
		final File file = t.getFile();
		final ExtensionMetadata extensionMetadata = new ExtensionMetadata();
		extensionMetadata.setScriptLanguage(t.getScriptLanguage());
		resourceExtensionsManager.writeServices(FilenameUtils.removeExtension(file.getName()), new FileHandle(file), extensionMetadata, new MethodParameters(MethodType.PUT));
	}

	/**
	 * Deploy all the instances of the RestExtensionPayload available to the MarkLogic module database.
	 * See the constructor of this class to see how the RestExtensionPayload instances are created.
	 *
	 */
	@Override
	public void deploy() {
		for(final RestExtensionPayload payload : this.payloads){
			this.deploy(payload);
		}
	}

}
