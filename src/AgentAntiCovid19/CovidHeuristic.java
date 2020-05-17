package AgentAntiCovid19;

import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

import java.util.ArrayList;

public class CovidHeuristic implements IEstimatedCostFunction {
    @Override
    public double getEstimatedCost(NTree node) {
        CovidAgentState agentState = (CovidAgentState) node.getAgentState();
        return ((agentState.cantEnfermosFueraDeCasa()*2.0)+(agentState.cantEnfermosEnSensores()*1.0));
    }
}