package org.sanju.ml.deployer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.sanju.ml.ConnectionManager;
import org.sanju.ml.payload.TransformPayload;

/**
 *
 * @author Sanju Thomas
 *
 */
public class TestTransformDeployer extends AbstractTest{

	private TransformPayload transformsPayload;
	private Deployer<TransformPayload> mlModuleDeployer;

	@Override
	@Before
	public void setup() throws FileNotFoundException, IOException{
		super.setup();
		this.mlModuleDeployer = new TransformDeployer(ConnectionManager.getClient(this.mlApplicationServer), this.properties);
	}

	@Test
	public void shouldDeployJavaScriptTransform(){

		this.transformsPayload = new TransformPayload(new File("src/test/resources/transforms/js-test-transform.sjs"));
		this.mlModuleDeployer.deploy(this.transformsPayload);
	}

	@Test
	public void shouldDeployXQueryTransform(){

		this.transformsPayload = new TransformPayload(new File("src/test/resources/transforms/xq-test-transform.xqy"));
		this.mlModuleDeployer.deploy(this.transformsPayload);
	}

	@Test
	public void shouldDeployXLSTransform(){

		this.transformsPayload = new TransformPayload(new File("src/test/resources/transforms/xl-test-transform.xls"));
		this.mlModuleDeployer.deploy(this.transformsPayload);

	}

}
