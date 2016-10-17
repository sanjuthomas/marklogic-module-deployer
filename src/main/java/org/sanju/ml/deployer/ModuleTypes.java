package org.sanju.ml.deployer;

/**
 *
 * @author Sanju Thomas
 *
 */
public enum ModuleTypes{

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