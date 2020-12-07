package es.kokoro.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class OpcionesController {

    @FXML
    public Button btn_cerrar;
    @FXML
    public Button btn_socios;

    @FXML
    public void cerrarAccion(ActionEvent event) {

        Stage stage = (Stage) btn_cerrar.getScene().getWindow();
        stage.close();

    }

    @FXML
    public void abrirSocios(ActionEvent event2) throws Exception {
        Stage stage1 = (Stage) btn_socios.getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Socios.fxml"));
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
