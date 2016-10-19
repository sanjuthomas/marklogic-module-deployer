package org.sanju.ml.deployer;

import org.sanju.ml.payload.Payload;

/**
 * @author Sanju Thomas
 *
 * Public interface that abstract the deploy behaviors.
 */
public interface Deployer<T extends Payload> {

	/**
	 * Deploy the given payload to MarkLogic module database.
	 *
	 * @param t
	 */
	void deploy(T t);

	/**
	 * Deploy all the payloads in the instance of a concrete implementation of Deployer.
	 *
	 */
	void deploy();
}
