package org.sanju.ml;

/**
 *
 * @author Sanju Thomas
 *
 */
public class ApplicationServer {

	private final int port;
	private final String contentDatabase;
	private final String moduleDatabase;
	private final Server mlServer;

	public ApplicationServer(final Server mlServer, final int port, final String contentDatabase, final String moduleDatabase){
		this.port = port;
		this.mlServer = mlServer;
		this.contentDatabase = contentDatabase;
		this.moduleDatabase = moduleDatabase;
	}

	public Server getMlServer() {
		return this.mlServer;
	}

	public int getPort() {
		return this.port;
	}

	public String getContentDatabase() {
		return this.contentDatabase;
	}

	public String getModuleDatabase() {
		return this.moduleDatabase;
	}

	public String name(){
		final StringBuilder builder = new StringBuilder();
		builder.append(this.mlServer.getHost());
		builder.append(":");
		builder.append(this.port);
		return builder.toString();
	}

}
