package mx.edu.uacm.is.slt.ds.vitalpet.service;

import mx.edu.uacm.is.slt.ds.vitalpet.modelo.Operacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestorDeOperaciones {

    private final List<Operacion> operaciones;
    private int nextId = 1;            // arranca en 1
    private static final int MAX_ID = 100;

    public GestorDeOperaciones() {
        this.operaciones = new ArrayList<>();
    }

    // crea una nueva Operacion con el id y nombre dados y la añade a la lista interna
    public Operacion crearOperacion(String nombre) {
        if (nextId > MAX_ID) {
            throw new IllegalStateException("Ya no quedan Ids disponibles");
        }
        Operacion op = new Operacion(nextId++, nombre);
        operaciones.add(op);
        return op;
    }

    /**
     * Elimina la operacion indicada de la lista.
     * @return true si existía y fue eliminada, false en caso contrario.
     */
    public boolean eliminarOperacion(Operacion op) {
        return operaciones.remove(op);
    }

    // devuelve la lista inmutable de todas las operaciones registradas.

    public List<Operacion> listarOperaciones() {
        return Collections.unmodifiableList(operaciones);
    }

}