package AgentAntiCovid19;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class CovidAgentState extends SearchBasedAgentState{
    private String position;
    private ArrayList<SickPerson> newSickPersonsList = new ArrayList<SickPerson>();
    private ArrayList<TramoCalle> cutStreetsList = new ArrayList<TramoCalle>();



    //private Boolean seeSickPerson = false; //Esto nos sirve para saber si en el nodo donde está el agente vio un enfermo
    private Integer totalOfGoRealized = 0;
    private Integer totalOfMulctRealized = 0;
    private Integer totalOfSickPersonHealted = 0;

    //Inicializo el mapa y la lista de posiciones visitadas.
    private HashMap<String, Collection<String>> knownMap;
    private ArrayList<String> visitedPositions;

    public CovidAgentState(){
        this.initState();
    }

    @Override
    public void initState() {
        position = "A8";    //Posición inicial del agente: Nodo: 008, Calles: Pedro de Vega y Echague.

        knownMap = new HashMap<String, Collection<String>>();

        //Inicializo la lista de nodos.
        CSVToMatrix converter = new CSVToMatrix(';');
        String path = "mapita2.csv";
        ArrayList<String[]> nodes = converter.fileToMatrix(path);
        path = "sucesoritos2.csv";
        ArrayList<String[]> nodesSuccesors = converter.fileToMatrix(path);
        //Inicializar mapa con los nodos del archivo NODOS-Mapa.csv
        for(int i=0;i<nodes.size();i++){
            ArrayList<String> succesors = new ArrayList<String>();
            for(int j=0;j<nodesSuccesors.size();j++){
                if(nodesSuccesors.get(j)[0].contentEquals(nodes.get(i)[0])) {
                    succesors.add(nodesSuccesors.get(j)[1]);
                }
            }
            knownMap.put(nodes.get(i)[0], succesors);
        }

        //Inicializo la lista de nodos visitados.
        visitedPositions = new ArrayList<String>();

        //Inicializo
        ArrayList<SickPerson> newSickPersonsList = new ArrayList<SickPerson>();
        ArrayList<TramoCalle> cutStreetsList = new ArrayList<TramoCalle>();

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

    public void setNewSickPersonsList(ArrayList<SickPerson> sickPersonsList) {
        this.newSickPersonsList = sickPersonsList;
    }

    public ArrayList<SickPerson> getNewSickPersonsList() {
        return newSickPersonsList;
    }

    public ArrayList<TramoCalle> getCutStreetsList() {
        return cutStreetsList;
    }

    public void setCutStreetsList(ArrayList<TramoCalle> cutStreetsList) {
        this.cutStreetsList = cutStreetsList;
    }

    /*public Boolean getSeeSickPerson(){return seeSickPerson;}

    public void setSeeSickPerson(Boolean seeSickPerson) { this.seeSickPerson = seeSickPerson; }*/

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof CovidAgentState)){
            return false;
        }
        if((this.getPosition().equals(((CovidAgentState) obj).getPosition())) && (this.getNewSickPersonsList().containsAll(((CovidAgentState) obj).getNewSickPersonsList()) &&
        this.getNewSickPersonsList().size()==((CovidAgentState) obj).getNewSickPersonsList().size()) &&
                (this.getCutStreetsList().containsAll(((CovidAgentState) obj).getCutStreetsList()) &&
                        this.getCutStreetsList().size()==((CovidAgentState) obj).getCutStreetsList().size()))
        {return true;}
        else{
            return false;
        }
    }

    @Override
    public SearchBasedAgentState clone() {
        CovidAgentState newState = new CovidAgentState();
        newState.setPosition(position);
        newState.setTotalOfGoRealized(totalOfGoRealized);
        newState.setTotalOfMulctRealized(totalOfMulctRealized);
        newState.setTotalOfSickPersonHealted(totalOfSickPersonHealted);

        ArrayList<SickPerson> clonenewSickPersonList = (ArrayList<SickPerson>) newSickPersonsList.clone();
        ArrayList<TramoCalle> clonecutStreetList = (ArrayList<TramoCalle>) cutStreetsList.clone();
        ArrayList<String> cloneVisitedPositions = (ArrayList<String>) visitedPositions.clone();

        newState.setNewSickPersonsList(clonenewSickPersonList);
        newState.setCutStreetsList(clonecutStreetList);
        newState.setVisitedPositions(cloneVisitedPositions);
        //newState.setSeeSickPerson(seeSickPerson);
        return newState;
    }

    @Override
    public void updateState(Perception p) { //Tengo que agarrar la percepción que paso
        visitedPositions.add(position);
        CovidPerception cp = (CovidPerception) p;
        this.newSickPersonsList = cp.getEnfermosNuevos();
        this.cutStreetsList = cp.getCallesCortadas();
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

    @Override
    public String toString() {
        String str = "Nodo: " + position + " Enfermos: ";
        for(SickPerson sp: newSickPersonsList){
            str+="{"+"id:"+sp.getId()+","+"actualPos:"+sp.getActualPosition()+","+"homePos:"+sp.getHomePosition()+","+"cantMultas:"+sp.getCantMultas()+"}";
        }
        return str;
    }
}
