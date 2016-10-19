package org.sanju.ml.deployer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.sanju.ml.payload.PayloadHelper.ContentType;
import org.sanju.ml.payload.TransformPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.admin.TransformExtensionsManager;
import com.marklogic.client.io.FileHandle;

/**
 * Deployer implementation to deploy all transform files to MarkLogic module database.
 *
 * @author Sanju Thomas
 *
 */
public class TransformDeployer implements Deployer<TransformPayload> {

	private static final Logger logger = LoggerFactory.getLogger(TransformDeployer.class);
	private final DatabaseClient databaseClient;
	private final List<TransformPayload> payloads = new ArrayList<>();

	/**
	 *
	 * @param databaseClient
	 * @param properties
	 * @throws IOException
	 * @see {@link DatabaseClient}
	 * @see {@link Properties}
	 * @see {@link IOException}
	 */
	public TransformDeployer(final DatabaseClient databaseClient, final Properties properties) throws IOException {
		this.databaseClient = databaseClient;
		final List<File> files = ModuleUtils.loadAssets(properties.getProperty(ModuleTypes.TRANSFORMS.getSourceLocation()));
		for(final File file : files){
			this.payloads.add(new TransformPayload(file));
		}
	}

	/**
	 *
	 */
	@Override
	public void deploy(final TransformPayload t) {
		logger.info("Deploying {} to database {}", t.getFile().getName(), this.databaseClient.getDatabase());
		final TransformExtensionsManager tem = this.databaseClient.newServerConfigManager().newTransformExtensionsManager();
		final File file = t.getFile();
		final String contentType = t.getContentType();

		if (ContentType.XLS.getType().equalsIgnoreCase(contentType)) {
			tem.writeXSLTransform(FilenameUtils.removeExtension(file.getName()), new FileHandle(file));
		}else if(ContentType.XQY.getType().equalsIgnoreCase(contentType)){
			tem.writeXQueryTransform(FilenameUtils.removeExtension(file.getName()), new FileHandle(file));
		}else if(ContentType.SJS.getType().equalsIgnoreCase(contentType)){
			tem.writeJavascriptTransform(FilenameUtils.removeExtension(file.getName()), new FileHandle(file));
		}else{
			logger.error("Content type {}  is not known.", contentType);
			throw new IllegalArgumentException(contentType + " is not known!");
		}
	}

	@Override
	public void deploy() {

		for(final TransformPayload payload : this.payloads){
			this.deploy(payload);
		}
	}

}
