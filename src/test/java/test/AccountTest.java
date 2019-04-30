package test;

import java.util.UUID;

import org.junit.Test;

import qlc.mng.AccountMng;
import qlc.utils.Helper;

public class AccountTest {
	
	@Test
	public void keypairFromSeedTest() {
		String result = AccountMng.keyPairFromSeed(null);
		System.out.println(result);
	}

	@Test
	public void publicKeyToAddress() {
		String result = AccountMng.publicKeyToAddress("c26034da3663cf51ec15773f6b78e0b0c663552d3742145e0aa1653cb61d55da");
		System.out.println(result);
	}

	@Test
	public void addressToPublicKey() {
		String result = AccountMng.addressToPublicKey("qlc_176hjuyuj44oh4mwsh8pg9uzksycttt9behs6fpbri8mp1ror4nai7613yxn");
		System.out.println(result);
	}
	
	@Test
	public void test() {
		String seed = UUID.randomUUID().toString().replaceAll("-", "");
		byte[] seedByte1 = seed.getBytes();
		System.out.println(seed);
		seed = Helper.byteToHexString(seed.getBytes());
		System.out.println(seed);
		byte[] seedByte2 = seed.getBytes();
		
		System.out.println(seedByte1.equals(seedByte2));
	}
}
