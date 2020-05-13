package AgentAntiCovid19;

import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

import java.util.ArrayList;

public class CovidHeuristic implements IEstimatedCostFunction {
    @Override
    public double getEstimatedCost(NTree node) {
        CovidAgentState agentState = (CovidAgentState) node.getAgentState();
        ArrayList<SickPerson> sickPeople = agentState.getNewSickPersonsList();
        String position = agentState.getPosition();
        Integer distance =  agentState.getTotalOfGoRealized();
        Integer cost = null;
        int weithg = 10;//Esto se puede cambiar
        //Ahora usaremos la metrica para tratar de buscar al que menos multas tiene
        /*if(agentState.getSeeSickPerson()){
            for (SickPerson i:sickPeople) {
                if(i.getActualPosition()==position) {
                    cost = distance + weithg*i.getCantMultas(); //Esto nos da el costo estimado para ir al nodo que tiene un enfermo
                    break;
                }
            }
        }else{
             cost = distance;
        }*/
        //return cost;
        return 1;
    }
}
