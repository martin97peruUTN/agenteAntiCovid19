package AgentAntiCovid19.actions;

import AgentAntiCovid19.CovidAgentState;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

import java.util.ArrayList;

public class Go extends SearchAction {
    private String objetiveNode ="";
    private Double cost=0.0;

    public Go(String objetiveNode){
        this.objetiveNode = objetiveNode;
    }

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        CovidAgentState agentState = (CovidAgentState) s;

        //if (agentState.getVisitedPositions().contains(this.target)){
        //  return null;
        //}

        ArrayList<String> succesors = new ArrayList<String>();
        succesors.addAll(agentState.getSuccesors());




    }

    @Override
    public Double getCost() {
        return null;
    }

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        return null;
    }

    @Override
    public String toString() {
        return null;
    }

    o

}
