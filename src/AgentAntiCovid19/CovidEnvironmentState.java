package AgentAntiCovid19;

import frsf.cidisi.faia.state.EnvironmentState;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class CovidEnvironmentState extends EnvironmentState {
    private HashMap<String, Collection<String>> map;
    private ArrayList<SickPerson> sickPersonsList = new ArrayList<SickPerson>;
    private ArrayList<SickPerson> sickPersonsList = new ArrayList<SickPerson>();
    private String agentPosition="";

    public CovidEnvironmentState() {this.initState();}

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return null;
        //map.clone();
    }

    @Override
    public void initState() {
        CSVToMatrix converter;
        String path = "NODOS-Mapa.csv";
        converter = new CSVToMatrix(';');
        ArrayList<String[]> nodes = converter.fileToMatrix(path);

        map = new HashMap<String, Collection<String>>();

        for(int i=0;i<nodes.size();i++){
            ArrayList<String> succesors = new ArrayList<String>();
            for(int j=0; j<)
        }

    }

    @Override
    public String toString() {
        return null;
    }
}
