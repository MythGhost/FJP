package util;

import java.util.Arrays;

/**
 * This class converts byte array data from and to hex and also provides
 * a hexdump method.
 * <p>
 * This class is based on source code from the jCoderZ.org project, who's copyright
 * notice can be found at the end of this file.
 */
public final class HexUtil {
    private static final int BYTE_UNSIGNED_MAX = 255;
    private static final int HALF_BYTE = 4;
    private static final byte LOW_HALF_MASK = (byte) 0x0F;
    private static final byte HIGH_HALF_MASK = (byte) 0xF0;
    private static final int BYTE_MASK = 0xFF;
    private static final int CHARS_PER_BYTE = 2;
    private static final int DECIMAL_OFFSET = 10;

    private static final int ASCII_MAX_VALUE = 127;
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private static final char[] HEX_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F'};

    private static final String[] BYTE_AS_HEX = new String[BYTE_UNSIGNED_MAX + 1];

    private static final int[] CHAR_TO_NIBBLE_LOW = new int[BYTE_UNSIGNED_MAX + 1];
    private static final int[] CHAR_TO_NIBBLE_HIGH = new int[BYTE_UNSIGNED_MAX + 1];

    private static final int DUMP_BYTES_PER_LINE = 16;
    private static final int DUMP_BYTES_PER_COLUMN = 8;
    private static final int DUMP_ADDR_LEN = 8;
    private static final int DUMP_BUFFER_SIZE = DUMP_ADDR_LEN + // address
            DUMP_BYTES_PER_LINE * CHARS_PER_BYTE + // bytes
            DUMP_BYTES_PER_LINE + // whitespaces between bytes
            1 + 1; // extra whitespace after address and to separate columns

    static {
        // set up tables/arrays for byte to hex conversion
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BYTE_AS_HEX.length; i++) {
            sb.setLength(0);
            final int lowerFourBits = i & LOW_HALF_MASK;
            final int highFourBits = (i & HIGH_HALF_MASK) >> HALF_BYTE;
            sb.append(HEX_CHARS[highFourBits]);
            sb.append(HEX_CHARS[lowerFourBits]);
            BYTE_AS_HEX[i] = sb.toString();
        }

