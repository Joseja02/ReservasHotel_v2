package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Reservas {
    private List<Reserva> coleccionReservas;

    public Reservas() {
        coleccionReservas = new ArrayList<>();
    }

    public List<Reserva> get() {
        return copiaProfundaReservaes();
    }

    private List<Reserva> copiaProfundaReservaes() {

        List<Reserva> copiaReservas = new ArrayList<>();

        copiaReservas.addAll(coleccionReservas);

        return copiaReservas;
    }

    public int getTamano() {
        int counter = 0;
        for (int i = 0; i < coleccionReservas.size(); i++)
            counter++;
        return counter;
    }

    public void insertar(Reserva reserva) throws OperationNotSupportedException {

        if (reserva == null) {
            throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
        }
        if (buscar(reserva) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe una reserva igual.");
        }
        if (!coleccionReservas.contains(reserva)) {
            coleccionReservas.add(reserva);
        }
    }
    public Reserva buscar(Reserva reserva) {

        if (coleccionReservas.contains(reserva)) {
            int i = coleccionReservas.indexOf(reserva);
            reserva = coleccionReservas.get(i);
            return new Reserva(reserva);
        } else {
            throw new NullPointerException("ERROR: No existe ninguna reserva como la indicada.");
        }
    }

    public void borrar(Reserva reserva) throws OperationNotSupportedException {
        if (coleccionReservas.contains(reserva)) {
            coleccionReservas.remove(reserva);
        } else {
            throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
        }
    }
    public List<Reserva> getReservas(Huesped huesped) {
        if (huesped == null) {
            throw new NullPointerException("ERROR: No se pueden buscar reservas de un huésped nulo.");
        }
        List<Reserva> reservasHuesped = new ArrayList<>();
        for (int i = 0; i < get().size(); i++) {
            Reserva reserva = get().get(i);
            if (reserva.getHuesped().getDni().equals(huesped.getDni())) {
                reservasHuesped.add(new Reserva(reserva));
            }
        }
        return reservasHuesped;
    }

    public List<Reserva> getReservas(TipoHabitacion tipoHabitacion) {
        if (tipoHabitacion == null) {
            throw new NullPointerException("ERROR: No se pueden buscar reservas de un tipo de habitación nula.");
        }
        List<Reserva> reservasHuesped = new ArrayList<>();
        for (int i = 0; i < get().size(); i++) {
            Reserva reserva = get().get(i);
            if (reserva.getHabitacion().getTipoHabitacion().equals(tipoHabitacion)) {
                reservasHuesped.add(new Reserva(reserva));
            }
        }
        return reservasHuesped;
    }

    public List<Reserva> getReservasFuturas(Habitacion habitacion) {
        if (habitacion == null)
            throw new NullPointerException("ERROR: No se pueden buscar reservas de una habitación nula.");

        List<Reserva> reservasHuesped = new ArrayList<>();
        for (int i = 0; i < get().size(); i++) {
            Reserva reserva = get().get(i);
            if (reserva.getHabitacion().getIdentificador().equals(habitacion.getIdentificador()) &&
                    reserva.getFechaInicioReserva().isAfter(LocalDate.now())) {
                reservasHuesped.add(new Reserva(reserva));
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
