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

}
