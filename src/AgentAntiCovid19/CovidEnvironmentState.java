package AgentAntiCovid19;

import frsf.cidisi.faia.state.EnvironmentState;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class CovidEnvironmentState extends EnvironmentState {
    private HashMap<String, Collection<String>> map;
    private ArrayList<SickPerson> sickPersonsList = new ArrayList<SickPerson>();
    private ArrayList<Sensor>  sensorsList = new ArrayList<Sensor>();
    private String agentPosition = "";

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

        //Inicializar mapa con los nodos del archivo NODOS-Mapa.csv
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

        //Inicializar lista de sensores con el archivo SENSORES.csv
        path = "SENSORES.csv";
        converter = new CSVToMatrix(';');
        ArrayList<String[]> sensors = converter.fileToMatrix(path);
        for(int i=0;i<sensors.size();i++){
            sensorsList.add(new Sensor(sensors.get(i)[0], sensors.get(i)[1], sensors.get(i)[2], sensors.get(i)[3]));
        }

        //Inicializar lista de enfermos con el archivo ENFERMOS.csv
        path = "ENFERMOS.csv";
        converter = new CSVToMatrix(';');
        ArrayList<String[]> sickPersons = converter.fileToMatrix(path);
        for(int i=0;i<sensors.size();i++){
            sickPersonsList.add(new SickPerson(sickPersons.get(i)[0], sickPersons.get(i)[1], sickPersons.get(i)[2]));
        }

    }

    public void sendPerception(CovidPerception cp){
        //Percepción de aparición de nuevo enfermo, agrego a la lista de enfermos un nuevo enfermo.
        if(cp.getTipo()=="ANE"){
            sickPersonsList.add(new SickPerson(cp.getEstado(), cp.getNodo1(), cp.getNodo2()));
            for(Sensor s: sensorsList){
                if(s.getId()==cp.getNodo1()){
                    s.setHasSickPerson(true);
                }
            }
        }
        //Percepción de corte de calle: si la calle está cortada le quito a la lista de sucesores de nodo1 el nodo2.
        //Si la calle no está cortada le agrego a la lista de sucesores de nodo1 el nodo2.
        if(cp.getTipo()=="ACC"){
            if(Boolean.valueOf(cp.getEstado())){
                map.get(cp.getNodo1()).remove(cp.getNodo2());
            }
            else{
                if(!map.get(cp.getNodo1()).contains(cp.getNodo2())){
                    map.get(cp.getNodo1()).add(cp.getNodo2());
                }
            }
        }
        //Percepción de aparición de enfermo en el mapa: busco el enfermo en la lista de enfermos y le cambio la posición actual.
        if(cp.getTipo()=="AEM"){
            for(SickPerson sp: sickPersonsList){
                if(sp.getId()==cp.getEstado()){
                    sp.setActualPosition(cp.getNodo1());
                }
            }
        }
    }

    @Override
    public String toString() {
        String str = "";
        str = str + "[ \n";
        for (String point : map.keySet()) {
            str = str + "[ " + point + " --> ";
            Collection<String> successors = map.get(point);
            if (successors != null) {
                for (String successor : successors) {
                    str = str + successor + " ";
                }
            }
            str = str + " ]\n";
        }
        str = str + " ]";
        return str;
    }

    //public void sendPerception(CovidPerception cp){
    //    if(cp.getType()=="ACC"){

    //    }
    //}

    public boolean equals(Object obj){
        return true;
    }

    public HashMap<String, Collection<String>> getMap() {
        return map;
    }

    public void setMap(HashMap<String, Collection<String>> map) {
        this.map = map;
    }

    public ArrayList<SickPerson> getSickPersonsList() {
        return sickPersonsList;
    }

    public void setSickPersonsList(ArrayList<SickPerson> sickPersonsList) {
        this.sickPersonsList = sickPersonsList;
    }

    public ArrayList<Sensor> getSensorsList() {
        return sensorsList;
    }

    public void setSensorsList(ArrayList<Sensor> sensorsList) {
        this.sensorsList = sensorsList;
    }

    public String getAgentPosition() {
        return agentPosition;
    }

    public void setAgentPosition(String agentPosition) {
        this.agentPosition = agentPosition;
    }
}
