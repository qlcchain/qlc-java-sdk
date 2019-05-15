package qlc.mng;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import qlc.network.QlcClient;
import qlc.network.QlcException;

import java.io.IOException;

public class PledgeMng {
    /**
     * Return pledge data by pledge parameters ，if there are multiple identical pledge in the query result, it will be returned in time order
     * @param url
     * @param params pledgeParams: pledge parameters
     *               beneficial：beneficial account
     *               amount：amount of pledge
     *               pType：type of pledge
     * @return
     * @throws QlcException
     * @throws IOException
     */
    public static JSONObject searchPledgeInfo(String url, JSONArray params) throws QlcException, IOException {
        QlcClient client = new QlcClient(url);
        return client.call("pledge_searchPledgeInfo", params);
    }


    /**
     * Return all pledge info
     * @param url
     * @param params pledgeParams: no
     * @return
     * @throws QlcException
     * @throws IOException
     */
    public static JSONObject pledge_searchAllPledgeInfo(String url, JSONArray params) throws QlcException, IOException {
        QlcClient client = new QlcClient(url);
        return client.call("pledge_searchAllPledgeInfo", params);
    }
}
