package org.iesalandalus.programacion.reservashotel.vista;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva.FORMATO_FECHA_RESERVA;

public final class Consola {
    public Consola() {
    }

    public static void mostrarMenu() {
        System.out.println("");
        System.out.println(" | ------- Programa reservas del hotel IES Al-Ándalus (hecho por José Javier Sierra) ------- |");
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion.toString());
        }
        System.out.println("| ------------------------------------------------------------------------- |");
    }

    public static Opcion elegirOpcion() {
        try {
            Opcion opcion;
            System.out.println("");
            System.out.print("Elige una opción: ");
            opcion = Opcion.values()[Entrada.entero()];
            return opcion;
        } catch (Exception e) {
            System.out.print("ERROR: Has introducido una opción fuera de rango. Selecciona una de las opciones mostradas arriba: ");
            return elegirOpcion();
        }
    }

    public static Huesped leerHuesped() {
        Huesped huesped;
        String nombre;
        String telefono;
        String correo;
        String dni;
        LocalDate fechaNacimiento;
        System.out.print("Introduzca DNI del Huesped: ");
        dni = Entrada.cadena();
        System.out.print("Introduzca nombre del Huesped: ");
        nombre = Entrada.cadena();
        System.out.print("Introduzca teléfono del Huesped: ");
        telefono = Entrada.cadena();
        System.out.print("Introduzca email del Huesped: ");
        correo = Entrada.cadena();
        System.out.print("Introduzca fecha de nacimiento del Huesped (Formato fecha: " + FORMATO_FECHA_RESERVA + ")");
        fechaNacimiento = LocalDate.parse(Entrada.cadena(), DateTimeFormatter.ofPattern(FORMATO_FECHA_RESERVA));

        huesped = new Huesped(nombre, dni, correo, telefono, fechaNacimiento);
        return huesped;
    }

    public static Huesped leerHuespedPorDni() {
        Huesped huesped;
        String dni;
        System.out.print("Introduzca DNI del Huesped: ");
        dni = Entrada.cadena();

        huesped = new Huesped("Nombre Ficticio", dni, "ficticio@test.com", "123456789", LocalDate.of(2002, 8, 19));

        return huesped;
    }

    public static LocalDate leerFecha(String mensaje) {
        try {
            System.out.print(mensaje);
            return LocalDate.parse(Entrada.cadena(), DateTimeFormatter.ofPattern(FORMATO_FECHA_RESERVA));
        } catch (Exception e) {
            System.out.println("El formato de fecha introducido no es válido.");
            return leerFecha(mensaje);
        }
    }

    public static Habitacion leerHabitacion() {
        Habitacion habitacion;
        int planta;
        int puerta;
        double precio;
        TipoHabitacion tipoHabitacion;

        System.out.print("Introduzca planta de habitación: ");
        planta = Entrada.entero();
        System.out.print("Introduzca puerta de habitación: ");
        puerta = Entrada.entero();
        System.out.print("Introduzca precio de habitación: ");
        precio = Double.parseDouble(Entrada.cadena());
        System.out.print("Introduzca tipo de habitación: ");
        tipoHabitacion = leerTipoHabitacion();

        habitacion = new Habitacion(planta, puerta, precio, tipoHabitacion);
        return habitacion;
    }

    public static Habitacion leerHabitacionPorIdentificador() {
        Habitacion habitacion;
        int planta;
        int puerta;

        System.out.print("Introduzca planta de habitación: ");
        planta = Entrada.entero();
        System.out.print("Introduzca puerta de habitación: ");
        puerta = Entrada.entero();

        habitacion = new Habitacion(planta, puerta, 50.25, TipoHabitacion.DOBLE);
        return habitacion;
    }

    public static TipoHabitacion leerTipoHabitacion() {
        for (TipoHabitacion opcion : TipoHabitacion.values()) {
            System.out.println(opcion.ordinal() + "  -  " + opcion.toString());
        }
        System.out.print("Escoja tipo de habitación: ");
        return TipoHabitacion.values()[Entrada.entero()];
    }

    public static Regimen leerRegimen() {
        for (Regimen opcion : Regimen.values()) {
            System.out.println(opcion.ordinal() + "  -  " + opcion.toString());
        }
        System.out.print("Escoja tipo de régimen: ");
        return Regimen.values()[Entrada.entero()];
    }

    public static Reserva leerReserva() {
        Reserva reserva;
        Huesped huesped;
        Habitacion habitacion;
        Regimen regimen;
        LocalDate fechaInicioReserva;
        LocalDate fechaFinReserva;
        int numeroPersonas;

        System.out.print("Introduzca la fecha inicio de reserva(" + FORMATO_FECHA_RESERVA + "): ");
        fechaInicioReserva = LocalDate.parse(Entrada.cadena(), DateTimeFormatter.ofPattern(FORMATO_FECHA_RESERVA));
        System.out.print("Introduzca la fecha fin de reserva(" + FORMATO_FECHA_RESERVA + "): ");
        fechaFinReserva = LocalDate.parse(Entrada.cadena(), DateTimeFormatter.ofPattern(FORMATO_FECHA_RESERVA));
        System.out.print("Introduzca número de personas: ");
        numeroPersonas = Entrada.entero();

        huesped = leerHuesped();
        habitacion = leerHabitacion();
        regimen = leerRegimen();

        reserva = new Reserva(huesped, habitacion, regimen, fechaInicioReserva, fechaFinReserva, numeroPersonas);
        return reserva;
    }

    public static LocalDateTime leerFechaHora(String mensaje) {
        boolean comprobacionFechaErronea;
        do {
            try {
                comprobacionFechaErronea = false;
                System.out.print(mensaje);
                return LocalDateTime.parse(Entrada.cadena(), DateTimeFormatter.ofPattern(FORMATO_FECHA_RESERVA));
            } catch (Exception e) {
                System.out.println("El formato de fecha introducido no es válido.");
                comprobacionFechaErronea = true;
            }
        } while (comprobacionFechaErronea == true);
        return leerFechaHora(mensaje);
    }

}
