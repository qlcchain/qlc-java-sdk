package qlc.bean;

import java.math.BigInteger;
import java.util.List;

public class Account {
	
	private String account;							// the account address
	
	private BigInteger coinBalance;					// balance of main token of the account (default is QLC)
	
	private BigInteger vote;						
	
	private	BigInteger network;  
	
	private	BigInteger storage;
	
	private	BigInteger oracle;
	
	private	String representative;					// representative address of the account
	
	private List<TokenMeta> tokens;					// each token info for the account
	
	/** 
	 * @return account
	 */
	public String getAccount() {
		return account;
	}

	/** 
	 * @param account
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/** 
	 * @return coinBalance
	 */
	public BigInteger getCoinBalance() {
		return coinBalance;
	}

	/** 
	 * @param coinBalance
	 */
	public void setCoinBalance(BigInteger coinBalance) {
		this.coinBalance = coinBalance;
	}

	/** 
	 * @return vote
	 */
	public BigInteger getVote() {
		return vote;
	}

	/** 
	 * @param vote
	 */
	public void setVote(BigInteger vote) {
		this.vote = vote;
	}

	/** 
	 * @return network
	 */
	public BigInteger getNetwork() {
		return network;
	}

	/** 
	 * @param network
	 */
	public void setNetwork(BigInteger network) {
		this.network = network;
	}

	/** 
	 * @return storage
	 */
	public BigInteger getStorage() {
		return storage;
	}

	/** 
	 * @param storage
	 */
	public void setStorage(BigInteger storage) {
		this.storage = storage;
	}

	/** 
	 * @return oracle
	 */
	public BigInteger getOracle() {
		return oracle;
	}

	/** 
	 * @param oracle
	 */
	public void setOracle(BigInteger oracle) {
		this.oracle = oracle;
	}

	/** 
	 * @return representative
	 */
	public String getRepresentative() {
		return representative;
	}

	/** 
	 * @param representative
	 */
	public void setRepresentative(String representative) {
		this.representative = representative;
	}
	
	/** 
	 * @return tokens
	 */
	public List<TokenMeta> getTokens() {
		return tokens;
	}

	/** 
	 * @param tokens
	 */
	public void setTokens(List<TokenMeta> tokens) {
		this.tokens = tokens;
	}
	
}
