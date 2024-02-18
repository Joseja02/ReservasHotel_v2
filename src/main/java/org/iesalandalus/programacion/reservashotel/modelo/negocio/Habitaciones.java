package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
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

        copiaHabitaciones.addAll(coleccionHabitaciones);

        return copiaHabitaciones;
    }

    public void insertar(Habitacion habitacion) throws OperationNotSupportedException {

        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se puede insertar una habitación nula.");
        }
        if (buscar(habitacion) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe una habitación con ese identificador.");
        }
        if (!coleccionHabitaciones.contains(habitacion)) {
            coleccionHabitaciones.add(habitacion);
        }
    }

    public Habitacion buscar(Habitacion habitacion) {

        if (coleccionHabitaciones.contains(habitacion)) {
            int i = coleccionHabitaciones.indexOf(habitacion);
            habitacion = coleccionHabitaciones.get(i);
            return new Habitacion(habitacion);
        } else {
            throw new NullPointerException("ERROR: No existe ninguna habitación como la indicada.");
        }
    }

    public void borrar(Habitacion habitacion) {
        if (coleccionHabitaciones.contains(habitacion)) {
            coleccionHabitaciones.remove(habitacion);
        } else {
            throw new NullPointerException("ERROR: No se puede borrar una habitación nula.");
        }
    }
    public int getTamano() {
        int counter = 0;
        for (int i = 0; i < coleccionHabitaciones.size(); i++)
                counter++;
        return counter;
    }
}
