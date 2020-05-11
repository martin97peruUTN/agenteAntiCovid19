package AgentAntiCovid19;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

import java.util.ArrayList;

public class CovidPerception extends Perception {
    private ArrayList<SickPerson> nuevosEnfermos;
    private ArrayList<SickPerson> movimientosEnfermos;
    private ArrayList<TramoCalle> corteDeCalles;

    public CovidPerception(ArrayList<SickPerson> nuevosEnfermos, ArrayList<SickPerson> movimientosEnfermos, ArrayList<TramoCalle> corteDeCalles) {
        this.nuevosEnfermos = nuevosEnfermos;
        this.movimientosEnfermos = movimientosEnfermos;
        this.corteDeCalles = corteDeCalles;
    }

    public CovidPerception() {
    }

    public ArrayList<SickPerson> getNuevosEnfermos() {
        return nuevosEnfermos;
    }

    public void setNuevosEnfermos(ArrayList<SickPerson> nuevosEnfermos) {
        this.nuevosEnfermos = nuevosEnfermos;
    }

    public ArrayList<SickPerson> getMovimientosEnfermos() {
        return movimientosEnfermos;
    }

    public void setMovimientosEnfermos(ArrayList<SickPerson> movimientosEnfermos) {
        this.movimientosEnfermos = movimientosEnfermos;
    }

    public ArrayList<TramoCalle> getCorteDeCalles() {
        return corteDeCalles;
    }

    public void setCorteDeCalles(ArrayList<TramoCalle> corteDeCalles) {
        this.corteDeCalles = corteDeCalles;
    }

    public void setCorteDeCalles(TramoCalle tramo) {
        this.corteDeCalles.add(tramo);
    }

    @Override
    public void initPerception(Agent agent, Environment environment) {

    }

    @Override
    public String toString() {
        String cp = "";
        //TODO terminar esto jaja
        return cp;
    }
}