        // set up tables/arrays for hex to byte conversion
        Arrays.fill(CHAR_TO_NIBBLE_LOW, -1);
        Arrays.fill(CHAR_TO_NIBBLE_HIGH, -1);
        for (int i = '0'; i <= '9'; i++) {
            CHAR_TO_NIBBLE_LOW[i] = i - '0';
            CHAR_TO_NIBBLE_HIGH[i] = (i - '0') << HALF_BYTE;
        }
        for (int i = 'a'; i <= 'f'; i++) {
            CHAR_TO_NIBBLE_LOW[i] = i - 'a' + DECIMAL_OFFSET;
            CHAR_TO_NIBBLE_HIGH[i] = (i - 'a' + DECIMAL_OFFSET) << HALF_BYTE;
        }
        for (int i = 'A'; i <= 'F'; i++) {
            CHAR_TO_NIBBLE_LOW[i] = i - 'A' + DECIMAL_OFFSET;
            CHAR_TO_NIBBLE_HIGH[i] = (i - 'A' + DECIMAL_OFFSET) << HALF_BYTE;
        }
    }

    private HexUtil() {
        // utility class that provides only static methods
    }

    /**
     * Converts a byte array into an upper-case hex string, starting at the
     * given offset and converting the given number of bytes.
     *
     * @param data   the byte data to convert to hex
     * @param offset the start offset in the byte array
     * @param length the number of bytes to convert
     * @return null if data was null, an empty string if data.length == 0,
     * and the hex representation of the byte array otherwise
     * @throws IndexOutOfBoundsException if offset + length > data.lenght
     */
    public static String bytesToHex(final byte[] data, final int offset, final int length)
            throws IndexOutOfBoundsException {
        final String result;
        if (data == null) {
            result = null;
        } else {
            final StringBuffer sbuf = new StringBuffer();
            for (int i = offset; i < offset + length; i++) {
                sbuf.append(BYTE_AS_HEX[data[i] & BYTE_MASK]);
            }
            result = sbuf.toString();
        }
        return result;
    }

    /**
     * Converts a byte array into an upper-case hex string, starting at the
     * first byte and converting the whole array.
     *
     * @param data the byte data to convert to hex
     * @return null if data was null, an empty string if data.length == 0,
     * and the hex representation of the byte array otherwise
     */
    public static String bytesToHex(final byte[] data) {
        final String result;
        if (data != null) {
            result = bytesToHex(data, 0, data.length);
        } else {
            result = null;
        }
        return result;
    }

    /**
     * Convert the given hex string to a byte array.
     *
     * @param s the string to convert
     * @return null if the string is null, an empty byte array if s.length == 0
     * and a byte array representing the hex string otherwise
     * @throws IllegalArgumentException if the string is not a multiple of 2
     *                                  characters long, or if the string contains an invalid hex char
     */
    public static byte[] stringToBytes(final String s) throws IllegalArgumentException {
        final byte[] result;
        if (s == null) {
            result = null;
        } else if (s.length() == 0) {
            result = new byte[0];
        } else {
            // string must be a multiple of 2 chars
            if (s.length() % CHARS_PER_BYTE != 0) {
                throw new IllegalArgumentException("The length of a hex string must be a multiple of 2 (was " + s.length() + ")");
            }
            int count = 0;
            result = new byte[s.length() / CHARS_PER_BYTE];
            try {
                for (int i = 0; i < s.length(); i++) {
                    final char c1 = s.charAt(i);
                    final char c2 = s.charAt(++i);
                    final int b = CHAR_TO_NIBBLE_HIGH[c1] | CHAR_TO_NIBBLE_LOW[c2];
                    if (b == -1) {
                        throw new IllegalArgumentException("'" + c1 + c2 + "' is not a valid hex representation of a byte");
                    }
                    result[count] = (byte) b;
                    ++count;
                }
            } catch (IndexOutOfBoundsException ex) {
                final char c1 = s.charAt(count * CHARS_PER_BYTE);
                final char c2 = s.charAt(count * CHARS_PER_BYTE + 1);
                throw new IllegalArgumentException("'" + c1 + c2 + "' is not a valid hex representation of a byte");
            }
        }
        return result;
    }

    /**
     * Produces a hexdump of the given byte array with a formatting
     * as in "hexdump -C" (canonical hex + ASCII display). This formatting
     * contains an address column, sixteen bytes of hex separated by spaces,
     * with an extra space after eight bytes, and an ascii print-out of the
     * bytes enclosed in pipe symbols. Non-ASCII characters are replaced by
     * dots.
     *
     * @param data the byte data to dump
     * @return a string containing the hexdump, or null if data was null
     */
    public static String dump(final byte[] data) {
        final String result;
        if (data == null) {
            result = null;
        } else {
            int offset = 0;
            final StringBuilder dumpBuffer = new StringBuilder();
            final StringBuffer lineBuffer = new StringBuffer();
            final StringBuilder charBuffer = new StringBuilder();
            while (offset < data.length) {
                lineBuffer.setLength(0);
                charBuffer.setLength(0);
                charBuffer.append("|");
                lineBuffer.append(offsetToHex(offset));
                lineBuffer.append(" ");
                final int end = (Math.min(offset + DUMP_BYTES_PER_LINE, data.length));
                for (int i = offset; i < end; i++) {
                    final byte b = data[i];
                    lineBuffer.append(bytesToHex(new byte[]{b}));
                    lineBuffer.append(" ");

                    if (i - offset == DUMP_BYTES_PER_COLUMN - 1) {
                        lineBuffer.append(" ");
                    }
                    final char c = (char) b;
                    if ((!Character.isISOControl(c)) && isAscii(c)) {
                        charBuffer.append(c);
                    } else {
                        charBuffer.append(".");
                    }
                }
                padBuffer(lineBuffer);
                charBuffer.append("|");
                dumpBuffer.append(lineBuffer);
                dumpBuffer.append(charBuffer);
                dumpBuffer.append(LINE_SEPARATOR);
                offset += DUMP_BYTES_PER_LINE;
            }
            result = dumpBuffer.toString();
        }
        return result;
    }

    /**
     * Tests if the given character is an ASCII character, i.e. if it's
     * integer value is less than or equal to 127.
     *
     * @param c the character to test.
     * @return <code>true</code> if c &lt;= 127, <code>false</code> otherwise.
     */
    public static boolean isAscii(final char c) {
        return (c <= ASCII_MAX_VALUE);
    }

    private static void padBuffer(final StringBuffer sbuf) {
        while (sbuf.length() < DUMP_BUFFER_SIZE) {
            sbuf.append(" ");
        }
    }

    private static String offsetToHex(int offset) {
        StringBuilder s = new StringBuilder(Integer.toHexString(offset));
        while (s.length() < DUMP_ADDR_LEN) {
            s.insert(0, "0");
        }
        return s.toString();
    }

    /*
     * Parts of this file are
     * Copyright 2006, The jCoderZ.org Project. All rights reserved.
     *
     * Redistribution and use in source and binary forms, with or without
     * modification, are permitted provided that the following conditions are
     * met:
     *
     *    * Redistributions of source code must retain the above copyright
     *      notice, this list of conditions and the following disclaimer.
     *    * Redistributions in binary form must reproduce the above
     *      copyright notice, this list of conditions and the following
     *      disclaimer in the documentation and/or other materials
     *      provided with the distribution.
     *    * Neither the name of the jCoderZ.org Project nor the names of
     *      its contributors may be used to endorse or promote products
     *      derived from this software without specific prior written
     *      permission.
     */
}
