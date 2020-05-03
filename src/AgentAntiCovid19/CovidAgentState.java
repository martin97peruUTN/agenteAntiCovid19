package AgentAntiCovid19;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

import java.util.ArrayList;

/**
 * @author Leandro
 */

public class CovidAgentState extends SearchBasedAgentState{
    private String posX;
    private String posY;
    private ArrayList<SickPerson> sickPersonsList = new ArrayList<SickPerson>();
    private ArrayList<String[]> nodes;

    public ArrayList<SickPerson> getSickPersonsList() {
        return sickPersonsList;
    }

    public void setSickPersonsList(ArrayList<SickPerson> sickPersonsList) {
        this.sickPersonsList = sickPersonsList;
    }

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
