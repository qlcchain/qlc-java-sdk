package qlc.bean;

public final class Address {
	
	private final String address;
	
	private final String publicKey;
	
	private final String privateKey;
	
	public Address(String address, String publicKey, String privateKey) {
		this.address = address;
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}

	/** 
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/** 
	 * @return publicKey
	 */
	public String getPublicKey() {
		return publicKey;
	}

	/** 
	 * @return privateKey
	 */
	public String getPrivateKey() {
		return privateKey;
	}
	
}
