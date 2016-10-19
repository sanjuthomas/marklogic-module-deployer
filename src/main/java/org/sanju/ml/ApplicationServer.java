package org.sanju.ml;

/**
 *
 * @author Sanju Thomas
 *
 * Encapsulate the MarkLogic application server attributes.
 *
 */
public class ApplicationServer {

	private final int port;
	private final Server mlServer;

	/**
	 * Create an instance of the MarkLogic AppplicationServer class.
	 *
	 * @param mlServer
	 * @param port
	 * @see {@link Server}
	 */
	public ApplicationServer(final Server mlServer, final int port){
		this.port = port;
		this.mlServer = mlServer;
	}

	/**
	 * Return the MLServer instance.
	 *
	 * @return mlServer
	 * @see {@link Server}
	 */
	public Server getMlServer() {
		return this.mlServer;
	}

	/**
	 * Return the port number of the application server.
	 *
	 * @return port
	 */
	public int getPort() {
		return this.port;
	}

	/**
	 * Construct the name of the ApplicationServer in the host:port format.
	 *
	 * @return name
	 */
	public String name(){
		final StringBuilder builder = new StringBuilder();
		builder.append(this.mlServer.getHost());
		builder.append(":");
		builder.append(this.port);
		return builder.toString();
	}

}
