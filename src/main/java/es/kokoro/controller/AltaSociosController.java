package es.kokoro.controller;

import es.kokoro.dao.DAO;
import es.kokoro.dao.mysql.MySqlFactoryDAO;
import es.kokoro.enums.Periodo;
import es.kokoro.model.Socio;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import static es.kokoro.commons.FormatFecha.FFStringToDate;

public class AltaSociosController implements Initializable {
    @FXML
    public Button btn_atras;
    @FXML
    public Button btn_cerrar;
    @FXML
    public Button btn_NuevoSocio;
    @FXML
    public TextField txt_nombre;
    @FXML
    public TextField txt_apellidos;
    @FXML
    public TextField txt_dni;
    @FXML
    public TextField txt_nacionalidad;
    @FXML
    public TextField txt_direccion;
    @FXML
    public DatePicker dt_FechaNac;
    @FXML
    public TextField txt_poblacion;
    @FXML
    public TextField txt_telefono;
    @FXML
    public TextField txt_email;
    @FXML
    public ComboBox<String> cmb_Periodo;
    @FXML
    public TextField txt_cuota;

    @FXML
    public void atrasAccion(ActionEvent event5) throws Exception {
        Stage stage2 = (Stage) btn_atras.getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Socios.fxml"));
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

      @FXML
    public void nuevoSocio(ActionEvent event3) throws Exception {
        try {
            String nombre = this.txt_nombre.getText();
            String apellidos = this.txt_apellidos.getText();
            String DNI = this.txt_dni.getText();
            String nacionalidad = this.txt_nacionalidad.getText();
            String direccion = this.txt_direccion.getText();
            String poblacion = this.txt_poblacion.getText();
            String telefono = this.txt_telefono.getText();
            String email = this.txt_email.getText();
            Date fechaNac = FFStringToDate(dt_FechaNac.getEditor().getText(), "dd/mm/yyyy");
            Periodo periodo = Periodo.valueOf(Periodo.getEnumByString(this.cmb_Periodo.getSelectionModel().getSelectedItem()));
            Double cuota = Double.parseDouble(this.txt_cuota.getText());

            Socio socio = new Socio ( nombre, apellidos,DNI,nacionalidad,direccion, poblacion,telefono,email,null, periodo,cuota,true, fechaNac);

            DAO<Socio> mySqlSocioDao = MySqlFactoryDAO.getMySqlFactoryDAO(Socio.class);
            mySqlSocioDao.save(socio);

            Stage stage = (Stage)this.btn_NuevoSocio.getScene().getWindow();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Socios.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage2 = new Stage();
                stage2.setScene(new Scene(root1));
                stage.close();
                stage2.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.cmb_Periodo.setItems(
                FXCollections.observableArrayList(
                        Periodo.MES.getNombrePeriodo(), Periodo.TRI.getNombrePeriodo(), Periodo.ANU.getNombrePeriodo()
        ));
        this.cmb_Periodo.getSelectionModel().selectFirst();
    }
}
