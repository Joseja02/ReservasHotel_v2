package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;

public class Huespedes {
    private List<Huesped> coleccionHuespedes;

    public Huespedes() {
        coleccionHuespedes = new ArrayList<>();
    }

    public List<Huesped> get() {
        return copiaProfundaHuespedes();
    }

    private List<Huesped> copiaProfundaHuespedes() {

        List<Huesped> copiaHuespedes = new ArrayList<>();

        copiaHuespedes.addAll(coleccionHuespedes);

        return copiaHuespedes;
    }

    public int getTamano() {
        int counter = 0;
        for (int i = 0; i < coleccionHuespedes.size(); i++)
            counter++;
        return counter;
    }

    public void insertar(Huesped huesped) throws OperationNotSupportedException {

        if (huesped == null) {
            throw new NullPointerException("ERROR: No se puede insertar un huésped nulo.");
        }
        if (buscar(huesped) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe un huésped igual.");
        }
        if (!coleccionHuespedes.contains(huesped)) {
            coleccionHuespedes.add(huesped);
        }
    }
    public Huesped buscar(Huesped huesped) {

        if (coleccionHuespedes.contains(huesped)) {
            int i = coleccionHuespedes.indexOf(huesped);
            huesped = coleccionHuespedes.get(i);
            return new Huesped(huesped);
        } else {
            throw new NullPointerException("ERROR: No existe ningún huésped como el indicado.");
        }
    }

    public void borrar(Huesped huesped) throws OperationNotSupportedException {

        if (coleccionHuespedes.contains(huesped)) {
            coleccionHuespedes.remove(huesped);
        } else {
            throw new NullPointerException("ERROR: No se puede borrar un huésped nulo.");
        }
    }
}
