package qlc.rpc;

import qlc.bean.StateBlock;
import qlc.mng.AccountMng;
import qlc.utils.Constants;

public class StateBlockRpc {
	
	public String getRoot(StateBlock block) {
		if (block.getType().equals(Constants.BLOCK_TYPE_OPEN))
			return AccountMng.addressToPublicKey(block.getAddress());
		else
			return block.getPrevious();
	}
	
	public String getHash() {
		return null;
	}
}
