package AgentAntiCovid19;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

public class CovidEnvironment extends Environment {
    //Constantes de probabilidad para las percepciones
    final int NUEVO_ENFERMO = 15;
    final int MOVIMIENTO_ENFERMO = 30;
    final int CALLE_CORTADA = 10;

    private HashMap<Integer, CovidPerception> perceptionsList = new HashMap<Integer, CovidPerception>();

    public CovidEnvironment(){
        //Creamos el estado del ambiente y se lo seteamos al ambiente.
        this.environmentState = new CovidEnvironmentState();
    }

    @Override
    public Perception getPercept() {
        String ALPHABET_AUX = "ABCDEFGHIJKLMNOPQR";
        //Estas son todas las posibles letras para los nodos que tenemos
        Integer NUM_NODOS = 12;
        //Numero maximo para los nodos que tenemos
        int randomNumber;

        Random rand = new Random();

        CovidPerception actualPerception = new CovidPerception();


        //Movimientos de enfermos
        for (SickPerson p: ((CovidEnvironmentState)this.getEnvironmentState()).getSickPersonsList()){
            randomNumber = rand.nextInt(100);
            if(randomNumber<MOVIMIENTO_ENFERMO){
                //TODO todo este metodo
            }
        }

        //Enfermo nuevo
        randomNumber = rand.nextInt(100);
        if(randomNumber<NUEVO_ENFERMO){
            String nodoCasa = "";
            Integer id=0;

            randomNumber = rand.nextInt(((CovidEnvironmentState)this.getEnvironmentState()).getSensorsList().size());

            String nodoSensorActual = ((CovidEnvironmentState)this.getEnvironmentState()).getSensorsList().get(randomNumber).getId();

            nodoCasa = generateRandomNode(ALPHABET_AUX,NUM_NODOS);

            for(SickPerson p: ((CovidEnvironmentState)this.getEnvironmentState()).getSickPersonsList()){
                if(id<p.getId()){
                    id=p.getId();
                }
            }

            actualPerception.getNuevosEnfermos().add(new SickPerson(id+1,nodoSensorActual,nodoCasa));
        }

        //Corte de calle
        randomNumber = rand.nextInt(100);
        if(randomNumber<CALLE_CORTADA){

            TramoCalle nuevoCorte = new TramoCalle();
            String nombreNodoInicio = generateRandomNode(ALPHABET_AUX,NUM_NODOS);
            nuevoCorte.setInitialNode(nombreNodoInicio);

            int cantSucesores = (((CovidEnvironmentState)this.getEnvironmentState()).getMap()).get(nombreNodoInicio).size();
            randomNumber = rand.nextInt(cantSucesores);
            ArrayList sucesores = (ArrayList)(((CovidEnvironmentState)this.getEnvironmentState()).getMap()).get(nombreNodoInicio);
            nuevoCorte.setFinalNode((String) sucesores.get(randomNumber));
        }

        return actualPerception;
    }

    private String generateRandomNode(String alphabetAux, Integer numNodos){
        String nodoCasa = "";
        Random rand = new Random();
        do{
            nodoCasa = alphabetAux.charAt(rand.nextInt(alphabetAux.length()))+String.valueOf(rand.nextInt(numNodos));
        }while(!((CovidEnvironmentState)this.getEnvironmentState()).getMap().containsKey(nodoCasa));
        return nodoCasa;
    }
}
