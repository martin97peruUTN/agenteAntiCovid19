package AgentAntiCovid19;

/**
 * @author Leandro
 */

public class Node {

    private String id;
    private String streets;
    private double x, y;

    public Node(String id, String streets, String x, String y){
        this.id = id;
        this.streets = streets;
        this.x = Double.valueOf(x);
        this.y = Double.valueOf(y);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStreets() {
        return streets;
    }

    public void setStreets(String streets) {
        this.streets = streets;
    }

    public double getXPosition() {
        return x;
    }

    public void setXPosition(double x) {
        this.x = x;
    }

    public double getYPosition() {
        return y;
    }

    public void setYPosition(double y) {
        this.y = y;
    }



}