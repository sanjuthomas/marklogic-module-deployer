package org.sanju.ml.deployer;

import org.sanju.ml.payload.MLRestExtensionsPayload;

/**
 * 
 * @author Sanju Thomas
 *
 */
public interface MLModuleDeployer {
	
	void deploy(MLRestExtensionsPayload mlRestExtensionsPayload);

}
