package qlc.bean;

import java.math.BigInteger;

// 
public class TokenMate {
		
	private String type;					// token hash
	
	private String header;					// the latest block hash for the token chain
	
	private String representative;			// representative address
	
	private String open;					// the open block hash for the token chain
	
	private BigInteger balance;				// balance for the token
	
	private String account;  				// account that token belong to
	
	private Integer modified;				// timestamp
	
	private Integer blockCount;				// total block number for the token chain
	
	private String tokenName;				// the token name
	
	private String pending;					// pending amount

	/** 
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/** 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/** 
	 * @return header
	 */
	public String getHeader() {
		return header;
	}

	/** 
	 * @param header
	 */
	public void setHeader(String header) {
		this.header = header;
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
	 * @return open
	 */
	public String getOpen() {
		return open;
	}

	/** 
	 * @param open
	 */
	public void setOpen(String open) {
		this.open = open;
	}

	/** 
	 * @return balance
	 */
	public BigInteger getBalance() {
		return balance;
	}

	/** 
	 * @param balance
	 */
	public void setBalance(BigInteger balance) {
		this.balance = balance;
	}

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
	 * @return modified
	 */
	public Integer getModified() {
		return modified;
	}

	/** 
	 * @param modified
	 */
	public void setModified(Integer modified) {
		this.modified = modified;
	}

	/** 
	 * @return blockCount
	 */
	public Integer getBlockCount() {
		return blockCount;
	}

	/** 
	 * @param blockCount
	 */
	public void setBlockCount(Integer blockCount) {
		this.blockCount = blockCount;
	}

	/** 
	 * @return tokenName
	 */
	public String getTokenName() {
		return tokenName;
	}

	/** 
	 * @param tokenName
	 */
	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}

	/** 
	 * @return pending
	 */
	public String getPending() {
		return pending;
	}

	/** 
	 * @param pending
	 */
	public void setPending(String pending) {
		this.pending = pending;
	}
		
}
