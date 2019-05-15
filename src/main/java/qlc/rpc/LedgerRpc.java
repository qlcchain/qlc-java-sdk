package qlc.rpc;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import qlc.mng.LedgerMng;
import qlc.network.QlcClient;
import qlc.network.QlcException;

import java.io.IOException;

public class LedgerRpc {

	/**
	 * Return number of blocks for a specific account
	 * @param url
	 * @param params string : the account address
	 * @return
	 * @throws QlcException
	 * @throws IOException
	 */
	public static JSONObject accountBlocksCount(String url, JSONArray params) throws QlcException, IOException {
		QlcClient client = new QlcClient(url);
		return client.call("ledger_accountBlocksCount", params);
	}

	/**
	 * Return blocks for the account, include each token of the account and order of blocks is forward from the last one
	 * @param url
	 * @param params string : the account address, int: number of blocks to return, int: optional , offset, index of block where to start, default is 0
	 * @return
	 * @throws QlcException
	 * @throws IOException
	 */
	public static JSONObject accountHistoryTopn(String url, JSONArray params) throws QlcException, IOException {
		QlcClient client = new QlcClient(url);
		return client.call("ledger_accountHistoryTopn", params);
	}

	/**
	 * Return account detail info, include each token in the account
	 * @param url
	 * @param params string : the account address
	 * @return
	 * @throws QlcException
	 * @throws IOException
	 */
	public static JSONObject accountInfo(String url, JSONArray params) throws QlcException, IOException {
		QlcClient client = new QlcClient(url);
		return client.call("ledger_accountInfo", params);
	}

	/**
	 * Return the representative address for account
	 * @param url
	 * @param params string : the account address
	 * @return
	 * @throws QlcException
	 * @throws IOException
	 */
	public static JSONObject accountRepresentative(String url, JSONArray params) throws QlcException, IOException {
		QlcClient client = new QlcClient(url);
		return client.call("ledger_accountRepresentative", params);
	}

	/**
	 * Return the vote weight for account
	 * @param url
	 * @param params string :  the vote weight for the account (if account not found, return error)
	 * @return
	 * @throws QlcException
	 * @throws IOException
	 */
	public static JSONObject accountVotingWeight(String url, JSONArray params) throws QlcException, IOException {
		QlcClient client = new QlcClient(url);
		return client.call("ledger_accountVotingWeight", params);
	}


	/**
	 * Return account list of chain
	 * @param url
	 * @param params int: number of accounts to return
	int: optional , offset, index of account where to start, default is 0
	 * @return []address: addresses list of accounts
	 * @throws QlcException
	 * @throws IOException
	 */
	public static JSONObject accounts(String url, JSONArray params) throws QlcException, IOException {
		QlcClient client = new QlcClient(url);
		return client.call("ledger_accounts", params);
	}

	/**
	 * Returns balance and pending (amount that has not yet been received) for each account
	 * @param url
	 * @param params []string: addresses list
	 * @return balance and pending amount of each token for each account
	 * @throws QlcException
	 * @throws IOException
	 */
	public static JSONObject accountsBalance(String url, JSONArray params) throws QlcException, IOException {
		QlcClient client = new QlcClient(url);
		return client.call("ledger_accountsBalance", params);
	}

	/**
	 * Return pairs of token name and block hash (representing the head block ) of each token for each account
	 * @param url
	 * @param params []string: addresses list
	 * @return token name and block hash for each account
	 * @throws QlcException
	 * @throws IOException
	 */
	public static JSONObject accountsFrontiers(String url, JSONArray params) throws QlcException, IOException {
		QlcClient client = new QlcClient(url);
		return client.call("ledger_accountsFrontiers", params);
	}

	/**
	 * Return pending info for accounts
	 * @param url
	 * @param params []string: addresses list for accounts
	int: get the maximum number of pending for each account, if set -1, return all pending
	 * @return pending info for each token of each account, means:
	tokenName : token name
	type : token type
	source : sender account of transaction
	amount : amount of transaction
	hash : hash of send block
	timestamp: timestamp
	 * @throws QlcException
	 * @throws IOException
	 */
	public static JSONObject accountsPending(String url, JSONArray params) throws QlcException, IOException {
		QlcClient client = new QlcClient(url);
		return client.call("ledger_accountsPending", params);
	}

