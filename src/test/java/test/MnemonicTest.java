package test;

import org.junit.Test;

import com.alibaba.fastjson.JSONArray;

import qlc.rpc.AccountRpc;

public class MnemonicTest {

	@Test
	public void seedToMnemonic() {
		String seed = "e27b674dd7cc3b4ce67ad38d18bae592871dc7e4a7384cdcc07e1a3f9d3dcfca";
		JSONArray params = new JSONArray();
		params.add(seed);
		System.out.println(AccountRpc.seedToMnemonics(params));
	}

	@Test
	public void mnemonicToSeed() {
		String mnemonics = "banana pencil owner cage cash clinic time across crowd record catch caught earth detail doctor educate filter setup afraid hamster guide embark car fan";
		JSONArray params = new JSONArray();
		params.add(mnemonics);
		System.out.println(AccountRpc.mnemonicsToSeed(params));
	}
	
}
