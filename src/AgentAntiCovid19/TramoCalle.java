package AgentAntiCovid19;

import com.sun.javafx.binding.StringConstant;

public class TramoCalle {
    private String initialNode;
    private String finalNode;

    public TramoCalle(String initialNode, String finalNode) {
        this.initialNode = initialNode;
        this.finalNode = finalNode;
    }

    public TramoCalle() {
    }

    public String getInitialNode() {
        return initialNode;
    }

    public void setInitialNode(String initialNode) {
        this.initialNode = initialNode;
    }

    public String getFinalNode() {
        return finalNode;
    }

    public void setFinalNode(String finalNode) {
        this.finalNode = finalNode;
    }

    @Override
    public String toString() {
        return "El corte va de la esquina del nodo "+initialNode+" y la esquina del nodo "+finalNode+")\n";
    }
}
