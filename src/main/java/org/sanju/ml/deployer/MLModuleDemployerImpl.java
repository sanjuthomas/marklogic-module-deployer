package org.sanju.ml.deployer;

import org.sanju.ml.payload.MLRestExtensionsPayload;

import com.marklogic.client.DatabaseClient;

/**
 * 
 * @author Sanju Thomas
 *
 */

public class MLModuleDemployerImpl implements MLModuleDeployer{

	private DatabaseClient databaseClient;
	
	public MLModuleDemployerImpl(final DatabaseClient databaseClient){
		this.databaseClient = databaseClient;
	}
	
	@Override
	public void deploy(final MLRestExtensionsPayload mlRestExtensionsPayload) {
		
	}

}
