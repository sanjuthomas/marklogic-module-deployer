package org.sanju.ml.plugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

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
import org.sanju.ml.ApplicationServer;
import org.sanju.ml.ConnectionManager;
import org.sanju.ml.Credential;
import org.sanju.ml.Server;
import org.sanju.ml.deployer.ModuleTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marklogic.client.DatabaseClient;

/**
 *
 * @author Sanju Thomas
 *
 */
@Mojo(name = "ml-module-deployer", defaultPhase = LifecyclePhase.INSTALL)
public class MLModuleDeployerMojo extends AbstractMojo {

	private static final Logger logger = LoggerFactory.getLogger(MLModuleDeployerMojo.class);

	@Parameter(property = "ml.configuration", defaultValue = "${basedir}/src/main/resources/ml-server-config.properties")
	private String mlConfiguration;

	public void setMlConfiguration(final String mlConfiguration) {
		this.mlConfiguration = mlConfiguration;
	}

	@Override
	public void execute() throws MojoExecutionException {
		DatabaseClient databasecClient = null;
		try {
			final Properties properties = new Properties();
			properties.load(new FileInputStream(new File(this.mlConfiguration)));
			final String host = properties.getProperty(PropertyConstants.ML_HOST);
			final Integer port = Integer.valueOf(properties.getProperty(PropertyConstants.ML_PORT));
			final String username = properties.getProperty(PropertyConstants.ML_USERNAME);
			final String password = properties.getProperty(PropertyConstants.ML_PASSWORD);
			final String contentDatabase = properties.getProperty(PropertyConstants.ML_CONTENT_DATABASE);
			final String moduleDatabase = properties.getProperty(PropertyConstants.ML_MODULE_DATABASE);
			final Credential credential = new Credential(username, password);
			final Server server = new Server(host, credential);
			final ApplicationServer applicationServer = new ApplicationServer(server, port, contentDatabase,
					moduleDatabase);
			databasecClient = ConnectionManager.getClient(applicationServer);
			final ModuleTypes[] types = ModuleTypes.values();
			for (final ModuleTypes type : types) {
				final Constructor<?> constructor = Class.forName(properties.getProperty(type.getDeployerClass())).getConstructor(DatabaseClient.class, Properties.class);
				final Object instance = constructor.newInstance(databasecClient, properties);
				final Method method = instance.getClass().getMethod(PropertyConstants.ML_MODULE_DEPLOYER_METHOD);
				method.invoke(instance);
			}
		} catch (NoSuchMethodException | SecurityException | ClassNotFoundException | IOException
				| InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			logger.error("Error occurred while execting MarkLogic Module Deployer Maven Plugin", e.getMessage(), e);
		} finally{
			ConnectionManager.close(databasecClient);
		}
	}

}
