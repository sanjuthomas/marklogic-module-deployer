package org.sanju.ml.deployer;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sanju.ml.ConnectionManager;
import org.sanju.ml.payload.RestExtensionPayload;

import com.marklogic.client.admin.ResourceExtensionsManager;
import com.marklogic.client.io.StringHandle;

/**
 *
 * @author Sanju Thomas
 *
 */
public class TestRESTExtensionDeployer extends AbstractTest{

	private RestExtensionPayload mlRestExtensionsPayload;
	private Deployer<RestExtensionPayload> mlModuleDeployer;
	private ResourceExtensionsManager resourceExtensionsManager;

	@Override
	@Before
	public void setup() throws FileNotFoundException, IOException{
		super.setup();
		this.mlModuleDeployer = new RestExtensionDeployer(ConnectionManager.getClient(this.mlApplicationServer), this.properties);
		this.resourceExtensionsManager = this.databaseClient.newServerConfigManager().newResourceExtensionsManager();
	}

	@After
	public void tearDown(){
		ConnectionManager.close(this.databaseClient);
	}

	@Test
	public void shouldDeployJavaScriptExtension(){

		this.mlRestExtensionsPayload = new RestExtensionPayload(new File("src/test/resources/rest-extensions/js-test-extension.sjs"));
		this.mlModuleDeployer.deploy(this.mlRestExtensionsPayload);
		assertNotNull(this.resourceExtensionsManager.readServices("js-test-extension", new StringHandle()).get());
	}

	@Test
	public void shouldDeployXQueryExtension(){

		this.mlRestExtensionsPayload = new RestExtensionPayload(new File("src/test/resources/rest-extensions/xq-test-extension.xqy"));
		this.mlModuleDeployer.deploy(this.mlRestExtensionsPayload);
		assertNotNull(this.resourceExtensionsManager.readServices("xq-test-extension", new StringHandle()).get());
	}

}
