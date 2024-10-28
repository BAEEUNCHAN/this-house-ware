package com.contractor.app.util;

import java.util.Random;

public class RandomValueUtil {

    private static int randomValueLength = 15;
    private static final char[] characters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 
                                            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 
                                            'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
    
    public static String getRandomValue() {
        Random random = new Random(System.currentTimeMillis());
        int tablelength = characters.length;
        StringBuffer buf = new StringBuffer();
        
        for(int i = 0; i < randomValueLength; i++) {
            buf.append(characters[random.nextInt(tablelength)]);
        }
        
        return buf.toString();
    }

}
