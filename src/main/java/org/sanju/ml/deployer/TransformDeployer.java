package org.sanju.ml.deployer;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.sanju.ml.payload.PayloadHelper.ContentType;
import org.sanju.ml.payload.TransformPayload;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.admin.TransformExtensionsManager;
import com.marklogic.client.io.FileHandle;

/**
 *
 * @author Sanju Thomas
 *
 */
public class TransformDeployer implements ModuleDeployer<TransformPayload> {

	final DatabaseClient databaseClient;

	public TransformDeployer(final DatabaseClient databaseClient) {
		this.databaseClient = databaseClient;
	}

	@Override
	public void deploy(final TransformPayload t) {
		final TransformExtensionsManager tem = this.databaseClient.newServerConfigManager().newTransformExtensionsManager();
		final File file = t.getFile();
		final String contentType = t.getContentType();

		if (ContentType.XLS.getType().equalsIgnoreCase(contentType)) {
			tem.writeXSLTransform(FilenameUtils.removeExtension(file.getName()), new FileHandle(file));
		}else if(ContentType.XQY.getType().equalsIgnoreCase(contentType)){
			tem.writeXQueryTransform(FilenameUtils.removeExtension(file.getName()), new FileHandle(file));
		}else if(ContentType.SJS.getType().equalsIgnoreCase(contentType)){
			tem.writeJavascriptTransform(FilenameUtils.removeExtension(file.getName()), new FileHandle(file));
		}
	}

}
