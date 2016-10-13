package org.sanju.ml.deployer;

import java.io.File;

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
public class RestExtensionDeployer implements ModuleDeployer<RestExtensionPayload>{
	
	final DatabaseClient databaseClient;
	
	public RestExtensionDeployer(final DatabaseClient databaseClient){
		this.databaseClient = databaseClient;
	}

	@Override
	public void deploy(RestExtensionPayload t) {
		
		final ResourceExtensionsManager resourceExtensionsManager = this.databaseClient.newServerConfigManager().newResourceExtensionsManager();
		final File file = t.getFile();
		final ExtensionMetadata extensionMetadata = new ExtensionMetadata();
		extensionMetadata.setScriptLanguage(t.getScriptLanguage());
		resourceExtensionsManager.writeServices(FilenameUtils.removeExtension(file.getName()), new FileHandle(file), extensionMetadata, new MethodParameters(MethodType.PUT));
	}

}
