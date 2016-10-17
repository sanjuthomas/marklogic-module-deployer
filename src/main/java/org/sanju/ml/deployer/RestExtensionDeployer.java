package org.sanju.ml.deployer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.sanju.ml.payload.RestExtensionPayload;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.admin.ExtensionMetadata;
import com.marklogic.client.admin.MethodType;
import com.marklogic.client.admin.ResourceExtensionsManager;
import com.marklogic.client.admin.ResourceExtensionsManager.MethodParameters;
import com.marklogic.client.io.FileHandle;

/**
 *
 * @author Sanju Thomas
 *
 */
public class RestExtensionDeployer implements Deployer<RestExtensionPayload>{

	final DatabaseClient databaseClient;
	final List<RestExtensionPayload> payloads = new ArrayList<>();

	public RestExtensionDeployer(final DatabaseClient databaseClient, final Properties properties){
		this.databaseClient = databaseClient;
		final List<File> files = ModuleUtils.loadAssets(properties.getProperty(ModuleTypes.REST_EXT.getSourceLocation()));
		for(final File file : files){
			this.payloads.add(new RestExtensionPayload(file));
		}
	}

	@Override
	public void deploy(final RestExtensionPayload t) {

		final ResourceExtensionsManager resourceExtensionsManager = this.databaseClient.newServerConfigManager().newResourceExtensionsManager();
		final File file = t.getFile();
		final ExtensionMetadata extensionMetadata = new ExtensionMetadata();
		extensionMetadata.setScriptLanguage(t.getScriptLanguage());
		resourceExtensionsManager.writeServices(FilenameUtils.removeExtension(file.getName()), new FileHandle(file), extensionMetadata, new MethodParameters(MethodType.PUT));
	}

	@Override
	public void deploy() {
		for(final RestExtensionPayload payload : this.payloads){
			this.deploy(payload);
		}
	}

}
