package AgentAntiCovid19;

import frsf.cidisi.faia.state.EnvironmentState;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class CovidEnvironmentState extends EnvironmentState {
    private HashMap<String, Collection<String>> map;
    private ArrayList<SickPerson> enfermos;
    private ArrayList<TramoCalle> cortesDeCalle; //Esto es lo que se percibe, la lista de enfermos y la lista de cortes de calle.
    private String agentPosition = "";
    private int iteration = 0;

    //Tengo que agregar la información del ambiente, los enfermos y los cortes de calle.

    public CovidEnvironmentState() {this.initState();}

    @Override
    public Object clone() {
       return map.clone();
    }

    @Override
    public void initState() {
        //Inicializo la posición del agente en el ambiente.
        agentPosition="A8";

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
                if(nodesSuccesors.get(j)[0].contentEquals(nodes.get(i)[0])) {
                        succesors.add(nodesSuccesors.get(j)[1]);
                }
            }
            map.put(nodes.get(i)[0], succesors);
        }

        //Tomo desde acá los enfermos con los que inicializo el ambiente.
        enfermos = new ArrayList<SickPerson>();
        path = "ENFERMOS.csv";
        ArrayList<String[]>  enfermitos = converter.fileToMatrix(path);
        for(int i=0;i<enfermitos.size();i++){
            enfermos.add(new SickPerson(Integer.valueOf(enfermitos.get(i)[0]), enfermitos.get(i)[1], enfermitos.get(i)[2], enfermitos.get(i)[3]));
        }

        //Inicializo sin cortes de calles en el ambiente.
        cortesDeCalle = new ArrayList<TramoCalle>();

    }

    public void actualizarAmbiente(){
        //Acá actualizo el ambiente con las percepciones de los .csv
        //Si hay cortes de calles nuevos los agrego a la lista de cortes de calle del estado del ambiente.
        if (this.getCalleCortadaNueva()!=null) {
            this.cortesDeCalle.add(this.getCalleCortadaNueva());
            for (TramoCalle tc : this.cortesDeCalle) {
                map.get(tc.getInitialNode()).remove(tc.getFinalNode());
            }
        }
        //Si hay enfermos nuevos los agrego a la lista de enfermos del estado del ambiente.
        if (this.getEnfermoNuevo()!=null) {
            this.enfermos.add(this.getEnfermoNuevo());
        }
        //Si hay enfermos que se movieron, actualizo su posición en la lista de enfermos del estado del ambiente.
        if (this.getEnfermoQueSeMovio()!=null) {
            for (SickPerson sp : this.enfermos) {
                if (sp.getId().equals(this.getEnfermoQueSeMovio().getId())) {
                    sp.setActualPosition(this.getEnfermoQueSeMovio().getActualPosition());
                }
            }
        }
        iteration++;
    }

    public int getIteration(){
        return this.iteration;
    }

    public ArrayList<SickPerson> getEnfermos() {
        return enfermos;
    }

    public ArrayList<TramoCalle> getCortesDeCalle() {
        return cortesDeCalle;
    }

    private TramoCalle getCalleCortadaNueva (){
        //Tomo las calles cortadas nuevas del csv.
        TramoCalle calleCortadaNueva = new TramoCalle();
        String path = "callecitasCortadasNuevas.csv";
        CSVToMatrix converter = new CSVToMatrix(';');
        ArrayList<String[]> cutStreets = converter.fileToMatrix(path);
        if(iteration<cutStreets.size()){
            calleCortadaNueva.setInitialNode(cutStreets.get(iteration)[1]);
            calleCortadaNueva.setFinalNode(cutStreets.get(iteration)[2]);
            return calleCortadaNueva;
        }
        else{return null;}
    }

    private SickPerson getEnfermoNuevo (){
        //Tomo los enfermos nuevos del csv.
        SickPerson enfermoNuevo = new SickPerson();
        String path = "enfermitosNuevos.csv";
        CSVToMatrix converter = new CSVToMatrix(';');
        ArrayList<String[]> sickPersons = converter.fileToMatrix(path);
        if (iteration<sickPersons.size()){
            enfermoNuevo.setId(Integer.valueOf(sickPersons.get(iteration)[0]));
            enfermoNuevo.setActualPosition(sickPersons.get(iteration)[1]);
            enfermoNuevo.setHomePosition(sickPersons.get(iteration)[2]);
            enfermoNuevo.setCantMultas(Integer.valueOf(sickPersons.get(iteration)[3]));
            return enfermoNuevo;
        }
        else{return null;}
    }

    private SickPerson getEnfermoQueSeMovio(){
        //Tomo los movimientos de enfermos del csv.
        SickPerson enfermoQueSeMovio = new SickPerson();
        String path = "enfermitosMovidos.csv";
        CSVToMatrix converter = new CSVToMatrix(';');
        ArrayList<String[]> sickPersonsMove = converter.fileToMatrix(path);
        if(iteration<sickPersonsMove.size()) {
            enfermoQueSeMovio.setId(Integer.valueOf(sickPersonsMove.get(iteration)[0]));
            enfermoQueSeMovio.setActualPosition(sickPersonsMove.get(iteration)[1]);
            enfermoQueSeMovio.setHomePosition(sickPersonsMove.get(iteration)[2]);
            enfermoQueSeMovio.setCantMultas(Integer.valueOf(sickPersonsMove.get(iteration)[3]));
            return enfermoQueSeMovio;
        }
        else{return null;}
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

    public boolean equals(Object obj){
        return true;
    }

    public HashMap<String, Collection<String>> getMap() {
        return map;
    }

    public void setMap(HashMap<String, Collection<String>> map) {
        this.map = map;
    }

    public String getAgentPosition() {
        return agentPosition;
    }

    public void setAgentPosition(String agentPosition) {
        this.agentPosition = agentPosition;
    }
}
