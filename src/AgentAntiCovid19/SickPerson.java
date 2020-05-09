package AgentAntiCovid19;


public class SickPerson {
    private String id; //0
    private String actualPosition; //1
    private String homePosition; //2
    private Integer cantMultas;

    public SickPerson(String id, String actualPosition, String homePosition){
        this.id = id;
        this.actualPosition = actualPosition;
        this.homePosition = homePosition;
        this.cantMultas = 0;
    }

    public String getActualPosition() {
        return actualPosition;
    }

    public void setActualPosition(String actualPosition) {
        this.actualPosition = actualPosition;
    }

    public String getHomePosition() {
        return homePosition;
    }

    public void setHomePosition(String homePosition) {
        this.homePosition = homePosition;
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
