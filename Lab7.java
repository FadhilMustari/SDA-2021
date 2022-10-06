import java.io.*;
import java.util.*;

public class Lab7 {
    private static InputReader in;
    private static PrintWriter out;

    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);
        
        int n;
        int m;
        int k;
        int[] u;
        int[] v;
        int[] s;

        n = in.nextInt();
        m = in.nextInt();
        k = in.nextInt();

        u = new int[m + 1];
        v = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            u[i] = in.nextInt();
            v[i] = in.nextInt();
        }

        s = new int[k + 1];
        for (int j = 1; j <= k; j++) {
            s[j] = in.nextInt();
        }

        // TODO: Hitung dan cetak jawaban yang diminta
        //       sesuai deskripsi soal

        out.close();
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;
 
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }
 
        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
 
        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}   