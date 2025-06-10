package mx.edu.uacm.is.slt.ds.vitalpet.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InfoProyectoController {
    @FXML private Label lblVersion;
    @FXML private Label lblDesarrolladores;

    @FXML
    public void initialize() {
        lblVersion.setText("v1.0.1");
        lblDesarrolladores.setText("Rafael Garcia Mauricio\t16-002-0234\n" +
                "Angel Edgar Aguilar Vazquez\t14-003-0737\n" +
                "Alejandra Vanessa Martinez Maldonado\t15-003-0426\n" +
                "Oscar Regalado Pacheco\t18-003-1597\n" +
                "Castillo Zavala Eduardo Roberto\t18-003-0851");
    }
}