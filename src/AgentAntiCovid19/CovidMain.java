package AgentAntiCovid19;

import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CovidMain {

    public static ArrayList<String> main(String[] args) throws PrologConnectorException {

        CovidEnvironment environment = new CovidEnvironment();

        CovidEnvironmentState environmentState = (CovidEnvironmentState) environment.getEnvironmentState();

        CovidAgent agent = new CovidAgent(environmentState.getMap(), environmentState.getSickPersonsList(), environmentState.getSensorsList(), args[0]);

        SearchBasedAgentSimulator simu = new SearchBasedAgentSimulator(environment, agent);

        simu.start();

        ArrayList<String> visitedNodes = ((CovidAgentState)agent.getAgentState()).getVisitedPositions();

        //Acá armo el .csv con los nodos visitados para mostrar en la ventana de Google Maps
        try{
            File archivoNodos = new File("NODOS-Visitados.csv");
            FileWriter escritor = new FileWriter(archivoNodos);
            BufferedWriter bufferEscritor = new BufferedWriter(escritor);

            System.out.println("Nodos visitados por el agente: ");
            for(int i=0;i<visitedNodes.size();i++){
                bufferEscritor.write(visitedNodes.get(i));
                if(i<(visitedNodes.size()-1)){
                    System.out.println(visitedNodes.get(i)+", ");
                }
                bufferEscritor.newLine();
            }

            bufferEscritor.close();
            escritor.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        System.out.println("Cantidad de nodos visitados por el agente: "+String.valueOf(((CovidAgentState) agent.getAgentState()).getVisitedPositions().size()));
        System.out.println("Cantidad de multas realizadas por el agente: "+String.valueOf(((CovidAgentState) agent.getAgentState()).getTotalOfMulctRealized()));

        ArrayList<String> resultados = new ArrayList<String>();
        resultados.add(String.valueOf(((CovidAgentState) agent.getAgentState()).getVisitedPositions().size()));
        resultados.add(String.valueOf(((CovidAgentState) agent.getAgentState()).getTotalOfMulctRealized()));

        return resultados;
    }

}
