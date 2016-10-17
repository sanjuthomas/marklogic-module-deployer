package org.sanju.ml.deployer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.Before;
import org.sanju.ml.ApplicationServer;
import org.sanju.ml.Credential;
import org.sanju.ml.Server;

public abstract class AbstractTest {

	protected Server mlServer;
	protected ApplicationServer mlApplicationServer;
	protected Properties properties;

	//these are integration test cases, expect the following to be setup in the ML server.
	@Before
	public void setup() throws FileNotFoundException, IOException{
		this.properties = new Properties();
		this.properties.load(new FileInputStream(new File("src/test/resources/ml-server-config.properties")));
		this.mlServer = new Server("localhost", new Credential("admin", "admin"));
		this.mlApplicationServer = new ApplicationServer(this.mlServer, 15000, "azsearch-content", "azsearch-modules");
	}

}
