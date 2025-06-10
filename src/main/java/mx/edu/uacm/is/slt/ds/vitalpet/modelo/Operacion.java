package mx.edu.uacm.is.slt.ds.vitalpet.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Operacion {

    private int id;
    private String nombre;
    private List<Tarea> listaTareas;
    private EstadoOperacion estado;

    private int nextTaskId = 1;
    private static final int MAX_TASK_ID = 100;

    public Operacion(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.listaTareas = new ArrayList<>();
        this.estado = EstadoOperacion.EN_EJECUCION;
    }

    public void agregarTarea(Tarea tarea) {
        listaTareas.add(tarea);
    }

    // getters y setters
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public List<Tarea> getListaTareas() {
        return Collections.unmodifiableList(listaTareas);
    }
    public EstadoOperacion getEstado() {
        return estado;
    }
    public void setEstado(EstadoOperacion estado) {
        this.estado = estado;
    }

    // Lógica de control de la operación
    public void iniciar() {
        /* ... */
    }
    public boolean pausar() {
        /* ... */ return true;
    }
    public boolean reanudar() {
        /* ... */ return true;
    }
    public void detener() {
        /* ... */
    }


    // genera y devuelve el siguiente ID de tarea entre 1–100 y Lanza excepción si se agotan.
    public int generarIdTarea() {
        if (nextTaskId > MAX_TASK_ID) {
            throw new IllegalStateException("Ids de tarea agotados");
        }
        return nextTaskId++;
    }

    public boolean eliminarTarea(Tarea t) {
        return listaTareas.remove(t);
    }

}
