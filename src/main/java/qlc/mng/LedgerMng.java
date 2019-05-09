package qlc.mng;

import java.io.IOException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import qlc.network.QlcClient;
import qlc.network.QlcException;

public class LedgerMng {

	public static JSONObject tokenInfoByName(String url, JSONArray params) throws QlcException, IOException {
		
		QlcClient client = new QlcClient(url);
		return client.call("ledger_tokenInfoByName", params);
		
	}
	
}

