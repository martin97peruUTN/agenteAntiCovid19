package AgentAntiCovid19;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

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

        CovidPerception actualPerception = new CovidPerception();

        Random rand = new Random();
        int randomNumber;
        Integer id=0;

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
            String alphabetAux = "ABCDEFGHIJKLMNOPQR";
            char letraNodo;

            randomNumber = rand.nextInt(((CovidEnvironmentState)this.getEnvironmentState()).getSensorsList().size());

            String nodoSensorActual = ((CovidEnvironmentState)this.getEnvironmentState()).getSensorsList().get(randomNumber).getId();

            do{
                randomNumber = 3+rand.nextInt(7);
                letraNodo = alphabetAux.charAt(rand.nextInt(alphabetAux.length()));
                nodoCasa = letraNodo+String.valueOf(randomNumber);
            }while(!((CovidEnvironmentState)this.getEnvironmentState()).getMap().containsKey(nodoCasa));

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
            //TODO Todo este metodo
        }

        return actualPerception;
    }
}
