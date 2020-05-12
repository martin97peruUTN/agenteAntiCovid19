package AgentAntiCovid19;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;


public class CovidGoal extends GoalTest {

    @Override
    public boolean isGoalState(AgentState agentState) {
        boolean b = true;
        for(SickPerson sp: ((CovidAgentState)agentState).getSickPersonsList()){
            if(!sp.getHomePosition().contentEquals(sp.getActualPosition())){
                b = false;
            }
        }
        if((((CovidAgentState)agentState).getSickPersonsList().isEmpty()) || b){
            return true;
        }
        return false;
    }

}
