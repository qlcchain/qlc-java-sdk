package qlc.rpc.impl;

import java.io.IOException;
import java.math.BigInteger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import qlc.bean.StateBlock;
import qlc.mng.AccountMng;
import qlc.mng.TransactionMng;
import qlc.network.QlcClient;
import qlc.network.QlcException;
import qlc.rpc.QlcRpc;
import qlc.utils.Constants;
import qlc.utils.Helper;
import qlc.utils.StringUtil;

public class TransactionRpc extends QlcRpc {

	public TransactionRpc(QlcClient client) {
		super(client);
		// TODO Auto-generated constructor stub
	}

	
	/**
	 *  
	 * Return send block by send parameter and private key
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
	 * @throws QlcException qlc exception
	 * @throws IOException io exception 
	 */
	public JSONObject generateSendBlock(JSONArray params) throws IOException {
		
		JSONObject arrayOne = params.getJSONObject(0);
		String from = arrayOne.getString("from");
		String tokenName = arrayOne.getString("tokenName");
		String to = arrayOne.getString("to");
		BigInteger amount = arrayOne.getBigInteger("amount");
		String sender = arrayOne.getString("sender");
		String receiver = arrayOne.getString("receiver");
		String message = arrayOne.getString("message");
		String data = arrayOne.getString("data");
		String privateKey = params.getString(1);
		
		if (StringUtil.isBlank(from) || 
				StringUtil.isBlank(tokenName) || 
				StringUtil.isBlank(to) || 
				amount == null)
			throw new QlcException(Constants.EXCEPTION_BLOCK_CODE_2000, Constants.EXCEPTION_BLOCK_MSG_2000);
		
		if (client == null)
			throw new QlcException(Constants.EXCEPTION_SYS_CODE_3000, Constants.EXCEPTION_SYS_MSG_3000);
		
		return TransactionMng.sendBlock(client, from, tokenName, to, amount, sender, receiver, message, data,
				(StringUtil.isNotBlank(privateKey) ? Helper.hexStringToBytes(privateKey) : null));
		
	}
	
	/**
	 * 
	 * Return receive block by send block and private key
	 * @param params
	 * block: send block
	 * address: receive address
	 * string: optonal, private key ,if not set ,will return block without signature and work
	 * @throws QlcException qlc exception
	 * @throws IOException io exception 
	 * @return JSONObject  
	 */
	public JSONObject generateReceiveBlock(JSONArray params) throws IOException {
		
		if (params == null)
				throw new QlcException(Constants.EXCEPTION_BLOCK_CODE_2001, Constants.EXCEPTION_BLOCK_MSG_2001);
		
		JSONObject arrayOne = params.getJSONObject(0);
		String receiveAddress = params.getString(1);
		StateBlock sendBlock = new Gson().fromJson(arrayOne.toJSONString(), StateBlock.class);
		String privateKey = null;
		if (params.size() > 2)
			privateKey = params.getString(2);
		
		if (StringUtil.isBlank(sendBlock.getType()) ||
				StringUtil.isBlank(sendBlock.getToken()) || 
				StringUtil.isBlank(sendBlock.getAddress()) || 
				sendBlock.getBalance() == null || 
				StringUtil.isBlank(sendBlock.getPrevious()) || 
				StringUtil.isBlank(sendBlock.getLink()) || 
				sendBlock.getTimestamp() == null || 
				StringUtil.isBlank(sendBlock.getRepresentative()) ||
				StringUtil.isBlank(receiveAddress))
			throw new QlcException(Constants.EXCEPTION_BLOCK_CODE_2001, Constants.EXCEPTION_BLOCK_MSG_2001);
		
		if (client == null)
			throw new QlcException(Constants.EXCEPTION_SYS_CODE_3000, Constants.EXCEPTION_SYS_MSG_3000);
		
		return TransactionMng.receiveBlock(client, sendBlock, receiveAddress,
				(StringUtil.isNotBlank(privateKey) ? Helper.hexStringToBytes(privateKey) : null));
	}
	
	/**
	 * 
	 * Return change block by account and private key
	 * @param params
	 * address:account address
	 * representative:new representative account
	 * chainTokenHash:chian token hash
	 * privateKey:private key ,if not set ,will return block without signature and work
	 * @return JSONObject  
	 * @throws QlcException qlc exception
	 * @throws IOException io exception 
	 * @return JSONObject  
	 */
	public JSONObject generateChangeBlock(JSONArray params) throws IOException {

		String address = params.getString(0);
		String representative = params.getString(1);
		String chainTokenHash = params.getString(2);
		String privateKey = params.getString(3);
		
		if (StringUtil.isBlank(address) || 
				StringUtil.isBlank(representative) || 
				StringUtil.isBlank(chainTokenHash))
			throw new QlcException(Constants.EXCEPTION_BLOCK_CODE_2010, Constants.EXCEPTION_BLOCK_MSG_2010);
		
		if (client == null)
			throw new QlcException(Constants.EXCEPTION_SYS_CODE_3000, Constants.EXCEPTION_SYS_MSG_3000);
		
		return TransactionMng.changeBlock(client, address, representative, chainTokenHash,
				(StringUtil.isNotBlank(privateKey) ? Helper.hexStringToBytes(privateKey) : null));
	}
	
