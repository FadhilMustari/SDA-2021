import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.*;

public class TP1 {
    private static InputReader in;
    private static PrintWriter out;

    static Long FindMaxSum(Long[] arr, int n){
        Long inc = arr[0];
        long exc = 0;

        for (int i = 1; i < arr.length; i++){
            Long ninc = exc + arr[i];
            Long nexc = Math.max(inc, exc);

            inc =  ninc;
            exc = nexc;
        }
        Long ans = Math.max(inc, exc);
        return ans;
    }

    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);

        HashMap<String, Integer> jenisKereta = new HashMap<>();
        HashMap<String, Long[]> gerbong = new HashMap<>();
        ArrayList<String> namaKereta = new ArrayList<>();

        int jumlahKereta = in.nextInt();

        while (jumlahKereta > 0){
            String nama = in.next();
            int jenis = in.nextInt();
            int jumlahgerbong = in.nextInt();
            // Masukin nama kereta ke arraylist
            namaKereta.add(nama);
            // Masukin jenis kereta ke hashmap
            jenisKereta.put(nama, jenis);
            // data penumpang tiap gerbong
            Long[] datatemp = new Long[jumlahgerbong];
            for (int i = 0; i < jumlahgerbong; i++){
                int datagerbong = in.nextInt();
                datatemp[i] = (long) datagerbong;
            }
            // Masukin data penumpang tiap gerbong ke hashmap
            gerbong.put(nama, datatemp);
            jumlahKereta--;
        }

        int jumlahPerintah = in.nextInt();
        List<String> jawaban = new ArrayList<>();
        while (jumlahPerintah > 0){
            int perintah = in.nextInt();
            if (perintah == 1){
                String nama = in.next();
                int newjenis = in.nextInt();
                jenisKereta.replace(nama, newjenis);
            } else if(perintah == 2){
                String nama = in.next();
                int indexgerbong = in.nextInt();
                int perubahan = in.nextInt();
                Long[] temp = gerbong.get(nama);
                temp[indexgerbong-1] += perubahan;
                gerbong.replace(nama, temp);
            } else if (perintah == 3){
                int jumlahJenis = 0;
                ArrayList<Integer> temporary = new ArrayList<>();
                for (String nama:namaKereta){
                    if (!(temporary.contains(jenisKereta.get(nama)))){
                        temporary.add(jenisKereta.get(nama));
                        jumlahJenis++;
                    }
                }
                jawaban.add(String.valueOf(jumlahJenis));
            } else if (perintah == 4){
                Long jawabanfinal = 0L;
                String namaKeretaFinal = "";
                for (String nama: namaKereta){
                    Long hasilMax = FindMaxSum(gerbong.get(nama), gerbong.get(nama).length);
                    if (hasilMax > jawabanfinal){
                        jawabanfinal = hasilMax;
                        namaKeretaFinal = nama;
                    }
                }
                jawaban.add(namaKeretaFinal + " " + String.valueOf(jawabanfinal));
            } else{
                continue;
            }
            jumlahPerintah--;
        }
        for (String hasilakhir : jawaban){
            System.out.println(hasilakhir);
        }
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