package mx.edu.uacm.is.slt.ds.vitalpet.controlador;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;

public class NuevaOperacionController {
    @FXML public DialogPane dialogPane;
    @FXML public TextField txtId;
    @FXML public TextField txtNombre;
    @FXML public ButtonType btnAceptar;  // coincidente con fx:id

    public boolean validar() {
        return !txtNombre.getText().trim().isEmpty();
    }

    // Combina y devuelve el resultado "id,nombre"
    public String getResultado() {
        return txtId.getText().trim() + "," + txtNombre.getText().trim();
    }

    public StringProperty idTextProperty() {
        return txtId.textProperty();
    }
    public StringProperty nombreTextProperty() {
        return txtNombre.textProperty();
    }
    public int getId() {
        return Integer.parseInt(txtId.getText().trim());
    }

    public String getNombre() {
        return txtNombre.getText().trim();
    }
}