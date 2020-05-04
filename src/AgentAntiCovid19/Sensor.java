package AgentAntiCovid19;

public class Sensor {
    private double posX;
    private double posY;
    private boolean hasSickPerson;

    public Sensor (String x, String y){
        this.posX = Double.valueOf(x);
        this.posY = Double.valueOf(y);
        this.hasSickPerson = false;
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
