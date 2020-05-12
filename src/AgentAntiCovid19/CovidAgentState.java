package AgentAntiCovid19;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class CovidAgentState extends SearchBasedAgentState{
    //Posición del Agente.
    private String position;
    private String selectedPosition;
    private ArrayList<Sensor> sensorsList = new ArrayList<Sensor>();
    private ArrayList<SickPerson> sickPersonsList = new ArrayList<SickPerson>();
    private ArrayList<Node> nodesList = new ArrayList<Node>();
    //private Boolean seeSickPerson = false; //Esto nos sirve para saber si en el nodo donde está el agente vio un enfermo
    private Integer totalOfGoRealized = 0; //Total de go realizados.
    private Integer totalOfMulctRealized = 0; //Total de multas realizadas.
    private Integer totalOfSickPersonHealted = 0; //Total de personas curadas.

    ArrayList<String[]> nodes;

    //Inicializo el mapa y la lista de posiciones visitadas.
    private HashMap<String, Collection<String>> knownMap;
    private ArrayList<String> visitedPositions;

    public CovidAgentState(HashMap<String, Collection<String>> map, ArrayList<Sensor> sensorsList, ArrayList<SickPerson> sickPersonsList, String selPosition){
        this.selectedPosition = selPosition;
        this.knownMap = (HashMap<String, Collection<String>>) map.clone();
        this.sickPersonsList = (ArrayList<SickPerson>) sickPersonsList.clone();
        this.sensorsList = (ArrayList<Sensor>) sensorsList.clone();
    }

    @Override
    public void initState() {
        position = this.selectedPosition;    //Seteo la posición inicial del agente: Nodo: 008, Calles: Pedro de Vega y Echague.

        //Inicializo la lista de nodos.
        CSVToMatrix converter = new CSVToMatrix(';');
        String path = "mapita.csv";
        nodes = converter.fileToMatrix(path);
        for(int i=0;i<nodes.size();i++){
            nodesList.add(new Node(nodes.get(i)[0], nodes.get(i)[1], nodes.get(i)[2], nodes.get(i)[3]));
        }

        //Inicializo la lista de nodos visitados.
        visitedPositions = new ArrayList<String>();

    }

    public void setKnownMap(HashMap<String, Collection<String>> knownMap) {
        this.knownMap = knownMap;
    }

    @Override
    public CovidAgentState clone() {
        CovidAgentState newState = new CovidAgentState(knownMap, sensorsList, sickPersonsList, "0");

        newState.setPosition(position);
        newState.setTotalOfGoRealized(totalOfGoRealized);
        newState.setTotalOfMulctRealized(totalOfMulctRealized);
        newState.setTotalOfSickPersonHealted(totalOfSickPersonHealted);
        newState.setVisitedPositions((ArrayList<String>) visitedPositions.clone());
        newState.setNodesList((ArrayList<Node>) nodesList.clone());
        newState.setSensorsList((ArrayList<Sensor>) sensorsList.clone());
        newState.setSickPersonsList((ArrayList<SickPerson>) sickPersonsList.clone());
        //newState.setSeeSickPerson(seeSickPerson);
        return newState;
    }

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
        //Boolean comparing = (position.equals(((CovidAgentState) obj).getPosition()) && (sickPersonsList.size() == ((CovidAgentState) obj).getSickPersonsList().size()) && boolSickPerList && boolVisitedPositions);
        //return comparing;
        return (this.position.contentEquals(auxCompare.getPosition()) && (this.getSickPersonsList().size()==auxCompare.getSickPersonsList().size()));
    }

    @Override
    public void updateState(Perception p) {
        visitedPositions.add(position);
        if(p!=null) {
            CovidPerception cp = (CovidPerception) p;
            switch (cp.getTipo()) {
                //Percepción de aparición de nuevo enfermo, agrego a la lista de enfermos un nuevo enfermo.
                case "ANE":
                    sickPersonsList.add(new SickPerson(Integer.valueOf(cp.getEstado()), cp.getNodo1(), cp.getNodo2(), "0"));
                    break;
                //Percepción de corte de calle: si la calle está cortada le quito a la lista de sucesores de nodo1 el nodo2.
                //Si la calle no está cortada le agrego a la lista de sucesores de nodo1 el nodo2.
                case "ACC":
                    if (Boolean.valueOf(cp.getEstado())) {
                        knownMap.get(cp.getNodo1()).remove(cp.getNodo2());
                    } else {
                        if (!knownMap.get(cp.getNodo1()).contains(cp.getNodo2())) {
                            knownMap.get(cp.getNodo1()).add(cp.getNodo2());
                        }
                    }
                    break;
                //Percepción de aparición de enfermo en el mapa: busco el enfermo en la lista de enfermos y le cambio la posición actual.
                case "AEM":
                    for (SickPerson sp : sickPersonsList) {
                        if (sp.getId() == Integer.valueOf(cp.getEstado())) {
                            sp.setActualPosition(cp.getNodo1());
                            break;
                        }
                    }
                    break;
                default:
                    break;
            }
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

    /*public Boolean getSeeSickPerson(){return seeSickPerson;}

    public void setSeeSickPerson(Boolean seeSickPerson) { this.seeSickPerson = seeSickPerson; }*/

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
        String str = "Nodo: " + position + " Enfermos: ";
        for(SickPerson sp: sickPersonsList){
            str+="{"+"id:"+sp.getId()+","+"actualPos:"+sp.getActualPosition()+","+"homePos:"+sp.getHomePosition()+","+"cantMultas:"+sp.getCantMultas()+"}";
        }
        return str;
    }
}
