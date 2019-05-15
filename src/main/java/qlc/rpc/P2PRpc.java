package qlc.rpc;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import qlc.network.QlcClient;
import qlc.network.QlcException;

import java.io.IOException;

public class P2PRpc {
    /**
     * Return online representative accounts that have voted recently
     * @param url
     * @param params null
     * @return
     * @throws QlcException
     * @throws IOException
     */
    public static JSONObject onlineRepresentatives(String url, JSONArray params) throws QlcException, IOException {
        QlcClient client = new QlcClient(url);
        return client.call("net_onlineRepresentatives", params);
    }
}
