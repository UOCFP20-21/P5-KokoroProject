package es.kokoro.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ModificarSociosController {
    @FXML
    public Button btn_atras;
    @FXML
    public Button btn_cerrar;

    @FXML
    public void atrasAccion(ActionEvent event5) throws Exception {
        Stage stage2 = (Stage) btn_atras.getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Opciones.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage2.close();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void cerrarAccion(ActionEvent event) {

        Stage stage = (Stage) btn_cerrar.getScene().getWindow();
        stage.close();

    }
}
