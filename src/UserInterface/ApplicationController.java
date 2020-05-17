package UserInterface;

import AgentAntiCovid19.CovidMain;
import frsf.cidisi.faia.exceptions.PrologConnectorException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ApplicationController implements Initializable {
    @FXML
    private Label lblPosition;
    @FXML
    private ChoiceBox methodSel;
    @FXML
    private Button startBtn = new Button();

    private String position="A8 - Pedro de Vega y Echagüe.", method;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblPosition.setText(position);

        methodSel.setItems(FXCollections.observableArrayList("DepthFirstSearch", "BreathFirstSearch", "A*Search", "UniformCostSearch", "GreedySearch"));

        methodSel.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                method = t1.toString();
            }
        });
    }

    @FXML
    private void changeScene(Event event){
        if(method!=null) {
            String args[] = {method};
            ArrayList<String> displayInfo = new ArrayList<>();
            try{
                displayInfo = CovidMain.main(args);
            }
            catch (PrologConnectorException e){
                e.printStackTrace();
            }


            Parent parent = null;
            GMapController controller;
            try {
                FXMLLoader loader = new FXMLLoader();
                Pane p = loader.load(getClass().getResource("guadalupeMap.fxml").openStream());
                controller = loader.getController();
                loader.setController(controller);

                controller.setVisitedNodesLbl(displayInfo.get(0));
                controller.setTotalGoLbl(displayInfo.get(1));
                controller.setTotalSPHLbl(displayInfo.get(2));

                /*controller.setVisitedNodesLbl("15");
                controller.setTotalGoLbl("20");
                controller.setCantOfMucltsLbl("5");
                controller.setTotalSPHLbl("1");*/

                Scene scene = new Scene(p, 1280, 700);

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setTitle("GuadalupeMap");
                window.setWidth(1280);
                window.setHeight(700);
                window.setScene(scene);
                window.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("No ha seleccionado un método de búsqueda.");
        }
    }
}

