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

        System.out.println("Cantidad de nodos visitados por el agente: "+String.valueOf(visitedNodes.size()));
        System.out.println("Cantidad de multas realizadas por el agente: ");
        System.out.println("Estado final del sensor 1: "+String.valueOf(((CovidAgentState)agent.getAgentState()).getSensorsList().get(0).isHasSickPerson()));
        System.out.println("Estado final del sensor 2: "+String.valueOf(((CovidAgentState)agent.getAgentState()).getSensorsList().get(1).isHasSickPerson()));
        System.out.println("Estado final del sensor 3: "+String.valueOf(((CovidAgentState)agent.getAgentState()).getSensorsList().get(2).isHasSickPerson()));
        System.out.println("Estado final del sensor 4: "+String.valueOf(((CovidAgentState)agent.getAgentState()).getSensorsList().get(3).isHasSickPerson()));

        ArrayList<String> resultados = new ArrayList<String>();
        resultados.add(String.valueOf(visitedNodes.size()));
        resultados.add(String.valueOf(((CovidAgentState)agent.getAgentState()).getSensorsList().get(0).isHasSickPerson()));
        resultados.add(String.valueOf(((CovidAgentState)agent.getAgentState()).getSensorsList().get(1).isHasSickPerson()));
        resultados.add(String.valueOf(((CovidAgentState)agent.getAgentState()).getSensorsList().get(2).isHasSickPerson()));
        resultados.add(String.valueOf(((CovidAgentState)agent.getAgentState()).getSensorsList().get(1).isHasSickPerson()));

        return resultados;
    }

}
