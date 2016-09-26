package org.sanju.ml.deployer;

import org.sanju.ml.payload.MLPayload;

/**
 * 
 * @author Sanju Thomas
 *
 */
public interface MLModuleDeployer {
	
	public void deploy(final MLPayload mlPayload);

}
