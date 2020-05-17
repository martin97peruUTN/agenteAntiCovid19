package AgentAntiCovid19;

import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

import java.util.ArrayList;

public class CovidHeuristic implements IEstimatedCostFunction {
    @Override
    public double getEstimatedCost(NTree node) {
        CovidAgentState agentState = (CovidAgentState) node.getAgentState();
        return ((agentState.totalDistance()*5.0)+(agentState.cantEnfermosEnSensores()*2.0)+
                (agentState.cantEnfermosFueraDeCasa()*3.0));
    }
}