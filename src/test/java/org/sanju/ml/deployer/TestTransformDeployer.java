package org.sanju.ml.deployer;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sanju.ml.ConnectionManager;
import org.sanju.ml.payload.TransformPayload;

import com.marklogic.client.admin.TransformExtensionsManager;
import com.marklogic.client.io.StringHandle;

/**
 *
 * @author Sanju Thomas
 *
 */
public class TestTransformDeployer extends AbstractTest{

	private TransformPayload transformsPayload;
	private Deployer<TransformPayload> mlModuleDeployer;
	private TransformExtensionsManager transformExtensionManager;

	@Override
	@Before
	public void setup() throws FileNotFoundException, IOException{
		super.setup();
		this.mlModuleDeployer = new TransformDeployer(ConnectionManager.getClient(this.mlApplicationServer), this.properties);
		this.transformExtensionManager =  this.databaseClient.newServerConfigManager().newTransformExtensionsManager();
	}

	@After
	public void tearDown(){
		ConnectionManager.close(this.databaseClient);
	}

	@Test
	public void shouldDeployJavaScriptTransform(){

		this.transformsPayload = new TransformPayload(new File("src/test/resources/transforms/js-test-transform.sjs"));
		this.mlModuleDeployer.deploy(this.transformsPayload);
		assertNotNull(this.transformExtensionManager.readJavascriptTransform("js-test-transform", new StringHandle()).get());
	}

	@Test
	public void shouldDeployXQueryTransform(){

		this.transformsPayload = new TransformPayload(new File("src/test/resources/transforms/xq-test-transform.xqy"));
		this.mlModuleDeployer.deploy(this.transformsPayload);
		assertNotNull(this.transformExtensionManager.readJavascriptTransform("xq-test-transform", new StringHandle()).get());
	}

	@Test
	public void shouldDeployXLSTransform(){

		this.transformsPayload = new TransformPayload(new File("src/test/resources/transforms/xl-test-transform.xls"));
		this.mlModuleDeployer.deploy(this.transformsPayload);
		assertNotNull(this.transformExtensionManager.readJavascriptTransform("xl-test-transform", new StringHandle()).get());
	}

}
