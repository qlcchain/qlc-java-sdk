package qlc.utils;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;

public class Helper {
	
    public static byte[] reverse(byte[] v) {
        byte[] result = new byte[v.length];
        for (int i=0; i<v.length; i++) {
            result[i] = v[v.length-i-1];
        }
        return result;
    }
    
    public static byte[] hexStringToBytes(String value) {
        if (value==null || value.length()==0) {
            return new byte[0];
        }
        if (value.length()%2 == 1) {
            throw new IllegalArgumentException();
        }
        byte[] result = new byte[value.length()/2];
        for (int i=0; i<result.length; i++) {
            result[i] = (byte) Integer.parseInt(value.substring(i*2, i*2+2), 16);
        }
        return result;
    }
    
    public static String byteToHexString(byte[] value) {
        StringBuilder sb = new StringBuilder();
        for (byte b : value) {
            int v = Byte.toUnsignedInt(b);
            sb.append(Integer.toHexString(v >>> 4));
            sb.append(Integer.toHexString(v & 0x0f));
        }
        return sb.toString();
    }

    public static String reverse(String value) {
    	return byteToHexString(reverse(hexStringToBytes(value)));
    }

    public static byte[] removePrevZero(byte[] bt) {
		if(bt.length==33 && bt[0]==0) {
			return Arrays.copyOfRange(bt, 1, 33);
		}
		return bt;
	}
    
	public static String getSeed() {
		//String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		//String seed = byteToHexString(uuid.getBytes());
		//return seed;
		
		SecureRandom random = new SecureRandom();
		byte[] seed = random.generateSeed(20);
		return byteToHexString(seed);
	}
	
	public static String hexStringToBinary(String hex) {
		String value = new BigInteger(hex, 16).toString(2);
		String formatPad = "%" + (hex.length()*4) + "s";
		return (String.format(formatPad, value).replace(" ", ""));
	}
	
	public static String binaryToHexString(String binary) {
		BigInteger num = new BigInteger(binary, 2);
		return num.toString(16).toUpperCase();
	}

}
