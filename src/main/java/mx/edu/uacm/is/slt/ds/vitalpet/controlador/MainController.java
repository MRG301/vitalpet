package mx.edu.uacm.is.slt.ds.vitalpet.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController {

    @FXML private Label headerLabel;

    @FXML
    public void initialize() {
        headerLabel.setText("VitalPet\nPanel Principal");
    }

    @FXML
    private void onMostrarAcercaDe() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/fxml/info-proyecto-view.fxml"));
        Parent root = loader.load();
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Acerca de VitalPet");
        dialog.setScene(new Scene(root));
        dialog.showAndWait();
    }
    // Cambiar nombre del metodo
    @FXML
    private void onEditarOperaciones() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/fxml/operacion-view.fxml"));
        Parent root = loader.load();
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("VitalPet - Gestión de Operaciones");
        dialog.setScene(new Scene(root));
        dialog.showAndWait();
    }

}