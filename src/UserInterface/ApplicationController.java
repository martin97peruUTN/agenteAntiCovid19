package UserInterface;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ApplicationController implements Initializable {
    @FXML
    private Label lblPosition;
    @FXML
    private ChoiceBox methodSel;
    @FXML
    private Button startBtn = new Button();

    private String position, method;

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



}

