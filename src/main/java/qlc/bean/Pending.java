package qlc.bean;

import java.math.BigInteger;
import java.util.List;

public class Pending {

	private String address;
	
	private List<PendingInfo> infoList;
	
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
	 * @return infoList
	 */
	public List<PendingInfo> getInfoList() {
		return infoList;
	}

	/** 
	 * @param infoList
	 */
	public void setInfoList(List<PendingInfo> infoList) {
		this.infoList = infoList;
	}

	public class PendingInfo {
		
		private String source;
		
		private BigInteger amount;
		
		private String type;
		
		private String tokenName;
		
		private String hash;
		
		private Long timestamp;

		/** 
		 * @return source
		 */
		public String getSource() {
			return source;
		}

		/** 
		 * @param source
		 */
		public void setSource(String source) {
			this.source = source;
		}

		/** 
		 * @return amount
		 */
		public BigInteger getAmount() {
			return amount;
		}

		/** 
		 * @param amount
		 */
		public void setAmount(BigInteger amount) {
			this.amount = amount;
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
		 * @return hash
		 */
		public String getHash() {
			return hash;
		}

		/** 
		 * @param hash
		 */
		public void setHash(String hash) {
			this.hash = hash;
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
		
	}
}
