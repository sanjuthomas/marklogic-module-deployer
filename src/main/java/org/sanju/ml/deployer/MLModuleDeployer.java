package org.sanju.ml.deployer;

import org.sanju.ml.payload.MLRestExtensionsPayload;
import org.sanju.ml.payload.MLTransformsPayload;

/**
 *
 * @author Sanju Thomas
 *
 */
public interface MLModuleDeployer {

	void deploy(MLRestExtensionsPayload mlRestExtensionsPayload);

	void deploy(MLTransformsPayload mlTransformsPayload);

}
