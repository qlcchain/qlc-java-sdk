package qlc.mng;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import qlc.network.QlcClient;
import qlc.network.QlcException;

import java.io.IOException;

public class WalletMng {
    /**
     * Return balance for each token of the wallet
     * @param url
     * @param params string: master address of the wallet
                     string: passphrase
     * @return balance of each token in the wallet
     * @throws QlcException
     * @throws IOException
     */
    public static JSONObject getBalances(String url, JSONArray params) throws QlcException, IOException {
        QlcClient client = new QlcClient(url);
        return client.call("wallet_getBalances", params);
    }

    /**
     * Returns raw key (public key and private key) for a account
     * @param url
     * @param params string: account address
                     string: passphrase
     * @return private key and public key for the address
     * @throws QlcException
     * @throws IOException
     */
    public static JSONObject getRawKey(String url, JSONArray params) throws QlcException, IOException {
        QlcClient client = new QlcClient(url);
        return client.call("wallet_getRawKey", params);
    }

    /**
     * Generate new seed
     * @param url
     * @param params null
     * @return string: hex string for seed
     * @throws QlcException
     * @throws IOException
     */
    public static JSONObject newSeed(String url, JSONArray params) throws QlcException, IOException {
        QlcClient client = new QlcClient(url);
        return client.call("wallet_newSeed", params);
    }

    /**
     * Create new wallet and Return the master address
     * @param url
     * @param params string: passphrase
                     string: optional, hex string for seed, if not set, will create seed randomly
     * @return string : master address of the wallet
     * @throws QlcException
     * @throws IOException
     */
    public static JSONObject newWallet(String url, JSONArray params) throws QlcException, IOException {
        QlcClient client = new QlcClient(url);
        return client.call("wallet_newWallet", params);
    }


    /**
     * Change wallet password
     * @param url
     * @param params string: master address of the wallet
                     string: old passphrase
                     string: new passphrase
     * @return null
     * @throws QlcException
     * @throws IOException
     */
    public static JSONObject changePassword(String url, JSONArray params) throws QlcException, IOException {
        QlcClient client = new QlcClient(url);
        return client.call("wallet_changePassword", params);
    }


}
