package org.sanju.ml.deployer;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.sanju.ml.MLApplicationServer;
import org.sanju.ml.MLClientFactory;
import org.sanju.ml.MLCredential;
import org.sanju.ml.MLServer;
import org.sanju.ml.payload.RestExtensionsPayload;

/**
 *
 * @author Sanju Thomas
 *
 */
public class TestMLModuleDemployerImpl {

	private ModuleDeployer mlModuleDeployer;
	private MLServer mlServer;
	private MLApplicationServer mlApplicationServer;
	private RestExtensionsPayload mlRestExtensionsPayload;


	@Before
	public void setup(){
		this.mlServer = new MLServer("localhost", new MLCredential("admin", "admin"));
		this.mlApplicationServer = new MLApplicationServer(this.mlServer, 15000, "azsearch-content", "azsearch-modules");
		this.mlModuleDeployer = new RestExtensionDeployer(MLClientFactory.getClient(this.mlApplicationServer));


	}

	@Test
	public void shouldDeployJavaScriptExtension(){

		this.mlRestExtensionsPayload = new RestExtensionsPayload(new File("src/test/resources/rest-extensions/js-test-extension.sjs"));
		this.mlModuleDeployer.deploy(this.mlRestExtensionsPayload);
	}

	@Test
	public void shouldDeployXQueryExtension(){

		this.mlRestExtensionsPayload = new RestExtensionsPayload(new File("src/test/resources/rest-extensions/xq-test-extension.xqy"));
		this.mlModuleDeployer.deploy(this.mlRestExtensionsPayload);
	}



}
