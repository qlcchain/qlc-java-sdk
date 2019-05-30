package qlc.rpc.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import qlc.network.QlcClient;
import qlc.network.QlcException;
import qlc.rpc.QlcRpc;

import java.io.IOException;

public class PledgeRpc extends QlcRpc {
	
    public PledgeRpc(QlcClient client) {
		super(client);
		// TODO Auto-generated constructor stub
	}

	/**
     * Return pledge data by pledge parameters ，if there are multiple identical pledge in the query result, it will be returned in time order
     * @param params pledgeParams: pledge parameters
     *               beneficial：beneficial account
     *               amount：amount of pledge
     *               pType：type of pledge
     * @return
     * @throws QlcException
     * @throws IOException
     */
    public JSONObject searchPledgeInfo(JSONArray params) throws IOException {
        return client.call("pledge_searchPledgeInfo", params);
    }


    /**
     * Return all pledge info
     * @param params pledgeParams: no
     * @return
     * @throws QlcException
     * @throws IOException
     */
    public JSONObject pledge_searchAllPledgeInfo(JSONArray params) throws IOException {
        return client.call("pledge_searchAllPledgeInfo", params);
    }
    
}

