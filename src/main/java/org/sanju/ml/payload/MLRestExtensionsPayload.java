package org.sanju.ml.payload;

import java.io.File;

import com.marklogic.client.admin.ExtensionMetadata.ScriptLanguage;

/**
 *
 * @author Sanju Thomas
 *
 */
public class MLRestExtensionsPayload extends MLPayload{

	private final ScriptLanguage scriptLanguage;

	public MLRestExtensionsPayload(final File file) {
		super(file);
		this.scriptLanguage = LanguagePicker.getScriptingLanguage(file);
	}

	public ScriptLanguage getScriptLanguage() {
		return this.scriptLanguage;
	}

}
