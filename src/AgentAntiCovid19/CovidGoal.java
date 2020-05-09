package AgentAntiCovid19;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;


public class CovidGoal extends GoalTest {


    public boolean isGoalState(CovidAgentState agentState) {

        for(SickPerson p: agentState.getSickPersonsList()){
            if(p.getActualPosition() != p.getHomePosition()){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isGoalState(AgentState agentState) {
        return false;
    }
}
