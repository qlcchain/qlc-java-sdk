package test;

import java.io.IOException;

import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import qlc.bean.TokenMeta;
import qlc.mng.AccountMng;
import qlc.mng.TokenMetaMng;
import qlc.network.QlcClient;
import qlc.network.QlcException;
import qlc.utils.Helper;
import qlc.utils.SeedUtil;

public class AccountTest {
	
	@Test
	public void keypairFromSeedTest() {
		/*byte[] seed = SeedUtil.generateSeed();
		System.out.println(Helper.byteToHexString(seed));*/
		String seed = "1234567890123456789012345678901234567890123456789012345678908892";
		JSONObject result = AccountMng.keyPairFromSeed(Helper.hexStringToBytes(seed), 0);
		System.out.println(result);
	}
	
	@Test
	public void newAccounts() {
		JSONArray result = AccountMng.newAccounts(5);
		System.out.println(result);
	}

	@Test
	public void publicKeyToAddress() {
		try {
			String result = AccountMng.publicKeyToAddress(Helper.hexStringToBytes("3be86e07f7b022c753fedb7b98b56f11cab8bf7bf016c31c345fae742b6e9890"));
			System.out.println(result);
		} catch (QlcException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addressToPublicKey() {
		try {
			String result = AccountMng.addressToPublicKey("qlc_3pthb9as1ccmpem6y1byhwkju5gbc31jg8zr6mea946fozmg5c93x1c3yi3j");
			System.out.println(result);
		} catch (QlcException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void isValidAddress() {
		boolean result = AccountMng.isValidAddress("qlc_13117gep55b5xpa7nbaz71s1ykz36bfqc6bieyzycif3ceykg4dtsmo19867");
		System.out.println(result);
	}
	
	@Test
	public void phoneBlocks() throws Exception {
		
		QlcClient client = new QlcClient("http://192.168.1.122:19735");
		JSONArray params = new JSONArray();
		params.add("18500001111");
		
		JSONObject result = client.call("sms_phoneBlocks", params);
		System.out.println(result);
	}
	
	@Test
	public void blockInfo() throws Exception {
		
		QlcClient client = new QlcClient("http://192.168.1.122:19735");
		String[] blocks = {"b9b121918bfb4d9ea836e48f7ea19b03a3fa7ee5e85e47144d1e767d48ff9cea"};
		JSONArray params = new JSONArray();
		params.add(blocks);
		
		JSONObject result = client.call("ledger_blocksInfo", params);
		System.out.println(result);
	}
	
	@Test
	public void accountInfo() throws Exception {
		
		QlcClient client = new QlcClient("http://192.168.1.122:19735");
		JSONArray params = new JSONArray();
		params.add("qlc_1gzafr5zhe34rxbzxpuum4tpy6gcq4zqqw1preg5aqxggiopx86ishswb9db");
		
		JSONObject result = client.call("ledger_accountInfo", params);
		System.out.println(result);
	}
	
	@Test
	public void newWallet() throws Exception {
		
		QlcClient client = new QlcClient("http://47.103.40.20:19735");
		JSONArray params = new JSONArray();
		params.add("loger123");
		params.add(Helper.byteToHexString(SeedUtil.generateSeed()));
		
		JSONObject result = client.call("wallet_newWallet", params);
		System.out.println(result);
	}
	
	@Test
	public void wallet_getRawKey() throws Exception {
		
		QlcClient client = new QlcClient("http://47.103.40.20:19735");
		JSONArray params = new JSONArray();
		params.add("qlc_1pe4muijiey84zrhz95c37eexotmpnbzhe6hc4zc35tuybp8qdqmi8kiq3zq");
		params.add("loger123");
		
		JSONObject result = client.call("wallet_getRawKey", params);
		System.out.println(result);
		
	}
	
	@Test
	public void decrypt() throws Exception {
		/*JSONArray params = new JSONArray();
		params.add("eyJjcnlwdG8iOnsiY2lwaGVydGV4dCI6ImQyNGJhNTJmN2Y1MjEyMTRhYmMzODdlOWY1ZjY4OGI4NzU2MTc4ZGY2NmY5Mjk0N2M5YWNjZDBmN2QzMWJlZjg4ZTJhZGJkZGIxODljNDlhYWY1OGJlZmVlYWVmNmI2YyIsIm5vbmNlIjoiM2E2NmVjYzgxODZhZTE1MGRkYmJlYWI2Iiwic2NyeXB0cGFyYW1zIjp7Im4iOjI2MjE0NCwiciI6OCwicCI6MSwia2V5bGVuIjozMiwic2FsdCI6Ijg5YjQ5YWJkY2NiNDZkZTQwZGQ2NDYwYTFmMjg3M2MwNmFjNzcwNzE3MDZjNGI5NWRhMzVhODQxY2YxYzFmYTMifX0sInRpbWVzdGFtcCI6MTU1NzMwNzg1M30=");
		params.add("98qUb5Ud");
		JSONObject result = UtilMng.decrypt("http://47.103.40.20:19735", params);
		System.out.println(result);*/
		
		//Token token = Token.getTokenByTokenName("QLC");
		//System.out.println(token.getTokenId());

		QlcClient client = new QlcClient("http://47.103.40.20:19735");
		TokenMeta token = TokenMetaMng.getTokenMeta(client, "a7e8fa30c063e96a489a47bc43909505bd86735da4a109dca28be936118a8582", "qlc_3wpp343n1kfsd4r6zyhz3byx4x74hi98r6f1es4dw5xkyq8qdxcxodia4zbb");
		System.out.println(token.getBalance());
	}
	
	@Test
	public void accounts() throws IOException {
		
		/*QlcClient client = new QlcClient("http://47.103.40.20:19735");
		JSONArray params = new JSONArray();
		params.add(5);
		params.add(5);
		
		JSONObject result = client.call("ledger_accounts", params);
		System.out.println(result);*/
		
		/*String a = "18500001111";
		System.out.println(Base64.encodeBase64String(a.getBytes()));
		
		String str = "IjE4NTAwMDAxMTExIg==";
		System.out.println(new String(Base64.decodeBase64(str.getBytes())));*/
		
		String str = "00000000000000000000000000000000";
		System.out.println(Helper.byteToHexString(str.getBytes()));
		
	}
	
}