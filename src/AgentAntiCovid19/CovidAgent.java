package AgentAntiCovid19;

import AgentAntiCovid19.actions.*;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.solver.search.DepthFirstSearch;
import frsf.cidisi.faia.solver.search.Search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CovidAgent extends SearchBasedAgent {
    private String searchMethod;

    public CovidAgent(HashMap<String, Collection<String>> map, ArrayList<SickPerson> sickPersonsList, String position, String searchMethod){
        // Inicializo el método de búsqueda elegido.
        this.searchMethod = searchMethod;

        // Inicializo la meta del agente.
        CovidGoal goal = new CovidGoal();

        // Inicializo el estado del agente.
        CovidAgentState agentState = new CovidAgentState();
        this.setAgentState(agentState);

        // Inicializo las acciones del agente.
        Vector<SearchAction> actions = new Vector<SearchAction>();

        actions.addElement(new GoNorth());
        actions.addElement(new GoSouth());
        actions.addElement(new GoEast());
        actions.addElement(new GoWest());
        actions.addElement(new MulctSickPerson());

        // Problema a resolver del agente
        Problem problem = new Problem(goal, agentState, actions);
        this.setProblem(problem);

    }


    //metodo que se utiliza para percibir sobre el ambiente
    @Override
    public void see(Perception percepcion) {
        this.getAgentState().updateState(percepcion);
    }

    //Este metodo es para decidir que accion tomar segun el metodo de busqueda
    @Override
    public Action selectAction() {
        //Metodos de busqueda
        //BreathFirstSearch searchStrategy = new BreathFirstSearch();
        DepthFirstSearch searchStrategy = new DepthFirstSearch();

        Search searchSolver = new Search(searchStrategy);

        // Usando el graphviz que manda el arvol a un archivo xml y se puede mostrar de forma gráfica
        searchSolver.setVisibleTree(Search.GRAPHVIZ_TREE);

        // Setear como se va a resolver el problema con busqueda
        this.setSolver(searchSolver);

        // Buscar las acciones para pasar al arbol de busqueda
        Action selectedAction = null;
        try {
            selectedAction = this.getSolver().solve(new Object[]{this.getProblem()});
        } catch (Exception ex) {
            Logger.getLogger(CovidAgent.class.getName()).log(Level.SEVERE, null, ex);
        }

        // regresar la accion definida
        return selectedAction;
    }
}
