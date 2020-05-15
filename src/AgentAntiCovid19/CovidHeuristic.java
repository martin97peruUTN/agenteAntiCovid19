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
        Integer cost = null;
        int weight = 10;//Esto se puede cambiar
        //Ahora usaremos la metrica para tratar de buscar al que menos multas tiene
        if(agentState.getSeeSickPerson()){
            for (SickPerson i:sickPeople) {
                if(i.getActualPosition().equals(position)) {
                    cost = distance + weight*i.getCantMultas(); //Esto nos da el costo estimado para ir al nodo que tiene un enfermo
                    break;
                }
            }
        }else{
             cost = distance;
        }
        return cost;
    }
}
