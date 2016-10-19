package org.sanju.ml;

/**
 * Encapsulate the MarkLogic Server attributes.
 *
 * @author Sanju Thomas
 *
 */
public class Server {

	private final String host;
	private final Credential credential;

	/**
	 * Constructor to create an instance of the Server class.
	 *
	 * @param host
	 * @param credential
	 */
	public Server(final String host, final Credential credential){
		this.host = host;
		this.credential = credential;
	}

	/**
	 *
	 * @return host
	 */
	public String getHost() {
		return this.host;
	}

	/**
	 *
	 * @return credential
	 */
	public Credential getCredential() {
		return this.credential;
	}

}
