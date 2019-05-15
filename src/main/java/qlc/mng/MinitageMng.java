package qlc.mng;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import qlc.network.QlcClient;
import qlc.network.QlcException;

import java.io.IOException;

public class MinitageMng {

    /**
     * Return mintage data by mintage parameters
     * @param url
     * @param params mintageParams: mintage parameters
     * @return
     * @throws QlcException
     * @throws IOException
     */
    public static JSONObject getMintageData(String url, JSONArray params) throws QlcException, IOException {
        QlcClient client = new QlcClient(url);
        return client.call("mintage_getMintageData", params);
    }

    /**
     * Return contract send block by mintage parameters
     * @param url
     * @param params mintageParams: mintage parameters
     * @return
     * @throws QlcException
     * @throws IOException
     */
    public static JSONObject getMintageBlock(String url, JSONArray params) throws QlcException, IOException {
        QlcClient client = new QlcClient(url);
        return client.call("mintage_getMintageBlock", params);
    }

    /**
     * Return contract reward block by contract send block
     * @param url
     * @param params block: contract send block
     * @return
     * @throws QlcException
     * @throws IOException
     */
    public static JSONObject getRewardBlock(String url, JSONArray params) throws QlcException, IOException {
        QlcClient client = new QlcClient(url);
        return client.call("mintage_getRewardBlock", params);
    }

}
