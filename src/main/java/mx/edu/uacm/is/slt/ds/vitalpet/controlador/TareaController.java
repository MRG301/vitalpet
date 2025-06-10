package mx.edu.uacm.is.slt.ds.vitalpet.controlador;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import mx.edu.uacm.is.slt.ds.vitalpet.modelo.EstadoTarea;
import mx.edu.uacm.is.slt.ds.vitalpet.modelo.TipoPrioridad;

public class TareaController {
    @FXML public DialogPane dialogPane;
    @FXML private TextField txtNombre;
    @FXML public ChoiceBox<TipoPrioridad> choicePrioridad;
    @FXML public ChoiceBox<EstadoTarea> choiceEstado;
    @FXML public ButtonType btnAceptar;

    @FXML
    public void initialize() {
        // Carga los valores de los enums en los ChoiceBox
        choicePrioridad.getItems().setAll(TipoPrioridad.values());
        choiceEstado   .getItems().setAll(EstadoTarea.values());
    }

    public void initData(Integer id, String nombre,
                         TipoPrioridad prioridad, EstadoTarea estado) {

        txtNombre.setText(nombre != null ? nombre : "");
        choicePrioridad.setValue(prioridad);
        choiceEstado.setValue(estado);
    }

    // Devuelve true si el nombre no está vacío y hay una prioridad y estado seleccionados
    public boolean validar() {
        return !txtNombre.getText().trim().isEmpty()
                && choicePrioridad.getValue() != null
                && choiceEstado.getValue() != null;
    }

    // Devuelve el nombre ingresado
    public String getNombre() {
        return txtNombre.getText().trim();
    }

    // Devuelve la prioridad
    public TipoPrioridad getPrioridad() {
        return choicePrioridad.getValue();
    }

    // Devuelve el estado
    public EstadoTarea getEstado() {
        return choiceEstado.getValue();
    }

    // Permite enlazar la desactivación con el botón de
    public StringProperty nombreTextProperty() {
        return txtNombre.textProperty();
    }
}