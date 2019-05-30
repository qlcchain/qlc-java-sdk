package qlc.rpc;

import qlc.network.QlcClient;

public class QlcRpc {

	protected QlcClient client;

	public QlcRpc(QlcClient client) {
		super();
		this.client = client;
	}

	/** 
	 * @return client
	 */
	public QlcClient getClient() {
		return client;
	}

	/** 
	 * @param client
	 */
	public void setClient(QlcClient client) {
		this.client = client;
	}
	
}
