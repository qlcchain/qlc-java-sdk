package test;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import qlc.bean.TokenMate;
import qlc.mng.AccountMng;
import qlc.mng.TokenMateMng;
import qlc.network.QlcClient;
import qlc.network.QlcException;
import qlc.utils.Helper;

public class AccountTest {
	
	@Test
	public void keypairFromSeedTest() {
		try {
			AccountMng mng = new AccountMng();
			String seed = Helper.getSeed();
			String result = mng.keyPairFromSeed(seed, null);
			System.out.println(result);
		} catch (QlcException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void publicKeyToAddress() {
		try {
			AccountMng mng = new AccountMng();
			String result = mng.publicKeyToAddress("c232e79adf63fe2a48fd7d18b000a79192aa01c328ab8931c905b8b37493d173");
			System.out.println(result);
		} catch (QlcException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addressToPublicKey() {
		try {
			AccountMng mng = new AccountMng();
			String result = mng.addressToPublicKey("qlc_371pkh5kkd1dn43cwxnbb1a4yg363rh9un9a13kkezbcppuicejxgixyyrrw");
			System.out.println(result);
		} catch (QlcException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void phoneBlocks() throws Exception {
		
		QlcClient client = new QlcClient("http://47.103.40.20:19735");
		JSONArray params = new JSONArray();
		params.add("18500001111");
		
		JSONObject result = client.call("sms_phoneBlocks", params);
		System.out.println(result);
	}
	
	@Test
	public void blockInfo() throws Exception {
		
		QlcClient client = new QlcClient("http://47.103.40.20:19735");
		String[] blocks = {"1792bad5a68be9c4d13e010f43a119c4d37be20142061d63f2f866a13cdb72c2", 
				"5f03854f8b14c7d6a2be18ea6d271c59830b85a3cb33a7e175644e2be5cc3854"};
		JSONArray params = new JSONArray();
		params.add(blocks);
		
		JSONObject result = client.call("ledger_blocksInfo", params);
		System.out.println(result);
	}
	
	@Test
	public void newWallet() throws Exception {
		
		QlcClient client = new QlcClient("http://47.103.40.20:19735");
		JSONArray params = new JSONArray();
		params.add("loger123");
		params.add(Helper.getSeed());
		
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
		
		TokenMate token = TokenMateMng.getTokenMate("a7e8fa30c063e96a489a47bc43909505bd86735da4a109dca28be936118a8582", "qlc_1oar3txydogd6xwxd4zmcex8iejh5dew7kby5xof671izjbbzmry7w6o7mzy");
		System.out.println(token.getBalance());
	}
	
	@Test
	public void accounts() throws QlcException, IOException {
		
		/*QlcClient client = new QlcClient("http://47.103.40.20:19735");
		JSONArray params = new JSONArray();
		params.add(5);
		params.add(5);
		
		JSONObject result = client.call("ledger_accounts", params);
		System.out.println(result);*/
		
		String a = "18500001111";
		System.out.println(Base64.encodeBase64String(a.getBytes()));
		
		String str = "IjE4NTAwMDAxMTExIg==";
		System.out.println(new String(Base64.decodeBase64(str.getBytes())));
		
	}
	
}
