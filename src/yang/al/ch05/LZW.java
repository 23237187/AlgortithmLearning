package yang.al.ch05;

import yang.al.stdlib.BinaryStdIn;
import yang.al.stdlib.BinaryStdOut;

/**
 * Created by root on 15-6-10.
 */
public class LZW {
    private static final int R = 256;
    private static final int L = 4096;
    private static final int W = 12;

    public static void compress() {
        String input = BinaryStdIn.readString();
        TST<Integer> st = new TST<Integer>();
        for (int i = 0; i < R; i++) {
            st.put("" + (char) i, i);
        }
        int code = R + 1;

        while (input.length() > 0) {
            String s = st.longestPrefixOf(input);
            BinaryStdOut.write(st.get(s), W);
            int t = s.length();
            if (t < input.length() && code < L)
                st.put(input.substring(0, t+1), code++);
            input = input.substring(t);
        }

        BinaryStdOut.write(R, W);
        BinaryStdOut.close();
    }

    public static void expand() {
        String[] st = new String[L];
        int i;

        for (i = 0; i < R; i++) {
            st[i] = "" + (char) i;
        }
        st[i++] = "";

        int codeword = BinaryStdIn.readInt(W);
        if (codeword == R) return;
        String val = st[codeword];
        while (true) { //val 上一条已解码出的字符串
            BinaryStdOut.write(val);
            codeword = BinaryStdIn.readInt(W);
            if (codeword == R) break;
            String s = st[codeword];//如果为空 属于特例 用特例修正
            if (i == codeword) s = val + val.charAt(0);//特例修正
            if (i < L) st[i++] = val + s.charAt(0);//由于编码过程每轮一定会加入s+c的新编码项，所以这条语句一定有意义
            val = s;
        }

        BinaryStdOut.close();
    }
}
