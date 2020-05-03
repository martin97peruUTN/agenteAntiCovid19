package AgentAntiCovid19;

import frsf.cidisi.faia.state.EnvironmentState;

import java.util.ArrayList;

public class CovidEnvironmentState extends EnvironmentState {
    private ArrayList<SickPerson> sickPersonsList = new ArrayList<SickPerson>();
    private String agentPosition="";

    public CovidEnvironmentState() {this.initState();}

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return null;
        //return map.clone();
    }

    @Override
    public void initState() {
        CSVToMatrix converter;
        String path = "NODOS-Mapa.csv";
        converter = new CSVToMatrix(';');
        ArrayList<String[]> nodes = converter.fileToMatrix(path);
    }

    @Override
    public String toString() {
        return null;
    }
}
