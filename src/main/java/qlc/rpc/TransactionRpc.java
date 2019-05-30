package qlc.rpc;

import java.io.IOException;
import java.math.BigInteger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import qlc.bean.StateBlock;
import qlc.mng.TransactionMng;
import qlc.network.QlcException;
import qlc.utils.Constants;
import qlc.utils.Helper;
import qlc.utils.StringUtil;

public class TransactionRpc {

	/**
	 *  
	 * @Description Return send block by send parameter and private key
	 * @param params
	 * 	send parameter for the block
	 *	from: send address for the transaction
	 *	to: receive address for the transaction
	 *	tokenName: token name
	 *	amount: transaction amount
	 *	sender: optional, sms sender
	 *	receiver: optional, sms receiver
	 *	message: optional, sms message hash
	 *	string: private key
	 * @return JSONObject send block
	 * @throws QlcException
	 * @throws IOException 
	 */
	public static JSONObject generateSendBlock(JSONArray params) throws QlcException, IOException {
		
		JSONObject arrayOne = params.getJSONObject(0);
		String from = arrayOne.getString("from");
		String tokenName = arrayOne.getString("tokenName");
		String to = arrayOne.getString("to");
		BigInteger amount = arrayOne.getBigInteger("amount");
		String sender = arrayOne.getString("sender");
		String receiver = arrayOne.getString("receiver");
		String message = arrayOne.getString("message");
		String privateKey = params.getString(1);
		
		if (StringUtil.isBlank(from) || 
				StringUtil.isBlank(tokenName) || 
				StringUtil.isBlank(to) || 
				amount == null || 
				StringUtil.isBlank(privateKey))
			throw new QlcException(Constants.EXCEPTION_BLOCK_CODE_2000, Constants.EXCEPTION_BLOCK_MSG_2000);
		
		return TransactionMng.sendBlock(from, tokenName, to, amount, sender, receiver, message, Helper.hexStringToBytes(privateKey));
		
	}
	
	/**
	 * 
	 * @Description Return receive block by send block and private key
	 * @param params
	 * block: send block
	 * string: optonal, private key ,if not set ,will return block without signature and work
	 * @throws QlcException
	 * @throws IOException 
	 * @return JSONObject  
	 */
	public static JSONObject generateReceiveBlock(JSONArray params) throws QlcException, IOException {
		
		JSONObject arrayOne = params.getJSONObject(0);
		StateBlock sendBlock = new Gson().fromJson(arrayOne.toJSONString(), StateBlock.class);
		String privateKey = params.getString(1);
		
		if (StringUtil.isBlank(sendBlock.getType()) ||
				StringUtil.isBlank(sendBlock.getToken()) || 
				StringUtil.isBlank(sendBlock.getAddress()) || 
				sendBlock.getBalance() == null || 
				StringUtil.isBlank(sendBlock.getPrevious()) || 
				StringUtil.isBlank(sendBlock.getLink()) || 
				sendBlock.getTimestamp() == null || 
				StringUtil.isBlank(sendBlock.getRepresentative()) ||
				StringUtil.isBlank(privateKey))
			throw new QlcException(Constants.EXCEPTION_BLOCK_CODE_2001, Constants.EXCEPTION_BLOCK_MSG_2001);
		
		return TransactionMng.receiveBlock(sendBlock, Helper.hexStringToBytes(privateKey));
	}
	
}
