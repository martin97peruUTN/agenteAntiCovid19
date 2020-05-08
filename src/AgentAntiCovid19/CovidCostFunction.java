package AgentAntiCovid19;

import frsf.cidisi.faia.solver.search.IStepCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

public class CovidCostFunction implements IStepCostFunction {
    @Override
    public double calculateCost(NTree node) {
        return node.getAction().getCost();
    }
}
