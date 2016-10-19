package org.sanju.ml.deployer;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.sanju.ml.ConnectionManager;
import org.sanju.ml.payload.QueryOptionPayload;

import com.marklogic.client.admin.QueryOptionsManager;
import com.marklogic.client.io.Format;
import com.marklogic.client.io.StringHandle;

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
		final QueryOptionsManager qom =  this.databaseClient.newServerConfigManager().newQueryOptionsManager();
		final StringHandle readHandle = new StringHandle();
		readHandle.setFormat(Format.XML);
		qom.readOptions("test-query-option", readHandle);
		final String output = readHandle.get();
		assertNotNull(output);
	}
}
