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
		files.forEach(file -> this.payloads.add(new TransformPayload(file)));
	}

	/**
	 * Deploy given instance of a TransformPayload into MarkLogic module database.
	 *
	 */
	@Override
	public void deploy(final TransformPayload t) {
		logger.info("Deploying {}", t.getFile().getName());
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

	/**
	 * Deploy all the instances of the TransformPayload available to the MarkLogic module database.
	 * See the constructor of this class to see how the TransformPayload instances are created.
	 *
	 */
	@Override
	public void deploy() {
		payloads.forEach(payload -> this.deploy(payload));
	}

}
