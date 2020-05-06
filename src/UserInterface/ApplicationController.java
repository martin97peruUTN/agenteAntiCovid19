package UserInterface;

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
import java.util.ResourceBundle;

public class ApplicationController implements Initializable {
    @FXML
    private Label lblPosition;
    @FXML
    private ChoiceBox methodSel;
    @FXML
    private Button startBtn = new Button();

    private String position="A1", method;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblPosition.setText(position);

        methodSel.setItems(FXCollections.observableArrayList("Depth First Search", "Breath First Search", "A* Search", "Uniform Cost Search", "Greedy Search"));

        methodSel.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                method = t1.toString();
            }
        });
    }

    @FXML
    private void changeScene(Event event){
        Parent parent = null;
        GMapController controller;
        try {
            FXMLLoader loader = new FXMLLoader();
            Pane p = loader.load(getClass().getResource("guadalupeMap.fxml").openStream());
            controller = loader.getController();
            loader.setController(controller);

            Scene scene = new Scene(p, 1280,720);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("GuadalupeMap");
            window.setWidth(1280);
            window.setHeight(720);
            window.setScene(scene);
            window.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}

