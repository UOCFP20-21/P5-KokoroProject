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
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import static es.kokoro.commons.FormatFecha.FFStringToDate;

public class ModificarSociosController implements Initializable {
    @FXML
    public Button btn_atras;
    @FXML
    public Button btn_cerrar;
    @FXML
    public Button btn_ModificarSocio;
    @FXML
    public Label lbl_idsocioValue;
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
    ComboBox<String> cmb_Periodo;
    @FXML
    public TextField txt_cuota;
    @FXML
    public CheckBox chck_estado;
    private Socio socio;

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
    public void modificar(ActionEvent event5) throws Exception {

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
        Boolean estado = chck_estado.isSelected();

        Socio socioAModificar = new Socio(nombre, apellidos, DNI, nacionalidad, direccion, poblacion, telefono, email, socio.getIdSocio(), periodo, cuota, estado, fechaNac);

        DAO<Socio> mySqlSocioDao = MySqlFactoryDAO.getMySqlFactoryDAO(Socio.class);
        mySqlSocioDao.update(socioAModificar);

        Stage stage = (Stage) this.btn_ModificarSocio.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Socios.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(root1));
        stage.close();
        stage2.show();
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
        try {
            lbl_idsocioValue.setText(Integer.toString(socio.getIdSocio()));
            txt_nombre.setText(socio.getNombre());
            txt_apellidos.setText(socio.getApellidos());
            txt_dni.setText(socio.getIdentificador());
            txt_nacionalidad.setText(socio.getNacionalidad());
            txt_direccion.setText(socio.getDireccion());
            txt_poblacion.setText(socio.getPoblacion());
            txt_telefono.setText(socio.getTelefono());
            txt_email.setText(socio.getEmail());
            dt_FechaNac.setValue(socio.getFechaNac().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            cmb_Periodo.getSelectionModel().select(socio.getPeriodo().getNombrePeriodo());
            txt_cuota.setText(Double.toString(socio.getCuota()));
            chck_estado.setSelected(socio.isEstado());
        } catch (Exception exception) {
            System.out.println(exception);
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
