package org.sanju.ml.deployer;

import org.junit.Before;
import org.sanju.ml.ApplicationServer;
import org.sanju.ml.Credential;
import org.sanju.ml.Server;

public abstract class AbstractTest {

	protected Server mlServer;
	protected ApplicationServer mlApplicationServer;

	//these are integration test cases, expect the following to be setup in the ML server.
	@Before
	public void setup(){
		this.mlServer = new Server("localhost", new Credential("admin", "admin"));
		this.mlApplicationServer = new ApplicationServer(this.mlServer, 15000, "azsearch-content", "azsearch-modules");
	}

}
