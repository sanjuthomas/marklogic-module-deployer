package org.sanju.ml.deployer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.sanju.ml.payload.QueryOptionPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.admin.QueryOptionsManager;
import com.marklogic.client.io.FileHandle;

/**
 *
 * @author Sanju Thomas
 *
 */

public class QueryOptionDeployer implements Deployer<QueryOptionPayload>{

	private static final Logger logger = LoggerFactory.getLogger(QueryOptionDeployer.class);
	private final List<QueryOptionPayload> payloads = new ArrayList<>();
	private final DatabaseClient databaseClient;

	public QueryOptionDeployer(final DatabaseClient databaseClient, final Properties properties) throws IOException{
		this.databaseClient = databaseClient;
		final List<File> files = ModuleUtils.loadAssets(properties.getProperty(ModuleTypes.OPTIONS.getSourceLocation()));
		for(final File file : files){
			this.payloads.add(new QueryOptionPayload(file));
		}
	}

	@Override
	public void deploy(final QueryOptionPayload t) {
		logger.info("Deploying {} to database {}", t.getFile().getName(), this.databaseClient.getDatabase());
		final File file = t.getFile();
		final QueryOptionsManager qom =  this.databaseClient.newServerConfigManager().newQueryOptionsManager();
		qom.writeOptions(FilenameUtils.removeExtension(file.getName()), new FileHandle(file));
	}

	@Override
	public void deploy() {
		for(final QueryOptionPayload payload : this.payloads){
			this.deploy(payload);
		}
	}

}
