package org.sanju.ml.loader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.sanju.ml.ApplicationServer;
import org.sanju.ml.ConnectionManager;
import org.sanju.ml.Credential;
import org.sanju.ml.Server;
import org.sanju.ml.deployer.ModuleTypes;
import org.sanju.ml.plugin.PropertyConstants;

import com.marklogic.client.DatabaseClient;

/**
 *
 * @author Sanju Thomas
 *
 */
public class ModulesLoader implements Loader{

	private final Properties properties;

	/**
	 * @param properties
	 */
	public ModulesLoader(final Properties properties){
		this.properties = properties;
	}

	@Override
	public void load() throws InstantiationException, IllegalAccessException, ClassNotFoundException,
	NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {

		final String host = this.properties.getProperty(PropertyConstants.ML_HOST);
		final Integer port = Integer.valueOf(this.properties.getProperty(PropertyConstants.ML_PORT));
		final String username = this.properties.getProperty(PropertyConstants.ML_USERNAME);
		final String password = this.properties.getProperty(PropertyConstants.ML_PASSWORD);
		final String contentDatabase = this.properties.getProperty(PropertyConstants.ML_CONTENT_DATABASE);
		final String moduleDatabase = this.properties.getProperty(PropertyConstants.ML_MODULE_DATABASE);
		final Credential credential = new Credential(username, password);
		final Server server = new Server(host, credential);
		final ApplicationServer applicationServer = new ApplicationServer(server, port, contentDatabase, moduleDatabase);
		final DatabaseClient databasecClient = ConnectionManager.getClient(applicationServer);

		final ModuleTypes[] types = ModuleTypes.values();
		for(final ModuleTypes type : types){
			final Constructor<?> constructor = Class.forName(this.properties.getProperty(type.getDeployerClass())).getConstructor(DatabaseClient.class, Properties.class);
			final Object instance = constructor.newInstance(databasecClient, this.properties);
			final Method method = instance.getClass().getMethod("deploy");
			method.invoke(instance);
		}
	}

}


