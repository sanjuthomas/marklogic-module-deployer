package org.sanju.ml.deployer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.sanju.ml.payload.QueryOptionPayload;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.admin.QueryOptionsManager;
import com.marklogic.client.io.FileHandle;

/**
 *
 * @author Sanju Thomas
 *
 */

public class QueryOptionDeployer implements Deployer<QueryOptionPayload>{

	final List<QueryOptionPayload> payloads = new ArrayList<>();

	final DatabaseClient databaseClient;

	public QueryOptionDeployer(final DatabaseClient databaseClient, final Properties properties){
		this.databaseClient = databaseClient;
	}

	@Override
	public void deploy(final QueryOptionPayload t) {

		final File file = t.getFile();
		final QueryOptionsManager qom =  this.databaseClient.newServerConfigManager().newQueryOptionsManager();
		qom.writeOptions(FilenameUtils.removeExtension(file.getName()), new FileHandle(file));

	}

}
