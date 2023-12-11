public class Node {
    private String id;
    private String left;
    private String right;

    public Node(String id, String left, String right) {
        this.id=id;
        this.left=left;
        this.right=right;
    }

    public Node(Node node){
        this.id=node.getId();
        this.left=node.getLeft();
        this.right=node.getRight();
    }

    public String getLeft() {
        return left;
    }

    public String getRight() {
        return right;
    }

    public String getId() {
        return id;
    }
}
