package AgentAntiCovid19;

import AgentAntiCovid19.actions.*;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.solver.search.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CovidAgent extends SearchBasedAgent {
    private String searchMethod;

    //Constructor
    public CovidAgent(HashMap<String, Collection<String>> map, ArrayList<SickPerson> sickPersonsList, ArrayList<Sensor> sensorslist, String searchMethod){
        // Inicializo el método de búsqueda elegido.
        this.searchMethod = searchMethod;

        // Inicializo la meta del agente.
        CovidGoal goal = new CovidGoal();

        // Inicializo el estado del agente.
        CovidAgentState agentState = new CovidAgentState(map,sensorslist, sickPersonsList,"A8");
        agentState.initState();
        this.setAgentState(agentState);

        // Inicializo las acciones del agente.
        Vector<SearchAction> actions = new Vector<SearchAction>();

        // Cargo las acciones Go del archivo NODOS-Mapa.csv
        CSVToMatrix converter;
        String path = "NODOS-Mapa.csv";
        converter = new CSVToMatrix(';');
        ArrayList<String[]> nodes = converter.fileToMatrix(path);

        for (int i = 0; i < nodes.size(); i++) {
            actions.addElement(new Go(nodes.get(i)[0]));
        }

        //Agrego la acción MulctSickPerson (multar enfermo).
        actions.addElement(new MulctSickPerson());

        //Seteo problema a resolver del agente.
        Problem problem = new Problem(goal, agentState, actions);
        this.setProblem(problem);

    }


    //Este método es usado para percibir el ambiente
    @Override
    public void see(Perception percepcion) {
        this.getAgentState().updateState(percepcion);
    }

    //Acá en este método se decide qué acción tomar según el método de búsqueda elegido en la interfaz
    @Override
    public Action selectAction() {

        if(this.searchMethod.equals("Depth First Search")){
            //Método de búsqueda en profundidad
            DepthFirstSearch depthFirstSearch = new DepthFirstSearch();
            Search dfsSolver = new Search(depthFirstSearch);
            dfsSolver.setVisibleTree(Search.XML_TREE);
            //Seteo el search solver
            this.setSolver(dfsSolver);
        }
        if(this.searchMethod.equals("Breath First Search")){
            //Método de búsqueda en anchura
            BreathFirstSearch breathFirstSearch = new BreathFirstSearch();
            Search bfsSolver = new Search(breathFirstSearch);
            bfsSolver.setVisibleTree(Search.XML_TREE);
            //Seteo el search solver
            this.setSolver(bfsSolver);
        }
        if(this.searchMethod.equals("A* Search")){
            //Método de búsqueda A*
            IStepCostFunction aCostFunction = new CovidCostFunction();
            IEstimatedCostFunction heuristicAStar = new CovidHeuristic();
            AStarSearch aStarSearch = new AStarSearch(aCostFunction, heuristicAStar);
            Search sasSolver = new Search(aStarSearch);
            sasSolver.setVisibleTree(Search.XML_TREE);
            //Seteo el search solver
            this.setSolver(sasSolver);
        }
        if(this.searchMethod.equals("Uniform Cost Search")){
            //Método de búsqueda de costo uniforme
            IStepCostFunction uniCostFunction = new CovidCostFunction();
            UniformCostSearch uniCostSearch = new UniformCostSearch(uniCostFunction);
            Search uniCostSolver = new Search(uniCostSearch);
            uniCostSolver.setVisibleTree(Search.XML_TREE);
            //Seteo el search solver
            this.setSolver(uniCostSolver);
        }
        if(this.searchMethod.equals("Greedy Search")){
            //Método de búsqueda ávara
            IEstimatedCostFunction gHeuristicFunction = new CovidHeuristic();
            GreedySearch gSearch = new GreedySearch(gHeuristicFunction);
            Search gsSolver = new Search(gSearch);
            gsSolver.setVisibleTree(Search.XML_TREE);
            //Seteo el search solver
            this.setSolver(gsSolver);
        }

        //Buscar las acciones para pasar al árbol de búsqueda
        Action selectedAction = null;
        try {
            selectedAction = this.getSolver().solve(new Object[]{this.getProblem()});
        } catch (Exception ex) {
            Logger.getLogger(CovidAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Regresar la acción definida
        return selectedAction;
    }

}
