package AgentAntiCovid19;

public class Sensor {
    private String id;
    private double posX;
    private double posY;
    private boolean hasSickPerson;

    public Sensor (String id, String y, String x, String hasSickPerson){
        this.id = id;
        this.posX = Double.valueOf(x);
        this.posY = Double.valueOf(y);
        this.hasSickPerson = Boolean.valueOf(hasSickPerson);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public boolean isHasSickPerson() {
        return hasSickPerson;
    }

    public void setHasSickPerson(boolean hasSickPerson) {
        this.hasSickPerson = hasSickPerson;
    }
}
