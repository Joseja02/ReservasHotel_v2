package org.iesalandalus.programacion.reservashotel.vista;

public enum Opcion {
    SALIR("SALIR"),
    INSERTAR_HUESPED("INSERTAR_HUESPED"),
    BUSCAR_HUESPED("BUSCAR_HUESPED"),
    BORRAR_HUESPED("BORRAR_HUESPED"),
    MOSTRAR_HUESPEDES("MOSTRAR_HUESPEDES"),
    INSERTAR_HABITACION("INSERTAR_HABITACION"),
    BUSCAR_HABITACION("BUSCAR_HABITACION"),
    BORRAR_HABITACION("BORRAR_HABITACION"),
    MOSTRAR_HABITACIONES("MOSTRAR_HABITACIONES"),
    INSERTAR_RESERVA("INSERTAR_RESERVA"),
    ANULAR_RESERVA("ANULAR_RESERVA"),
    MOSTRAR_RESERVAS("MOSTRAR_RESERVAS"),
    CONSULTAR_DISPONIBILIDAD("CONSULTAR_DISPONIBILIDAD"),
    REALIZAR_CHECKIN("REALIZAR_CHECKIN"),
    REALIZAR_CHECKOUT("REALIZAR_CHECKOUT");

    private final String mensajeAMostrar;

    private Opcion(String mensajeAMostrar){
        this.mensajeAMostrar = mensajeAMostrar;
    }

    @Override
    public String toString() {
        return valueOf(mensajeAMostrar).ordinal() + " .- " + mensajeAMostrar.replace("_"," ");
    }
}
