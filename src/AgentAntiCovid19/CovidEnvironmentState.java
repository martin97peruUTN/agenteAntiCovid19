package AgentAntiCovid19;

import frsf.cidisi.faia.state.EnvironmentState;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class CovidEnvironmentState extends EnvironmentState {
    private HashMap<String, Collection<String>> map;
    private ArrayList<SickPerson> sickPersonsList = new ArrayList<SickPerson>();
    private ArrayList<Sensor>  sensorsList = new ArrayList<Sensor>();
    private Node agentPosition = new Node("A8","PEDRO DE VEGA Y ECHAGUE","-31.615826","-60.673291");
    // Le pusimos esta posición al nodo inicial del agente porque es el que definimos en la etapa 1.

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
         * Inicializar lista de sensores con el archivo SENSORES.csv
         */
        path = "SENSORES.csv";
        converter = new CSVToMatrix(';');
        ArrayList<String[]> sensors = converter.fileToMatrix(path);
        for(int i=0;i<sensors.size();i++){
            sensorsList.add(new Sensor(sensors.get(i)[0], sensors.get(i)[1], sensors.get(i)[2], sensors.get(i)[3]));
        }

        /**
         * Inicializar lista de enfermos con el archivo ENFERMOS.csvv
         */



    }

    @Override
    public String toString() {
        return null;
    }
}
