package mx.edu.uacm.is.slt.ds.vitalpet.controlador;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import mx.edu.uacm.is.slt.ds.vitalpet.modelo.*;
import mx.edu.uacm.is.slt.ds.vitalpet.service.GestorDeOperaciones;

import java.io.IOException;
import java.util.Optional;

public class OperacionController {

    // Operaciones
    @FXML private TableView<Operacion> tabla;
    @FXML private TableColumn<Operacion, Integer> colId;
    @FXML private TableColumn<Operacion, String> colNombre;
    @FXML private TableColumn<Operacion, EstadoOperacion> colEstado;

    // Tareas
    @FXML private TableView<Tarea> tablaTareas;
    @FXML private TableColumn<Tarea, Integer>       tColId;
    @FXML private TableColumn<Tarea, String>        tColNombre;
    @FXML private TableColumn<Tarea, TipoPrioridad> tColPrioridad;
    @FXML private TableColumn<Tarea, EstadoTarea>   tColEstado;

    @FXML private Button btnNuevo;
    @FXML private Button btnEditar;
    @FXML private Button btnEliminar;
    @FXML private Button btnGestionarTareas;

    @FXML private Button btnEditarTarea;
    @FXML private Button btnEliminarTarea;

    private final GestorDeOperaciones gestor = new GestorDeOperaciones();

    @FXML
    public void initialize() {

        // columnas de Operaciones
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        // columnas de Tareas
        tColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tColNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tColPrioridad.setCellValueFactory(new PropertyValueFactory<>("prioridad"));
        tColEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        // Carga Operaciones
        tabla.setItems(FXCollections.observableArrayList(gestor.listarOperaciones()));

        // Listener: cuando cambia la operación seleccionada, actualiza la tabla de tareas
        tabla.getSelectionModel().selectedItemProperty().addListener((obs, oldOp, newOp) -> {
            if (newOp != null) {
                tablaTareas.setItems(FXCollections.observableArrayList(newOp.getListaTareas()));
            } else {
                tablaTareas.getItems().clear();
            }
        });

        // quita Editar y Eliminar de Tarea si no hay selección
        btnEditarTarea.disableProperty().bind(
                tablaTareas.getSelectionModel().selectedItemProperty().isNull()
        );
        btnEliminarTarea.disableProperty().bind(
                tablaTareas.getSelectionModel().selectedItemProperty().isNull()
        );
    }

