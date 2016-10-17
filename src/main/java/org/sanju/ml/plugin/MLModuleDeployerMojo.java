package org.sanju.ml.plugin;

/**
 * Maven plugin to deploy the MarkLogic modules into MarkLogic module database.
 *
 * @author Sanju Thomas
 * @date 20th Sep, 2016
 *
 */

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;


@Mojo(name = "ml-module-deployer", defaultPhase = LifecyclePhase.INSTALL)
public class MLModuleDeployerMojo extends AbstractMojo {

	@Parameter( property = "ml.configuration", defaultValue = "${basedir}" )
	private String mlConfiguration;

	@Override
	public void execute() throws MojoExecutionException {


	}
}
