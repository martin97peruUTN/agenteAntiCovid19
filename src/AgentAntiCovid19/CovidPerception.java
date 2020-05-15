package AgentAntiCovid19;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

import java.util.ArrayList;

public class CovidPerception extends Perception {
    private ArrayList<TramoCalle> callesCortadas;
    private ArrayList<SickPerson> enfermos;

    public CovidPerception() {
        this.callesCortadas = new ArrayList<TramoCalle>();
        this.enfermos = new ArrayList<SickPerson>();
    }

    @Override
    public void initPerception(Agent agent, Environment environment) {
        CovidAgent covidAgent = (CovidAgent) agent;
        CovidEnvironment covidEnvironment = (CovidEnvironment) environment;
        CovidEnvironmentState covidEnvironmentState = (CovidEnvironmentState) covidEnvironment.getEnvironmentState();
        this.setCallesCortadas(covidEnvironmentState.getCortesDeCalle());
        this.setEnfermos(covidEnvironmentState.getEnfermos());
    }

    public ArrayList<TramoCalle> getCallesCortadas() {
        return callesCortadas;
    }

    public void setCallesCortadas(ArrayList<TramoCalle> callesCortadas) {
        this.callesCortadas = callesCortadas;
    }

    public ArrayList<SickPerson> getEnfermos() {
        return enfermos;
    }

    public void setEnfermos(ArrayList<SickPerson> enfermos) {
        this.enfermos = enfermos;
    }

    @Override
    public String toString() {
        String cp = "";
        if(!callesCortadas.isEmpty()){
            for(TramoCalle tc: callesCortadas){
                cp = cp + "La calle entre el nodo "+tc.getInitialNode()+" y el nodo "+tc.getFinalNode()+" está cortada. ";
            }
        }
        if(!enfermos.isEmpty()){
            for(SickPerson sp: enfermos){
                cp = cp + "Hay un enfermo en el nodo "+sp.getActualPosition()+". ";
            }
        }
        return cp;
    }

}
