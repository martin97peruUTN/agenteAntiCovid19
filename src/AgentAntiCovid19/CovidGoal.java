package AgentAntiCovid19;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;
/**
 * @author Leandro
 */

public class CovidGoal extends GoalTest {
    @Override
    public boolean isGoalState(AgentState agentState) {
        if(((CovidAgentState)))agentState).getsickPersonsList().isEmpty()){
            return true;
        }

        return false;
    }
}
