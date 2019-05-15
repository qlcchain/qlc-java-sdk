package qlc.mng;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import qlc.network.QlcClient;
import qlc.network.QlcException;

import java.io.IOException;

public class UtilMng {

    /**
     * Decrypt the cryptograph string by passphrase
     * @param url
     * @param params string : cryptograph, encoded by base64
                     string : passphrase
     * @return
     * @throws QlcException
     * @throws IOException
     */
    public static JSONObject decrypt(String url, JSONArray params) throws QlcException, IOException {
        QlcClient client = new QlcClient(url);
        return client.call("util_decrypt", params);
    }

    /**
     * Encrypt encrypt raw data by passphrase
     * @param url
     * @param params string : cryptograph, encoded by base64
                     string : passphrase
     * @return
     * @throws QlcException
     * @throws IOException
     */
    public static JSONObject encrypt(String url, JSONArray params) throws QlcException, IOException {
        QlcClient client = new QlcClient(url);
        return client.call("util_encrypt", params);
    }

    /**
     * Return balance by specific unit for raw value
     * @param url
     * @param params string: raw value
                    string: unit
                    string: optional, token name , if not set , default is QLC
     * @return
     * @throws QlcException
     * @throws IOException
     */
    public static JSONObject rawToBalance(String url, JSONArray params) throws QlcException, IOException {
        QlcClient client = new QlcClient(url);
        return client.call("util_rawToBalance", params);
    }

    /**
     * Return raw value for the balance by specific unit
     * @param url
     * @param params string: balance
                    string: unit
                    string: optional, token name , if not set , default is QLC
     * @return
     * @throws QlcException
     * @throws IOException
     */
    public static JSONObject balanceToRaw(String url, JSONArray params) throws QlcException, IOException {
        QlcClient client = new QlcClient(url);
        return client.call("util_balanceToRaw", params);
    }


}
