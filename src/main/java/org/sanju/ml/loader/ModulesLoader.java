package org.sanju.ml.loader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.sanju.ml.deployer.ModuleTypes;
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
	public ModulesLoader(final Properties properties){
		this.properties = properties;
	}

	@Override
	public void load() throws InstantiationException, IllegalAccessException, ClassNotFoundException,
	NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {

		final ModuleTypes[] types = ModuleTypes.values();
		for(final ModuleTypes type : types){
			final Object deployer = Class.forName(this.properties.getProperty(type.getDeployerClass())).newInstance();
			final Object payload = Class.forName(this.properties.getProperty(type.getPayloadClass())).newInstance();
			final Method method = deployer.getClass().getMethod("deploy", Payload.class);
			method.invoke(deployer, payload);
		}
	}

}


