package org.sanju.ml.deployer;

import java.io.File;

import org.codehaus.plexus.util.FileUtils;
import org.sanju.ml.payload.MLRestExtensionsPayload;

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

public class MLModuleDemployerImpl implements MLModuleDeployer{

	private final DatabaseClient databaseClient;

	public MLModuleDemployerImpl(final DatabaseClient databaseClient){
		this.databaseClient = databaseClient;
	}

	@Override
	public void deploy(final MLRestExtensionsPayload mlRestExtensionsPayload) {

		final ResourceExtensionsManager resourceExtensionsManager = this.databaseClient.newServerConfigManager().newResourceExtensionsManager();
		final File file = mlRestExtensionsPayload.getFile();
		final ExtensionMetadata extensionMetadata = new ExtensionMetadata();
		extensionMetadata.setScriptLanguage(mlRestExtensionsPayload.getScriptLanguage());
		resourceExtensionsManager.writeServices(FileUtils.extension(file.getName()), new FileHandle(file), extensionMetadata, new MethodParameters(MethodType.PUT));
	}

}
