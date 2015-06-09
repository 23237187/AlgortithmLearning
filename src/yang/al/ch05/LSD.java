package yang.al.ch05;

/**
 * Created by root on 15-6-9.
 */
public class LSD {
    private final static int BITS_PER_BYTE = 8;

    public static void sort(String[] a, int W) {
        int N = a.length;
        int R = 256;
        String[] aux = new String[N];//N

        for (int d = W-1; d >= 0; d--) {
            int[] count = new int[R+1];//R+1
            for (int i = 0; i < N; i++) {
                count[a[i].charAt(d) + 1]++;//4N
            }
            for (int r = 0; r < R; r++) {
                count[r+1] += count[r];//3R
            }
            for (int i = 0; i < N; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];//5N
            }
            for (int i = 0; i < N; i++) {
                a[i] = aux[i];//2N
            }
        }
    }
}
