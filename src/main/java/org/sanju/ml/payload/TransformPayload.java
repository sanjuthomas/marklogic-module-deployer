package org.sanju.ml.payload;

import java.io.File;

/**
 *
 * @author Sanju Thomas
 *
 */
public class TransformPayload extends Payload{

	private final String contentType;

	public TransformPayload(final File file) {
		super(file);
		this.contentType = PayloadHelper.getContentType(file);
	}

	public String getContentType() {
		return contentType;
	}
}
