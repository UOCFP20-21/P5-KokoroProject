package es.kokoro.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class InicioController {
    public Label label1;

    public void clickPrueba(ActionEvent actionEvent) {
        label1.setText("Texto de prueba");
    }
}
