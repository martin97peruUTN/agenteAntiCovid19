package AgentAntiCovid19;

import com.sun.scenario.animation.shared.SingleLoopClipEnvelope;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class CovidAgentState extends SearchBasedAgentState{
    private String position;
    private ArrayList<SickPerson> sickPersonsList = new ArrayList<SickPerson>();
    private ArrayList<Sensor> sensorsList = new ArrayList<Sensor>();
    private ArrayList<Node> nodesList = new ArrayList<Node>();

    private HashMap<String, Collection<String>> knownMap;
    private ArrayList<String> visitedPositions;

    public CovidAgentState(HashMap<String, Collection<String>> map, ArrayList<SickPerson> sickPersonsList, ArrayList<Sensor> sensorsList){
        this.knownMap = (HashMap<String, Collection<String>>) map.clone();
        this.sensorsList = (ArrayList<Sensor>) sensorsList;
        this.sickPersonsList = (ArrayList<SickPerson>) sickPersonsList;
    }

    @Override
    public void initState() {

        position = "A8";    //Posición inicial del agente: Nodo: A8, Calles: Pedro de Vega y Echague.

        //Inicializo el mapa del agente y la lista de nodos.
        CSVToMatrix converter = new CSVToMatrix(';');
        String path = "NODOS-Mapa.csv";
        ArrayList<String[]> nodes = converter.fileToMatrix(path);
        path = "NODOS-Sucesores.csv";
        ArrayList<String[]> nodesSuccesors = converter.fileToMatrix(path);
        for(int i=0;i<nodes.size();i++){
            nodesList.add(new Node(nodes.get(i)[0], nodes.get(i)[1], nodes.get(i)[2], nodes.get(i)[3]));
        }

        //Inicializo la lista de nodos visitados.
        visitedPositions = new ArrayList<String>();

        //Inicializo la lista de sensores.
        path="SENSORES.csv";
        ArrayList<String[]> sensors = converter.fileToMatrix(path);
        for(int i=0;i<sensors.size();i++){
            sensorsList.add(new Sensor(sensors.get(i)[0], sensors.get(i)[1], sensors.get(i)[2], sensors.get(i)[3]));
        }

        //Inicializo la lista de enfermos.
        path="ENFERMOS.csv";
        ArrayList<String[]> sickPersons = converter.fileToMatrix(path);
        for(int i=0;i<sickPersons.size();i++){
            sickPersonsList.add(new SickPerson(sickPersons.get(i)[0], sickPersons.get(i)[1], sickPersons.get(i)[2], sickPersons.get(i)[3], sickPersons.get(i)[4]));
        }
    }

    public ArrayList<SickPerson> getSickPersonsList() {
        return sickPersonsList;
    }

    public Collection<String> getSuccesors(){
        return knownMap.get(position);
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public ArrayList<Sensor> getSensorsList() {
        return sensorsList;
    }

    public void setSensorsList(ArrayList<Sensor> sensorsList) {
        this.sensorsList = sensorsList;
    }

    public ArrayList<Node> getNodesList() {
        return nodesList;
    }

    public void setNodesList(ArrayList<Node> nodesList) {
        this.nodesList = nodesList;
    }

    public HashMap<String, Collection<String>> getKnownMap() {
        return knownMap;
    }

    public void setKnownMap(HashMap<String, Collection<String>> knownMap) {
        this.knownMap = knownMap;
    }

    public ArrayList<String> getVisitedPositions() {
        return visitedPositions;
    }

    public void setVisitedPositions(ArrayList<String> visitedPositions) {
        this.visitedPositions = visitedPositions;
    }

    public void setSickPersonsList(ArrayList<SickPerson> sickPersonsList) {
        this.sickPersonsList = sickPersonsList;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof CovidAgentState)){
            return false;
        }
        return (position.equals(((CovidAgentState) obj).getPosition()) && (sickPersonsList.size() == ((CovidAgentState) obj).getSickPersonsList().size()));
    }

    @Override
    public SearchBasedAgentState clone() {

        return null;
    }

    @Override
    public void updateState(Perception p) {

    }

    @Override
    public String toString() {
        String str = "Nodo: " + position;
        return str;
    }
}
