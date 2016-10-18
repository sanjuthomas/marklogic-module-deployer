package org.sanju.ml.plugin;

import org.apache.maven.plugin.MojoExecutionException;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Sanju Thomas
 *
 */
public class TestMLModuleDeployerMojo {

	private MLModuleDeployerMojo mlModuleDeployerMojo;

	@Before
	public void setup(){
		this.mlModuleDeployerMojo = new MLModuleDeployerMojo();
	}

	@Test
	public void shouldDeployAllAssets() throws MojoExecutionException{
		this.mlModuleDeployerMojo.setMlConfiguration("src/test/resources/ml-server-config.properties");
		this.mlModuleDeployerMojo.execute();
	}
}
