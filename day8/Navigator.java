import java.util.HashMap;
import java.util.Vector;

public class Navigator {
    String path;
    HashMap<String, Node> forks = new HashMap<>();

    Navigator(HashMap<String, Node> forks, String path){
        this.forks = forks;
        this.path = path;
    }

    private Node getNode(String target){
        return forks.get(target);
    }

    public int length(){
        Node current = getNode("AAA");
        int pathLength=0;
        for(int i = 0; i < path.length(); i=(i+1)%path.length()){
            if(current.getId().equals("ZZZ")) return pathLength;
            if(path.charAt(i)=='R'){
                current=getNode(current.getRight());
            }
            else current=getNode(current.getLeft());
            pathLength+=1;
        }
        return pathLength;
    }

    private int length2(Node current){
        int pathLength=0;
        for(int i = 0; i < path.length(); i=(i+1)%path.length()){
            if(current.getId().charAt(2)=='Z') return pathLength;
            if(path.charAt(i)=='R'){
                current=getNode(current.getRight());
            }
            else current=getNode(current.getLeft());
            pathLength+=1;
        }
        return pathLength;
    }
    public long part2(){
        Vector<Integer> lengths = new Vector<>();
        // add all nodes ending in A to tracked list
        for(String id : forks.keySet()){
            Node node = forks.get(id);
            if(node.getId().charAt(2)=='A'){
                lengths.add(length2(node));
            }
        }
        return lcm(lengths);
    }

    public long lcm(Vector<Integer> x){
        long lcm = 1;
        int divisor=2;
        int xSum=-1;
        while(xSum!=x.size()) {
            boolean isDivisible = false;
            for (int i = 0; i != x.size(); i++) {
                if(x.get(i)%divisor==0){
                    isDivisible=true;
                    x.set(i, x.get(i)/divisor);
                }
            }


            if(!isDivisible) {
                divisor = nextPrime(divisor);
            } else {
                lcm*=divisor;
            }

            xSum=0;
            for(int value : x){
                xSum+=value;
            }
        }
        return lcm;
    }

    private boolean isPrime(int n)
    {
        if (n <= 3)  return true;
        // This is checked so that we can skip
        // middle five numbers in below loop
        if (n%2 == 0 || n%3 == 0) return false;

        for (int i=5; i*i<=n; i=i+6)
            if (n%i == 0 || n%(i+2) == 0)
                return false;
        return true;
    }

    private int nextPrime(int N)
    {
        int prime = N;
        boolean found = false;
        while (!found) {
            prime++;
            if (isPrime(prime))
                found = true;
        }

        return prime;
    }
}
