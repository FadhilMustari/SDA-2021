import java.io.*;
import java.util.StringTokenizer;
import java.util.Vector;

public class Lab6 {
    private static InputReader in;
    private static PrintWriter out;
    //TODO: Tambah fungsi atau class baru (jika diperlukan)

    protected Vector data;

    static void tambahLaptop(String N, int H, int K) {
        //TODO: Lengkapi method ini 
    }

    static void beliLaptop(int A) {
        //TODO: Lengkapi method ini 
    }

    public static void main(String args[]) throws IOException {
        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);

        int Q = in.nextInt();
        for (int i = 0; i < Q; i++) {
            int query = in.nextInt();
            if (query == 1) {
                tambahLaptop(in.next(), in.nextInt(), in.nextInt());
            } else {
                beliLaptop(in.nextInt());
            }
        }

        out.close();
    }

    // taken from https://codeforces.com/submissions/Petr
    // together with PrintWriter, these input-output (IO) is much faster than the usual Scanner(System.in) and System.out
    // please use these classes to avoid your fast algorithm gets Time Limit Exceeded caused by slow input-output (IO)
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