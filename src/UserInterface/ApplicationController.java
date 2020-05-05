package UserInterface;

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

    private String position;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblPosition.setText(position);

        methodSel.setItems(FXCollections.observableArrayList("Depth First Search", "Breath First Search", "A* Search", "Uniform Cost Search", "Greedy Search"));
    }



}

