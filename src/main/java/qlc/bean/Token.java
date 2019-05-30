package qlc.bean;

import java.math.BigInteger;

public class Token {

	private String tokenId;
	
	private String tokenName;
	
	private String tokenSymbol;
	
	private BigInteger totalSupply;
	
	private Integer decimals;
	
	private String owner;
	
	private BigInteger pledgeAmount;
	
	private Integer withdrawTime;

	/** 
	 * @return tokenId
	 */
	public String getTokenId() {
		return tokenId;
	}

	/** 
	 * @param tokenId
	 */
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
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
	 * @return tokenSymbol
	 */
	public String getTokenSymbol() {
		return tokenSymbol;
	}

	/** 
	 * @param tokenSymbol
	 */
	public void setTokenSymbol(String tokenSymbol) {
		this.tokenSymbol = tokenSymbol;
	}

	/** 
	 * @return totalSupply
	 */
	public BigInteger getTotalSupply() {
		return totalSupply;
	}

	/** 
	 * @param totalSupply
	 */
	public void setTotalSupply(BigInteger totalSupply) {
		this.totalSupply = totalSupply;
	}

	/** 
	 * @return decimals
	 */
	public Integer getDecimals() {
		return decimals;
	}

	/** 
	 * @param decimals
	 */
	public void setDecimals(Integer decimals) {
		this.decimals = decimals;
	}

	/** 
	 * @return owner
	 */
	public String getOwner() {
		return owner;
	}

	/** 
	 * @param owner
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/** 
	 * @return pledgeAmount
	 */
	public BigInteger getPledgeAmount() {
		return pledgeAmount;
	}

	/** 
	 * @param pledgeAmount
	 */
	public void setPledgeAmount(BigInteger pledgeAmount) {
		this.pledgeAmount = pledgeAmount;
	}

	/** 
	 * @return withdrawTime
	 */
	public Integer getWithdrawTime() {
		return withdrawTime;
	}

	/** 
	 * @param withdrawTime
	 */
	public void setWithdrawTime(Integer withdrawTime) {
		this.withdrawTime = withdrawTime;
	}

}
