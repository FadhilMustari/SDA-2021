import java.util.LinkedList;
import java.util.List;

public class Main{
    public static void main(String[] args) {
        
    }
}

class Edge{
    public Vertex dest;
    public double cost;
    
    public Edge( Vertex d, double c){
        dest = d;
        cost = c;
    }
}

class Vertex{
    public int key;
    public List<Edge> adj; //adj vertex
    public double dist; // cost vertexnya
    public Vertex prev; //prev vertex
    public int scratch;
    
    public Vertex(int Key){
        this.key = Key;
        adj = new LinkedList<Edge>();
    }
}

class Path implements Comparable<Path> {
    public Vertex dest;
    public double cost;

    public Path(Vertex d, double c){
        dest = d;
        cost = c;
    }

    @Override
    public int compareTo(Path rhs) {
        // TODO Auto-generated method stub
        double otherCost = rhs.cost;
        return cost < otherCost ? -1 : cost > otherCost ? 1 : 0;
    }

    
}