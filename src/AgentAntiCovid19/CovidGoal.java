package AgentAntiCovid19;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;


public class CovidGoal extends GoalTest {

    @Override
    public boolean isGoalState(AgentState agentState) {
        boolean b = true;
        for(SickPerson p: ((CovidAgentState)agentState).getNewSickPersonsList()){
            if(!p.getActualPosition().equals(p.getHomePosition())){
                b=false;
            }
        }
        return b;
    }

}
