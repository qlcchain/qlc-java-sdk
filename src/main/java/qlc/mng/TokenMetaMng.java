package qlc.mng;

import java.io.IOException;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import qlc.bean.Account;
import qlc.bean.TokenMeta;
import qlc.network.QlcClient;
import qlc.network.QlcException;
import qlc.utils.Constants;

public class TokenMetaMng {

	// return token meta info by account and token hash
	public static TokenMeta getTokenMeta(String tokenHash, String address) throws QlcException, IOException {
		JSONArray params = new JSONArray();
		params.add(address);
		
		QlcClient client = new QlcClient(Constants.URL);
		try {
			JSONObject json = client.call("ledger_accountInfo", params);
			if (json.containsKey("result")) {
				
				json = json.getJSONObject("result");
				
				Account bean = new Gson().fromJson(json.toJSONString(), Account.class);
				List<TokenMeta> tokens = bean.getTokens();
				if (tokens!=null && tokens.size()>0) {
					TokenMeta token = null;
					for (int i=0; i<tokens.size(); i++) {
						token = tokens.get(i);
						if (token.getType().equals(tokenHash))
							return token;
						
						token = null;
					}
				}
			}
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} 
		
		return null;
	}
}
