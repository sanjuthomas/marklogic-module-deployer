package org.sanju.ml.payload;

import java.io.File;

/**
 *
 * @author Sanju Thomas
 *
 */
public class TransformsPayload extends Payload{

	private final String contentType;

	public TransformsPayload(final File file) {
		super(file);
		this.contentType = PayloadHelper.getContentType(file);
	}

	public String getContentType() {
		return contentType;
	}
}
