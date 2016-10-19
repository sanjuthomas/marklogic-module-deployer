package org.sanju.ml.deployer;

/**
 * Enumeration that encapsulate the deployer and location properties of various types of MarkLogic server side artifacts.
 *
 * @author Sanju Thomas
 *
 */
public enum ModuleTypes {

	REST_EXT("rest.extension.deployer", "rest.extension.location"),
	OPTIONS("query.options.deployer", "query.options.location"),
	TRANSFORMS("transforms.deployer", "transforms.location"),
	LIBRARIES("libraries.deployer", "libraries.location");

	private final String deployerClass;
	private final String sourceLocation;

	/**
	 *
	 * @param deployer
	 * @param location
	 */
	ModuleTypes(final String deployer, final String location) {
		this.deployerClass = deployer;
		this.sourceLocation = location;
	}

	/**
	 *
	 * @return deployerClass
	 */
	public String getDeployerClass() {
		return this.deployerClass;
	}

	/**
	 *
	 * @return sourceLocation
	 */
	public String getSourceLocation() {
		return this.sourceLocation;
	}

}