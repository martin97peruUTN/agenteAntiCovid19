package AgentAntiCovid19;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

import java.util.ArrayList;

/**
 * @author Leandro
 */

public class CovidAgentState extends SearchBasedAgentState{
    private String position;
    private ArrayList<SickPerson> sickPersonsList = new ArrayList<SickPerson>();


    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public SearchBasedAgentState clone() {
        return null;
    }

    @Override
    public void updateState(Perception p) {

    }

    @Override
    public void initState() {

    }

    @Override
    public String toString() {
        return null;
    }
}
