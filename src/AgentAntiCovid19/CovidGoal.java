package AgentAntiCovid19;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;
/**
 *
 */

public class CovidGoal extends GoalTest {


    @Override
    public boolean isGoalState(AgentState agentState) {
        if(((CovidAgentState)))agentState).getSickPersonsList().isEmpty()){
            return true;
        }

        return false;
    }
}
