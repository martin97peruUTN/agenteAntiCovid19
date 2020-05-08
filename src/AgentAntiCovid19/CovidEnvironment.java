package AgentAntiCovid19;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

import java.util.ArrayList;
import java.util.HashMap;

public class CovidEnvironment extends Environment {
    private HashMap<Integer, CovidPerception> perceptionsList = new HashMap<Integer, CovidPerception>();
    private int iteration = 0;

    public CovidEnvironment(){
        //Creamos el estado del ambiente y se lo seteamos al ambiente.
        this.environmentState = new CovidEnvironmentState();

        //Inicializamos la lista de percepciones tomadas del archivo.
        CSVToMatrix converter;
        String path = "PERCEPCIONES.csv";
        converter = new CSVToMatrix(';');
        ArrayList<String[]> perceptions = converter.fileToMatrix(path);

        for(int i=0,k=0;i<perceptions.size();i++,k=0){
            if(Integer.valueOf(perceptions.get(i)[0])==i){ //Solo agrego una percepción a la lista de percepciones si la iteración es la misma que el índice de la percepción.
                                                            //Sino no pasa nada en esa iteración.
                perceptionsList.put(i, new CovidPerception(perceptions.get(i)[1], perceptions.get(i)[2], perceptions.get(i)[3], perceptions.get(i)[4]));
            }
            else{
                int j=0;
                while(j<Integer.valueOf(perceptions.get(i)[0])){
                 //En este caso agrego percepciones nulas en la lista hasta la posición k, ya que en esas iteraciones hasta no pasó nada.
                    perceptionsList.put(j,null);
                }
                perceptionsList.put(k, new CovidPerception(perceptions.get(i)[1], perceptions.get(i)[2], perceptions.get(i)[3], perceptions.get(i)[4]));
            }
        }
    }

    @Override
    public Perception getPercept() {
        //Mandamos una percepción de la lista de  por cada iteración.
        iteration++;
        CovidPerception actualPerception = perceptionsList.get(iteration);
        if (actualPerception!=null){
            ((CovidEnvironmentState)this.getEnvironmentState()).sendPerception(actualPerception);
            return actualPerception;
        }
        return null;
    }
}
