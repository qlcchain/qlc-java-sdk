package qlc.rpc;

import qlc.mng.AccountMng;

public class AccountRpc {

	// generate key pair from seed
	public static String keyPairFromSeed(String seed) {
		
		return AccountMng.keyPairFromSeed(seed);
		
	}

	// convert public key to address
	public static String publicKeyToAddress(String publicKey) {
		
		return AccountMng.publicKeyToAddress(publicKey);
		
	}
	
	// convert address to public key
	public static String addressToPublicKey(String address) {
		
		return AccountMng.addressToPublicKey(address);
		
	}

}
