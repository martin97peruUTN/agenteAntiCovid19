package AgentAntiCovid19;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;


public class CovidGoal extends GoalTest {

    @Override
    public boolean isGoalState(AgentState agentState) {
        CovidAgentState agentStateAux = (CovidAgentState) agentState;
        for(SickPerson p: agentStateAux.getSickPersonsList()){
            if(p.getActualPosition() != p.getHomePosition()){
                return false;
            }
        }
        return true;
    }

}
