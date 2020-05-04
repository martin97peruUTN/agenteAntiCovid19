package AgentAntiCovid19;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

import java.util.ArrayList;
import java.util.HashMap;

public class CovidEnvironment extends Environment {
    private HashMap<Integer, CovidPerception> perceptionsList = new HashMap<Integer, CovidPerception>();

    public CovidEnvironment(){
        //Creamos el estado del ambiente
        this.environmentState = new CovidEnvironmentState();

        CSVToMatrix converter;
        String path = "SUCESOS.csv";
        converter = new CSVToMatrix(';');
        ArrayList<String[]> events = converter.fileToMatrix(path);

    }

    @Override
    public Perception getPercept() {
        return null;
    }
}
