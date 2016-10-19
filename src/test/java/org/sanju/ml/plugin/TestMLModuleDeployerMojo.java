package org.sanju.ml.plugin;

import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.maven.plugin.MojoExecutionException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sanju.ml.ConnectionManager;
import org.sanju.ml.deployer.AbstractTest;

import com.marklogic.client.admin.ExtensionLibrariesManager;
import com.marklogic.client.admin.QueryOptionsManager;
import com.marklogic.client.admin.ResourceExtensionsManager;
import com.marklogic.client.admin.TransformExtensionsManager;
import com.marklogic.client.io.StringHandle;

/**
 *
 * @author Sanju Thomas
 *
 */
public class TestMLModuleDeployerMojo extends AbstractTest{

	private MLModuleDeployerMojo mlModuleDeployerMojo;
	private ExtensionLibrariesManager extensionLibrariesManager;
	private QueryOptionsManager queryOptionsManager ;
	private ResourceExtensionsManager resourceExtensionsManager;
	private TransformExtensionsManager transformExtensionManager;


	@Override
	@Before
	public void setup() throws FileNotFoundException, IOException{
		super.setup();
		this.mlModuleDeployerMojo = new MLModuleDeployerMojo();
	}

	@After
	public void tearDown(){
		ConnectionManager.close(this.databaseClient);
	}

	@Test
	public void shouldDeployAllAssets() throws MojoExecutionException{
		this.mlModuleDeployerMojo.setMlConfiguration("src/test/resources/ml-server-config.properties");
		this.mlModuleDeployerMojo.execute();

		super.databaseClient = ConnectionManager.getClient(this.mlApplicationServer);
		this.extensionLibrariesManager = this.databaseClient.newServerConfigManager().newExtensionLibrariesManager();
		this.queryOptionsManager = this.databaseClient.newServerConfigManager().newQueryOptionsManager();
		this.resourceExtensionsManager = this.databaseClient.newServerConfigManager().newResourceExtensionsManager();
		this.transformExtensionManager = this.databaseClient.newServerConfigManager().newTransformExtensionsManager();
		assertNotNull(this.resourceExtensionsManager.readServices("js-test-extension", new StringHandle()).get());
		assertNotNull(this.resourceExtensionsManager.readServices("xq-test-extension", new StringHandle()).get());
		assertNotNull(this.extensionLibrariesManager.read("/ext/libraries/test-lib.sjs", new StringHandle()).get());
		assertNotNull(this.queryOptionsManager.readOptions("test-query-option", new StringHandle()).get());
		assertNotNull(this.transformExtensionManager.readJavascriptTransform("js-test-transform", new StringHandle()).get());
		assertNotNull(this.transformExtensionManager.readJavascriptTransform("xq-test-transform", new StringHandle()).get());
		assertNotNull(this.transformExtensionManager.readJavascriptTransform("xl-test-transform", new StringHandle()).get());
	}

}
