package qlc.bean;

import java.io.IOException;
import java.math.BigInteger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import qlc.network.QlcClient;
import qlc.network.QlcException;
import qlc.utils.Constants;

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

	public static Token getTokenByTokenName (String tokenName) throws QlcException, IOException {
		
		JSONArray params = new JSONArray();
		params.add(tokenName);
		
		QlcClient client = new QlcClient(Constants.URL);
		JSONObject json = client.call("ledger_tokenInfoByName", params);
		if (json.containsKey("result")) {
			json = json.getJSONObject("result");
			return new Gson().fromJson(json.toJSONString(), Token.class);
		}
		
		return null;
	}

}
