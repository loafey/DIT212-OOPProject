package com.ESSBG.app.Network;

final class Converter {
    private final static int numberOfBits = 32;

    // Don't allow creating objects of this.
    private Converter() {
    }

    protected static int byteArrayToInt(byte[] b) {
        int sum = 0;
        int n = numberOfBits;
        for (int i = 0; i < b.length; i++) {
            n -= 8;
            sum |= (b[i] & 0xFF) << n;
        }
        return sum;
    }

    /**
     * Only non-negative values!
     *
     * Each byte contains 8 bits.
     *
     * @param a convert this value to array
     * @return bytesarray to represent the int
     */
    protected static byte[] intToByteArray(int a) {
        int n = numberOfBits;
        int nBytesAlloc = n / 8;
        byte[] hexArray = new byte[nBytesAlloc];
        for (int i = 0; i < nBytesAlloc; i++) {
            n -= 8;
            hexArray[i] = (byte) (a >> n);
        }
        return hexArray;
    }
}