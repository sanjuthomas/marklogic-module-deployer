package org.sanju.ml.deployer;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.sanju.ml.MLClientFactory;
import org.sanju.ml.payload.QueryOptionPayload;

/**
 *
 * @author Sanju Thomas
 *
 */
public class TestQueryOptionDeployer extends AbstractTest{

	private ModuleDeployer<QueryOptionPayload> mlModuleDeployer;
	private QueryOptionPayload queryOptionPayload;


	@Override
	@Before
	public void setup(){
		super.setup();
		this.mlModuleDeployer = new QueryOptionDeployer(MLClientFactory.getClient(this.mlApplicationServer));
		this.queryOptionPayload = new QueryOptionPayload(new File("src/test/resources/query-options/test-query-option.xml"));
	}

	@Test
	public void shouldLoadQueryOptions(){
		this.mlModuleDeployer.deploy(this.queryOptionPayload);
	}
}
