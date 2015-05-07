package yang.al.ch03;


import yang.al.stdlib.In;
import yang.al.stdlib.StdIn;
import yang.al.stdlib.StdOut;

public class FrequencyCounter {
    public static void main(String[] args) {
        int minlen = Integer.parseInt(args[0]);
        SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
        In inputfile = new In("/home/yang/tale.txt");
        while (!inputfile.isEmpty()){
            String word = inputfile.readString();
//            StdOut.println(word);
            if (word.length() < minlen) continue;
            if (!st.contains(word))
                st.put(word, 1);
            else
                st.put(word, st.get(word) + 1);
        }

        String max = " ";
        st.put(max, 0);
        for (String word: st.keys())
            if (st.get(word) > st.get(max))
                max = word;

        StdOut.println(max + " " + st.get(max));
    }
}
