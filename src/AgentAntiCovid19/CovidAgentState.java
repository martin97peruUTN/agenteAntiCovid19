package AgentAntiCovid19;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class CovidAgentState extends SearchBasedAgentState{
    private String position;
    private ArrayList<SickPerson> sickPersonsList = new ArrayList<SickPerson>();
    private ArrayList<TramoCalle> cutStreetsList = new ArrayList<TramoCalle>();

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
        position = "A8";    //Posición inicial del agente: Nodo: A8, Calles: Pedro de Vega y Echague.

        knownMap = new HashMap<String, Collection<String>>();

        //Inicializo la lista de nodos.
        CSVToMatrix converter = new CSVToMatrix(';');
        String path = "NODOS-Mapa.csv";
        ArrayList<String[]> nodes = converter.fileToMatrix(path);
        path = "NODOS-Sucesores.csv";
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

    public void setSickPersonsList(ArrayList<SickPerson> sickPersonsList) {
        this.sickPersonsList = sickPersonsList;
    }

    public ArrayList<SickPerson> getSickPersonsList() {
        return sickPersonsList;
    }

    public ArrayList<TramoCalle> getCutStreetsList() {
        return cutStreetsList;
    }

    public void setCutStreetsList(ArrayList<TramoCalle> cutStreetsList) {
        this.cutStreetsList = cutStreetsList;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof CovidAgentState)){
            return false;
        }
        if((this.getPosition().equals(((CovidAgentState) obj).getPosition())) && (this.getSickPersonsList().containsAll(((CovidAgentState) obj).getSickPersonsList()) &&
        this.getSickPersonsList().size()==((CovidAgentState) obj).getSickPersonsList().size()) &&
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

        ArrayList<SickPerson> cloneSickPersonList = (ArrayList<SickPerson>) sickPersonsList.clone();
        ArrayList<TramoCalle> cloneCutStreetList = (ArrayList<TramoCalle>) cutStreetsList.clone();
        ArrayList<String> cloneVisitedPositions = (ArrayList<String>) visitedPositions.clone();

        newState.setSickPersonsList(cloneSickPersonList);
        newState.setCutStreetsList(cloneCutStreetList);
        newState.setVisitedPositions(cloneVisitedPositions);
        return newState;
    }

    @Override
    public void updateState(Perception p) { //Tengo que agarrar la percepción que paso y setearla.
        if(!visitedPositions.contains(position)){
            visitedPositions.add(position);
        }
        if(p!=null) {
            CovidPerception cp = (CovidPerception) p;
            if (!cp.getEnfermos().isEmpty()) {
                this.sickPersonsList=cp.getEnfermos();
            }
            if (!cp.getCallesCortadas().isEmpty()) {
                this.cutStreetsList=cp.getCallesCortadas();
                for(TramoCalle tc:cutStreetsList){
                    knownMap.get(tc.getInitialNode()).remove(tc.getFinalNode());
                }
            }
            /*for(SickPerson sp:this.sickPersonsList){
                if(sp.getActualPosition().equals(this.position)){
                    this.setSeeSickPerson(true);
                }
            }*/
        }
    }

    /*public Double cantMultasQueFaltanHacer(){
        int multasParaCurarse = 0;
        for(SickPerson p: this.sickPersonsList){
            if(p.getCantMultas()<=3){
                multasParaCurarse = multasParaCurarse + (4-p.getCantMultas());
            }
        }
        return Double.valueOf(multasParaCurarse);
    }*/

    public Double cantEnfermosFueraDeCasa(){
        Double cantEnfermosFueraDeCasa = 0.0;
        for(SickPerson p: this.sickPersonsList){
            if(!p.getActualPosition().equals(p.getHomePosition())){
                cantEnfermosFueraDeCasa = cantEnfermosFueraDeCasa + 1.0;
            }
        }
        return cantEnfermosFueraDeCasa;
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
        for(SickPerson sp: sickPersonsList){
            str+="["+"id:"+sp.getId()+","+"actualPos:"+sp.getActualPosition()+","+"homePos:"+sp.getHomePosition()+"]";//","+"cantMultas:"+sp.getCantMultas()+"]";
        }
        return str;
    }
}
