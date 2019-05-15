package qlc.mng;

import qlc.bean.StateBlock;
import qlc.network.QlcException;
import qlc.utils.Constants;

public class StateBlockMng {
	
	public String getRoot(StateBlock block) throws QlcException {
		if (block.getType().equals(Constants.BLOCK_TYPE_OPEN)) {
			AccountMng mng = new AccountMng();
			return mng.addressToPublicKey(block.getAddress());
		} else
			return block.getPrevious();
	}
	
	public String getHash() {
		return null;
	}
}
