package yang.al.ch06;

import yang.al.stdlib.StdIn;
import yang.al.stdlib.StdOut;

/**
 * Created by root on 15-6-11.
 */
public class LRS {
    public static void main(String[] args) {
        String text = StdIn.readAll().replaceAll("\\s+", " ");
        SuffixArray sa = new SuffixArray(text);

        int N = sa.length();

        String lrs = "";
        for (int i = 1; i < N; i++) {
            int length = sa.lcp(i);
            if (length > lrs.length()) {
                lrs = text.substring(sa.index(i), sa.index(i) + length);
            }
        }

        StdOut.println("'" + lrs + "'");
    }
}
