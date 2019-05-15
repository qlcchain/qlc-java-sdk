package qlc.mng;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;

import qlc.network.QlcException;
import qlc.utils.Blake2b;
import qlc.utils.Constants;
import qlc.utils.ED25519;
import qlc.utils.Helper;

public final class AccountMng {

	public static final char[] ACCOUNT_ALPHABET = "13456789abcdefghijkmnopqrstuwxyz".toCharArray();
	
	public static final Map<String, Character> NUMBER_CHAR_MAP = new HashMap<String, Character>();
	
	public static final Map<Character, String> CHAR_NUMBER_MAP = new HashMap<Character, String>();
	
	// init the NUMBER_CHAR_MAP and CHAR_NUMBER_MAP
	private static void intMap() {
		
		for (int i=0; i<ACCOUNT_ALPHABET.length; i++) {
			String num = Integer.toBinaryString(i);
			while (num.length() < 5) // Not enough 5 digits, add 0
				num = "0" + num;
			NUMBER_CHAR_MAP.put(num, ACCOUNT_ALPHABET[i]);
			CHAR_NUMBER_MAP.put(ACCOUNT_ALPHABET[i], num);
		}
		
	}
	
	// generate key pair from seed
	public String keyPairFromSeed(String seed, Integer index) throws QlcException {
		
		// check param
		if (seed == null)
			throw new QlcException(Constants.EXCEPTION_CODE_1001, Constants.EXCEPTION_MSG_1001);
		byte[] seedByte = Helper.hexStringToBytes(seed);
		index = (index==null) ? 0 : index;
		
		// generate the public key and private key
		final Blake2b blake2b = Blake2b.Digest.newInstance(64);
		blake2b.update(seedByte);//add seed
		blake2b.update(ByteBuffer.allocate(4).putInt(index).array()); //and add index
		byte[] privateKey = blake2b.digest();
		byte[] publicKey = ED25519.publickey(privateKey);
		
		JSONObject json = new JSONObject();
		json.put("privKey", Helper.byteToHexString(privateKey));
		json.put("pubKey", Helper.byteToHexString(publicKey));
		
		return json.toJSONString();
	}

	// convert public key to address
	public String publicKeyToAddress(String publicKey) throws QlcException {
		
		if (publicKey == null)
			throw new QlcException(Constants.EXCEPTION_CODE_1002, Constants.EXCEPTION_MSG_1002);
		
		intMap();
		
		final Blake2b blake2b = Blake2b.Digest.newInstance(5);
		blake2b.update(Helper.hexStringToBytes(publicKey));
		byte[] digest = Helper.reverse(blake2b.digest());
		String digestNum = Helper.hexStringToBinary(Helper.byteToHexString((digest)));
		
		String checkValue = "";
		while (digestNum.length() < digest.length * 8)
			digestNum = "0" + digestNum;
		for (int i = 0; i < ((digest.length * 8) / 5); i++) {
			String fiveBit = digestNum.substring(i * 5, (i * 5) + 5);
			checkValue += NUMBER_CHAR_MAP.get(fiveBit);
		}

		String paddingValue = "";
		String publicKeyBinary = Helper.hexStringToBinary(publicKey);
		while (publicKeyBinary.length() < 260)
			publicKeyBinary = "0" + publicKeyBinary; 
		for (int i=0; i<publicKeyBinary.length(); i+=5) {
			String fiveBit = publicKeyBinary.substring(i, i + 5);
			paddingValue += NUMBER_CHAR_MAP.get(fiveBit);
		}

		//return the address prefixed with qlc_ and suffixed with the checksum
		return "qlc_" + paddingValue + checkValue;
		
		
	}
	
	// convert address to public key
	public String addressToPublicKey(String address) throws QlcException {
		
		if (StringUtils.isBlank(address))
			throw new QlcException(Constants.EXCEPTION_CODE_1003, Constants.EXCEPTION_MSG_1003);
		if (address.length()!=64 || !address.substring(0, 4).equals("qlc_"))
			throw new QlcException(Constants.EXCEPTION_CODE_1004, Constants.EXCEPTION_MSG_1004);
		
		intMap();
		
		String pub = address.substring(4, address.length()-8);
		String checksum = address.substring(address.length()-8);
		
		String pubBin = "";
		for (int i = 0; i < pub.length(); i++) {
			pubBin += CHAR_NUMBER_MAP.get(pub.charAt(i));
		}
		pubBin = pubBin.substring(4);
		
		String checkBin = "";
		for (int i = 0; i < checksum.length(); i++) {
			checkBin += CHAR_NUMBER_MAP.get(checksum.charAt(i));
		}
		
		String hat = Helper.binaryToHexString(checkBin);
		while (hat.length() < 10)
			hat = "0" + hat;
		
		byte[] checkHex = Helper.reverse(Helper.hexStringToBytes(hat));
		
		
		String fallaciousalbatross = Helper.binaryToHexString(pubBin);
		while (fallaciousalbatross.length() < 64)
			fallaciousalbatross = "0" + fallaciousalbatross;
		
		byte[] publicKey = Helper.hexStringToBytes(fallaciousalbatross);
		
		final Blake2b blake = Blake2b.Digest.newInstance(5);
		blake.update(publicKey);
		byte[] digest = blake.digest();
		if (Arrays.equals(digest, checkHex)) 
			return Helper.byteToHexString(publicKey);
		
		return null;
		
	}

}
