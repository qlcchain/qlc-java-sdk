package qlc.rpc;

import qlc.mng.AccountMng;
import qlc.network.QlcException;
import qlc.utils.Helper;

public class AccountRpc {

	/**
     * Create a new account by seed and index
     * @param seed
     * @param index : optional, index for account, if not set, default value is 0
     * @return account: the private and public key for account
			privKey: private key for the new account
			pubKey: public key for the new account
	 * @throws QlcException 
     */
	public static String create(String seed, Integer index) throws QlcException {
		
		return AccountMng.keyPairFromSeed(seed, index);
		
	}

	/**
     * Return account address by public key
     * @param publicKey: public key
     * @return account address
	 * @throws QlcException 
     */
	public static String publicKeyToAddress(String publicKey) throws QlcException {

		return AccountMng.publicKeyToAddress(publicKey);
		
	}
	
	/**
     * Return public key for account address
     * @param address:account address
     * @return public key
	 * @throws QlcException 
     */
	public static String addressToPublicKey(String address) throws QlcException {

		return AccountMng.addressToPublicKey(address);
		
	}

}
