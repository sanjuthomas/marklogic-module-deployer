package org.sanju.ml.deployer;

import org.junit.Before;
import org.sanju.ml.MLApplicationServer;
import org.sanju.ml.MLCredential;
import org.sanju.ml.MLServer;

public abstract class AbstractTest {

	protected MLServer mlServer;
	protected MLApplicationServer mlApplicationServer;

	//these are integration test cases, expect the following to be setup in the ML server.
	@Before
	public void setup(){
		this.mlServer = new MLServer("localhost", new MLCredential("admin", "admin"));
		this.mlApplicationServer = new MLApplicationServer(this.mlServer, 15000, "azsearch-content", "azsearch-modules");
	}

}
