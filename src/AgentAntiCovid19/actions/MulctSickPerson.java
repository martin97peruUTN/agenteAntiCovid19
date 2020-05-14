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
    private Double cost = 1.0;

    public MulctSickPerson(){

    }

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        CovidAgentState covidAgentState = (CovidAgentState) s;
        String position = covidAgentState.getPosition();
        for(SickPerson p: covidAgentState.getNewSickPersonsList()){
            if(position.equals(p.getActualPosition())) {
                if (p.getActualPosition().equals(position) && !p.getActualPosition().equals(p.getHomePosition())) {
                    //covidAgentState.setSeeSickPerson(false); //acá actualiza que el enfermo ya no está en ese nodo
                    if ((p.getCantMultas() + 1) > 3) {
                        covidAgentState.getNewSickPersonsList().remove(p);
                        covidAgentState.setTotalOfMulctRealized(covidAgentState.getTotalOfMulctRealized() + 1);
                        covidAgentState.setTotalOfSickPersonHealted(covidAgentState.getTotalOfSickPersonHealted() + 1);
                    }
                    if ((p.getCantMultas() + 1) <= 3) {
                        p.setActualPosition(p.getHomePosition());
                        covidAgentState.setTotalOfMulctRealized(covidAgentState.getTotalOfMulctRealized() + 1);
                        p.setCantMultas(p.getCantMultas() + 1);
                    }
                    return covidAgentState;
                }
            }
        }
        return null;
    }

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        this.execute((SearchBasedAgentState) ast);
        return null;
    }

    @Override
    public Double getCost() {
        return this.cost;
    }

    @Override
    public String toString() {
        return "Multar enfermo.";
    }
}
