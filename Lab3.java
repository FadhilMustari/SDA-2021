import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;
import java.lang.Math;
import java.lang.reflect.Array;

public class Lab3 {
    private static InputReader in;
    private static PrintWriter out;

    public static int[] data1;
    public static int[] data2;

    public static int pole(int x, int y, int[][] memo){
        if (!(memo[x][y] == 0)){
            return memo[x][y];
        } else if ((x == 0) || (y == 0)){
            return 0;
        } else if (data1[x] == data2[y]){
            memo[x][y] = 1 + pole(x-1, y-1, memo);
            return memo[x][y];
        } else {
            return Math.max(pole(x-1, y, memo), pole(x, y-1, memo));
        } 
    }

    public static int getOutput(int[] A, int[] B, int M, int N){
        // TODO: Complete this function to return the right answer
        data1 = A;
        data2 = B;

        int[][] data = new int[A.length+1][B.length+1];
        for (int x = 0; x < A.length; x++){
            for (int y = 0; y < B.length; y++){
                data[x][y] = 0;
                data[0][y] = 0;
                data[x][0] = 0;
            }
        }

        for (int x = 0; x < data.length; x++){
            for (int y = 0; y < data[x].length; y++){
                System.out.print(data[x][y] + " ");
            }
            System.out.println();
        }

        int result = pole(M-1, N-1, data) + 1;
        return result;
    }



    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);
        
        int M = in.nextInt();
        int N = in.nextInt();

        int[] A = new int[M];
        int[] B = new int[N];

        for (int i = 0; i < M; i++) {
            A[i] = in.nextInt();
        }
        for (int i = 0; i < N; i++) {
            B[i] = in.nextInt();
        }
        
        out.print(getOutput(A, B, M, N));
        
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