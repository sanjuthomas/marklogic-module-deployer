package org.sanju.ml.payload;

import java.io.File;

import com.marklogic.client.admin.ExtensionMetadata.ScriptLanguage;

/**
 *
 * @author Sanju Thomas
 *
 */
public class RestExtensionsPayload extends Payload{

	private final ScriptLanguage scriptLanguage;

	public RestExtensionsPayload(final File file) {
		super(file);
		this.scriptLanguage = PayloadHelper.getScriptingLanguage(file);
	}

	public ScriptLanguage getScriptLanguage() {
		return this.scriptLanguage;
	}

}
