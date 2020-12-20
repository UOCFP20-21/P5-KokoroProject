package es.kokoro.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InicioController {
    @FXML
    public Button btn_inicio;
    @FXML
    public Button btn_cerrar;

    @FXML
    public void cerrarAccion(ActionEvent event) {

        Stage stage = (Stage) btn_cerrar.getScene().getWindow();
        stage.close();

    }

    @FXML
    public void nuevaVentana(ActionEvent event2) throws Exception {
        Stage stage1 = (Stage) btn_inicio.getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Opciones.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage1.close();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

