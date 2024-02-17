package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;

import javax.naming.OperationNotSupportedException;

public class Huespedes {
    private int capacidad;
    private int tamano;
    private Huesped[] coleccionHuespedes;

    public Huespedes(int capacidad) {

        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        coleccionHuespedes = new Huesped[this.capacidad];
        tamano = 0;
    }

    public Huesped[] get() {
        return copiaProfundaHuespedes();
    }

    private Huesped[] copiaProfundaHuespedes() {

        Huesped[] copiaHuespedes = new Huesped[coleccionHuespedes.length];

        for (int i = 0; i < coleccionHuespedes.length; i++) {
            copiaHuespedes[i] = new Huesped(coleccionHuespedes[i]);
        }
        return copiaHuespedes;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getTamano() {
        int counter = 0;
        for (int i = 0; i < coleccionHuespedes.length; i++)
            if (coleccionHuespedes[i] != null)
                counter++;
        return counter;
    }

    public void insertar(Huesped huesped) throws OperationNotSupportedException {

        if (huesped == null) {
            throw new NullPointerException("ERROR: No se puede insertar un hu�sped nulo.");
        }

        tamano = getTamano();

        for (int i = 0; i < tamano; i++) {
            if (huesped.equals(coleccionHuespedes[i])) {
                throw new OperationNotSupportedException("ERROR: Ya existe un hu�sped con ese dni.");
            }
        }

        if (capacidadSuperada(tamano + 1)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan m�s hu�spedes.");
        }

        Huesped[] nuevoArray = new Huesped[tamano + 1];

        if (tamano >= 0) System.arraycopy(coleccionHuespedes, 0, nuevoArray, 0, tamano);
        nuevoArray[nuevoArray.length - 1] = huesped;

        coleccionHuespedes = nuevoArray;
        tamano = getTamano();
    }

    private int buscarIndice(Huesped huesped) {

        for (int i = 0; i < tamano; i++) {
            if (coleccionHuespedes[i].equals(huesped)) {
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

    public Huesped buscar(Huesped huesped) {

        if (huesped == null) {
            throw new NullPointerException("El hu�sped devuelto no es el que deber�a ser.");
        }

        int indice = buscarIndice(huesped);
        if (indice == -1) {
            return null;
        }

        return new Huesped(coleccionHuespedes[indice]);
    }

    public void borrar(Huesped huesped) throws OperationNotSupportedException {

        if (huesped == null) {
            throw new NullPointerException("ERROR: No se puede borrar un hu�sped nulo.");
        }

        int indice = buscarIndice(huesped);
        if (indice < 0) {
            throw new OperationNotSupportedException("ERROR: No existe ning�n hu�sped como el indicado.");
        }

        desplazarUnaPosicionHaciaLaIzquierda(indice);
    }

    private void desplazarUnaPosicionHaciaLaIzquierda(int indice) {
        Huesped[] nuevoArray = new Huesped[tamano - 1];
        System.arraycopy(coleccionHuespedes, 0, nuevoArray, 0, indice);
        if (!tamanoSuperado(indice)) {
            System.arraycopy(coleccionHuespedes, indice + 1, nuevoArray, indice, coleccionHuespedes.length - indice - 1);
        }
        coleccionHuespedes = nuevoArray;
        tamano = getTamano();
    }
}
