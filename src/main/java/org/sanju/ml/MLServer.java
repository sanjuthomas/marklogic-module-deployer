package org.sanju.ml;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class MLServer {

	private final String host;
	private final MLCredential credential;
	
	public MLServer(final String host, final MLCredential credential){
		this.host = host;
		this.credential = credential;
	}

	public String getHost() {
		return host;
	}

	public MLCredential getCredential() {
		return credential;
	}
	
}
