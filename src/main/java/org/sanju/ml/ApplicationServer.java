package org.sanju.ml;

/**
 *
 * @author Sanju Thomas
 *
 */
public class ApplicationServer {

	private final int port;
	private final Server mlServer;

	public ApplicationServer(final Server mlServer, final int port){
		this.port = port;
		this.mlServer = mlServer;
	}

	public Server getMlServer() {
		return this.mlServer;
	}

	public int getPort() {
		return this.port;
	}

	public String name(){
		final StringBuilder builder = new StringBuilder();
		builder.append(this.mlServer.getHost());
		builder.append(":");
		builder.append(this.port);
		return builder.toString();
	}

}
