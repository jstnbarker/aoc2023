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

    public int part2(){
        Vector<Node> positions = new Vector<>();
        // add all nodes ending in A to tracked list
        for(String id : forks.keySet()){
            Node node = forks.get(id);
            if(node.getId().charAt(2)=='A'){
                positions.add(new Node(node));
            }
        }

        int length = 0;
        for(int i = 0; i < path.length(); i=(i+1)%path.length()) {
            if (path.charAt(i) == 'R') {
                // update each node in positions with their right
                for (int j = 0; j < positions.size(); j++) {
                    positions.set(j, getNode(positions.get(j).getRight()));
                }
            } else {
                // update each node in positions with their left
                for (int j = 0; j < positions.size(); j++) {
                    positions.set(j, getNode(positions.get(j).getLeft()));
                }
            }
            length+=1;
            // check last character of each node in positions
            boolean areAllZ = true;
            for(int j = 0; j < positions.size(); j++) {
                if (positions.get(j).getId().charAt(2) != 'Z') {
                    areAllZ=false;
                    break;
                }
            }
            if(areAllZ) return length;
        }
        return 0;
    }
}
