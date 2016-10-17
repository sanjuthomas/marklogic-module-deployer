package org.sanju.ml.deployer;

import org.sanju.ml.payload.Payload;

/**
 * @author Sanju Thomas
 *
 */
public interface Deployer<T extends Payload> {

	void deploy(T t);

	void deploy();
}
