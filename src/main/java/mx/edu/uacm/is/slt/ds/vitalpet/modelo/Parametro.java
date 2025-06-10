package mx.edu.uacm.is.slt.ds.vitalpet.modelo;

public class Parametro {
    private String nombre;
    private Object valor;

    public Parametro(String nombre, Object valor) {
        this.nombre = nombre;
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return nombre + "=" + valor;
    }
}
