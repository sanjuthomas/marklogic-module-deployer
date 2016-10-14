package org.sanju.ml.loader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.sanju.ml.payload.Payload;

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
	public ModulesLoader(Properties properties){
		this.properties = properties;
	}

	enum ModuleTypes{

		REST_EXT("rest.extension.deployer", "rest.extension.payload", "rest.extension.location"),
		OPTIONS("query.options.deployer", "query.options.payload", "query.options.location"),
		TRANSFORMS("transform.deployer", "transform.payload", "transform.location"),
		LIBRARIES("libraries.deployer", "libraries.payload", "libraries.location");

		private final String deployerClass;
		private final String payloadClass;
		private final String sourceLocation;

		ModuleTypes(final String deployer, final String payload, final String location){
			this.deployerClass = deployer;
			this.payloadClass = payload;
			this.sourceLocation = location;
		}

		public String getDeployerClass() {
			return this.deployerClass;
		}

		public String getPayloadClass() {
			return this.payloadClass;
		}

		public String getSourceLocation() {
			return this.sourceLocation;
		}

	}

	@Override
	public void load() throws InstantiationException, IllegalAccessException, ClassNotFoundException, 
		NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		
		final ModuleTypes[] types = ModuleTypes.values();
		for(final ModuleTypes type : types){
			final Object deployer = Class.forName(properties.getProperty(type.getDeployerClass())).newInstance();
			final Object payload = Class.forName(properties.getProperty(type.getPayloadClass())).newInstance();
			final Method method = deployer.getClass().getMethod("deploy", Payload.class);
			method.invoke(deployer, payload);
		}
	}

}


