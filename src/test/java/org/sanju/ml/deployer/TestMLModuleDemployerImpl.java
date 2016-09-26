package org.sanju.ml.deployer;

import org.junit.Before;
import org.sanju.ml.MLApplicationServer;
import org.sanju.ml.MLClientFactory;
import org.sanju.ml.MLCredential;
import org.sanju.ml.MLServer;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class TestMLModuleDemployerImpl {

	private MLModuleDeployer mlModuleDeployer;
	private MLServer mlServer;
	private MLApplicationServer mlApplicationServer;
	
	
	@Before
	public void setup(){
		this.mlServer = new MLServer("localhost", new MLCredential("admin", "admin"));
		this.mlApplicationServer = new MLApplicationServer(mlServer, 15000, "ml-module-deployer-cb", "ml-module-deployer-mb");
		this.mlModuleDeployer = new MLModuleDemployerImpl(MLClientFactory.getClient(mlApplicationServer));
	}
	
	public void shouldDeploy(){
		
	}
	
	
}
