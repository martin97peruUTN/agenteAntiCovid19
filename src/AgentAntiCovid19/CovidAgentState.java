package AgentAntiCovid19;

import com.sun.scenario.animation.shared.SingleLoopClipEnvelope;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class CovidAgentState extends SearchBasedAgentState{
    private String position;
    private ArrayList<Sensor> sensorsList = new ArrayList<Sensor>();
    private ArrayList<SickPerson> sickPersonsList = new ArrayList<SickPerson>();
    private ArrayList<Node> nodesList = new ArrayList<Node>();
    private HashMap<String, Collection<String>> knownMap;
    private ArrayList<String> visitedPositions;
    private Integer totalOfGoRealized = 0;
    private Integer totalOfMulctRealized = 0;

    public CovidAgentState(HashMap<String, Collection<String>> map, ArrayList<Sensor> sensorsList, ArrayList<SickPerson> sickPersonsList, String position){
        this.knownMap = (HashMap<String, Collection<String>>) map.clone();
        this.sickPersonsList = (ArrayList<SickPerson>) sickPersonsList.clone();
        this.sensorsList = (ArrayList<Sensor>) sensorsList.clone();
        this.position = position;
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

        //Inicializo la lista de enfermos.
        path="ENFERMOS.csv";
        ArrayList<String[]> sickPersons = converter.fileToMatrix(path);
        for(int i=0;i<sickPersons.size();i++){
            sickPersonsList.add(new SickPerson(sickPersons.get(i)[0], sickPersons.get(i)[1], sickPersons.get(i)[2]));
        }
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

    public ArrayList<SickPerson> getSickPersonsList() {
        return sickPersonsList;
    }



    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof CovidAgentState)){
            return false;
        }
        CovidAgentState auxCompare = (CovidAgentState) obj;
        Boolean boolsickPerList = sickPersonsList.equals(auxCompare.getSickPersonsList());
        Boolean boolVisitedPositions = visitedPositions.equals(auxCompare.getVisitedPositions());
        /* Hecho con for comparando elemento por elemento
        for(int i=0;i<sickPersonsList.size();i++){
            Boolean boolSickPerLis2;
            if(sickPersonsList.get(i).equals(auxCompare.getSickPersonList().get(i))){
                boolSickPerLis2=true;
            }else{
                boolSickPerLis2 =false;
                break;
            }
        }*/
        /*for(int i=0;i<visitedPositions.size();i++){
            Boolean boolvisitedposition2;
            if(sickPersonsList.get(i).equals(auxCompare.getSickPersonList().get(i))){
                boolvisitedposition2=true;
            }else{
                boolvisitedposition2 =false;
                break;
            }
        }*/
        Boolean comparing = (position.equals(((CovidAgentState) obj).getPosition()) && (sickPersonsList.size() == ((CovidAgentState) obj).getSickPersonsList().size()) && boolsickPerList && boolVisitedPositions);
        return comparing;
    }

    @Override
    public SearchBasedAgentState clone() {
        CovidAgentState newState = new CovidAgentState(knownMap, sensorsList, sickPersonsList, position);
        newState.setTotalOfGoRealized(totalOfGoRealized);
        newState.setTotalOfMulctRealized(totalOfMulctRealized);
        newState.setVisitedPositions((ArrayList<String>) visitedPositions.clone());
        newState.setNodesList((ArrayList<Node>) nodesList.clone());
        newState.setSensorsList((ArrayList<Sensor>) sensorsList.clone());
        newState.setSickPersonsList((ArrayList<SickPerson>) sickPersonsList.clone());
        return newState;
    }

    @Override
    public void updateState(Perception p) {

    }

    public Integer getTotalOfGoRealized() {
        return totalOfGoRealized;
    }

    public void setTotalOfGoRealized(Integer totalOfGoRealized) {
        this.totalOfGoRealized = totalOfGoRealized;
    }

    public Integer getTotalOfMulctRealized() {
        return totalOfMulctRealized;
    }

    public void setTotalOfMulctRealized(Integer totalOfMulctRealized) {
        this.totalOfMulctRealized = totalOfMulctRealized;
    }

    public ArrayList<Sensor> getSensorsList() {
        return sensorsList;
    }

    public void setSensorsList(ArrayList<Sensor> sensorsList) {
        this.sensorsList = sensorsList;
    }

    @Override
    public String toString() {
        String str = "Nodo: " + position;
        return str;
    }
}
