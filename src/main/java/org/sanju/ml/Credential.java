package org.sanju.ml;

/**
 * Encapsulate the credential of the MarkLogic server.
 *
 * @author Sanju Thomas
 *
 */
public class Credential {

	private final String username;
	private final String password;

	/**
	 * Constructor for Credential class.
	 *
	 * @param username
	 * @param password
	 */
	public Credential(final String username, final String password){
		this.username = username;
		this.password = password;
	}

	/**
	 * @return username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 *
	 * @return password
	 */
	public String getPassword() {
		return this.password;
	}

}
