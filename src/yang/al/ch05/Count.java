package yang.al.ch05;

import yang.al.stdlib.StdIn;
import yang.al.stdlib.StdOut;

/**
 * Created by root on 15-6-9.
 */
public class Count {
    public static void main(String[] args) {
        Alphabet alpha = new Alphabet(args[0]);
        int R = alpha.R();
        int[] count = new int[R];
        String a = StdIn.readAll();
        int N = a.length();
        for (int i = 0; i < N; i++) {
            if (alpha.contains(a.charAt(i))) {
                count[alpha.toIndex(a.charAt(i))]++;
            }
        }
        for (int c = 0; c < R; c++) {
            StdOut.println(alpha.toChar(c) + " " + count[c]);
        }
    }
}
