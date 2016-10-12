package org.sanju.ml.deployer;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.sanju.ml.payload.MLRestExtensionsPayload;
import org.sanju.ml.payload.MLTransformsPayload;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.admin.ExtensionMetadata;
import com.marklogic.client.admin.MethodType;
import com.marklogic.client.admin.ResourceExtensionsManager;
import com.marklogic.client.admin.ResourceExtensionsManager.MethodParameters;
import com.marklogic.client.admin.TransformExtensionsManager;
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
		resourceExtensionsManager.writeServices(FilenameUtils.removeExtension(file.getName()), new FileHandle(file), extensionMetadata, new MethodParameters(MethodType.PUT));
	}

	@Override
	public void deploy(final MLTransformsPayload mlTransformsPayload) {

		final TransformExtensionsManager resourceExtensionsManager = this.databaseClient.newServerConfigManager().newTransformExtensionsManager();
		final File file = mlTransformsPayload.getFile();
	}

}
