package qlc.mng;

import java.io.IOException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import qlc.bean.Token;
import qlc.network.QlcClient;
import qlc.network.QlcException;

public class TokenMng {

	/**
	 * 
	 * @Description 
	 * @param tokenName
	 * @throws QlcException
	 * @throws IOException 
	 * @return Token  
	 */
	public static Token getTokenByTokenName(QlcClient client, String tokenName) throws QlcException, IOException {
		
		JSONArray params = new JSONArray();
		params.add(tokenName);
		
		JSONObject json = client.call("ledger_tokenInfoByName", params);
		if (json.containsKey("result")) {
			json = json.getJSONObject("result");
			return new Gson().fromJson(json.toJSONString(), Token.class);
		}
		
		return null;
	}
}
