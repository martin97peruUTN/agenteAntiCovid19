package AgentAntiCovid19;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

import java.util.ArrayList;

public class CovidPerception extends Perception {
    private ArrayList<TramoCalle> callesCortadas;
    private ArrayList<SickPerson> enfermosNuevos;
    private ArrayList<SickPerson> enfermosQueSeMovieron;

    public CovidPerception() {
        this.callesCortadas = new ArrayList<TramoCalle>();
        this.enfermosNuevos = new ArrayList<SickPerson>();
        this.enfermosQueSeMovieron = new ArrayList<SickPerson>();
    }

    @Override
    public void initPerception(Agent agent, Environment environment) {
        CovidAgent covidAgent = (CovidAgent) agent;
        CovidEnvironment covidEnvironment = (CovidEnvironment) environment;
        CovidEnvironmentState covidEnvironmentState = (CovidEnvironmentState) environment.getEnvironmentState();
        this.callesCortadas.addAll(covidEnvironment.getCallesCortadas());
        this.enfermosNuevos.addAll(covidEnvironment.getEnfermosNuevos());
        this.enfermosQueSeMovieron.addAll(covidEnvironment.getEnfermosQueSeMovieron());
    }

    public ArrayList<TramoCalle> getCallesCortadas() {
        return callesCortadas;
    }

    public void setCallesCortadas(ArrayList<TramoCalle> callesCortadas) {
        this.callesCortadas = callesCortadas;
    }

    public ArrayList<SickPerson> getEnfermosNuevos() {
        return enfermosNuevos;
    }

    public void setEnfermosNuevos(ArrayList<SickPerson> enfermosNuevos) {
        this.enfermosNuevos = enfermosNuevos;
    }

    public ArrayList<SickPerson> getEnfermosQueSeMovieron() {
        return enfermosQueSeMovieron;
    }

    public void setEnfermosQueSeMovieron(ArrayList<SickPerson> enfermosQueSeMovieron) {
        this.enfermosQueSeMovieron = enfermosQueSeMovieron;
    }

    @Override
    public String toString() {
        String cp = "";
        if(!callesCortadas.isEmpty()){
            for(TramoCalle tc: callesCortadas){
                cp = cp + "La calle entre el nodo "+tc.getInitialNode()+" y el nodo "+tc.getFinalNode()+" está cortada. ";
            }
        }
        if(!enfermosNuevos.isEmpty()){
            for(SickPerson sp: enfermosNuevos){
                cp = cp + "Hay un enfermo nuevo en el nodo "+sp.getActualPosition()+". ";
            }
        }
        if(!enfermosNuevos.isEmpty()){
            for(SickPerson spm: enfermosQueSeMovieron){
                cp = cp + "El enfermo "+spm.getId()+" se ha movido al nodo "+spm.getActualPosition()+". ";
            }
        }
        return cp;
    }

}
