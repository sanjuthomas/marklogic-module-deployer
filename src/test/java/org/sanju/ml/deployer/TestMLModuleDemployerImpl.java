package org.sanju.ml.deployer;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.sanju.ml.MLApplicationServer;
import org.sanju.ml.MLClientFactory;
import org.sanju.ml.MLCredential;
import org.sanju.ml.MLServer;
import org.sanju.ml.payload.MLRestExtensionsPayload;

/**
 *
 * @author Sanju Thomas
 *
 */
public class TestMLModuleDemployerImpl {

	private MLModuleDeployer mlModuleDeployer;
	private MLServer mlServer;
	private MLApplicationServer mlApplicationServer;
	private MLRestExtensionsPayload mlRestExtensionsPayload;


	@Before
	public void setup(){
		this.mlServer = new MLServer("localhost", new MLCredential("admin", "admin"));
		this.mlApplicationServer = new MLApplicationServer(this.mlServer, 15000, "azsearch-content", "azsearch-modules");
		this.mlModuleDeployer = new MLModuleDemployerImpl(MLClientFactory.getClient(this.mlApplicationServer));


	}

	@Test
	public void shouldDeployJavaScriptExtension(){

		this.mlRestExtensionsPayload = new MLRestExtensionsPayload(new File("src/test/resources/rest-extensions/js-test-extension.sjs"));
		this.mlModuleDeployer.deploy(this.mlRestExtensionsPayload);

	}


}
