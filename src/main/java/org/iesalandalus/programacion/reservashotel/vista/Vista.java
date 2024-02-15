package org.iesalandalus.programacion.reservashotel.vista;

import org.iesalandalus.programacion.reservashotel.controlador.Controlador;
import org.iesalandalus.programacion.reservashotel.modelo.Modelo;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Habitaciones;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Huespedes;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Reservas;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.Arrays;
import java.util.Comparator;

import static org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva.FORMATO_FECHA_RESERVA;

public class Vista {

    public static int CAPACIDAD = 4;
    private static Reservas reservas = new Reservas(CAPACIDAD);
    private static Huespedes huespedes = new Huespedes(CAPACIDAD);
    private static Habitaciones habitaciones = new Habitaciones(CAPACIDAD);
    private static Controlador controlador;
    private static Opcion opcion;

    public void setControlador(Controlador controlador) {
        if (controlador == null) {
            throw new NullPointerException("ERROR: No se puede asignar un controlador nulo");
        }
        Vista.controlador = controlador;
    }

    public void comenzar() {
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutarOpcion(opcion);
        }
        while (opcion != Opcion.SALIR);
    }

    public void terminar() {
        System.out.print("¡Hasta luego! - Tarea Online 5 | Jose Javier Sierra Berdún");
    }

    private static void ejecutarOpcion(Opcion opcion) {
        switch (opcion) {
            case SALIR -> System.out.print("¡Hasta luego! - Tarea Online 5 | Jose Javier Sierra Berdún");
            case INSERTAR_HUESPED -> insertarHuesped();
            case BUSCAR_HUESPED -> buscarHuesped();
            case BORRAR_HUESPED -> borrarHuesped();
            case MOSTRAR_HUESPEDES -> mostrarHuespedes();
            case INSERTAR_HABITACION -> insertarHabitacion();
            case BUSCAR_HABITACION -> buscarHabitacion();
            case BORRAR_HABITACION -> borrarHabitacion();
            case MOSTRAR_HABITACIONES -> mostrarHabitaciones();
            case INSERTAR_RESERVA -> insertarReserva();
            case ANULAR_RESERVA -> anularReserva();
            case MOSTRAR_RESERVAS -> mostrarReservas();
            case CONSULTAR_DISPONIBILIDAD -> {
                TipoHabitacion tipoHabitacion = Consola.leerTipoHabitacion();
                LocalDate fechaInicioReserva = Consola.leerFecha("Introduzca fecha inicio reserva (" + FORMATO_FECHA_RESERVA + "):");
                LocalDate fechaFinReserva = Consola.leerFecha("Introduzca fecha inicio reserva (" + FORMATO_FECHA_RESERVA + "):");
                consultarDisponibilidad(tipoHabitacion, fechaInicioReserva, fechaFinReserva);
            }
            case REALIZAR_CHECKIN -> realizarCheckin();
            case REALIZAR_CHECKOUT -> realizarCheckout();
            default -> System.out.print("Opción no válida: " + opcion);
        }
    }

    private static void insertarHuesped() {
        try {
            Huesped huesped = Consola.leerHuesped();
            huespedes.insertar(huesped);
            System.out.print("Huesped ha sido insertado");
        } catch (Exception e) {
            System.out.print("ERROR: " + e.getMessage());
            Entrada.cadena();
        }
    }

    private static void buscarHuesped() {
        try {
            Huesped huesped = Consola.leerHuespedPorDni();
            huesped = huespedes.buscar(huesped);
            if (huesped != null) {
                System.out.println(huesped.toString());
            } else {
                System.out.print("El huesped no existe");
            }
        } catch (Exception e) {
            System.out.print("ERROR: " + e.getMessage());
            Entrada.cadena();
        }
    }

    private static void borrarHuesped() {
        try {
            Huesped huesped = Consola.leerHuespedPorDni();
            huespedes.borrar(huesped);
            System.out.print("Huesped ha sido borrado");
        } catch (Exception e) {
            System.out.print("ERROR: " + e.getMessage());
            Entrada.cadena();
        }
    }

    private static void mostrarHuespedes() {
        try {
            if (huespedes.getTamano() > 0) {
                System.out.println("Estos son los Huespedes existentes: ");
                System.out.println(" ");
                for (int i = 0; i < huespedes.getTamano(); i++) {
                    System.out.println(huespedes.get()[i].toString());
                    System.out.println(" ");
                }

            } else {
                System.out.println("No existen hu?spedes ");
            }
        } catch (Exception e) {
            System.out.print("ERROR: " + e.getMessage());
            Entrada.cadena();
        }
    }

    private static void insertarHabitacion() {
        try {
            Habitacion habitacion = Consola.leerHabitacion();
            habitaciones.insertar(habitacion);
            System.out.print("La Habitacion ha sido insertada");
        } catch (Exception e) {
            System.out.print("ERROR: " + e.getMessage());
            Entrada.cadena();
        }
    }

    private static void buscarHabitacion() {
        try {
            Habitacion habitacion = Consola.leerHabitacion();
            habitacion = habitaciones.buscar(habitacion);
            if (habitacion != null) {
                System.out.println(habitacion.toString());
            } else {
                System.out.print("La Habitaci?n no existe");
            }
        } catch (Exception e) {
            System.out.print("ERROR: " + e.getMessage());
            Entrada.cadena();
        }
    }

    private static void borrarHabitacion() {
        try {
            Habitacion habitacion = Consola.leerHabitacion();
            habitaciones.borrar(habitacion);
            System.out.print("La Habitaci?n ha sido borrada");
        } catch (Exception e) {
            System.out.print("ERROR: " + e.getMessage());
            Entrada.cadena();
        }
    }

    private static void mostrarHabitaciones() {
        try {
            if (habitaciones.getTamano() > 0) {
                System.out.println("Estas son las Habitaciones existentes: ");
                System.out.println(" ");
                for (int i = 0; i < habitaciones.getTamano(); i++) {
                    System.out.println(habitaciones.get()[i].toString());
                    System.out.println(" ");
                }

            } else {
                System.out.println("No existen habitaciones ");
            }
        } catch (Exception e) {
            System.out.print("ERROR: " + e.getMessage());
            Entrada.cadena();
        }
    }

    private static void insertarReserva() {
        try {
            Reserva reserva = Consola.leerReserva();
            Reserva reservaExistente = reservas.buscar(reserva);
            if (reservaExistente == null) {
                reservas.insertar(reserva);
                System.out.print("La reserva ha sido registrada");
            } else {
                System.out.print("No es posible registrar esta reserva porque ya existe otr reserva para la misma fecha y habitaci?n seleccionada");
            }
        } catch (Exception e) {
            System.out.print("ERROR: " + e.getMessage());
            Entrada.cadena();
        }
    }

    private static void listarReservas(Huesped huesped) {
        try {
            if (reservas.getTamano() > 0) {
                System.out.println("Estas son las reservas para este huesped: ");
                System.out.println(" ");
                Reserva[] reservasHuesped = reservas.getReservas(huesped);
                if (reservasHuesped.length > 0) {
                    for (int i = 0; i < reservasHuesped.length; i++) {
                        System.out.println(reservasHuesped[i].toString());
                        System.out.println(" ");
                    }
                } else {
                    System.out.println("No existen reservas para este huesped");
                }

            } else {
                System.out.println("No existen reservas ");
            }
        } catch (Exception e) {
            System.out.print("ERROR: " + e.getMessage());
            Entrada.cadena();
        }
    }

    private static void listarReservas(TipoHabitacion tipoHabitacion) {
        try {
            if (reservas.getTamano() > 0) {
                System.out.println("Estas son las reservas para este tipo de habitaci?n: ");
                System.out.println(" ");
                Reserva[] reservasTipo = reservas.getReservas(tipoHabitacion);
                if (reservasTipo.length > 0) {
                    for (int i = 0; i < reservasTipo.length; i++) {
                        System.out.println(reservasTipo[i].toString());
                        System.out.println(" ");
                    }
                } else {
                    System.out.println("No existen reservas para este tipo de habitaci?n");
                }

            } else {
                System.out.println("No existen reservas ");
            }
        } catch (Exception e) {
            System.out.print("ERROR: " + e.getMessage());
            Entrada.cadena();
        }
    }

    private static Reserva[] getReservasAnulables(Reserva[] reservas) throws OperationNotSupportedException {
        Reservas reservasAnulables = new Reservas(CAPACIDAD);
        for (int i = 0; i < reservas.length; i++) {
            LocalDate fechaActual = LocalDate.now();
            if (fechaActual.isBefore(reservas[i].getFechaInicioReserva())) {
                reservasAnulables.insertar(new Reserva(reservas[i]));
            }
        }
        return reservasAnulables.get();
    }

    private static void anularReserva() {
        try {
            Huesped huesped = Consola.leerHuespedPorDni();
            huesped = huespedes.buscar(huesped);
            if (huesped != null) {
                Reserva[] reservasHuesped = reservas.getReservas(huesped);
                if (reservasHuesped.length > 0) {
                    Reserva[] reservasAnulables = getReservasAnulables(reservasHuesped);
                    if (reservasAnulables.length <= 0) {
                        System.out.println("No existen reservas anulables para este huesped");
                    } else {
                        if (reservasAnulables.length > 1) {
                            int eleccion = -1;
                            do {
                                for (int j = 0; j < reservasAnulables.length; j++) {
                                    System.out.println(j + " - " + reservasAnulables[j].toString());
                                }
                                System.out.print("Escoja la reserva que desea anular: ");
                                eleccion = Entrada.entero();
                            } while (eleccion < 0 || eleccion > reservasAnulables.length);
                            reservas.borrar(reservasAnulables[eleccion]);
                        } else {
                            //Solo Existe una reserva anulable para este huesped
                            System.out.println(reservasAnulables[0].toString());
                            System.out.println("Est? seguro de que desea anular esta reserva (S/N): ");
                            char respuesta = Entrada.caracter();
                            if (Character.toString(respuesta).equalsIgnoreCase("s"))
                                reservas.borrar(reservasAnulables[0]);
                        }
                    }
                } else {
                    System.out.println("No existen reservas para este huesped");
                }

            } else {
                System.out.print("El huesped no existe");
            }

        } catch (Exception e) {
            System.out.print("ERROR: " + e.getMessage());
            Entrada.cadena();
        }
    }

    private static void mostrarReservas() {
        try {
            if (reservas != null && reservas.getTamano() > 0) {
                System.out.println("Estas son las reservas existentes: ");
                System.out.println(" ");
                for (int i = 0; i < reservas.getTamano(); i++) {
                    System.out.println(reservas.get()[i].toString());
                    System.out.println(" ");
                }

            } else {
                System.out.println("No existen reservas ");
            }
        } catch (Exception e) {
            System.out.print("ERROR: " + e.getMessage());
            Entrada.cadena();
        }
    }

    private static int getNumElementosNoNulos(Reserva[] arrayReservas) {
        int noNulos = 0;
        for (int i = 0; i < arrayReservas.length; i++) {
            if (arrayReservas[i] != null) {
                noNulos++;
            }
        }
        return noNulos;
    }

    private static Habitacion consultarDisponibilidad(TipoHabitacion tipoHabitacion, LocalDate fechaInicioReserva, LocalDate fechaFinReserva) {
        boolean tipoHabitacionEncontrada = false;
        Habitacion habitacionDisponible = null;
        int numElementos = 0;

        Habitacion[] habitacionesTipoSolicitado = habitaciones.get(tipoHabitacion);

        if (habitacionesTipoSolicitado == null)
            return habitacionDisponible;

        for (int i = 0; i < habitacionesTipoSolicitado.length && !tipoHabitacionEncontrada; i++) {

            if (habitacionesTipoSolicitado[i] != null) {
                Reserva[] reservasFuturas = reservas.getReservasFuturas(habitacionesTipoSolicitado[i]);
                numElementos = getNumElementosNoNulos(reservasFuturas);

                if (numElementos == 0) {
                    habitacionDisponible = new Habitacion(habitacionesTipoSolicitado[i]);
                    tipoHabitacionEncontrada = true;
                } else {

                    Arrays.sort(reservasFuturas, 0, numElementos, Comparator.comparing(Reserva::getFechaFinReserva).reversed());

                    if (fechaInicioReserva.isAfter(reservasFuturas[0].getFechaFinReserva())) {
                        habitacionDisponible = new Habitacion(habitacionesTipoSolicitado[i]);
                        tipoHabitacionEncontrada = true;
                    }

                    if (!tipoHabitacionEncontrada) {

                        Arrays.sort(reservasFuturas, 0, numElementos, Comparator.comparing(Reserva::getFechaInicioReserva));

                        if (fechaFinReserva.isBefore(reservasFuturas[0].getFechaInicioReserva())) {
                            habitacionDisponible = new Habitacion(habitacionesTipoSolicitado[i]);
                            tipoHabitacionEncontrada = true;
                        }
                    }

                    if (!tipoHabitacionEncontrada) {
                        for (int j = 1; j < reservasFuturas.length && !tipoHabitacionEncontrada; j++) {
                            if (reservasFuturas[j] != null && reservasFuturas[j - 1] != null) {
                                if (fechaInicioReserva.isAfter(reservasFuturas[j - 1].getFechaFinReserva()) &&
                                        fechaFinReserva.isBefore(reservasFuturas[j].getFechaInicioReserva())) {

                                    habitacionDisponible = new Habitacion(habitacionesTipoSolicitado[i]);
                                    tipoHabitacionEncontrada = true;
                                }
                            }
                        }
                    }


                }
            }
        }

        return habitacionDisponible;
    }

    private static void realizarCheckin() {

        Huesped huesped = Consola.leerHuespedPorDni();
        huesped = controlador.buscar(huesped);
        boolean checkinFallido = false;

        Reserva[] reservasDelHuesped = controlador.getReservas(huesped);

        for (int i = 0; i < reservasDelHuesped.length; i++) {

            if (reservasDelHuesped[i].getFechaInicioReserva().isEqual(LocalDate.now())) {
                controlador.realizarCheckin(reservasDelHuesped[i], LocalDateTime.now());
            } else {
                checkinFallido = true;
            }
        }
        if (checkinFallido) {
            System.out.println("AVISO: Hay al menos 1 reserva de la que no se ha podido hacer un Checkin al ser de un día distinto");
        }

    }

    private static void realizarCheckout() {

        Huesped huesped = Consola.leerHuespedPorDni();
        huesped = controlador.buscar(huesped);
        boolean checkinFallido = false;

        Reserva[] reservasDelHuesped = controlador.getReservas(huesped);

        for (int i = 0; i < reservasDelHuesped.length; i++) {

            if (reservasDelHuesped[i].getFechaFinReserva().isEqual(LocalDate.now())) {
                controlador.realizarCheckout(reservasDelHuesped[i], LocalDateTime.now());
            } else {
                checkinFallido = true;
            }
        }
        if (checkinFallido) {
            System.out.println("AVISO: Hay al menos 1 reserva de la que no se ha podido hacer un Checkout al ser de un día distinto");
        }

    }
}
