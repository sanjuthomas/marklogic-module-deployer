package org.sanju.ml.deployer;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.sanju.ml.payload.Payload;

/**
 *
 * @author Sanju Thomas
 *
 */
public abstract class AbstractDeployer implements Deployer<Payload>{

	protected List<Files> loadAssets(final String location){

		return new ArrayList<>();
	}

}
