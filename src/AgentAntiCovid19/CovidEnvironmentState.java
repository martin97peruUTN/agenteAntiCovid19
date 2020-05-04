package AgentAntiCovid19;

import frsf.cidisi.faia.state.EnvironmentState;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class CovidEnvironmentState extends EnvironmentState {
    private HashMap<String, Collection<String>> map;
    private ArrayList<SickPerson> sickPersonsList = new ArrayList<SickPerson>();
    private ArrayList<Sensor>  sensorsList = new ArrayList<Sensor>();
    private String agentPosition="";

    public CovidEnvironmentState() {this.initState();}

    @Override
    public Object clone() {
       return map.clone();
    }

    @Override
    public void initState() {
        CSVToMatrix converter;
        String path = "NODOS-Mapa.csv";
        converter = new CSVToMatrix(';');
        ArrayList<String[]> nodes = converter.fileToMatrix(path);
        path = "NODOS-Sucesores.csv";
        ArrayList<String[]>  nodesSuccesors = converter.fileToMatrix(path);

        map = new HashMap<String, Collection<String>>();

        /**
         * Inicializar mapa con los nodos del archivo NODOS-Mapa.csv
         */
        for(int i=0;i<nodes.size();i++){
            ArrayList<String> succesors = new ArrayList<String>();
            for(int j=0;j<nodesSuccesors.size();j++){
                if(nodesSuccesors.get(j)[1]!="null") {
                    succesors.add(nodesSuccesors.get(j)[1]);
                }
                if(nodesSuccesors.get(j)[2]!="null") {
                    succesors.add(nodesSuccesors.get(j)[2]);
                }
                if(nodesSuccesors.get(j)[3]!="null") {
                    succesors.add(nodesSuccesors.get(j)[3]);
                }
                if(nodesSuccesors.get(j)[4]!="null") {
                    succesors.add(nodesSuccesors.get(j)[4]);
                }
            }
            map.put(nodes.get(i)[0], succesors);
        }

        /**
         * Inicializar
         */

    }

    @Override
    public String toString() {
        return null;
    }
}
