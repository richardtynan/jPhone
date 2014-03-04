package jphone.data;

import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * Read the bits in a space-separated string of 8-bit integers
 */
public class MessageAsBits {

    private ArrayList<Boolean> bitvec = new ArrayList<Boolean>();
    private int pos = 0;

    public MessageAsBits(String message) {

        StringTokenizer tokens = new StringTokenizer(message);

        int num;
        while (tokens.hasMoreTokens()) {
            num = Integer.parseInt(tokens.nextToken());
            for (int j = 7; j >= 0; j--) {
                bitvec.add((num & (1 << j)) != 0);
            }
        }

    }

    public String toString() {
        return bitvec.toString();
    }

    public char getBitHigh() {

        char ret;
        if (bitvec.get(pos) == true && bitvec.get(pos + 1) == true) {
            ret = 'H';
        } else {
            ret = 'L';
        }

        pos += 2;
        return ret;

    }

    public int getUint(int numBits) {

        Boolean bit;
        int ui = 0;
        for (int i = 0; i < numBits; i++) {
            bit = bitvec.get(pos);
            pos++;
            if (bit) {
                ui |= (1 << (numBits - i - 1));
            }
        }

        return ui;

    }

}
