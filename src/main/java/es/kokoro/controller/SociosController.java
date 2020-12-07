package es.kokoro.controller;

import es.kokoro.dao.DAO;
import es.kokoro.dao.mysql.MySQLSocioDAO;
import es.kokoro.dao.mysql.MySqlFactoryDAO;
import es.kokoro.model.Socio;
import javafx.beans.property.Property;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SociosController implements Initializable {
    @FXML
    public Button btn_cerrar;
    @FXML
    public Button btn_ModificarSocio;
    @FXML
    public Button btn_EliminarSocio;
    @FXML
    public Button btn_NuevoSocio;
    @FXML
    public Button btn_atras;
    @FXML
    public TableView<Socio> tbl_ListadoSocios;
    @FXML
    public TableColumn Clmn_idSocio;
    @FXML
    public TableColumn Clmn_Nombre;
    @FXML
    public TableColumn Clmn_Apellidos;
    @FXML
    public TableColumn Clmn_DNI;
    @FXML
    public TableColumn Clmn_Nacionalidad;
    @FXML
    public TableColumn Clmn_Direccion;
    @FXML
    public TableColumn Clmn_Poblacion;
    @FXML
    public TableColumn Clmn_Telefono;
    @FXML
    public TableColumn Clmn_Email;
    @FXML
    public TableColumn Clmn_FechaNac;
    @FXML
    public TableColumn Clmn_Periodo;
    @FXML
    public TableColumn Clmn_Cuota;
    @FXML
    public TableColumn Clmn_Estado;

    private Socio socios;



    @FXML
    public void cerrarAccion(ActionEvent event) {

        Stage stage = (Stage) btn_cerrar.getScene().getWindow();
        stage.close();

    }

    @FXML
    public void modificarSocio(ActionEvent event2) throws Exception {
        //TODO FALTA HACER
        Stage stage2 = (Stage) btn_ModificarSocio.getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ModificarSocio.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void eliminarSocio(ActionEvent event3) throws Exception {

        //TODO comprobar que hay una row seleccionada, falta sacar mensaje

        DAO<Socio> mySqlSocioDao = MySqlFactoryDAO.getMySqlFactoryDAO(Socio.class);

        Socio socioAEliminar = tbl_ListadoSocios.getSelectionModel().getSelectedItem();

        mySqlSocioDao.delete(socioAEliminar);
        tbl_ListadoSocios.getItems().remove(socioAEliminar);

    }

    @FXML
    public void nuevoSocio(ActionEvent event4) throws Exception {
        //TODO FALTA HACER
        Stage stage4 = (Stage) btn_NuevoSocio.getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AltaSocios.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void atrasAccion(ActionEvent event5) throws Exception {
        Stage stage5 = (Stage) btn_atras.getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Opciones.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage5.close();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            DAO<Socio> mySQLSocioDAO = MySqlFactoryDAO.getMySqlFactoryDAO(Socio.class);

            List<Socio> socios = mySQLSocioDAO.getAll();

            ObservableList<Socio> sociosData = FXCollections.observableArrayList(socios);

            Clmn_idSocio.setCellValueFactory(new PropertyValueFactory<>("IdSocio"));
            Clmn_Nombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
            Clmn_Apellidos.setCellValueFactory(new PropertyValueFactory<>("Apellidos"));
            Clmn_DNI.setCellValueFactory(new PropertyValueFactory<>("Identificador"));
            Clmn_Nacionalidad.setCellValueFactory(new PropertyValueFactory<>("Nacionalidad"));
            Clmn_Direccion.setCellValueFactory(new PropertyValueFactory<>("Direccion"));
            Clmn_Poblacion.setCellValueFactory(new PropertyValueFactory<>("Poblacion"));
            Clmn_Telefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
            Clmn_Email.setCellValueFactory(new PropertyValueFactory<>("Email"));
            Clmn_FechaNac.setCellValueFactory(new PropertyValueFactory<>("FechaNac"));
            Clmn_Periodo.setCellValueFactory(new PropertyValueFactory<>("Periodo"));
            Clmn_Cuota.setCellValueFactory(new PropertyValueFactory<>("Cuota"));
            Clmn_Estado.setCellValueFactory(new PropertyValueFactory<>("Estado"));

            tbl_ListadoSocios.setItems(sociosData);

        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
