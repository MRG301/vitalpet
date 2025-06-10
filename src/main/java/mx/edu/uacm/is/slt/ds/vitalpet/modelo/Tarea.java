package mx.edu.uacm.is.slt.ds.vitalpet.modelo;

import java.util.ArrayList;
import java.util.List;

public class Tarea {
    private int id;
    private String nombre;
    private EstadoTarea estado;
    private TipoPrioridad prioridad;
    private List<Parametro> entradas;
    private List<Parametro> salidas;

    public Tarea(int id, String nombre, TipoPrioridad prioridad) {
        this.id = id;
        this.nombre = nombre;
        this.estado = EstadoTarea.PENDIENTE;
        this.prioridad = prioridad;
        this.entradas = new ArrayList<>();
        this.salidas = new ArrayList<>();
    }

    public void agregarEntrada(String nombre, Object valor) {
        entradas.add(new Parametro(nombre, valor));
    }

    public void agregarSalida(String nombre, Object valor) {
        salidas.add(new Parametro(nombre, valor));
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrioridad(TipoPrioridad prioridad) {
        this.prioridad = prioridad;
    }


    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public EstadoTarea getEstado() {
        return estado;
    }
    public void setEstado(EstadoTarea estado) {
        this.estado = estado;
    }
    public TipoPrioridad getPrioridad() {
        return prioridad;
    }
    public List<Parametro> getEntradas() {
        return entradas;
    }
    public List<Parametro> getSalidas() {
        return salidas;
    }

}