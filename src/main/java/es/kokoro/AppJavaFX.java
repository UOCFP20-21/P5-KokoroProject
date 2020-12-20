package es.kokoro;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class AppJavaFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/view/inicio.fxml"));
        primaryStage.setTitle("Entreculturas");
        primaryStage.setScene(new Scene(root, 388, 577));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
