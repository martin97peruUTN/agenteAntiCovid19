package AgentAntiCovid19;


public class SickPerson {
    private Integer id; //0
    private String actualPosition; //1
    private String homePosition; //2
    private Integer cantMultas; //3

    public SickPerson(Integer id, String actualPosition, String homePosition, String cantMultas){
        this.id = id;
        this.actualPosition = actualPosition;
        this.homePosition = homePosition;
        this.cantMultas = Integer.valueOf(cantMultas);
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantMultas() {
        return cantMultas;
    }

    public void setCantMultas(Integer cantMultas) {
        this.cantMultas = cantMultas;
    }

    @Override
    public String toString() {
        return "ID: "+id+"\n"+"Posicion actual: "+actualPosition+"\n"+"Posicion de su casa: "+homePosition+"\n";
    }
}
