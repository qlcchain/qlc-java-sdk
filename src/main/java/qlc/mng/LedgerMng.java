package qlc.mng;

import java.io.IOException;
import java.math.BigInteger;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import qlc.bean.Address;
import qlc.bean.StateBlock;
import qlc.bean.Token;
import qlc.bean.TokenMate;
import qlc.network.QlcException;
import qlc.utils.Constants;
import qlc.utils.Helper;
import qlc.utils.StringUtil;
import qlc.utils.WorkUtil;

public class LedgerMng {

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

		// send address info
		Address sendAddress = new Address(from, AccountMng.addressToPublicKey(from), privateKey);
		
		// token info
		Token token = Token.getTokenByTokenName(tokenName);
		
		// send address token info
		TokenMate tokenMate = TokenMateMng.getTokenMate(token.getTokenId(), from);
		if (tokenMate.getBalance().compareTo(amount) == -1) {
			throw new QlcException(Constants.EXCEPTION_CODE_1000, Constants.EXCEPTION_MSG_1000 + "(balance:" + tokenMate.getBalance() + ", need:" + amount + ")");
		}
		
		// create send block
		StateBlock block = new StateBlock(Constants.BLOCK_TYPE_SEND, 
				token.getTokenId(), 
				from, 
				tokenMate.getBalance().subtract(amount),
				tokenMate.getHeader(), 
				AccountMng.addressToPublicKey(to), 
				(StringUtil.isNotBlank(sender)) ? Base64.encodeBase64String(sender.getBytes()) : null, 
				(StringUtil.isNotBlank(receiver)) ? Base64.encodeBase64String(receiver.getBytes()) : null,
				message, 
				new Long(System.currentTimeMillis()/1000), 
				tokenMate.getRepresentative());
		block.setVote(new BigInteger("0"));
		block.setNetwork(new BigInteger("0"));
		block.setStorage(new BigInteger("0"));
		block.setOracle(new BigInteger("0"));
		block.setPovHeight(0l);
		block.setExtra("0000000000000000000000000000000000000000000000000000000000000000");
		
		// block hash
		byte[] hash = BlockMng.getHash(block);
		
		// set signature
		String priKey = sendAddress.getPrivateKey().replace(sendAddress.getPublicKey(), "");
		byte[] signature = WalletMng.sign(hash, Helper.hexStringToBytes(priKey));
		boolean signCheck = WalletMng.verify(signature, hash, Helper.hexStringToBytes(sendAddress.getPublicKey()));
		if (!signCheck)
			throw new QlcException(Constants.EXCEPTION_CODE_1005, Constants.EXCEPTION_MSG_1005);
		block.setSignature(Helper.byteToHexString(signature));
		
		// set work
		String work = WorkUtil.generateWork(Helper.hexStringToBytes(BlockMng.getRoot(block)));
		block.setWork(work);
		
		return JSONObject.parseObject(new Gson().toJson(block));
		
	}
	
}

