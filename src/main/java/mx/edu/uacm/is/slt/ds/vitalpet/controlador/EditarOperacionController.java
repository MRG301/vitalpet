package mx.edu.uacm.is.slt.ds.vitalpet.controlador;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import mx.edu.uacm.is.slt.ds.vitalpet.modelo.EstadoOperacion;

public class EditarOperacionController {
    @FXML public DialogPane dialogPane;
    @FXML public TextField txtNombre;
    @FXML public ChoiceBox<EstadoOperacion> choiceEstado;
    @FXML public ButtonType btnAceptar;

    // Inicializa la lista de estados y valores iniciales
    public void initData(String nombreActual, EstadoOperacion estadoActual) {
        txtNombre.setText(nombreActual);
        choiceEstado.getItems().setAll(EstadoOperacion.values());
        choiceEstado.setValue(estadoActual);
    }

    // Validar antes de aceptar
    public boolean validar() {
        return !txtNombre.getText().trim().isEmpty() && choiceEstado.getValue() != null;
    }

    // Devuelve el nuevo nombre
    public String getNuevoNombre() {
        return txtNombre.getText().trim();
    }
    // Devuelve el nuevo estado
    public EstadoOperacion getNuevoEstado() {
        return choiceEstado.getValue();
    }

    public StringProperty nombreTextProperty() {
        return txtNombre.textProperty();
    }
}