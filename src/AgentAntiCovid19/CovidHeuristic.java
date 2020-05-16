package AgentAntiCovid19;

import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

import java.util.ArrayList;

public class CovidHeuristic implements IEstimatedCostFunction {
    @Override
    public double getEstimatedCost(NTree node) {
        CovidAgentState agentState = (CovidAgentState) node.getAgentState();
        ArrayList<SickPerson> sickPeople = agentState.getSickPersonsList();
        String position = agentState.getPosition();
        Integer distance =  agentState.getTotalOfGoRealized();

        return ((Double.valueOf(sickPeople.size())*0.5)+(agentState.cantMultasQueFaltanHacer()*0.3)+
                agentState.cantEnfermosFueraDeCasa()*0.2);
    }
}