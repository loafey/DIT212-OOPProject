package com.ESSBG.app.Network;

final class Converter {
    // Don't allow creating objects of this.
    private Converter() {
    }

    protected static int byteArrayToInt(byte[] b) {
        int sum = 0;
        for (int i = 0; i < b.length; i++) {
            sum |= (b[b.length - i] & 0xFF) << (8 * i);
        }
        return sum;
    }

    protected static byte[] intToByteArray(int a) {
        int numberOfBits = 64;
        byte[] hexArray = new byte[64 / 8];
        for (int i = 0; i < (numberOfBits / 8) + 1; i++) {
            hexArray[i] = (byte) ((a >> numberOfBits) & 0xFF);
            numberOfBits -= 8;
        }
        return hexArray;
    }
}