    @FXML
    private void onEditar() {
        Operacion sel = tabla.getSelectionModel().getSelectedItem();
        if (sel == null) {
            showAlert("Selección requerida", "Selecciona una operación para editar");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/editar-operacion-view.fxml")
            );
            DialogPane pane = loader.load();
            EditarOperacionController ctrl = loader.getController();

            // inicializa los campos con los valores
            ctrl.initData(sel.getNombre(), sel.getEstado());

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Editar Operación");
            dialog.setDialogPane(pane);

            // guardar hasta que haya texto y estado seleccionado
            Button okButton = (Button) pane.lookupButton(ctrl.btnAceptar);
            okButton.disableProperty().bind(
                    ctrl.nombreTextProperty().isEmpty()
                            .or(ctrl.choiceEstado.valueProperty().isNull())
            );

            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == ctrl.btnAceptar) {
                if (!ctrl.validar()) {
                    showAlert("Entrada invalida", "El nombre no puede quedar vacio y debes elegir un estado.");
                    return;
                }
                // Aplica cambios editados al objeto
                sel.setNombre(ctrl.getNuevoNombre());
                sel.setEstado(ctrl.getNuevoEstado());
                tabla.refresh();
            }

        } catch (IOException e) {
            showAlert("Error interno", "No se pudo abrir la edición");
        }
    }


    @FXML
    private void onEliminar() {
        Operacion sel = tabla.getSelectionModel().getSelectedItem();
        if (sel == null) {
            showAlert("Selección requerida", "Selecciona una operación para eliminar");
            return;
        }
        boolean ok = gestor.eliminarOperacion(sel);
        if (ok) {
            tabla.getItems().remove(sel);
        } else {
            showAlert("Error", "No se pudo eliminar la operación");
        }
    }

    @FXML
    private void onNuevo() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/nuevo-operacion-view.fxml")
            );
            DialogPane pane = loader.load();
            NuevaOperacionController ctrl = loader.getController();

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Nueva Operación");
            dialog.setDialogPane(pane);

            Button ok = (Button) pane.lookupButton(ctrl.btnAceptar);
            ok.disableProperty().bind(ctrl.nombreTextProperty().isEmpty());

            Optional<ButtonType> res = dialog.showAndWait();
            if (res.isPresent() && res.get() == ctrl.btnAceptar && ctrl.validar()) {
                Operacion op = gestor.crearOperacion(ctrl.getNombre());
                tabla.getItems().add(op);
                tabla.getSelectionModel().select(op);
            }
        } catch (IOException e) {
            showAlert("Error", "No se pudo abrir el formulario");
        }
    }


    @FXML
    private void onGestionarTareas() {
        Operacion selOp = tabla.getSelectionModel().getSelectedItem();
        if (selOp == null) {
            showAlert("Selecciona operación", "Elige primero una operación");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/tarea-view.fxml")
            );
            DialogPane pane = loader.load();
            TareaController ctrl = loader.getController();

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Nueva Tarea para Operación " + selOp.getId());
            dialog.setDialogPane(pane);

            // desactiva Guardar si no hay nombre o no se selecciona prioridad o estado
            Button ok = (Button) pane.lookupButton(ctrl.btnAceptar);
            ok.disableProperty().bind(
                    ctrl.nombreTextProperty().isEmpty()
                            .or(ctrl.choicePrioridad.valueProperty().isNull())
                            .or(ctrl.choiceEstado.valueProperty().isNull())
            );

            Optional<ButtonType> res = dialog.showAndWait();
            if (res.isPresent() && res.get() == ctrl.btnAceptar && ctrl.validar()) {

                int newTaskId = selOp.generarIdTarea();
                Tarea t = new Tarea(newTaskId,
                        ctrl.getNombre(),
                        ctrl.getPrioridad());
                t.setEstado(ctrl.getEstado());
                selOp.agregarTarea(t);

                // actualiza la tabla de tareas
                tablaTareas.setItems(
                        FXCollections.observableArrayList(selOp.getListaTareas())
                );
            }

        } catch (IOException e) {
            showAlert("Error", "No se pudo cargar el formulario de tareas");
        }
    }


    @FXML
    private void onEditarTarea() {
        Tarea sel = tablaTareas.getSelectionModel().getSelectedItem();
        if (sel == null) return;

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/tarea-view.fxml")
            );
            DialogPane pane = loader.load();
            TareaController ctrl = loader.getController();

            ctrl.initData(
                    sel.getId(),
                    sel.getNombre(),
                    sel.getPrioridad(),
                    sel.getEstado()
            );

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Editar Tarea ID " + sel.getId());
            dialog.setDialogPane(pane);

            Button ok = (Button) pane.lookupButton(ctrl.btnAceptar);
            ok.disableProperty().bind(
                    ctrl.nombreTextProperty().isEmpty()
                            .or(ctrl.choicePrioridad.valueProperty().isNull())
                            .or(ctrl.choiceEstado.valueProperty().isNull())
            );

            Optional<ButtonType> res = dialog.showAndWait();
            if (res.isPresent() && res.get() == ctrl.btnAceptar && ctrl.validar()) {

                sel.setNombre(ctrl.getNombre());
                sel.setPrioridad(ctrl.getPrioridad());
                sel.setEstado(ctrl.getEstado());
                tablaTareas.refresh();
            }

        } catch (IOException e) {
            showAlert("Error", "No se pudo abrir la edición de tarea");
        }
    }


    @FXML
    private void onEliminarTarea() {
        Tarea selTarea = tablaTareas.getSelectionModel().getSelectedItem();
        if (selTarea == null) return;  // por si acaso

        // Quitar del modelo
        Operacion selOp = tabla.getSelectionModel().getSelectedItem();
        selOp.eliminarTarea(selTarea);

        // Refrescar la vista
        tablaTareas.setItems(
                FXCollections.observableArrayList(selOp.getListaTareas())
        );
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}