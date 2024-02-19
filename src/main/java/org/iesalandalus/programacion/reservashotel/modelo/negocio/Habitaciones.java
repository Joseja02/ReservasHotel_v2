package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Habitaciones {
    private List<Habitacion> coleccionHabitaciones;

    public Habitaciones() {
        coleccionHabitaciones = new ArrayList<>();
    }

    public List<Habitacion> get() {
        return copiaProfundaHabitaciones();
    }

    public List<Habitacion> get(TipoHabitacion tipoHabitacion) {
        List<Habitacion> copia = copiaProfundaHabitaciones();
        List<Habitacion> habitacionesTipo = new ArrayList<>();

        for (int i = 0; i < coleccionHabitaciones.size(); i++) {
            Habitacion habitacion = copia.get(i);
            if (habitacion.getTipoHabitacion().equals(tipoHabitacion)) {
                habitacionesTipo.set(i, habitacion);
            }
        }
        return habitacionesTipo;
    }

    private List<Habitacion> copiaProfundaHabitaciones() {

        List<Habitacion> copiaHabitaciones = new ArrayList<>();

        Iterator<Habitacion> iterador = coleccionHabitaciones.iterator();
        while (iterador.hasNext()) {
            Habitacion habitacion = iterador.next();
            copiaHabitaciones.add(new Habitacion(habitacion));
        }
        return copiaHabitaciones;
    }

    public void insertar(Habitacion habitacion) throws OperationNotSupportedException {

        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se puede insertar una habitación nula.");
        }
        if (coleccionHabitaciones.contains(habitacion)) {
            throw new OperationNotSupportedException("ERROR: Ya existe una habitación con ese identificador.");
        }
        coleccionHabitaciones.add(habitacion);
    }

    public Habitacion buscar(Habitacion habitacion) {

        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se puede buscar una habitación nula.");
        }

        if (coleccionHabitaciones.contains(habitacion)) {
            int i = coleccionHabitaciones.indexOf(habitacion);
            habitacion = coleccionHabitaciones.get(i);
            return new Habitacion(habitacion);
        } else {
            return null;
        }
    }

    public void borrar(Habitacion habitacion) throws OperationNotSupportedException {
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se puede borrar una habitación nula.");
        }

        if (!coleccionHabitaciones.contains(habitacion)) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna habitación como la indicada.");
        }
        coleccionHabitaciones.remove(habitacion);
    }

    public int getTamano() {
        int counter = 0;
        for (int i = 0; i < coleccionHabitaciones.size(); i++)
            counter++;
        return counter;
    }
}
