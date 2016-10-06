package org.sanju.ml.payload;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;

import com.marklogic.client.admin.ExtensionMetadata.ScriptLanguage;

/**
 *
 * @author Sanju Thomas
 *
 */
public class LanguagePicker {

	private final static Map<String, ScriptLanguage> fileExtensionLanguageMap = new HashMap<>();

	static{
		fileExtensionLanguageMap.put("sjs", ScriptLanguage.JAVASCRIPT);
		fileExtensionLanguageMap.put("xqy", ScriptLanguage.XQUERY);
	}

	public static ScriptLanguage getScriptingLanguage(final File file) {

		final String fileExtension = FilenameUtils.getExtension(file.getName());
		final ScriptLanguage configuredScriptingLanguage  = fileExtensionLanguageMap.get(fileExtension);
		if(null == configuredScriptingLanguage){
			throw new IllegalArgumentException("Scripting languge for given file extension is not found. "
					+ "Given file extension is "+fileExtension);
		}
		return configuredScriptingLanguage;
	}
}
