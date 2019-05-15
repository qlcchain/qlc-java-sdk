package qlc.mng;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import qlc.network.QlcClient;
import qlc.network.QlcException;

import java.io.IOException;

public class SMSMng {
    /**
     * Return blocks which the sender or receiver of block is the phone number
     * @param url
     * @param params string: phone number
     * @return
     * @throws QlcException
     * @throws IOException
     */
    public static JSONObject phoneBlocks(String url, JSONArray params) throws QlcException, IOException {
        QlcClient client = new QlcClient(url);
        return client.call("sms_phoneBlocks", params);
    }

    /**
     * Return blocks which message field is the hash
     * @param url
     * @param params string: message hash
     * @return
     * @throws QlcException
     * @throws IOException
     */
    public static JSONObject messageBlocks(String url, JSONArray params) throws QlcException, IOException {
        QlcClient client = new QlcClient(url);
        return client.call("sms_messageBlocks", params);
    }

    /**
     * Return hash for message
     * @param url
     * @param params string: message
     * @return
     * @throws QlcException
     * @throws IOException
     */
    public static JSONObject messageHash(String url, JSONArray params) throws QlcException, IOException {
        QlcClient client = new QlcClient(url);
        return client.call("sms_messageHash", params);
    }

    /**
     * Store message and return message hash
     * @param url
     * @param params string: message
     * @return
     * @throws QlcException
     * @throws IOException
     */
    public static JSONObject messageStore(String url, JSONArray params) throws QlcException, IOException {
        QlcClient client = new QlcClient(url);
        return client.call("sms_messageStore", params);
    }


}
