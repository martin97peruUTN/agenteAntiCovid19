package AgentAntiCovid19;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

public class CovidEnvironment extends Environment {
    private int iteration=0;
    public CovidEnvironment(){
        //Creamos el estado del ambiente y se lo seteamos al ambiente.
        this.environmentState = new CovidEnvironmentState();
    }

    @Override
    public Perception getPercept() {
        iteration++;
        //Mandamos las percepciones.
        CovidPerception p = new CovidPerception();
        if(iteration==1) {
            p.setCallesCortadas(this.getCallesCortadas());
            p.setEnfermosNuevos(this.getEnfermosNuevos());
            p.setEnfermosQueSeMovieron(this.getEnfermosQueSeMovieron());
        }
        return p;
    }

    public ArrayList<TramoCalle> getCallesCortadas (){
        ArrayList<TramoCalle> callesCortadas = new ArrayList<TramoCalle>();
        String path = "callecitasCortadas.csv";
        CSVToMatrix converter = new CSVToMatrix(';');
        ArrayList<String[]> cutStreets = converter.fileToMatrix(path);
        for (int i = 0; i < cutStreets.size(); i++) {
            callesCortadas.add(new TramoCalle(cutStreets.get(i)[1], cutStreets.get(i)[2]));
        }
        return callesCortadas;
    }

    public ArrayList<SickPerson> getEnfermosNuevos (){
        ArrayList<SickPerson> enfermosNuevos = new ArrayList<SickPerson>();
        //Inicializar lista de enfermos con el archivo ENFERMOS.csv
        String path = "enfermitos.csv";
        CSVToMatrix converter = new CSVToMatrix(';');
        ArrayList<String[]> sickPersons = converter.fileToMatrix(path);
        for (int i = 0; i < sickPersons.size(); i++) {
            enfermosNuevos.add(new SickPerson(Integer.valueOf(sickPersons.get(i)[0]), sickPersons.get(i)[1], sickPersons.get(i)[2], sickPersons.get(i)[3]));
        }
        return enfermosNuevos;
    }

    public ArrayList<SickPerson> getEnfermosQueSeMovieron(){
        ArrayList<SickPerson> enfermosQueSeMovieron = new ArrayList<SickPerson>();
        String path = "enfermitosMovidos.csv";
        CSVToMatrix converter = new CSVToMatrix(';');
        ArrayList<String[]> sickPersonsMove = converter.fileToMatrix(path);
        for (int i = 0; i < sickPersonsMove.size(); i++) {
            enfermosQueSeMovieron.add(new SickPerson(Integer.valueOf(sickPersonsMove.get(i)[0]), sickPersonsMove.get(i)[1], sickPersonsMove.get(i)[2], sickPersonsMove.get(i)[3]));
        }
        return enfermosQueSeMovieron;
    }

    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    @Override
    public String toString() {
        return "Entorno Barrio Guadalupe";
    }
}
