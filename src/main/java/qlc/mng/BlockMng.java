package qlc.mng;

import java.math.BigInteger;

import qlc.bean.Block;
import qlc.bean.StateBlock;
import qlc.network.QlcException;
import qlc.utils.Constants;
import qlc.utils.HashUtil;
import qlc.utils.Helper;
import qlc.utils.StringUtil;

public final class BlockMng {
	
	// get block root
	public static String getRoot(StateBlock block) throws QlcException {
		if (block.getType().equals(Constants.BLOCK_TYPE_OPEN)) {
			return AccountMng.addressToPublicKey(block.getAddress());
		} else
			return block.getPrevious();
	}
	
	// get block hash
    public static byte[] getHash(StateBlock block) throws QlcException {
		
    	byte[] sources = new byte[1];
        sources = Helper.bigInttoBytes(new BigInteger(Block.Type.getIndex(block.getType())));
        
        sources = Helper.byteMerger(sources, Helper.hexStringToBytes(block.getToken()));
        
        sources = Helper.byteMerger(sources, Helper.hexStringToBytes(AccountMng.addressToPublicKey(block.getAddress())));
        
        byte[] balance = Helper.bigInttoBytes(block.getBalance());
        sources = Helper.byteMerger(sources, balance);

        byte[] vote = Helper.bigInttoBytes(block.getVote());
        sources = Helper.byteMerger(sources, vote);

        byte[] network = Helper.bigInttoBytes(block.getNetwork());
        sources = Helper.byteMerger(sources, network);

        byte[] storage = Helper.bigInttoBytes(block.getStorage());
        sources = Helper.byteMerger(sources, storage);

        byte[] oracle = Helper.bigInttoBytes(block.getOracle());
        sources = Helper.byteMerger(sources, oracle);

        sources = Helper.byteMerger(sources, Helper.hexStringToBytes(block.getPrevious()));

        sources = Helper.byteMerger(sources, Helper.hexStringToBytes(block.getLink()));

        if (StringUtil.isNotBlank(block.getSender()))
        	sources = Helper.byteMerger(sources, block.getSender().getBytes());

        if (StringUtil.isNotBlank(block.getReceiver()))
        	sources = Helper.byteMerger(sources, block.getReceiver().getBytes());

        sources = Helper.byteMerger(sources, Helper.hexStringToBytes(block.getMessage()));
        
        sources = Helper.byteMerger(sources, Helper.LongToBytes(block.getTimestamp()));
        
        sources = Helper.byteMerger(sources, Helper.LongToBytes(block.getPovHeight()));

        sources = Helper.byteMerger(sources, Helper.hexStringToBytes(block.getExtra()));

        sources = Helper.byteMerger(sources, Helper.hexStringToBytes(AccountMng.addressToPublicKey(block.getRepresentative())));
        
        byte[] output = HashUtil.digest(32, sources);
        return output;
    }

}
