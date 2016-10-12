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
public class PayloadHelper {

	
	private final static Map<String, ScriptLanguage> fileExtensionLanguageMap = new HashMap<>();
	private final static Map<String, String> fileExtensionContentTypeMap = new HashMap<>();

	static{
		fileExtensionLanguageMap.put("sjs", ScriptLanguage.JAVASCRIPT);
		fileExtensionLanguageMap.put("xqy", ScriptLanguage.XQUERY);
		fileExtensionContentTypeMap.put("xls", ContentType.XLS.getType());
		fileExtensionContentTypeMap.put("xqy", ContentType.XQY.getType());
		fileExtensionContentTypeMap.put("sjs", ContentType.SJS.getType());		
	}
	
	public enum ContentType{
		
		XLS("application/xslt+xml"),
		XQY("application/xslt+xml"),
		SJS("application/xslt+xml");
	
		private String type;
		
		ContentType(String type){
			this.type = type;
		}
		
		public String getType(){
			return this.type;
		}
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
	
	public static String getContentType(final File file){
		
		final String fileExtension = FilenameUtils.getExtension(file.getName());
		final String contentType  = fileExtensionContentTypeMap.get(fileExtension);
		if(null == contentType){
			throw new IllegalArgumentException("Content Type for given file extension is not found. "
					+ "Given file extension is "+fileExtension);
		}
		return contentType;
	}
}
