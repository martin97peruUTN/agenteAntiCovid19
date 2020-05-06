package UserInterface;

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
    private ListView<VisitedNodes> nodesListView;
    @FXML
    private Button backBtn = new Button();

    //private ArrayList<VisitedNodes> visitedNodesList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        webEngine = webView.getEngine();
        webEngine.load(getClass().getResource("GoogleMapsAPI.html").toExternalForm());
    }
}
