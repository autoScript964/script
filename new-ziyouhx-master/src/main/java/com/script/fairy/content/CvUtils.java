package com.script.fairy.content;

/**
 * Created by yunpai on 2020/8/13.
 */

public class CvUtils {
    public static int bytesToInt(byte[] b, int offset) {
        int value= 0;
        for (int i = 0; i < 4; i++) {
            int shift= (4 - 1 - i) * 8;
            value +=(b[i + offset] & 0x000000FF) << shift;//往高位游
        }
        return value;
    }

    public static short bytesToShort(byte[] bytes,int start){
        if( start+2 > bytes.length){
            return -1 ;
        }
        short s = 0 ;
        s |= bytes[start] << 8 ;
        s |= bytes[start+1] ;
        return s ;
    }
}
