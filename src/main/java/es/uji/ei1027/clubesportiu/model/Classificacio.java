package es.uji.ei1027.clubesportiu.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

public class Classificacio {

    private String nomNadador;
    private String nomProva;
    @DateTimeFormat(pattern = "HH:mm:ss.SSS")
    private LocalTime temps;
    private int posicio;

    public void setNomNadador(String nomNadador) {
        this.nomNadador = nomNadador;
    }

    public void setNomProva(String nomProva) {
        this.nomProva = nomProva;
    }

    public void setTemps(LocalTime temps) {
        this.temps = temps;
    }

    public void setPosicio(int posicio) {
        this.posicio = posicio;
    }

    public String getNomNadador() {
        return nomNadador;
    }

    public String getNomProva() {
        return nomProva;
    }

    public LocalTime getTemps() {
        return temps;
    }

    public int getPosicio() {
        return posicio;
    }
}
