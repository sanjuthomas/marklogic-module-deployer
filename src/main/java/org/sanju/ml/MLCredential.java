package org.sanju.ml;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class MLCredential {
	
	private String username;
	private String password;
	
	public MLCredential(final String username, final String password){
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
