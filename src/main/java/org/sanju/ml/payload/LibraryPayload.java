package org.sanju.ml.payload;

import java.io.File;

/**
 *
 * @author Sanju Thomas
 *
 */
public class LibraryPayload extends Payload{

	private String libraryPrefix;

	public LibraryPayload(final String libraryPrefix, final File file) {
		super(file);
		this.libraryPrefix = libraryPrefix;
	}

	public String getLibraryPrefix() {
		return this.libraryPrefix;
	}

	public void setLibraryPrefix(final String libraryPrefix) {
		this.libraryPrefix = libraryPrefix;
	}


}
