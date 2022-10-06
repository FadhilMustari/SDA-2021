import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

class LinkedList{
    Node head,tail;

    class Node{
        String data;
        Node next;
        Node prev;
    
        Node(String givenData){
            this.data = givenData;
        }
    }

    public void append(String data1){
        Node newNode = new Node(data1);
        if (head == null){
            head = tail = newNode;
            head.prev = null;
            tail.next = null;  
            return;
        }
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        tail.next = null;
    }
    
    public void prepend(String data){
        Node newHead = new Node(data);
        newHead.next = head;
        head.prev = newHead;
        head = newHead;
    }

    public void printList(){
        Node n = head;
        while (n != null){
            System.out.print(n.data + " ");
            n = n.next;
        }
    }
    public String cariEvolusi(String nama, int indeks) {
        Node current = head;
        while (!(current.data.equals(nama))){
            current = current.next;
        }
        Node counter = current;
        for (int i = 0; i < indeks; i++){
            counter = current.next;
        }
        return counter.data;
    }

    public String deletion(String data){
        Node current = head;
        while (!(current.data.equals(data))){
            current = current.next;
        }
        Node counter = current;
        if (head == null || counter == null){
            return "";
        }

        if (head == counter){
            head = counter.next;
        }

        if (!counter.next.equals(null)) {
            counter.next.prev = counter.prev;
        }

        if(!counter.prev.equals(null)){
            counter.prev.next = counter.next;
        }
        return "BERHASIL";
    }

    public String insertion(String data, String newNode){
        Node current = head;
        while (!(current.data.equals(data))){
            current = current.next;
        }
        Node oldNode = current;
        Node new_Node = new Node(newNode);
        new_Node.next = oldNode.next;
        oldNode.next = new_Node;
        new_Node.prev = oldNode;
        if (new_Node.next != null){
            new_Node.next.prev = new_Node;
        }
        return "BERHASIL";
    }
}

public class Lab4 {
    private static InputReader in;
    private static PrintWriter out;
    
    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);

        ArrayList<String> name = new ArrayList<>();
        LinkedList list = new LinkedList();
        
        int N = in.nextInt();

        for (int i = 0; i < N; i++) {
            String prev = in.next();
            String next = in.next();
            
            // TODO: Complete this or create a new function to return the right answer
            if (name.contains(next)){
                if (!name.contains(prev))
                    list.prepend(prev);
                    name.add(prev);
            } else{
                if (name.contains(prev)){
                    list.append(next);
                    name.add(next);
                } else{
                    list.append(prev);
                    list.append(next);
                    name.add(prev);
                    name.add(next);
                }
            }
        }

        list.printList();

        ArrayList<String> ans = new ArrayList<>();
        int Q = in.nextInt();
        for (int i = 0; i < Q; i++) {
            int query = in.nextInt();
            if (query == 1) {
                String X = in.next();
                int K = in.nextInt();

                // TODO: Complete this or create a new function to return the right answer
                ans.add((list.cariEvolusi(X, K)));

            } else if (query == 2) {
                String X = in.next();

                if (!(name.contains(X))){
                    ans.add("GAGAL");
                } else {
                    ans.add(list.deletion(X));
                }
                
                // TODO: Complete this or create a new function to return the right answer

            } else if (query == 3) {
                String X = in.next();
                String Y = in.next();

                if (!name.contains(Y)){
                    ans.add("GAGAL");
                } else {
                    ans.add(list.insertion(Y, X));
                }
                
                // TODO: Complete this or create a new function to return the right answer

            }
        }
        for (String jawaban : ans){
            System.out.println(jawaban);
        }

        out.close();
    }

    // taken from https://codeforces.com/submissions/Petr
    // together with PrintWriter, these input-output (IO) is much faster than the
    // usual Scanner(System.in) and System.out
    // please use these classes to avoid your fast algorithm gets Time Limit
    // Exceeded caused by slow input-output (IO)
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


