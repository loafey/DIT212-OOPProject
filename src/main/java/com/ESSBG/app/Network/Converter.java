package com.ESSBG.app.Network;

final class Converter {
    // Don't allow creating objects of this.
    private Converter() {
    }

    /**
     * Converts an array of bytes to an integer.
     *
     * @param b the byte array in question.
     * @return
     */

    protected static int byteArrayToInt(byte[] b) {
        int sum = 0;
        int n = b.length * 8;
        for (int i = 0; i < b.length; i++) {
            n -= 8;
            sum |= (b[i] & 0xFF) << n;
        }
        return sum;
    }

    /**
     * Only non-negative values!
     *
     * Each byte contains 8 bits. Converts an integer into a byte array to the
     * selected bytes
     *
     * @param numberOfBytes 1-4 bytes len of message as header:, <br>
     *                      1 byte: 255, 2 byte: 65535, 3 byte: 16777215, 4 byte:
     *                      2147483647 <br>
     *                      1 byte long message contains 255 characters or 2040
     *                      bits! In theory...
     * @param intToConvert  convert this value to byte array
     * @return bytesarray to represent the int
     * @throws IllegalArgumentException
     */
    protected static byte[] intToByteArray(int numberOfBytes, int intToConvert) throws IllegalArgumentException {
        // Check for valid int, assume a value above java max is impossible.
        if (0 < numberOfBytes && numberOfBytes > 4) {
            throw new IllegalArgumentException("Max bytes are 4");
        }
        if (numberOfBytes <= 3) {
            int maxValue = (int) Math.pow(2, numberOfBytes * 8 - 1);
            if (intToConvert > maxValue) {
                throw new IllegalArgumentException("Given number too big for the selected number of bytes!");
            }
        }
        if (intToConvert < 0) {
            throw new IllegalArgumentException("Number can't be negative!");
        }
        int numberOfBits = numberOfBytes * 8;
        byte[] hexArray = new byte[numberOfBytes];
        for (int i = 0; i < numberOfBytes; i++) {
            numberOfBits -= 8;
            hexArray[i] = (byte) (intToConvert >> numberOfBits);
        }
        return hexArray;
    }
}