package project3.service;

public interface Crypt {

	/**
	 * This method salts && encrypts the target.
	 *  
	 * @param target : The string you want encrypted.
	 * @return Returns the encrypted target.
	 */
	public String encrypt(String target);

	public String getRandom(int length);
	
}