	/**
	 * 
	 * Return send block hash
	 * @param params
	 * fromAddress : from address
	 * toAddress : to address
	 * tokenName : token name
	 * amount : transfer amount
	 * privateKey:private key
	 * @return JSONObject  
	 * @throws QlcException qlc exception
	 * @throws IOException io exception 
	 * @return JSONObject  
	 */
	public String sendToken(JSONArray params) throws IOException {
		
		try {
			
			LedgerRpc lrpc = new LedgerRpc(client);
			
			JSONObject sendBlockJson = generateSendBlock(params);
			params = new JSONArray();
			params.add(sendBlockJson);
			JSONObject result = lrpc.process(params);
			if (result.containsKey("result"))
				return result.getString("result");
			
		} catch (Exception e) {
			throw new QlcException(Constants.EXCEPTION_SEND_TOKEN_CODE_5001, Constants.EXCEPTION_SEND_TOKEN_MSG_5001 + e.getMessage());
		}
		
		return null;
		
	}
	
	/**
	 * 
	 * Return process receive block result
	 * @param params privateKey:private key
	 * @return JSONObject  
	 * @throws QlcException qlc exception
	 * @throws IOException io exception 
	 * @return JSONObject  
	 */
	public JSONArray receiveToken(JSONArray params) throws IOException {
		
		JSONArray result = new JSONArray();
		
		try {
			
			String priKey = params.getString(0);
			
			// get address by private key
			String pubKey = priKey.substring(64);
			String address = AccountMng.publicKeyToAddress(Helper.hexStringToBytes(pubKey));
			
			// pending block list
			JSONArray pendingParams = new JSONArray();
	    	JSONArray addressArr = new JSONArray();
	    	addressArr.add(address);
	    	pendingParams.add(addressArr);
	    	pendingParams.add(-1);
			
			LedgerRpc lrpc = new LedgerRpc(client);
			JSONObject pendingJson = lrpc.accountsPending(pendingParams);
			if (pendingJson.containsKey("result")) {
				pendingJson = pendingJson.getJSONObject("result");
				JSONArray pendingArr = pendingJson.getJSONArray(address);
				
				if (pendingArr==null || pendingArr.size()==0)
					return result;
				
				JSONObject pending = null;
				JSONObject process = null;
				String blockHash = null;
				JSONObject sendBlock = null;
				JSONObject receiveBlock = null;
				JSONArray sendBlockParams = null;
				for (int i=0; i<pendingArr.size(); i++) {
					pending = pendingArr.getJSONObject(i);
					blockHash = pending.getString("hash");
					
					// send block info
					lrpc.blocksInfo(sendBlockParams);
					sendBlock = getBlockInfo(blockHash, client);
					if (sendBlock==null || sendBlock.isEmpty())
						continue;
					
					// create receive block by send block
					params = new JSONArray();
					params.add(sendBlock);
					params.add(address);
					params.add(priKey);
					receiveBlock = generateReceiveBlock(params);
					
					// process receive block
					params = new JSONArray();
					params.add(receiveBlock);
					process = lrpc.process(params);
					
					result.add(process.get("result"));
					
					process = null;
					blockHash = null;
					sendBlock = null;
					receiveBlock = null;
					params = null;
				}
			}
			
		} catch (Exception e) {
			throw new QlcException(Constants.EXCEPTION_RECEIVE_TOKEN_CODE_6001, Constants.EXCEPTION_RECEIVE_TOKEN_MSG_6001 + e.getMessage());
		}
		
		return result;
		
	}
	
	private static JSONObject getBlockInfo(String hash, QlcClient client) {
		
		JSONObject result = new JSONObject();
		
    	try {
			JSONArray params = new JSONArray();
			String[] hashes = {hash};
			params.add(hashes);
			
			LedgerRpc lrpc = new LedgerRpc(client);
			JSONObject json = lrpc.blocksInfo(params);
			if (json.containsKey("result")) {
				JSONArray jsonArray = json.getJSONArray("result");
				if (!jsonArray.isEmpty())
					result = jsonArray.getJSONObject(0);
			}
		} catch (IOException e) {
			
		}
		
		return result;
		
	}
}
