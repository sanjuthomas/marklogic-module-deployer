package org.sanju.ml.deployer;

import java.io.File;

import org.sanju.ml.payload.TransformsPayload;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.admin.TransformExtensionsManager;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class TransformDeployer implements ModuleDeployer<TransformsPayload>{
	
	final DatabaseClient databaseClient;
	
	public TransformDeployer(final DatabaseClient databaseClient){
		this.databaseClient = databaseClient;
	}

	@Override
	public void deploy(TransformsPayload t) {
		final TransformExtensionsManager transformExtensionsManager = this.databaseClient.newServerConfigManager().newTransformExtensionsManager();
		final File file = t.getFile();
	}

}
