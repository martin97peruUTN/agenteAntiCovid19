package AgentAntiCovid19;

import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

public class CovidHeuristic implements IEstimatedCostFunction {
    @Override
    public double getEstimatedCost(NTree node) {
        CovidAgentState agentState = (CovidAgentState) node.getAgentState();
        //return (agentState.bestRouteToTarget());
        return 0.0;
    }
}
