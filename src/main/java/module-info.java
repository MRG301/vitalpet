module mx.edu.uacm.is.slt.ds.vitalpet.vitalpet {
    requires javafx.controls;
    requires javafx.fxml;


    opens mx.edu.uacm.is.slt.ds.vitalpet.vitalpet to javafx.fxml;
    exports mx.edu.uacm.is.slt.ds.vitalpet.vitalpet;
}