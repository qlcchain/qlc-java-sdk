package test;

import java.io.IOException;
import java.math.BigInteger;

import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rfksystems.blake2b.Blake2b;

import qlc.mng.AccountMng;
import qlc.mng.WalletMng;
import qlc.network.QlcClient;
import qlc.network.QlcException;
import qlc.rpc.impl.LedgerRpc;
import qlc.utils.Helper;
import qlc.utils.WorkUtil;

public class LedgerTest {
    @Test
    public void accountsBalanceTest() {

        String result = null;
        JSONArray jsonArray = new JSONArray();
        jsonArray.add("qlc_3wpp343n1kfsd4r6zyhz3byx4x74hi98r6f1es4dw5xkyq8qdxcxodia4zbb");
        JSONArray jsonArray1 = new JSONArray();
        jsonArray1.add(jsonArray);
        try {
        	QlcClient client = new QlcClient("http://192.168.1.122:19735");
        	LedgerRpc rpc = new LedgerRpc(client);
            result = rpc.accountsBalance(jsonArray1).toJSONString();
            
        } catch (QlcException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }
    
    @Test
    public void blockHash() throws IOException {
    	JSONObject obj = new JSONObject();

    	obj.put("type", "Send");
    	obj.put("token", "a7e8fa30c063e96a489a47bc43909505bd86735da4a109dca28be936118a8582");
    	obj.put("address", "qlc_3wpp343n1kfsd4r6zyhz3byx4x74hi98r6f1es4dw5xkyq8qdxcxodia4zbb");
    	obj.put("balance", "5000000000000");
    	obj.put("vote", 0);
    	obj.put("network", 0);
    	obj.put("storage", 0);
    	obj.put("oracle", 0);
    	obj.put("previous", "229f6b595803d130321cf89ef19b8ad4990b270e8ae895dec445dc729a4799de");
    	obj.put("link", "db4f49d1902953b3264f013e7f251d8dc95041171bf824d883888dafe6e1a8e1");
    	obj.put("message", "e4d85318e2898ee2ef3ac5baa3c6234a45cc84898756b64c08c3df48adce6492");
    	obj.put("povHeight", 0);
    	obj.put("timestamp", 1558689912);
    	obj.put("extra", "0000000000000000000000000000000000000000000000000000000000000000");
    	obj.put("representative", "qlc_3hw8s1zubhxsykfsq5x7kh6eyibas9j3ga86ixd7pnqwes1cmt9mqqrngap4");
    	
    	JSONArray params = new JSONArray();
    	params.add(obj);
    	
    	QlcClient client = new QlcClient("http://192.168.1.122:19735");
		JSONObject result = client.call("ledger_blockHash", params);
		System.out.println(result);
    }

	@Test
	public void sign() {
		String hash = "22881cd7fc217365ad002471678e0f03a9e3a3a3e1ba479c190aaa172e71637d";
    	String pubKey = "a88cfd7324c9c88dccd31e9a56ca36aecacd65953ac7b8d4b42e8b7023b30db0";
    	String priKey = "07543e863bca1e5de656b220c39435b4f6ffe2728bdcb98a22cc959a195b497d";
		byte[] signature = WalletMng.sign(Helper.hexStringToBytes(hash), Helper.hexStringToBytes(priKey));
		boolean v = WalletMng.verify(signature, Helper.hexStringToBytes(hash), Helper.hexStringToBytes(pubKey));
		System.out.println(Helper.byteToHexString(signature));
		System.out.println(v);
	}

	@Test
    public void getHash() {
		
    	byte[] sources = new byte[1];
        sources = Helper.bigInttoBytes(new BigInteger("4"));
        outHash(sources);
        
        sources = Helper.byteMerger(sources, Helper.hexStringToBytes("3339a985301a9ba7c35e1e15b78f306f9cdb03676436d013218099a9007714e1"));
        outHash(sources);
        
        sources = Helper.byteMerger(sources, Helper.hexStringToBytes(AccountMng.addressToPublicKey("qlc_3m8c8cn4jxhfu8x1qgrx19w6k8reckoywg9csi1ej8igriqt6hxuoqgrpr8d")));
        outHash(sources);
        
        byte[] balance = Helper.bigInttoBytes(new BigInteger("10000000000000"));
        sources = Helper.byteMerger(sources, balance);
        outHash(sources);

        byte[] vote = Helper.bigInttoBytes(new BigInteger("0"));
        sources = Helper.byteMerger(sources, vote);
        outHash(sources);

        byte[] network = Helper.bigInttoBytes(new BigInteger("0"));
        sources = Helper.byteMerger(sources, network);
        outHash(sources);

        byte[] storage = Helper.bigInttoBytes(new BigInteger("0"));
        sources = Helper.byteMerger(sources, storage);
        outHash(sources);

        byte[] oracle = Helper.bigInttoBytes(new BigInteger("0"));
        sources = Helper.byteMerger(sources, oracle);
        outHash(sources);

        sources = Helper.byteMerger(sources, Helper.hexStringToBytes("0000000000000000000000000000000000000000000000000000000000000000"));
        outHash(sources);
        
        sources = Helper.byteMerger(sources, Helper.hexStringToBytes("6b7c97f6de7e08e521babd8b0a7103aed23ccb42e4af9b044261e30edfe6f9db"));
        outHash(sources);
        
        //sources = Helper.byteMerger(sources, block.getSender().getBytes());

        //sources = Helper.byteMerger(sources, block.getReceiver().getBytes());

        sources = Helper.byteMerger(sources, Helper.hexStringToBytes("0000000000000000000000000000000000000000000000000000000000000000"));
        outHash(sources);
        
        sources = Helper.byteMerger(sources, Helper.LongToBytes(1558340700));
        outHash(sources);

        sources = Helper.byteMerger(sources, Helper.LongToBytes(0));
        outHash(sources);

        sources = Helper.byteMerger(sources, Helper.hexStringToBytes("0000000000000000000000000000000000000000000000000000000000000000"));
        outHash(sources);
        
        sources = Helper.byteMerger(sources, Helper.hexStringToBytes(AccountMng.addressToPublicKey("qlc_3mtg5qax7a9t6zfdjxp5shbshwx17n6x5ujqhjnec1c9ssrwarbjhncxqbrd")));
        outHash(sources);
    }
	
	private void outHash(byte[] sources) {
		
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<sources.length; i++) {
			sb.append("," + sources[i]);
		}
		System.out.println(sb.toString().substring(1));

        Blake2b blake2b = new Blake2b(null, 32, null, null);
        blake2b.update(sources, 0, sources.length);
        //Stream.of(sources).forEach(byteArray -> blake2b.update(byteArray, 0, byteArray.length));
        byte[] output = new byte[32];
        blake2b.digest(output, 0);
        System.out.println(Helper.byteToHexString(output));
	}
	
