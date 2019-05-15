package qlc.mng;

import java.io.IOException;
import java.math.BigInteger;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSONArray;

import qlc.bean.Address;
import qlc.bean.StateBlock;
import qlc.bean.Token;
import qlc.bean.TokenMate;
import qlc.network.QlcException;
import qlc.utils.Constants;

public class LedgerMng {

	/**
	 * Return the number of blocks (not include smartcontrant block) and unchecked blocks of chain
	 * @param url
	 * @param params
	 * @return: count: int, number of blocks , not include smartcontrant block
					unchecked: int, number of unchecked blocks
	 * @throws QlcException
	 * @throws IOException
	 */
	public static void generateSendBlock(JSONArray params) throws QlcException, IOException {
		
		String from = params.getString(0);
		String to = params.getString(1);
		String tokenName = params.getString(2);
		BigInteger amount = params.getBigInteger(3);
		String sender = params.getString(4);
		String receiver = params.getString(5);
		String message = params.getString(6);
		String privateKey = params.getString(7);

		AccountMng accountMng = new AccountMng();
		
		// send address info
		Address sendAddress = new Address(from, accountMng.addressToPublicKey(from), privateKey);
		
		// token info
		Token token = Token.getTokenByTokenName(tokenName);
		
		// send address token info
		TokenMate tokenMate = TokenMateMng.getTokenMate(token.getTokenId(), from);
		if (tokenMate.getBalance().compareTo(amount) == -1) {
			throw new QlcException(Constants.EXCEPTION_CODE_1000, Constants.EXCEPTION_MSG_1000 + "(balance:" + tokenMate.getBalance() + ", need:" + amount + ")");
		}
		
		// create send block
		StateBlock block = new StateBlock(Constants.BLOCK_TYPE_SEND, token.getTokenId(), from, tokenMate.getBalance().add(amount),
				tokenMate.getHeader(), accountMng.addressToPublicKey(to), Base64.encodeBase64String(sender.getBytes()), 
				Base64.encodeBase64String(receiver.getBytes()), message, System.currentTimeMillis()/1000+"", tokenMate.getRepresentative());
		
		// set work
		
		// set signature
		
	}
	
}

