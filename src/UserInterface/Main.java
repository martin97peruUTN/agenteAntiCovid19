package UserInterface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent app = FXMLLoader.load(getClass().getResource("application.fxml"));
        stage.setTitle("AntiCOVID19");
        stage.setScene(new Scene(app, 600, 400));
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
