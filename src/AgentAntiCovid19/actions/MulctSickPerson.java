package AgentAntiCovid19.actions;

import AgentAntiCovid19.CovidAgentState;
import AgentAntiCovid19.CovidEnvironmentState;
import AgentAntiCovid19.SickPerson;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class MulctSickPerson extends SearchAction {
    private Double cost = 1.0;
    private Integer idSickPerson;

    public MulctSickPerson(){

    }

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        CovidAgentState covidAgentState = (CovidAgentState) s;
        String position = covidAgentState.getPosition();
        for(SickPerson p: covidAgentState.getSickPersonsList()){
            if(p.getActualPosition().equals(position) && !p.getActualPosition().equals(p.getHomePosition())){
                covidAgentState.setSeeSickPerson(false); //acá actualiza que el enfermo ya no está en ese nodo
                idSickPerson = p.getId();
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
        CovidAgentState covidAgentState = (CovidAgentState) ast;
        CovidEnvironmentState covidEnvironmentState = (CovidEnvironmentState) est;
        String position = ((CovidEnvironmentState) est).getAgentPosition();
        for(SickPerson p: covidEnvironmentState.getSickPersonsList()){
            if(p.getActualPosition().equals(position) && !p.getActualPosition().equals(p.getHomePosition())){
                covidAgentState.setSeeSickPerson(false); //acá actualiza que el enfermo está en ese nodo
                idSickPerson = p.getId();
                //Actualizo el estado del ambiente.
                if((p.getCantMultas()+1)>3){
                    covidEnvironmentState.getSickPersonsList().remove(p);
                }
                else{
                    p.setActualPosition(p.getHomePosition());
                    p.setCantMultas(p.getCantMultas() +1 );
                    p.setCantMultas(p.getCantMultas()+1);
                }
                //Actualizo el estado del agente.
                for(SickPerson sp: covidAgentState.getSickPersonsList()){
                    if (sp.equals(p)){
                        if((sp.getCantMultas()+1)>3) {
                            covidEnvironmentState.getSickPersonsList().remove(p);
                            covidAgentState.setTotalOfSickPersonHealted(covidAgentState.getTotalOfSickPersonHealted()+1);
                        }
                        else{
                            sp.setActualPosition(sp.getHomePosition());
                            sp.setCantMultas(sp.getCantMultas() + 1);
                        }
                        return covidEnvironmentState;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Double getCost() {
        return this.cost;
    }


    @Override
    public String toString() {
        return "Multar persona: " + idSickPerson;
    }
}
