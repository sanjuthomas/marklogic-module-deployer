package org.sanju.ml;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class Server {

	private final String host;
	private final Credential credential;
	
	public Server(final String host, final Credential credential){
		this.host = host;
		this.credential = credential;
	}

	public String getHost() {
		return host;
	}

	public Credential getCredential() {
		return credential;
	}
	
}
