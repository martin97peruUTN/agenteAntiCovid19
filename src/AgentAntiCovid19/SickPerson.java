package AgentAntiCovid19;

/**
 * @author Leandro
 */

public class SickPerson {
    private String id;
    private double actualPosX;
    private double actualPosY;
    private double homePosX;
    private double homePosY;
    private Integer cantMultas;

    public SickPerson(String id, String actualPosY, String actualPosX, String homePosY, String homePosX){
        this.id = id;
        this.actualPosX = Double.valueOf(actualPosX);
        this.actualPosY = Double.valueOf(actualPosY);
        this.homePosX = Double.valueOf(homePosX);
        this.homePosY = Double.valueOf(homePosY);
        this.cantMultas = 0;
    }

    public double getActualPosX() {
        return actualPosX;
    }

    public void setActualPosX(double actualPosX) {
        this.actualPosX = actualPosX;
    }

    public double getActualPosY() {
        return actualPosY;
    }

    public void setActualPosY(double actualPosY) {
        this.actualPosY = actualPosY;
    }

    public double getHomePosX() {
        return homePosX;
    }

    public void setHomePosX(double homePosX) {
        this.homePosX = homePosX;
    }

    public double getHomePosY() {
        return homePosY;
    }

    public void setHomePosY(double homePosY) {
        this.homePosY = homePosY;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCantMultas() {
        return cantMultas;
    }

    public void setCantMultas(Integer cantMultas) {
        this.cantMultas = cantMultas;
    }

}
