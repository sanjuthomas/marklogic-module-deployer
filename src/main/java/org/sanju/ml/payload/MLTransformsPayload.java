package org.sanju.ml.payload;

import java.io.File;

import com.marklogic.client.admin.ExtensionMetadata.ScriptLanguage;

/**
 *
 * @author Sanju Thomas
 *
 */
public class MLTransformsPayload extends MLPayload{

	private final ScriptLanguage scriptLanguage;

	public MLTransformsPayload(final File file) {
		super(file);
		this.scriptLanguage = LanguagePicker.getScriptingLanguage(file);
	}

}
