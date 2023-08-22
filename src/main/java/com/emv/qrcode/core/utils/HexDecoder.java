package com.emv.qrcode.core.utils;

public class HexDecoder {
    public static byte[] decodeHex(String hexString) {
        if (hexString.length() % 2 != 0) {
            throw new IllegalArgumentException("Invalid hexadecimal string length");
        }
        int length = hexString.length() / 2;
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            int index = i * 2;
            String hex = hexString.substring(index, index + 2);
            bytes[i] = (byte) Integer.parseInt(hex, 16);
        }
        return bytes;
    }
}
