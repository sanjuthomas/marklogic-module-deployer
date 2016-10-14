package org.sanju.ml.deployer;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.sanju.ml.MLClientFactory;
import org.sanju.ml.payload.LibraryPayload;

/**
 *
 * @author Sanju Thomas
 *
 */
public class TestLibraryDeployer extends AbstractTest{

	private Deployer<LibraryPayload> mlModuleDeployer;
	private LibraryPayload libraryPayload;

	@Override
	@Before
	public void setup(){
		super.setup();
		this.mlModuleDeployer = new LibraryDeployer(MLClientFactory.getClient(this.mlApplicationServer));
		this.libraryPayload = new LibraryPayload("/lib/", new File("src/test/resources/libraries/test-lib.sjs"));
	}

	@Test
	public void shoulDeployLibraries(){
		this.mlModuleDeployer.deploy(this.libraryPayload);
	}

}
