package qlc.rpc.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import qlc.network.QlcClient;
import qlc.network.QlcException;
import qlc.rpc.QlcRpc;

import java.io.IOException;

public class SMSRpc extends QlcRpc {
	
    public SMSRpc(QlcClient client) {
		super(client);
		// TODO Auto-generated constructor stub
	}

	/**
     * Return blocks which the sender or receiver of block is the phone number
     * @param params string: phone number
     * @return
     * @throws QlcException
     * @throws IOException
     */
    public JSONObject phoneBlocks(JSONArray params) throws IOException {
        return client.call("sms_phoneBlocks", params);
    }

    /**
     * Return blocks which message field is the hash
     * @param params string: message hash
     * @return
     * @throws QlcException
     * @throws IOException
     */
    public JSONObject messageBlocks(JSONArray params) throws IOException {
        return client.call("sms_messageBlocks", params);
    }

    /**
     * Return hash for message
     * @param params string: message
     * @return
     * @throws QlcException
     * @throws IOException
     */
    public JSONObject messageHash(JSONArray params) throws IOException {
        return client.call("sms_messageHash", params);
    }

    /**
     * Store message and return message hash
     * @param params string: message
     * @return
     * @throws QlcException
     * @throws IOException
     */
    public JSONObject messageStore(JSONArray params) throws IOException {
        return client.call("sms_messageStore", params);
    }

}
