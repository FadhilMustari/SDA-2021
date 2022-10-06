import java.io.*;
import java.util.*;

public class Lab5 {
	private static InputReader in = new InputReader(System.in);
	private static PrintWriter out = new PrintWriter(System.out);

	public static void main(String[] args) {
		int p;
		int q;
		int[] h = new int[100005];
		int queryType;
		int x;
		int y;
		int z;
		AVL candyShop = new AVL();
		// TODO: Tambahkan atribut lain yang diperlukan (jika ada)

		p = in.nextInt();
		q = in.nextInt();
		for (int i = 1; i <= p; i++) {
			h[i] = in.nextInt();
			candyShop.root = candyShop.insert(h[i], candyShop.root);
		}
		for (int i = 0; i < q; i++) {
			queryType = in.nextInt();
			if (queryType == 1) {
				x = in.nextInt();
				y = in.nextInt();
				candyShop.root = candyShop.updatePrice(h[x], y, candyShop.root);
				h[x] = y;
			}
			else {
				z = in.nextInt();
                int count = 0;
                for (int a = 1; a <= p; a++){
                    if (h[a] <= z){
                        count += 1;
                    }
                }
				out.println(count);
			}
		}

		out.close();
	}

	static class AVL {
		Candy root;
		// TODO: Tambahkan atribut lain yang diperlukan (jika ada)

		public AVL() {
			this.root = null;
		}

		public Candy insert(int price, Candy current) {
			// TODO: Implementasi method insert

            if (current == null){
                return (new Candy(price));
            }

            if (price < current.price){
                current.left = insert(price, current.left);
            } else if (price > current.price){
                current.right = insert(price, current.right);
            } else {
                return current;
            }

            current.height = 1 + max(height(current.left), height(current.right));

            int balance = getBalance(current);

            //jika terjadi unbalanced, terdapat 4 kondisi sebagai berikut:
            //1. Left Left Case
            if (balance > 1 && price < current.left.price){
                return rightRotate(current);
            }
            //2. Right Right Case
            if (balance < -1 && price > current.right.price){
                return leftRotate(current);
            }
            //3. Left Right Case
            if (balance > 1 && price > current.left.price){
                current.left = leftRotate(current.left);
                return rightRotate(current);
            }
            //4. Right Left Case
            if (balance < -1 && price < current.right.price){
                current.right = rightRotate(current.right);
                return rightRotate(current);
            }

			return current;
		}

		public Candy deleteCandy(int price, Candy current){
            if (current == null){
                return current;
            }

            if (price < current.price){
                current.left = deleteCandy(price, current.left);
            } else if (price > current.price){
                current.right = deleteCandy(price, current.right);
            } else {
                if (current.left == null){
                    Candy temp = current.right;
                    return temp;
                } else if (current.right == null){
                    Candy temp = current.left;
                    return temp;
                }

                Candy temp = minValueCandy(current.right);

                current.price = temp.price;

                current.right = deleteCandy(temp.price, current.right);
            }
            return current;
        }

        public Candy updatePrice(int before, int after, Candy current){
			// TODO: Implementasi method update harga
            current = deleteCandy(before, current);

            current = insert(after, current);

			return current;
		}

		private Candy rightRotate(Candy y) { 
			// TODO: implement right rotation
            Candy x = y.left;
            Candy T3 = x.right;
            //rotasi x naik, sebelah kanannya y. T3 nempel ke kirinya y
            x.right = y;
            y.left = T3;
            // memperbarui tinggi
            y.height = max(height(y.left), height(y.right)) + 1;
            x.height = max(height(x.left), height(x.right)) + 1;
	
			return x;
		} 
	  
		private Candy leftRotate(Candy x) { 
			// TODO: implement left rotation
			Candy y = x.right;
            Candy T2 = y.left;
            //rotasi y naik, sebelah kirinya x, T2 nempel ke kanan x
            y.left = x;
            x.right = T2;
            //memperbarui tinggi
            x.height = max(height(x.left), height(x.right)) + 1;			
            y.height = max(height(y.left), height(y.right)) + 1;
            return y;
		}

		public int getNumCandyMaxPrice(int maxPrice) {
			return this.root.getNumCandyMaxPrice(maxPrice);
		}

        public Candy minValueCandy(Candy candy){
            Candy current = candy;

            while (current.left != null){
                current = current.left;
            }
            return current;
        }
	
		// Utility function to get height of node
		private int height(Candy n) { 
			return n == null ? 0 : n.height;
		}
		
		// Utility function to get max between two values
		private int max(int a, int b) { 
			return (a > b) ? a : b; 
		} 
	
		// Utility function to get balance factor of node
		private int getBalance(Candy N) { 
			if (N == null) 
				return 0; 
	  
			return height(N.left) - height(N.right); 
		}

		//TODO: Tambahkan method lain yang diperlukan (jika ada)
	}

	static class Candy {
		Candy left;
		Candy right;
		int price;
		int subTreeSize;
		int height;
		// TODO: Tambahkan atribut lain yang diperlukan (jika ada)

		public Candy(int price) {
			this.left = null;
			this.right = null;
			this.price = price;
			this.height = 0;
			this.subTreeSize = 1;
		}

		public int getNumCandyMaxPrice(int maxPrice) {
			// TODO: get count of candies with at most price max recursively
            
			return subTreeSize;
		}

		public int getSubTreeSize(){
			return this.subTreeSize;
		}

		public int getHeight(){
			return this.height;
		}
	
		public void setHeight(int height){
			this.height = height;
		}
	
		public void setSubTreeSize(int count){
			this.subTreeSize = count;
		}

		// TODO: Tambahkan method lain yang diperlukan (jika ada)
	}

	// taken from https://codeforces.com/submissions/Petr
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