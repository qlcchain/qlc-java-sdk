package qlc.utils;

import static java.util.function.Function.identity;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import qlc.utils.Helper;

public class WorkUtil {
	
    private static final Long THRESHOLD = 0xFFFFFFC000000000L;
    
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    private WorkUtil() {
    }

    public static String perform(String hash) {
        byte[] byteArray = Helper.hexStringToBytes(hash);
        return Stream.generate(() -> perform(byteArray))
                .parallel()
                .flatMap(identity())
                .map(Helper::reverse)
                .map(Helper::byteToHexString)
                .map(String::toLowerCase)
                .findAny()
                .get();
    }

    public static boolean verify(String hash, String pow) {
        return verify(Helper.hexStringToBytes(hash), Helper.reverse(Helper.hexStringToBytes(pow)));
    }

    public static boolean verify(byte[] byteArrayHash, byte[] byteArrayPOW) {
        byte[] work = HashUtil.digest(8, byteArrayPOW, byteArrayHash);
        long uWork = ByteBuffer.wrap(work).order(ByteOrder.LITTLE_ENDIAN).getLong();
        return Long.compareUnsigned(uWork, THRESHOLD) >= 0;
    }

    private static Stream<byte[]> perform(byte[] byteArrayHash) {
        byte[] byteArrayPOW = new byte[8];
        RANDOM.nextBytes(byteArrayPOW);
        for (byte b = -128; b < 127; b++) {
            byteArrayPOW[7] = b;
            if (verify(byteArrayHash, byteArrayPOW)) {
                return Stream.of(byteArrayPOW);
            }
        }
        return Stream.empty();
    }
    
}
