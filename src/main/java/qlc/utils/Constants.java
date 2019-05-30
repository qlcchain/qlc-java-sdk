package qlc.utils;

import java.math.BigInteger;

public class Constants {

	// basic node
	public static final String URL = "http://192.168.1.122:19735";
	
	// exception code
	public static final int EXCEPTION_CODE_1000 = 1000;
	public static final String EXCEPTION_MSG_1000 = "Not enought balance";
	
	public static final int EXCEPTION_CODE_1001 = 1001;
	public static final String EXCEPTION_MSG_1001 = "Seed can`t be empty";
	
	public static final int EXCEPTION_CODE_1002 = 1002;
	public static final String EXCEPTION_MSG_1002 = "Public key can`t be empty";
	
	public static final int EXCEPTION_CODE_1003 = 1003;	
	public static final String EXCEPTION_MSG_1003 = "Address can`t be empty";
	
	public static final int EXCEPTION_CODE_1004 = 1004;	
	public static final String EXCEPTION_MSG_1004 = "Address format error";
	
	public static final int EXCEPTION_CODE_1005 = 1005;	
	public static final String EXCEPTION_MSG_1005 = "Signature verification failed";
	
	
	public static final int EXCEPTION_BLOCK_CODE_2000 = 2000;	
	public static final String EXCEPTION_BLOCK_MSG_2000 = "Parameter error for send block";
	
	public static final int EXCEPTION_BLOCK_CODE_2001 = 2001;	
	public static final String EXCEPTION_BLOCK_MSG_2001 = "Parameter error for receive block";
	
	public static final int EXCEPTION_BLOCK_CODE_2002 = 2002;	
	public static final String EXCEPTION_BLOCK_MSG_2002 = "The block is not send block";
	
	public static final int EXCEPTION_BLOCK_CODE_2003 = 2003;	
	public static final String EXCEPTION_BLOCK_MSG_2003 = "Send block does not exist";
	
	public static final int EXCEPTION_BLOCK_CODE_2004 = 2004;	
	public static final String EXCEPTION_BLOCK_MSG_2004 = "receive address is mismatch private key";
	
	public static final int EXCEPTION_BLOCK_CODE_2005 = 2005;	
	public static final String EXCEPTION_BLOCK_MSG_2005 = "Pending not found";
	
	// block type
	public static final String BLOCK_TYPE_OPEN = "Open";
	public static final String BLOCK_TYPE_SEND = "Send";
	public static final String BLOCK_TYPE_RECEIVE = "Receive";
	public static final String BLOCK_TYPE_CHANGE = "Change";
	
	// block parameter default value
	public static final String ZERO_HASH = "0000000000000000000000000000000000000000000000000000000000000000";
	public static final BigInteger ZERO_BIG_INTEGER = new BigInteger("0");
	public static final Long ZERO_LONG = 0l;
	
	
	
}
