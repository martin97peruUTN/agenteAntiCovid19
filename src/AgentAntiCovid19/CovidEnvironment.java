package AgentAntiCovid19;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

public class CovidEnvironment extends Environment {
    public CovidEnvironment(){
        //Creamos el estado del ambiente y se lo seteamos al ambiente.
        this.environmentState = new CovidEnvironmentState();
    }

    @Override
    public CovidEnvironmentState getEnvironmentState() {
        return (CovidEnvironmentState) super.getEnvironmentState();
    }

    @Override
    public Perception getPercept() {
        //Actualizo el ambiente.
        ((CovidEnvironmentState) this.environmentState).actualizarAmbiente();
        //Mandamos las percepciones.
        CovidPerception p = new CovidPerception();
        p.setCallesCortadas(this.getEnvironmentState().getCortesDeCalle());
        p.setEnfermos(this.getEnvironmentState().getEnfermos());
        return p;
    }

    @Override
    public String toString() {
        return "Entorno Barrio Guadalupe";
    }
}
