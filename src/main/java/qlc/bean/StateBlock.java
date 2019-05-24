package qlc.bean;

import java.math.BigInteger;

public final class StateBlock extends Block {
	
	private String type;
	
	private String token;

	private String address;
	
	private BigInteger balance;
	
	private BigInteger vote;
	
	private BigInteger network;
	
	private BigInteger storage;
	
	private BigInteger oracle;
	
	private String previous;
	
	private String link;
	
	private String sender;
	
	private String receiver;
	
	private String message;
	
	private String data;
	
	private Long povHeight;
	
	private Integer quota;
	
	private Long timestamp;
	
	private String extra;
	
	private String representative;
	
	private String work;
	
	private String signature;

	public StateBlock(String type, String token, String address, BigInteger balance, String previous, String link,
			String sender, String receiver, String message, Long timestamp, String representative) {
		super();
		this.type = type;
		this.token = token;
		this.address = address;
		this.balance = balance;
		this.previous = previous;
		this.link = link;
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
		this.timestamp = timestamp;
		this.representative = representative;
	}

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
	 * @return token
	 */
	public String getToken() {
		return token;
	}

	/** 
	 * @param token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/** 
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/** 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
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
	 * @return previous
	 */
	public String getPrevious() {
		return previous;
	}

	/** 
	 * @param previous
	 */
	public void setPrevious(String previous) {
		this.previous = previous;
	}

	/** 
	 * @return link
	 */
	public String getLink() {
		return link;
	}

	/** 
	 * @param link
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/** 
	 * @return sender
	 */
	public String getSender() {
		return sender;
	}

	/** 
	 * @param sender
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/** 
	 * @return receiver
	 */
	public String getReceiver() {
		return receiver;
	}

	/** 
	 * @param receiver
	 */
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	/** 
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/** 
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/** 
	 * @return data
	 */
	public String getData() {
		return data;
	}

	/** 
	 * @param data
	 */
	public void setData(String data) {
		this.data = data;
	}

	/** 
	 * @return povHeight
	 */
	public Long getPovHeight() {
		return povHeight;
	}

	/** 
	 * @param povHeight
	 */
	public void setPovHeight(Long povHeight) {
		this.povHeight = povHeight;
	}

	/** 
	 * @return quota
	 */
	public Integer getQuota() {
		return quota;
	}

	/** 
	 * @param quota
	 */
	public void setQuota(Integer quota) {
		this.quota = quota;
	}

	/** 
	 * @return timestamp
	 */
	public Long getTimestamp() {
		return timestamp;
	}

	/** 
	 * @param timestamp
	 */
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	/** 
	 * @return extra
	 */
	public String getExtra() {
		return extra;
	}

	/** 
	 * @param extra
	 */
	public void setExtra(String extra) {
		this.extra = extra;
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
	 * @return work
	 */
	public String getWork() {
		return work;
	}

	/** 
	 * @param work
	 */
	public void setWork(String work) {
		this.work = work;
	}

	/** 
	 * @return signature
	 */
	public String getSignature() {
		return signature;
	}

	/** 
	 * @param signature
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

}
