package yang.al.ch06;

/**
 * Created by root on 15-6-11.
 */
public class SuffixArray {
    private static final int CUTOFF = 5;

    private final char[] text;
    private final int[] index;//index[i] = j means text.substring(j) is ith largest suffix
    private final int N;

    public SuffixArray(String text) {
        N = text.length();
        text = text + '\0';
        this.text = text.toCharArray();
        this.index = new int[N];
        for (int i = 0; i < N; i++) {
            index[i] = i;
        }
        sort(0, N-1, 0);
    }

    private void sort(int lo, int hi, int d) {
        if (hi <= lo + CUTOFF) {
            insertion(lo, hi, d);
            return;
        }

        int lt = lo, gt = hi;
        char v = text[index[lo] + d];
        int i = lo + 1;
        while (i <= gt) {
            char t = text[index[i] + d];
            if (t < v) exch(lt++, i++);
            else if (t > v) exch(i, gt--);
            else i++;
        }

        sort(lo, lt-1, d);
        if (v > 0) sort(lt, gt, d+1);
        sort(gt+1, hi, d);
    }

    private void insertion(int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(index[j], index[j-1], d) ; j++) {
                exch(j, j-1);
            }
        }
    }

    private boolean less(int i, int j, int d) {
        if (i == j) return false;
        i = i + d;
        j = j + d;
        while (i < N && j < N) {
            if (text[i] < text[j]) return true;
            if (text[i] > text[j]) return false;
            i++;
            j++;
        }
        return i > j;
    }

    private void exch(int i, int j) {
        int swap = index[i];
        index[i] = index[j];
        index[j] = swap;
    }

    public int length() {
        return N;
    }

    public int index(int i) {
        if (i < 0 || i >= N) throw new IndexOutOfBoundsException();
        return index[i];
    }

    public int lcp(int i) {
        if (i < 1 || i >= N) throw new IndexOutOfBoundsException();
        return lcp(index[i], index[i-1]);
    }


    public int lcp(int i, int j) {
        int length = 0;
        while (i < N && j < N) {
            if (text[i] != text[j]) return length;
            i++;
            j++;
            length++;
        }
        return length;
    }

    public String select(int i) {
        if (i < 0 || i >= N) throw new IndexOutOfBoundsException();
        return new String(text, index[i], N - index[i]);
    }

    public int rank(String query) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (lo - hi) / 2;
            int cmp = compare(query, index[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    private int compare(String query, int i) {
        int M = query.length();
        int j = 0;
        while (i < N && j < M) {
            if (query.charAt(j) != text[i]) return query.charAt(j) - text[i];
            i++;
            j++;
        }
        if (i < N) return -1;
        if (j < M) return +1;
        return 0;
    }



}
