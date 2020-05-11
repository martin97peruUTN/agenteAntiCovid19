package UserInterface;

import AgentAntiCovid19.CSVToMatrix;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
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
    @FXML
    private Label visitedNodesLbl;
    @FXML
    private Label cantOfMucltsLbl;
    @FXML
    private Label totalGoLbl;
    @FXML
    private Label totalSPMLbl;

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

        //Acá mostramos los nodos visitados en la consola.
        System.out.println("Nodos visitados por el agente: ");
        for(int i=0;i<listaDeNodosVisitados.size();i++){
            System.out.println(listaDeNodosVisitados.get(i).getStreets());
            System.out.println("");
        }

        //Acá seteo la ListView de la ventana con el mapa, para mostrar los nodos visitados.
        /*ObservableList<VisitedNode> observableList = FXCollections.observableList(listaDeNodosVisitados);
        nodesListView.setItems(observableList);
        nodesListView.getSelectionModel().selectedItemProperty().addListener(((observableValue, visitedNode, t1) -> {
            String lat = nodesListView.getSelectionModel().getSelectedItem().getLat();
            String lng = nodesListView.getSelectionModel().getSelectedItem().getLng();
            addMarker(lat,lng);
        }));*/

        backBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                changeScene(mouseEvent);
            }
        });


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

    public void changeScene(MouseEvent event){
        Parent parent = null;
        try{
            parent = FXMLLoader.load(getClass().getResource("application.fxml"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        Scene scene = new Scene(parent, 600, 400);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("AntiCOVID19");
        window.setWidth(600);
        window.setHeight(400);
        window.setScene(scene);
        window.show();
    }

    public void setVisitedNodesLbl(String cantNodosVisitados) {
        visitedNodesLbl.setText(cantNodosVisitados + " Nodos.");
        visitedNodesLbl.setVisible(true);
    }

    public void setTotalGoLbl(String cantGoRealizados){
        totalGoLbl.setText(cantGoRealizados + " Nodos.");
        totalGoLbl.setVisible(true);
    }

    public void setCantOfMucltsLbl(String cantMultasRealizadas) {
        cantOfMucltsLbl.setText(cantMultasRealizadas + " Multas.");
        cantOfMucltsLbl.setVisible(true);
    }

    public void setTotalSPMLbl(String cantEnfermosMultados){
        totalSPMLbl.setText(cantEnfermosMultados + " Enfermos.");
        totalSPMLbl.setVisible(true);
    }
}
