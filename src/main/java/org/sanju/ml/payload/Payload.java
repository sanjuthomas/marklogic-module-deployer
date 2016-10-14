package org.sanju.ml.payload;

import java.io.File;

/**
 * @author Sanju Thomas
 *
 */
public  class Payload {

	private final File file;

	/**
	 *
	 * @param contentType
	 * @param endpoint
	 */
	public Payload(final File file){
		this.file = file;
	}

	public File getFile() {
		return this.file;
	}

}
