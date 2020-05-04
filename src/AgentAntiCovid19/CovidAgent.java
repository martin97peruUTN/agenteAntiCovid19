package AgentAntiCovid19;

import AgentAntiCovid19.actions.*;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.solver.search.BreathFirstSearch;
import frsf.cidisi.faia.solver.search.DepthFirstSearch;
import frsf.cidisi.faia.solver.search.Search;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CovidAgent extends SearchBasedAgent {

    public CovidAgent(){
        // Meta del agente
        CovidGoal goal = new CovidGoal();

        // Estado del agente
        CovidAgentState agentState = new CovidAgentState();
        this.setAgentState(agentState);

        // Acciones del agente
        Vector<SearchAction> actions = new Vector<SearchAction>();

        actions.addElement(new IrNorte());
        actions.addElement(new IrSur());
        actions.addElement(new IrEste());
        actions.addElement(new IrOeste());
        actions.addElement(new MultarEnfermo());

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
