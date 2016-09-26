package org.sanju.ml.payload;

import java.io.File;

/**
 * @author Sanju Thomas
 *
 */
public  class MLPayload {
	
	private final String contentType;
	private final String endpoint;
	
	/**
	 * 
	 * @param contentType
	 * @param endpoint
	 */
	public MLPayload(final String contentType, final String endpoint){
		this.contentType = contentType;
		this.endpoint = endpoint;
	}
	
	public String getContentType() {
		return contentType;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public String load(final File file){
		
		return "";
	}
}
