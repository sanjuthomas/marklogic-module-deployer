package org.sanju.ml.payload;

import java.io.File;

/**
 *
 * @author Sanju Thomas
 *
 */
public class LibraryPayload extends Payload{

	private String libraryPrefix;

	/**
	 *
	 * @param libraryPrefix
	 * @param file
	 */
	public LibraryPayload(final String libraryPrefix, final File file) {
		super(file);
		this.libraryPrefix = libraryPrefix;
	}

	/**
	 *
	 * @return
	 */
	public String getLibraryPrefix() {
		return this.libraryPrefix;
	}

	/**
	 *
	 * @param libraryPrefix
	 */
	public void setLibraryPrefix(final String libraryPrefix) {
		this.libraryPrefix = libraryPrefix;
	}


}
