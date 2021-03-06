package AgentAntiCovid19.actions;

import AgentAntiCovid19.CovidAgentState;
import AgentAntiCovid19.CovidEnvironmentState;
import AgentAntiCovid19.SickPerson;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

import java.util.ArrayList;

public class Go extends SearchAction {
    private String objetiveNode ="";
    private Double cost=1.0;

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
        if (succesors != null){
            for(SickPerson p:agentState.getSickPersonsList()){
                if(p.getActualPosition()==objetiveNode){
                    agentState.setSeeSickPerson(true);
                    break;
                }
            }
            int i = succesors.indexOf(this.objetiveNode);
            if (i>=0){
                agentState.setPosition(this.objetiveNode);
                agentState.setTotalOfGoRealized((int) (agentState.getTotalOfGoRealized()+this.cost));
                return agentState;
            }
        }
        return null;
    }

    @Override
    public Double getCost() {
        return this.cost;
    }

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        if(this.execute((SearchBasedAgentState) ast)!=null)
            ((CovidEnvironmentState) est).setAgentPosition(this.objetiveNode);
        return est;
    }

    @Override
    public String toString() {
        return "Go " + objetiveNode;
    }

}
