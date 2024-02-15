package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;

public class Habitaciones {
    private final int capacidad;
    private int tamano;
    private Habitacion[] habitaciones;

    public Habitaciones(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        habitaciones = new Habitacion[this.capacidad];
        tamano = getTamano();
    }

    public Habitacion[] get() {
        return copiaProfundaHabitaciones();
    }

    public Habitacion[] get(TipoHabitacion tipoHabitacion) {
        Habitacion[] copia = copiaProfundaHabitaciones();
        Habitacion[] habitacionesTipo = new Habitacion[capacidad];
        for (int i = 0; i < getTamano(); i++) {
            Habitacion habitacion = copia[i];
            if (habitacion.getTipoHabitacion().equals(tipoHabitacion)) {
                habitacionesTipo[i] = habitacion;
            }
        }
        return habitacionesTipo;
    }

    private Habitacion[] copiaProfundaHabitaciones() {

        Habitacion[] copiaHabitaciones = new Habitacion[getCapacidad()];

        for (int i = 0; i < habitaciones.length; i++) {
            copiaHabitaciones[i] = new Habitacion(habitaciones[i]);
        }
        return copiaHabitaciones;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getTamano() {
        int counter = 0;
        for (int i = 0; i < habitaciones.length; i++)
            if (habitaciones[i] != null)
                counter++;
        return counter;
    }

    public void insertar(Habitacion habitacion) throws OperationNotSupportedException {

        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se puede insertar una habitación nula.");
        }

        tamano = getTamano();

        if (buscar(habitacion) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe una habitación con ese identificador.");
        }
        if (capacidadSuperada(getTamano() + 1)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más habitaciones.");
        }

        Habitacion[] nuevoArray = new Habitacion[tamano + 1];

        if (capacidad >= 0) {
            for (int i = 0; i < getTamano(); i++) {
                nuevoArray[i] = new Habitacion(get()[i]);
            }
        }
        nuevoArray[nuevoArray.length - 1] = new Habitacion(habitacion);

        this.habitaciones = new Habitacion[nuevoArray.length];
        System.arraycopy(nuevoArray, 0, habitaciones, 0, nuevoArray.length);
        tamano = getTamano();
    }

    private int buscarIndice(Habitacion habitacion) {
        for (int i = 0; i < tamano; i++) {
            if (get()[i].getIdentificador().equals(habitacion.getIdentificador())) {
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

    public Habitacion buscar(Habitacion habitacion) {

        if (habitacion == null) {
            throw new NullPointerException("La habitación devuelta no es la que debería ser.");
        }

        int indice = buscarIndice(habitacion);
        if (indice == -1) {
            return null;
        }

        return new Habitacion(get()[indice]);
    }

    public void borrar(Habitacion habitacion) throws OperationNotSupportedException {

        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se puede borrar una habitación nula.");
        }

        int indice = buscarIndice(habitacion);
        if (indice < 0) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna habitación como la indicada.");
        }

        desplazarUnaPosicionHaciaLaIzquierda(indice);
    }

    private void desplazarUnaPosicionHaciaLaIzquierda(int indice) {
        Habitacion[] nuevoArray = new Habitacion[tamano - 1];
        System.arraycopy(habitaciones, 0, nuevoArray, 0, indice);
        if (!tamanoSuperado(indice)) {
            System.arraycopy(habitaciones, indice + 1, nuevoArray, indice, habitaciones.length - indice - 1);
        }
        habitaciones = nuevoArray;
        tamano = getTamano();
    }
}
