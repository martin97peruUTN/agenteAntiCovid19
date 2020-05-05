package AgentAntiCovid19;

import frsf.cidisi.faia.exceptions.PrologConnectorException;

import java.util.ArrayList;

public class CovidMain {

    public static ArrayList<String> main(String[] args) throws PrologConnectorException {

        CovidEnvironment environment = new CovidEnvironment();

        CovidEnvironmentState environmentState = (CovidEnvironmentState) environment.getEnvironmentState();

        CovidAgent agent = new CovidAgent(environmentState.getMap(), environmentState.getSickPersonsList(), environmentState.getSensorsList(), environmentState.getAgentPosition(), args[0]);


    }

}
