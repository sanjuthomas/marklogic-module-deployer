package org.sanju.ml;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class MLApplicationServer {
	
	private final int port;
	private final String contentDatabase;
	private final String moduleDatabase;
	private final MLServer mlServer;
	
	public MLApplicationServer(final MLServer mlServer, final int port, final String contentDatabase, final String moduleDatabase){
		this.port = port;
		this.mlServer = mlServer;
		this.contentDatabase = contentDatabase;
		this.moduleDatabase = moduleDatabase;
	}
	
	public MLServer getMlServer() {
		return mlServer;
	}

	public int getPort() {
		return port;
	}

	public String getContentDatabase() {
		return contentDatabase;
	}

	public String getModuleDatabase() {
		return moduleDatabase;
	}

	public String name(){
		final StringBuilder builder = new StringBuilder();
		builder.append(this.mlServer.getHost());
		builder.append(":");
		builder.append(this.port);
		return builder.toString();
	}
	
}
