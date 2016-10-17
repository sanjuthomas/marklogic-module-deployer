package org.sanju.ml.deployer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.sanju.ml.ConnectionManager;
import org.sanju.ml.payload.RestExtensionPayload;

/**
 *
 * @author Sanju Thomas
 *
 */
public class TestRESTExtensionDeployer extends AbstractTest{

	private RestExtensionPayload mlRestExtensionsPayload;
	private Deployer<RestExtensionPayload> mlModuleDeployer;

	@Override
	@Before
	public void setup() throws FileNotFoundException, IOException{
		super.setup();
		this.mlModuleDeployer = new RestExtensionDeployer(ConnectionManager.getClient(this.mlApplicationServer), this.properties);
	}

	@Test
	public void shouldDeployJavaScriptExtension(){

		this.mlRestExtensionsPayload = new RestExtensionPayload(new File("src/test/resources/rest-extensions/js-test-extension.sjs"));
		this.mlModuleDeployer.deploy(this.mlRestExtensionsPayload);
	}

	@Test
	public void shouldDeployXQueryExtension(){

		this.mlRestExtensionsPayload = new RestExtensionPayload(new File("src/test/resources/rest-extensions/xq-test-extension.xqy"));
		this.mlModuleDeployer.deploy(this.mlRestExtensionsPayload);
	}



}
