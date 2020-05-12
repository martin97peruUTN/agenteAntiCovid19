package AgentAntiCovid19;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

import java.util.ArrayList;

public class CovidPerception extends Perception {
    private String tipo;
    private String nodo1;
    private String nodo2;
    private String estado;

    public CovidPerception(String tipo, String nodo1, String nodo2, String estado) {
        this.tipo=tipo;
        this.nodo1=nodo1;
        this.nodo2=nodo2;
        this.estado=estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNodo1() {
        return nodo1;
    }

    public void setNodo1(String nodo1) {
        this.nodo1 = nodo1;
    }

    public String getNodo2() {
        return nodo2;
    }

    public void setNodo2(String nodo2) {
        this.nodo2 = nodo2;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public void initPerception(Agent agent, Environment environment) {

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
