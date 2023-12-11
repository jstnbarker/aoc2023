public class Node {
    private String id;
    private String left;
    private String right;

    public Node(String id, String left, String right) {
        this.id=id;
        this.left=left;
        this.right=right;
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