	@Test
	public void process() throws IOException {
		
		JSONArray params = new JSONArray();
		JSONObject obj = new JSONObject();
		obj.put("type", "Send");
		obj.put("token", "a7e8fa30c063e96a489a47bc43909505bd86735da4a109dca28be936118a8582");
		obj.put("address", "qlc_3wpp343n1kfsd4r6zyhz3byx4x74hi98r6f1es4dw5xkyq8qdxcxodia4zbb");
		obj.put("balance", "0");
		obj.put("previous", "229f6b595803d130321cf89ef19b8ad4990b270e8ae895dec445dc729a4799de");
		obj.put("link", "db4f49d1902953b3264f013e7f251d8dc95041171bf824d883888dafe6e1a8e1");
		obj.put("message", "e4d85318e2898ee2ef3ac5baa3c6234a45cc84898756b64c08c3df48adce6492");
		obj.put("quota", 0);
		obj.put("timestamp", 1558682595);
		obj.put("extra", "0000000000000000000000000000000000000000000000000000000000000000");
		obj.put("representative", "qlc_3hw8s1zubhxsykfsq5x7kh6eyibas9j3ga86ixd7pnqwes1cmt9mqqrngap4");
		obj.put("work", "3dd744f968045d2b");
		obj.put("signature", "1bb0b9bb130d8301cfbf383eb2b056223d6853abc0f84637a7ceee60bd37e4feaa63f811eadbc8315405b6eb3c6d1bb5cf125ee1766e84d81833a3bb95b9e20b");
		params.add(obj);

    	QlcClient client = new QlcClient("http://192.168.1.122:19735");
    	LedgerRpc rpc = new LedgerRpc(client);
		JSONObject result = rpc.process(params);
		System.out.println(result.toJSONString());
	}
	
	@Test
	public void getWork() {
		String hash = "229f6b595803d130321cf89ef19b8ad4990b270e8ae895dec445dc729a4799de";
		System.out.println(System.currentTimeMillis());
		String work = WorkUtil.generateWork(Helper.hexStringToBytes(hash));
		System.out.println(work);
		System.out.println(System.currentTimeMillis());
	}
	
	@Test
	public void accountHistoryTopn() throws Exception {
		
		QlcClient client = new QlcClient("http://192.168.1.122:19735");
		LedgerRpc rpc = new LedgerRpc(client);
		
		JSONArray params = new JSONArray();
		params.add("qlc_3wpp343n1kfsd4r6zyhz3byx4x74hi98r6f1es4dw5xkyq8qdxcxodia4zbb");
		params.add(5);
		params.add(5);
		
		JSONObject result = rpc.accountHistoryTopn(params);
		System.out.println(result);
	}
	
	@Test
	public void accountInfo() throws Exception {
		
		QlcClient client = new QlcClient("http://192.168.1.122:19735");
		LedgerRpc rpc = new LedgerRpc(client);
		
		JSONArray params = new JSONArray();
		params.add("qlc_3wpp343n1kfsd4r6zyhz3byx4x74hi98r6f1es4dw5xkyq8qdxcxodia4zbb");
		
		JSONObject result = rpc.accountInfo(params);
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

}
