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


}
