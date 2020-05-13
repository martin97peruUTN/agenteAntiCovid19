package AgentAntiCovid19;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

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
    public Perception getPercept() {
        //Mandamos una percepción de la lista de  por cada iteración.
       CovidPerception p = new CovidPerception();
       p.setCallesCortadas(this.getCallesCortadas());
       p.setEnfermosNuevos(this.getEnfermosNuevos());

       return p;
    }

    //CREAR METODOS PARA DEVOLVER ENFERMOS NUEVOS Y CALLES CORTADAS;

    public ArrayList<TramoCalle> getCallesCortadas (){
        ArrayList<TramoCalle> callesCortadas = new ArrayList<TramoCalle>();
        return callesCortadas;
    }

    public ArrayList<SickPerson> getEnfermosNuevos (){
        ArrayList<SickPerson> enfermosNuevos = new ArrayList<SickPerson>();
        //Inicializar lista de enfermos con el archivo ENFERMOS.csv

        String path = "enfermitos.csv";
        CSVToMatrix converter = new CSVToMatrix(';');
        ArrayList<String[]> sickPersons = converter.fileToMatrix(path);
        for(int i=0;i<sickPersons.size();i++){
           enfermosNuevos.add(new SickPerson(Integer.valueOf(sickPersons.get(i)[0]), sickPersons.get(i)[1], sickPersons.get(i)[2], sickPersons.get(i)[3]));
        }
        return enfermosNuevos;
    }

    @Override
    public String toString() {
        return "Entorno Barrio Guadalupe";
    }
}
