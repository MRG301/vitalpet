module mx.edu.uacm.is.slt.ds.vitalpet.vitalpet {
    requires javafx.controls;
    requires javafx.fxml;

    opens mx.edu.uacm.is.slt.ds.vitalpet.controlador to javafx.fxml, javafx.graphics;
    exports mx.edu.uacm.is.slt.ds.vitalpet.controlador;
    opens mx.edu.uacm.is.slt.ds.vitalpet.modelo to javafx.base;

}