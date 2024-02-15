package org.iesalandalus.programacion.reservashotel.modelo.dominio;

public enum TipoHabitacion {
    SUITE("SUITE", 4),
    SIMPLE("SIMPLE", 1),
    DOBLE("DOBLE", 2),
    TRIPLE("TRIPLE", 3);

    private final String cadenaAMostrar;
    private final int numeroMaximoPersonas;
    private TipoHabitacion(String cadenaAMostrar, int numeroMaximoPersonas){
        this.cadenaAMostrar = cadenaAMostrar;
        this.numeroMaximoPersonas = numeroMaximoPersonas;
    }
    public int getNumeroMaximoPersonas() {
        return numeroMaximoPersonas;
    }
    @Override
    public String toString() {
        return "TipoHabitacion{" +
                "cadenaAMostrar='" + cadenaAMostrar + '\'' +
                ", numeroMaximoPersonas=" + numeroMaximoPersonas +
                '}';
    }
}