	/**
	 * Return total number of accounts of chain
	 * @param url
	 * @param params
	 * @return: total number of accounts
	 * @throws QlcException
	 * @throws IOException
	 */
	public static JSONObject accountsCount(String url, JSONArray params) throws QlcException, IOException {
		QlcClient client = new QlcClient(url);
		return client.call("ledger_accountsCount", params);
	}

	/**
	 * Return the account that the block belong to
	 * @param url
	 * @param params string: block hash
	 * @return: string: the account address (if block not found, return error)
	 * @throws QlcException
	 * @throws IOException
	 */
	public static JSONObject blockAccount(String url, JSONArray params) throws QlcException, IOException {
		QlcClient client = new QlcClient(url);
		return client.call("ledger_blockAccount", params);
	}


	/**
	 * Return hash for the block
	 * @param url
	 * @param params block: block info
	 * @return: string: block hash
	 * @throws QlcException
	 * @throws IOException
	 */
	public static JSONObject blockHash(String url, JSONArray params) throws QlcException, IOException {
		QlcClient client = new QlcClient(url);
		return client.call("ledger_blockHash", params);
	}


	/**
	 * Return blocks list of chain
	 * @param url
	 * @param params int: number of blocks to return
	int: optional, offset, index of block where to start, default is 0
	 * @return: []block: blocks list
	 * @throws QlcException
	 * @throws IOException
	 */
	public static JSONObject blocks(String url, JSONArray params) throws QlcException, IOException {
		QlcClient client = new QlcClient(url);
		return client.call("ledger_blocks", params);
	}

	/**
	 * Return the number of blocks (include smartcontrant block) and unchecked blocks of chain
	 * @param url
	 * @param params int: number of blocks to return
	int: optional, offset, index of block where to start, default is 0
	 * @return: number of blocks, means:
	count: int, number of blocks , include smartcontrant block
	unchecked: int, number of unchecked blocks
	 * @throws QlcException
	 * @throws IOException
	 */
	public static JSONObject blocksCount(String url, JSONArray params) throws QlcException, IOException {
		QlcClient client = new QlcClient(url);
		return client.call("ledger_blocksCount", params);
	}

	/**
	 * Report number of blocks by type of chain
	 * @param url
	 * @param params
	 * @return: number of blocks for each type
	 * @throws QlcException
	 * @throws IOException
	 */
	public static JSONObject blocksCountByType(String url, JSONArray params) throws QlcException, IOException {
		QlcClient client = new QlcClient(url);
		return client.call("ledger_blocksCountByType", params);
	}

	/**
	 * Return blocks info for blocks hash
	 * @param url
	 * @param params []string: blocks hash
	 * @return: []block: blocks info
	 * @throws QlcException
	 * @throws IOException
	 */
	public static JSONObject blocksInfo(String url, JSONArray params) throws QlcException, IOException {
		QlcClient client = new QlcClient(url);
		return client.call("ledger_blocksInfo", params);
	}


	/**
	 * Accept a specific block hash and return a consecutive blocks hash list， starting with this block, and traverse forward to the maximum number
	 * @param url
	 * @param params string : block hash to start at
	int: get the maximum number of blocks, if set n to -1, will list blocks to open block
	 * @return: []string: block hash list (if block not found, return error)
	 * @throws QlcException
	 * @throws IOException
	 */
	public static JSONObject chain(String url, JSONArray params) throws QlcException, IOException {
		QlcClient client = new QlcClient(url);
		return client.call("ledger_chain", params);
	}

	/**
	 * Return a list of pairs of delegator and it's balance for a specific representative account
	 * @param url
	 * @param params string: representative account address
	 * @return: each delegator and it's balance
	 * @throws QlcException
	 * @throws IOException
	 */
	public static JSONObject delegators(String url, JSONArray params) throws QlcException, IOException {
		QlcClient client = new QlcClient(url);
		return client.call("ledger_delegators", params);
	}

