package UserInterface;

import AgentAntiCovid19.CSVToMatrix;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GMapController implements Initializable {

    @FXML
    private WebView webView;
    @FXML
    private WebEngine webEngine;
    @FXML
    private ScrollPane mapPanel;
    @FXML
    private ListView<VisitedNode> nodesListView;
    @FXML
    private Button backBtn = new Button();

    private ArrayList<VisitedNode> listaDeNodosVisitados;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        webEngine = webView.getEngine();
        webEngine.load(getClass().getResource("GoogleMapsAPI.html").toExternalForm());

        webEngine.getLoadWorker().stateProperty().addListener((((observableValue, oldState, t1) -> {
            if(t1 == Worker.State.SUCCEEDED){
                addPolyline();
            }
        })));
        //Tomo los nodos del archivo .csv de nodos.
        CSVToMatrix converter = new CSVToMatrix(';');
        String path = "NODOS-Mapa.csv";
        ArrayList<String[]> nodes = converter.fileToMatrix(path);
        //Tomo los nodos visitados del archivo generado .csv de nodos visitados. Este .csv se genera con la ejecución del agente.
        path = "NODOS-Visitados.csv";
        ArrayList<String[]> nodosVisitados = converter.fileToMatrix(path);

        listaDeNodosVisitados = new ArrayList<VisitedNode>();

        //Acá convierto los nodos visitados de String a objetos VisitedNode
        for(int i=0;i<nodosVisitados.size();i++){
            for(int j=0;j<nodes.size();j++){
                if(nodosVisitados.get(i)[0].equals(nodes.get(j)[0])){
                    listaDeNodosVisitados.add(new VisitedNode(nodes.get(j)[0], nodes.get(j)[1], nodes.get(j)[2], nodes.get(j)[3]));
                }
            }
        }



    }

    public void addMarker(String lat, String lng) {
        webEngine.executeScript("document.removeMarker()");
        webEngine.executeScript("document.marker(" + lat + "," + lng + ")");
    }

    public void addPolyline() {
        for (int i = 0; i < listaDeNodosVisitados.size(); i++) {
            webEngine.executeScript("document.addPolylineCoord("
                    + listaDeNodosVisitados.get(i).getLat()
                    + " , "
                    + listaDeNodosVisitados.get(i).getLng()
                    + ")");
        }
        webEngine.executeScript("document.runSnapToRoad()");
    }
}
