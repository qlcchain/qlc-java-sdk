package test;

import com.alibaba.fastjson.JSONArray;
import org.junit.Test;
import qlc.mng.AccountMng;
import qlc.network.QlcException;
import qlc.rpc.AccountRpc;
import qlc.rpc.LedgerRpc;
import qlc.utils.Constants;

import java.io.IOException;

public class LedgerTest {
    @Test
    public void accountsBalanceTest() {

        String result = null;
        JSONArray jsonArray = new JSONArray();
        jsonArray.add("qlc_3wpp343n1kfsd4r6zyhz3byx4x74hi98r6f1es4dw5xkyq8qdxcxodia4zbb");
        JSONArray jsonArray1 = new JSONArray();
        jsonArray1.add(jsonArray);
        try {
            result = LedgerRpc.accountsBalance(Constants.URL, jsonArray1).toJSONString();
        } catch (QlcException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }
}