	/**
	 * Return number of delegators for a specific representative account
	 * @param url
	 * @param params string: representative account address
	 * @return: int: number of delegators for the account
	 * @throws QlcException
	 * @throws IOException
	 */
	public static JSONObject delegatorsCount(String url, JSONArray params) throws QlcException, IOException {
		QlcClient client = new QlcClient(url);
		return client.call("ledger_delegatorsCount", params);
	}

	/**
	 * Return send block by send parameter and private key
	 * @param url
	 * @param params send parameter for the block
	from: send address for the transaction
	to: receive address for the transaction
	tokenName: token name
	amount: transaction amount
	sender: optional, sms sender
	receiver: optional, sms receiver
	message: optional, sms message hash
	string: private key
	 * @return: block: send block
	 * @throws QlcException
	 * @throws IOException
	 */
	public static JSONObject generateSendBlock(String url, JSONArray params) throws QlcException, IOException {
		QlcClient client = new QlcClient(url);
		return client.call("ledger_generateSendBlock", params);
	}

	/**
	 * Return receive block by send block and private key
	 * @param url
	 * @param params block: send block
	string: private key
	 * @return: block: receive block
	 * @throws QlcException
	 * @throws IOException
	 */
	public static JSONObject generateReceiveBlock(String url, JSONArray params) throws QlcException, IOException {
		QlcClient client = new QlcClient(url);
		return client.call("ledger_generateReceiveBlock", params);
	}


	/**
	 * Return change block by account and private key
	 * @param url
	 * @param params string: account address
	string: new representative account
	string: private key
	 * @return: block: change block
	 * @throws QlcException
	 * @throws IOException
	 */
	public static JSONObject generateChangeBlock(String url, JSONArray params) throws QlcException, IOException {
		QlcClient client = new QlcClient(url);
		return client.call("ledger_generateChangeBlock", params);
	}

	/**
	 * Check block base info, update chain info for the block, and broadcast block
	 * @param url
	 * @param params block: block
	 * @return: string: hash of the block
	 * @throws QlcException
	 * @throws IOException
	 */
	public static JSONObject process(String url, JSONArray params) throws QlcException, IOException {
		QlcClient client = new QlcClient(url);
		return client.call("ledger_process", params);
	}

	/**
	 * Return pairs of representative and its voting weight
	 * @param url
	 * @param params bool , optional, if not set or set false, will return representatives randomly, if set true, will sorting represetntative balance in descending order
	 * @return: each representative and its voting weight
	 * @throws QlcException
	 * @throws IOException
	 */
	public static JSONObject representatives(String url, JSONArray params) throws QlcException, IOException {
		QlcClient client = new QlcClient(url);
		return client.call("ledger_representatives", params);
	}

	/**
	 * Return tokens of the chain
	 * @param url
	 * @param params
	 * @return: []token: the tokens info
	 * @throws QlcException
	 * @throws IOException
	 */
	public static JSONObject tokens(String url, JSONArray params) throws QlcException, IOException {
		QlcClient client = new QlcClient(url);
		return client.call("ledger_tokens", params);
	}

	/**
	 * Return token info by token id
	 * @param url
	 * @param params string: token id
	 * @return: token: token info
	 * @throws QlcException
	 * @throws IOException
	 */
	public static JSONObject tokenInfoById(String url, JSONArray params) throws QlcException, IOException {
		QlcClient client = new QlcClient(url);
		return client.call("ledger_tokenInfoById", params);
	}

	/**
	 * Return token info by token name
	 * @param url
	 * @param params string: token name
	 * @return: token: token info
	 * @throws QlcException
	 * @throws IOException
	 */
	public static JSONObject tokenInfoByName(String url, JSONArray params) throws QlcException, IOException {
		QlcClient client = new QlcClient(url);
		return client.call("ledger_tokenInfoByName", params);
	}

	/**
	 * Return the number of blocks (not include smartcontrant block) and unchecked blocks of chain
	 * @param url
	 * @param params
	 * @return: count: int, number of blocks , not include smartcontrant block
	unchecked: int, number of unchecked blocks
	 * @throws QlcException
	 * @throws IOException
	 */
	public static JSONObject transactionsCount(String url, JSONArray params) throws QlcException, IOException {
		QlcClient client = new QlcClient(url);
		return client.call("ledger_transactionsCount", params);
	}

}

