package org.sanju.ml.loader;

import java.util.Properties;

/**
 *
 * @author Sanju Thomas
 *
 */
public class ModulesLoader implements Loader{

	enum ModuleTypes{

		REST_EXT("rest.ext.deployer", "rest.ext.payload", "rest.ext.location"),
		OPTIONS("rest.options.deployer", "rest.options.payload", "rest.options.location"),
		TRANSFORMS("rest.transform.deployer", "rest.transform.payload", "rest.transform.location"),
		LIBRARIES("rest.libraries.deployer", "rest.libraries.payload", "rest.libraries.location");

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
	public void load(final Properties properties) {

		final ModuleTypes[] types = ModuleTypes.values();
		for(final ModuleTypes type : types){


		}
	}

}


