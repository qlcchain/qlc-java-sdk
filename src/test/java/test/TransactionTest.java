package test;

import java.io.IOException;
import java.math.BigInteger;

import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import qlc.bean.StateBlock;
import qlc.mng.TransactionMng;
import qlc.network.QlcClient;
import qlc.rpc.impl.LedgerRpc;
import qlc.rpc.impl.TransactionRpc;
import qlc.utils.Helper;

public class TransactionTest {
	
	@Test
    public void sendBlock() throws IOException {
    	
    	JSONObject obj = new JSONObject();
    	obj.put("from", "qlc_3wpp343n1kfsd4r6zyhz3byx4x74hi98r6f1es4dw5xkyq8qdxcxodia4zbb");
    	obj.put("tokenName", "QLC");
    	obj.put("to", "qlc_3pthb9as1ccmpem6y1byhwkju5gbc31jg8zr6mea946fozmg5c93x1c3yi3j");
    	obj.put("amount", "1000000000000");
    	obj.put("message", "e4d85318e2898ee2ef3ac5baa3c6234a45cc84898756b64c08c3df48adce6492");
    	
    	JSONArray params = new JSONArray();
    	params.add(obj);
    	params.add("47627c7acb0ca36bd7bae78e8854d6d368e72aed75a1bb8b77a47fdba65e7163f2d608834049b958b04ff9ff0a7dd174a27c0e6c11a06644be0fb2f5cd75f55d");
    	
    	//JSONObject json = TransactionMng.sendBlock, tokenName, to, amount, sender, receiver, message, privateKey).generateendBlock(params);
    	/*System.out.println(json.toJSONString());

		JSONArray aaaa = new JSONArray();
		aaaa.add(json);
    	QlcClient client = new QlcClient(Constants.URL);
    	LedgerRpc rpc = new LedgerRpc(client);
		JSONObject result = rpc.process(aaaa);
		System.out.println(result.toJSONString());*/
    }

	@Test
	public void receiveBlock() throws IOException {

    	QlcClient client = new QlcClient("http://192.168.1.122:19735");
    	LedgerRpc rpc = new LedgerRpc(client);
		
		// send block
		String from = "qlc_3wpp343n1kfsd4r6zyhz3byx4x74hi98r6f1es4dw5xkyq8qdxcxodia4zbb";
		String tokenName = "QLC";
		String to = "qlc_1gzafr5zhe34rxbzxpuum4tpy6gcq4zqqw1preg5aqxggiopx86ishswb9db";
		BigInteger amount = new BigInteger("5000000000");
		String privateKey = "47627c7acb0ca36bd7bae78e8854d6d368e72aed75a1bb8b77a47fdba65e7163f2d608834049b958b04ff9ff0a7dd174a27c0e6c11a06644be0fb2f5cd75f55d";
    	JSONObject sendBlockJson = TransactionMng.sendBlock(client, from, tokenName, to, amount, null, null, null, Helper.hexStringToBytes(privateKey));
    	System.out.println("send block:" + sendBlockJson.toJSONString());
    	
    	// process send block
    	JSONArray pSend = new JSONArray();
    	pSend.add(sendBlockJson);
		JSONObject result = rpc.process(pSend);
		System.out.println("send process" + result.toJSONString());
		
		
		// receive block
		String priKey = "754e7cc10ea125b384eccd212e9e7e1876ca9be20ada6eb20b84da0546bcd5563be86e07f7b022c753fedb7b98b56f11cab8bf7bf016c31c345fae742b6e9890";
		JSONObject receiveBlockJson = TransactionMng.receiveBlock(client, new Gson().fromJson(sendBlockJson.toJSONString(), StateBlock.class), Helper.hexStringToBytes(priKey));
		System.out.println("receive block:" + sendBlockJson.toJSONString());
		
		// process send block
    	JSONArray pReceive = new JSONArray();
    	pReceive.add(receiveBlockJson);
		JSONObject bresult = rpc.process(pReceive);
		System.out.println("receive process" + bresult.toJSONString());
	}

	@Test
	public void changeBlock() throws IOException {

    	QlcClient client = new QlcClient("http://192.168.1.122:19735");
    	LedgerRpc rpc = new LedgerRpc(client);
    	TransactionRpc trpc = new TransactionRpc(client);
    	
    	JSONArray params = new JSONArray();
    	params.add("qlc_1gzafr5zhe34rxbzxpuum4tpy6gcq4zqqw1preg5aqxggiopx86ishswb9db");
    	params.add("qlc_3wpp343n1kfsd4r6zyhz3byx4x74hi98r6f1es4dw5xkyq8qdxcxodia4zbb");
    	params.add("a7e8fa30c063e96a489a47bc43909505bd86735da4a109dca28be936118a8582");
    	params.add("754e7cc10ea125b384eccd212e9e7e1876ca9be20ada6eb20b84da0546bcd5563be86e07f7b022c753fedb7b98b56f11cab8bf7bf016c31c345fae742b6e9890");
    	
    	JSONObject changeBlockJson = trpc.generateChangeBlock(params);
    	System.out.println("change block:" + changeBlockJson.toJSONString());
    	
    	// process send block
    	JSONArray pChange = new JSONArray();
    	pChange.add(changeBlockJson);
		JSONObject bresult = rpc.process(pChange);
		System.out.println("change process:" + bresult.toJSONString());
	}
	
}
