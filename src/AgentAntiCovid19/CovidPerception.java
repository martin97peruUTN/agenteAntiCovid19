package AgentAntiCovid19;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

import java.util.ArrayList;

public class CovidPerception extends Perception {
    private ArrayList<TramoCalle> callesCortadas;
    private ArrayList<SickPerson> enfermosNuevos;

    public CovidPerception() {
        this.callesCortadas = new ArrayList<TramoCalle>();
        this.enfermosNuevos = new ArrayList<SickPerson>();
    }

    @Override
    public void initPerception(Agent agent, Environment environment) {
        CovidAgent covidAgent = (CovidAgent) agent;
        CovidEnvironment covidEnvironment = (CovidEnvironment) environment;
        CovidEnvironmentState covidEnvironmentState = (CovidEnvironmentState) environment.getEnvironmentState();

        this.callesCortadas.addAll(covidEnvironment.getCallesCortadas());
        this.enfermosNuevos.addAll(covidEnvironment.getEnfermosNuevos());
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

    @Override
    public String toString() {
        String cp = "";
        return cp;
    }

}
