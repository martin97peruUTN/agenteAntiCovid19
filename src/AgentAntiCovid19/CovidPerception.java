package AgentAntiCovid19;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class CovidPerception extends Perception {
    private String tipo;
    private String nodo1;
    private String nodo2;
    private String estado;

    @Override
    public void initPerception(Agent agent, Environment environment) {

    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        String cp = "";
        if(this.tipo=="ANE"){
            if(this.nodo1=="I3"){
                cp = "Ha sido detectado un nuevo enfermo en el Sensor 1.";
            }
            if(this.nodo1=="Q10"){
                cp = "Ha sido detectado un nuevo enfermo en el Sensor 2.";
            }
            if(this.nodo1=="J12"){
                cp = "Ha sido detectado un nuevo enfermo en el Sensor 3.";
            }
            if(this.nodo1=="C10"){
                cp = "Ha sido detectado un nuevo enfermo en el Sensor 4.";
            }
        }

        if(this.tipo=="ACC"){
            if(Boolean.valueOf(this.estado)){
                cp = "La calle entre los nodos " + this.nodo1 + " y " + this.nodo2 + " está cortada.";
            }
            else{
                cp = "La calle entre los nodos " + this.nodo1 + " y " + this.nodo2 + " está abierta.";
            }
        }

        if(this.tipo=="AEM"){
            cp = "El enfermo " + this.estado + " ha roto la cuarentena y se encuentra en el nodo " + this.nodo1 + ".";
        }
        return cp;
    }
}
