package qlc.mng;

import qlc.bean.StateBlock;
import qlc.utils.Constants;

public class StateBlockMng {
	
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
