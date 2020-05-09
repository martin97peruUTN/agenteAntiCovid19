package AgentAntiCovid19;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;


public class CovidGoal extends GoalTest {


    public boolean isGoalState(CovidAgentState agentState) {
        if (agentState.getSickPersonsList().isEmpty()){
            return true;
        }

        return false;
    }

    @Override
    public boolean isGoalState(AgentState agentState) {
        return false;
    }
}
