package org.sanju.ml.deployer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.sanju.ml.ConnectionManager;
import org.sanju.ml.payload.LibraryPayload;

import com.marklogic.client.admin.ExtensionLibrariesManager;
import com.marklogic.client.io.StringHandle;

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
	public void setup() throws FileNotFoundException, IOException{
		super.setup();
		this.mlModuleDeployer = new LibraryDeployer(ConnectionManager.getClient(this.mlApplicationServer), this.properties);
		this.libraryPayload = new LibraryPayload("/ext/libraries/", new File("src/test/resources/libraries/test-lib.sjs"));
	}

	@Test
	public void shoulDeployLibraries(){
		this.mlModuleDeployer.deploy(this.libraryPayload);
		final ExtensionLibrariesManager elm = this.databaseClient.newServerConfigManager().newExtensionLibrariesManager();
		elm.read("/ext/libraries/test-lib.sjs", new StringHandle()).get();
	}
}
