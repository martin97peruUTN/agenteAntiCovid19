package AgentAntiCovid19;

import AgentAntiCovid19.actions.Go;
import AgentAntiCovid19.actions.MulctSickPerson;
import frsf.cidisi.faia.solver.search.IStepCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

public class CovidCostFunction implements IStepCostFunction {
    @Override
    public double calculateCost(NTree node) {
        if(node.getAction().getClass().equals(MulctSickPerson.class)){
            return node.getAction().getCost();
        }
        else {return node.getAction().getCost()+1.0;}
    }
}
