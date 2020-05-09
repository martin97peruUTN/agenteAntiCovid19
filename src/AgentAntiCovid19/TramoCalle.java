package AgentAntiCovid19;

public class TramoCalle {
    private Node initialNode;
    private Node finalNode;

    public TramoCalle(Node initialNode, Node finalNode) {
        this.initialNode = initialNode;
        this.finalNode = finalNode;
    }

    public TramoCalle() {
    }

    public Node getInitialNode() {
        return initialNode;
    }

    public void setInitialNode(Node initialNode) {
        this.initialNode = initialNode;
    }

    public Node getFinalNode() {
        return finalNode;
    }

    public void setFinalNode(Node finalNode) {
        this.finalNode = finalNode;
    }
}
