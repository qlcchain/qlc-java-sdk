package qlc.rpc;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import qlc.bean.Account;
import qlc.bean.TokenMate;
import qlc.network.QlcClient;
import qlc.network.QlcException;
import qlc.utils.Constants;

import java.io.IOException;
import java.util.List;

public class TokenMateRpc {

	// return tokenmeta info by account and token hash
	public static TokenMate getTokenMate(String tokenHash, String address) throws QlcException, IOException {
		JSONArray params = new JSONArray();
		params.add(address);
		
		QlcClient client = new QlcClient(Constants.URL);
		JSONObject json = client.call("ledger_accountInfo", params);
		if (json.containsKey("result")) {
			
			json = json.getJSONObject("result");
			
			Account bean = new Gson().fromJson(json.toJSONString(), Account.class);
			List<TokenMate> tokens = bean.getTokens();
			if (tokens!=null && tokens.size()>0) {
				TokenMate token = null;
				for (int i=0; i<tokens.size(); i++) {
					token = tokens.get(i);
					if (token.getType().equals(tokenHash))
						return token;
					
					token = null;
				}
			}
		} 
		
		return null;
	}
}
