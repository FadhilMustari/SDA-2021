
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

class treeNode{
    int key,height;
    treeNode left,right;

    treeNode(int value){
        this.key = value;
        left = null;
        right = null;
    }
    int getValue(){
        return key;
    }
}

class Node{  
    String nama;
    int kode;
    int tujuan;
    Node previous;  
    Node next;  

    public Node(String nama, int kode, int tujuan) {  
        this.nama = nama;  
        this.kode = kode;
        this.tujuan = tujuan;
        previous = null;
        next = null;
    }

    String getNama(){
        return nama;
    }
    
    int getKode(){
        return kode;
    }

    int getTujuan(){
        return tujuan;
    }
}

public class TP2 {
    private static InputReader in;
    private static PrintWriter out;

    treeNode root;
    Node head,tail = null;
    int size;  
    String hasil;

    public static void searchNode(treeNode root, int value, treeNode left, treeNode right){
        if (root == null){
            return;
        } else if (root.getValue() == value){
            root.left = left;
            root.right = right;
            return;
        } else {
            searchNode(root.left, value, left, right);
            searchNode(root.right, value, left, right);
        }
    }

    //addNode() will add a node to the list  
    public void addNode(String nama, int kode, int tujuan) {  
        Node current;
        //Create a new node  
        Node newNode = new Node(nama, kode, tujuan);  
  
        //If list is empty  
        if(head == null) {  
            head = newNode;
            tail = newNode;
            head.previous = null;
            tail.next = null;
        } else if (head.getKode() > newNode.getKode()) {
            newNode.next = head;
            newNode.previous = null;

            if (head != null){
                head.previous = newNode;
            }
            head = newNode;
        } else if (tail.kode <= newNode.kode){
            newNode.previous = tail;
            tail.next = newNode;
            tail = newNode;
            tail.next = null;
        } else if (head.getKode() <= newNode.getKode()){
            current = head;

            if (current.next == null){
                newNode.previous = current;
                current.next = newNode;
            } 
            while (current.next.kode < newNode.kode){
                current = current.next;
            }
            current.next.previous = newNode;
            newNode.next = current.next;
            newNode.previous = current;
            current.next = newNode;
        } 
        size++;
    }  

    public String hapusNode(String nama){
        Node current = head;
        // Hapus Head Node
        if (head.getNama().equals(nama)){
            head = head.next;
            head.previous = null;
            hasil = "BERHASIL";
        } else if(tail.getNama().equals(nama)){
            tail = tail.previous;
            tail.next = null;
            hasil = "BERHASIL";
        } else {
            int counter = size-2;
            boolean count = false;
            for (int i = 0; i < counter; i++){
                if (current.next.getNama().equals(nama)){
                    current.next.next.previous = current;
                    current.next = current.next.next;
                    count = true;
                    hasil = "BERHASIL";
                    break;
                } else {
                    current = current.next;
                }
            }
            if (count == false){
                hasil = "GAGAL";
            }
        }
        return hasil;

    }
    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);

        treeNode root = new treeNode(1);
        TP2 dList = new TP2();  
        List<String> jawaban = new ArrayList<>();

   
        int jumlahpulau = in.nextInt();
        for (int i = 0; i < jumlahpulau; i++){
            int left = in.nextInt();
            int right = in.nextInt();
            if (left < 1){
                treeNode rightnode = new treeNode(right);
                searchNode(root, i+1, null, rightnode);
            } else if (right < 1){
                treeNode leftnode = new treeNode(left);
                searchNode(root, i+1, leftnode, null);
            } else if (left < 1 && right < 1){
                continue;
            } else {
                treeNode leftnode = new treeNode(left);
                treeNode rightnode = new treeNode(right);
                searchNode(root, i+1, leftnode, rightnode); 
            }
        }

        int jumlahquery = in.nextInt();
        for (int i = 0; i < jumlahquery; i++){
            int query = in.nextInt();
            if (query == 1){
                String nama = in.next();
                int kode = in.nextInt();
                int tujuan = in.nextInt();
                dList.addNode(nama, kode, tujuan);;
            } else if (query == 2){
                String nama = in.next();
                jawaban.add(dList.hapusNode(nama));
            } else if (query == 3){
                
            }
        }

        for (String hasilakhir : jawaban){
            out.println(hasilakhir);
        }

        // don't forget to close/flush the output
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