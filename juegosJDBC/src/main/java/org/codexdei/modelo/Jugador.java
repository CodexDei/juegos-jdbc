package org.codexdei.modelo;

import java.util.Date;

public class Jugador {

    private Integer idJugador = 0;
    private String alias;
    private Long puntaje;
    private Date fechaRegistro;

    public Jugador(){
    }

    public Jugador(String alias, long puntaje, Date fechaRegistro){

        this.alias = alias;
        this.puntaje = puntaje;
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(Integer idJugador) {
        this.idJugador = idJugador;
    }

    public String getAlias(){
        return this.alias;
    }

    public void setAlias(String alias){
        this.alias = alias;
    }

    public Long getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Long puntaje) {
        this.puntaje = puntaje;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {

        return
        "# Jugador: " + this.idJugador + " || " +
        "Alias: " + this.alias + " || " +
        "Puntaje: " + this.puntaje + " || " +
        "Fecha_Registro: " + this.fechaRegistro;
    }
}
