package es.kokoro;

import es.kokoro.controller.InicioController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.beans.EventHandler;
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
