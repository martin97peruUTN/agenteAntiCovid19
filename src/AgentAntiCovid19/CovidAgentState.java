package AgentAntiCovid19;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class CovidAgentState extends SearchBasedAgentState{
    private String position;
    private ArrayList<Sensor> sensorsList = new ArrayList<Sensor>();
    private ArrayList<SickPerson> sickPersonsList = new ArrayList<SickPerson>();
    private ArrayList<VisualMapNode> nodesList = new ArrayList<VisualMapNode>();
    private HashMap<String, Collection<String>> knownMap;
    private ArrayList<String> visitedPositions;
    private Boolean seeSickPerson = false; //Esto nos sirve para saber si en el nodo donde está el agente vio un enfermo
    private Integer totalOfGoRealized = 0;
    private Integer totalOfMulctRealized = 0;
    private Integer totalOfSickPersonHealted = 0;

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
            nodesList.add(new VisualMapNode(nodes.get(i)[0], nodes.get(i)[1], nodes.get(i)[2], nodes.get(i)[3]));
        }

        //Inicializo la lista de nodos visitados.
        visitedPositions = new ArrayList<String>();

        //Inicializo la lista de enfermos.
        path="ENFERMOS.csv";
        ArrayList<String[]> sickPersons = converter.fileToMatrix(path);
        for(int i=0;i<sickPersons.size();i++){
            sickPersonsList.add(new SickPerson(Integer.valueOf(sickPersons.get(i)[0]), sickPersons.get(i)[1], sickPersons.get(i)[2]));
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

    public ArrayList<VisualMapNode> getNodesList() {
        return nodesList;
    }

    public void setNodesList(ArrayList<VisualMapNode> nodesList) {
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

    public Boolean getSeeSickPerson(){return seeSickPerson;}

    public void setSeeSickPerson(Boolean seeSickPerson) { this.seeSickPerson = seeSickPerson; }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof CovidAgentState)){
            return false;
        }
        CovidAgentState auxCompare = (CovidAgentState) obj;
        Boolean boolSickPerList = sickPersonsList.equals(auxCompare.getSickPersonsList());
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
        Boolean comparing = (position.equals(((CovidAgentState) obj).getPosition()) && (sickPersonsList.size() == ((CovidAgentState) obj).getSickPersonsList().size()) && boolSickPerList && boolVisitedPositions);
        return comparing;
    }

    @Override
    public SearchBasedAgentState clone() {
        CovidAgentState newState = new CovidAgentState(knownMap, sensorsList, sickPersonsList, position);
        newState.setTotalOfGoRealized(totalOfGoRealized);
        newState.setTotalOfMulctRealized(totalOfMulctRealized);
        newState.setVisitedPositions((ArrayList<String>) visitedPositions.clone());
        newState.setNodesList((ArrayList<VisualMapNode>) nodesList.clone());
        newState.setSensorsList((ArrayList<Sensor>) sensorsList.clone());
        newState.setSickPersonsList((ArrayList<SickPerson>) sickPersonsList.clone());
        newState.setTotalOfSickPersonHealted(totalOfSickPersonHealted);
        newState.setSeeSickPerson(seeSickPerson);
        return newState;
    }

    @Override
    public void updateState(Perception p) {
        visitedPositions.add(position);
        if(p!=null){
            CovidPerception cp = (CovidPerception) p;
            if(cp.getNuevosEnfermos()!=null){//Si la lista de enfermos nuevos de la percepción no está vacía agrego todos los enfermos a la lista de enfermos.
                for(SickPerson p1: cp.getNuevosEnfermos()){
                    sickPersonsList.add(p1);
                }
            }
            if(cp.getCorteDeCalles()!=null){//Si la lista de cortes de calles no está vacía saco el nodoFinal de los sucesores del nodoInicial.
                for(TramoCalle t: cp.getCorteDeCalles()){
                    knownMap.get(t.getInitialNode()).remove(t.getFinalNode());
                }
            }
            if(cp.getMovimientosEnfermos()!=null){//Si la lista de movimientos de enfermos no está vacía actualizo las posiciones de los enfermos que se movieron.
                for(SickPerson sp: cp.getMovimientosEnfermos()){
                    for(SickPerson pe: sickPersonsList){
                        if(sp.getId()==pe.getId()){
                            pe.setActualPosition(sp.getActualPosition());
                        }
                    }
                }
            }
        }
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

    public Integer getTotalOfSickPersonHealted() {
        return totalOfSickPersonHealted;
    }

    public void setTotalOfSickPersonHealted(Integer totalOfSickPersonHealted) {
        this.totalOfSickPersonHealted = totalOfSickPersonHealted;
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
