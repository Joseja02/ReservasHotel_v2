package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reservas {
    private final int capacidad;
    private int tamano;
    private Reserva[] coleccionReservas;

    public Reservas(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        coleccionReservas = new Reserva[this.capacidad];
        tamano = 0;
    }

    public Reserva[] get() {
        return copiaProfundaReservaes();
    }

    private Reserva[] copiaProfundaReservaes() {

        Reserva[] copiaReservaes = new Reserva[getCapacidad()];

        for (int i = 0; i < coleccionReservas.length; i++) {
            copiaReservaes[i] = new Reserva(coleccionReservas[i]);
        }
        return copiaReservaes;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getTamano() {
        int counter = 0;
        for (int i = 0; i < coleccionReservas.length; i++)
            if (coleccionReservas[i] != null)
                counter++;
        return counter;
    }

    public void insertar(Reserva reserva) throws OperationNotSupportedException {

        if (reserva == null) {
            throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
        }

        tamano = getTamano();

        if (buscar(reserva) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe una reserva igual.");
        }
        if (capacidadSuperada(getTamano() + 1)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más reservas.");
        }

        Reserva[] nuevoArray = new Reserva[tamano + 1];

        if (capacidad >= 0) {
            for (int i = 0; i < getTamano(); i++) {
                nuevoArray[i] = new Reserva(get()[i]);
            }
        }
        nuevoArray[nuevoArray.length - 1] = new Reserva(reserva);

        this.coleccionReservas = new Reserva[nuevoArray.length];
        System.arraycopy(nuevoArray, 0, coleccionReservas, 0, nuevoArray.length);
        tamano = getTamano();
    }

    private int buscarIndice(Reserva reserva) {
        for (int i = 0; i < tamano; i++) {
            Reserva res = new Reserva(get()[i]);
            if (res.getHuesped().getDni().equals(reserva.getHuesped().getDni()) &&
                    res.getHabitacion().equals(reserva.getHabitacion()) &&
                    res.getFechaInicioReserva().equals(reserva.getFechaInicioReserva())) {
                return i;
            }
        }
        return -1;
    }

    private boolean tamanoSuperado(int indice) {
        return indice == tamano - 1;
    }

    private boolean capacidadSuperada(int indice) {
        return indice > capacidad;
    }

    public Reserva buscar(Reserva reserva) {

        if (reserva == null) {
            throw new NullPointerException("La reserva devuelta no es la que debería ser.");
        }

        int indice = buscarIndice(reserva);
        if (indice < 0) {
            return null;
        }

        return new Reserva(get()[indice]);
    }

    public void borrar(Reserva reserva) throws OperationNotSupportedException {

        if (reserva == null) {
            throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
        }

        int indice = buscarIndice(reserva);
        if (indice < 0) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna reserva como la indicada.");
        }

        desplazarUnaPosicionHaciaLaIzquierda(indice);
    }

    private void desplazarUnaPosicionHaciaLaIzquierda(int indice) {
        Reserva[] nuevoArray = new Reserva[tamano - 1];
        System.arraycopy(coleccionReservas, 0, nuevoArray, 0, indice);
        if (!tamanoSuperado(indice)) {
            System.arraycopy(coleccionReservas, indice + 1, nuevoArray, indice, coleccionReservas.length - indice - 1);
        }
        coleccionReservas = nuevoArray;
        tamano = getTamano();
    }

    public Reserva[] getReservas(Huesped huesped) {
        if (huesped == null) {
            throw new NullPointerException("ERROR: No se pueden buscar reservas de un huesped nulo.");
        }

        Reserva[] reservasHuesped = new Reserva[capacidad];
        int posReservasHuesped = 0;
        for (int i = 0; i < get().length; i++) {
            Reserva reserva = get()[i];
            if (reserva.getHuesped().getDni().equals(huesped.getDni())) {
                reservasHuesped[posReservasHuesped] = new Reserva(reserva);
                posReservasHuesped++;
            }
        }
        return reservasHuesped;
    }

    public Reserva[] getReservas(TipoHabitacion tipoHabitacion) {
        if (tipoHabitacion == null) {
            throw new NullPointerException("ERROR: No se pueden buscar reservas de un tipo de habitación nula.");
        }
        Reserva[] reservasHuesped = new Reserva[capacidad];
        int posReservasTipo = 0;
        for (int i = 0; i < coleccionReservas.length; i++) {
            Reserva reserva = get()[i];
            if (reserva.getHabitacion().getTipoHabitacion().equals(tipoHabitacion)) {
                reservasHuesped[posReservasTipo] = new Reserva(reserva);
                posReservasTipo++;
            }
        }
        return reservasHuesped;
    }

    public Reserva[] getReservasFuturas(Habitacion habitacion) {
        if (habitacion == null)
            throw new NullPointerException("ERROR: No se pueden buscar reservas de una habitación nula.");

        Reserva[] reservasHuesped = new Reserva[capacidad];
        int posReservasHabitacion = 0;
        for (int i = 0; i < coleccionReservas.length; i++) {
            Reserva reserva = get()[i];
            if (reserva.getHabitacion().getIdentificador().equals(habitacion.getIdentificador()) &&
                    reserva.getFechaInicioReserva().isAfter(LocalDate.now())) {
                reservasHuesped[posReservasHabitacion] = new Reserva(reserva);
                posReservasHabitacion++;
            }
        }
        return reservasHuesped;
    }

    public void realizarCheckin(Reserva reserva, LocalDateTime fecha) {
        reserva.setCheckIn(fecha);
    }

    public void realizarCheckout(Reserva reserva, LocalDateTime fecha) {
        reserva.setCheckOut(fecha);
    }
}
