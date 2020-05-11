package AgentAntiCovid19.actions;

import AgentAntiCovid19.CovidAgentState;
import AgentAntiCovid19.CovidEnvironmentState;
import AgentAntiCovid19.SickPerson;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class MulctSickPerson extends SearchAction {
    private Double cost = 2.0;

    public MulctSickPerson(){

    }

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        CovidAgentState covidAgentState = (CovidAgentState) s;
        String position = covidAgentState.getPosition();

        for(SickPerson p: covidAgentState.getSickPersonsList()){
            if(p.getActualPosition().equals(position) && !p.getActualPosition().equals(p.getHomePosition())){
                if((p.getCantMultas()+1)>3){
                    covidAgentState.getSickPersonsList().remove(p);
                    covidAgentState.setTotalOfSickPersonHealted(covidAgentState.getTotalOfSickPersonHealted()+1);
                }
                else {
                    p.setActualPosition(p.getHomePosition());
                    p.setCantMultas(p.getCantMultas() + 1);
                }
                return covidAgentState;
            }
        }
        return null;
    }

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        this.execute((SearchBasedAgentState) ast);
        return est;
    }

    @Override
    public Double getCost() {
        return this.cost;
    }


    @Override
    public String toString() {
        return null;
    }
}
