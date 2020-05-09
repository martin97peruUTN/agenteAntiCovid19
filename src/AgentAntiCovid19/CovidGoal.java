package AgentAntiCovid19;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;


public class CovidGoal extends GoalTest {

    @Override
    public boolean isGoalState(AgentState agentState) {
        CovidAgentState aS = (CovidAgentState) agentState;
        for(SickPerson p: aS.getSickPersonsList()){
            if(!p.getActualPosition().equals(p.getHomePosition())){
                return false;
            }
        }
        return true;
    }
}
