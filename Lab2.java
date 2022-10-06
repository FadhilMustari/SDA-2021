// import java.io.IOException;
// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.io.InputStream;
// import java.io.OutputStream;
// import java.io.PrintWriter;
// import java.util.*;

// public class Lab2 {
//     private static InputReader in;
//     private static PrintWriter out;

//     static void getOutput(String S) {
//         // TODO: Complete this function to return the right answer
//         // Stack<Character> ans = new Stack<>();
//         LinkedList<Character> temp = new LinkedList<Character>();
//         for (int i = 0; i < S.length(); i++){
//             if (S.charAt(i) == ('<')){
//                 temp.addLast(S.charAt(i));
//             } else if (S.charAt(i) == ('>')){
//                 temp.addFirst(S.charAt(i));
//             } else if (S.charAt(i) == ('-')){
//                 temp.pop();
//             } else {
//                 temp.push(S.charAt(i));
//             }
//             // for (int x = 0; x < temp.size();x++){
//             //     out.print(temp.get(i));
//             // }
//         }
//         for (int i = 0; i < temp.size();i++){
//             out.print(temp.get(i));
//         }
//     }

//     public static void main(String[] args) throws IOException {
//         InputStream inputStream = System.in;
//         in = new InputReader(inputStream);
//         OutputStream outputStream = System.out;
//         out = new PrintWriter(outputStream);

//         String S = in.next();

//         getOutput(S);

//         out.close();
//     }

//     // taken from https://codeforces.com/submissions/Petr
//     // together with PrintWriter, these input-output (IO) is much faster than the usual Scanner(System.in) and System.out
//     // please use these classes to avoid your fast algorithm gets Time Limit Exceeded caused by slow input-output (IO)
//     static class InputReader {
//         public BufferedReader reader;
//         public StringTokenizer tokenizer;

//         public InputReader(InputStream stream) {
//             reader = new BufferedReader(new InputStreamReader(stream), 32768);
//             tokenizer = null;
//         }

//         public String next() {
//             while (tokenizer == null || !tokenizer.hasMoreTokens()) {
//                 try {
//                     tokenizer = new StringTokenizer(reader.readLine());
//                 } catch (IOException e) {
//                     throw new RuntimeException(e);
//                 }
//             }
//             return tokenizer.nextToken();
//         }

//         public int nextInt() {
//             return Integer.parseInt(next());
//         }

//     }
// }