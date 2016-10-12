package org.sanju.ml.payload;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.marklogic.client.admin.ExtensionMetadata.ScriptLanguage;

/**
 * @author Sanju Thomas
 *
 */
public  class Payload {

	protected final static Map<String, ScriptLanguage> fileExtensionLanguageMap = new HashMap<>();
	private final File file;

	static{
		fileExtensionLanguageMap.put("js", ScriptLanguage.JAVASCRIPT);
		fileExtensionLanguageMap.put("xqy", ScriptLanguage.XQUERY);
	}

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
