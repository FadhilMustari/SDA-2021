import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

public class Lab1 {
    private static InputReader in;
    private static PrintWriter out;

    static int[] getAnswer(int N, int[] A) {
        int[] ret = new int[N];
        int counter = 0;
        // TODO: Complete this function to return the right answer
        for (int i = 0; i < N; i++){
            int count = 0;
            for (int x = 0; x < A.length; x++){
                int sum = 0;
                for (int y = x; y < A.length; y++){
                    sum += A[y];
                    if (sum > ret[counter]){
                        ret[counter] = sum;
                    }
                }
            }
            /**
             * Algoritma rotasi array yang saya gunakan terinspirasi dari sumber yang saya dapatkan di google
             * dengan link sebagai berikut https://www.javatpoint.com/java-program-to-right-rotate-the-elements-of-an-array
             */
            int j, first;
            first = A[0];
            for (j = 0; j < A.length - 1; j++){
                A[j] = A[j+1];
            }
            A[j] = first;
            counter++;
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);

        int N = in.nextInt();
        int[] A = new int[N];

        for(int i = 0; i < N; i++) {
            A[i] = in.nextInt();
        }

        int[] ans = getAnswer(N, A);
        for(int i = 0; i < N; i++) {
            out.print(ans[i]);
            if (i == N - 1) out.print("\n");
            else out.print(" ");
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