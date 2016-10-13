package org.sanju.ml.deployer;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.sanju.ml.MLClientFactory;
import org.sanju.ml.payload.TransformPayload;

/**
 *
 * @author Sanju Thomas
 *
 */
public class TestTransformDeployer extends AbstractTest{

	private TransformPayload transformsPayload;
	private ModuleDeployer<TransformPayload> mlModuleDeployer;

	@Override
	@Before
	public void setup(){
		super.setup();
		this.mlModuleDeployer = new TransformDeployer(MLClientFactory.getClient(this.mlApplicationServer));
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
