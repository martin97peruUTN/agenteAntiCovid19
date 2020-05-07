package AgentAntiCovid19;

import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

import java.util.ArrayList;

public class CovidMain {

    public static ArrayList<String> main(String[] args) throws PrologConnectorException {

        CovidEnvironment environment = new CovidEnvironment();

        CovidEnvironmentState environmentState = (CovidEnvironmentState) environment.getEnvironmentState();

        CovidAgent agent = new CovidAgent(environmentState.getMap(), environmentState.getSickPersonsList(), environmentState.getSensorsList(), args[0]);

        SearchBasedAgentSimulator simu = new SearchBasedAgentSimulator(environment, agent);

        simu.start();

        ArrayList<String> visitedNodes = ((CovidAgentState)agent.getAgentState()).getVisitedPositions();

        return null;
    }

}
