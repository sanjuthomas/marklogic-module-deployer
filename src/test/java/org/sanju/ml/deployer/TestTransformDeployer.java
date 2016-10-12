package org.sanju.ml.deployer;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.sanju.ml.MLClientFactory;
import org.sanju.ml.payload.TransformsPayload;

/**
 *
 * @author Sanju Thomas
 *
 */
public class TestTransformDeployer extends AbstractTest{

	private TransformsPayload transformsPayload;
	private ModuleDeployer<TransformsPayload> mlModuleDeployer;

	@Override
	@Before
	public void setup(){
		super.setup();
		this.mlModuleDeployer = new TransformDeployer(MLClientFactory.getClient(this.mlApplicationServer));
	}

	@Test
	public void shouldDeployJavaScriptTransform(){

		this.transformsPayload = new TransformsPayload(new File("src/test/resources/transforms/js-test-transform.sjs"));
		this.mlModuleDeployer.deploy(this.transformsPayload);
	}

	@Test
	public void shouldDeployXQueryTransform(){

		this.transformsPayload = new TransformsPayload(new File("src/test/resources/transforms/xq-test-transform.xqy"));
		this.mlModuleDeployer.deploy(this.transformsPayload);
	}

}
