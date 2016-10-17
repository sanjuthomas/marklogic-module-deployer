package org.sanju.ml.deployer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.sanju.ml.ConnectionManager;
import org.sanju.ml.payload.QueryOptionPayload;

/**
 *
 * @author Sanju Thomas
 *
 */
public class TestQueryOptionDeployer extends AbstractTest{

	private Deployer<QueryOptionPayload> mlModuleDeployer;
	private QueryOptionPayload queryOptionPayload;


	@Override
	@Before
	public void setup() throws FileNotFoundException, IOException{
		super.setup();
		this.mlModuleDeployer = new QueryOptionDeployer(ConnectionManager.getClient(this.mlApplicationServer), this.properties);
		this.queryOptionPayload = new QueryOptionPayload(new File("src/test/resources/query-options/test-query-option.xml"));
	}

	@Test
	public void shouldLoadQueryOptions(){
		this.mlModuleDeployer.deploy(this.queryOptionPayload);
	}
}
