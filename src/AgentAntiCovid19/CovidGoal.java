package AgentAntiCovid19;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;


public class CovidGoal extends GoalTest {

    @Override
    public boolean isGoalState(AgentState agentState) {
        boolean b = true;
        for(SickPerson sp: ((CovidAgentState)agentState).getSickPersonsList()){
            if(!(sp.getHomePosition().equals(sp.getActualPosition()))){
                b = false;
            }
        }
        //System.out.println("Estos son los print de b:");
        //System.out.println(String.valueOf(b));
        //System.out.println("Estos son los print de la otra condición:");
        //System.out.println(String.valueOf(((CovidAgentState)agentState).getSickPersonsList().isEmpty()));
        return ((((CovidAgentState)agentState).getSickPersonsList().isEmpty()) || b);
    }

}
