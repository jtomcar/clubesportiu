package es.uji.ei1027.clubesportiu.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDate;

public class Prova {

    private String nom;
    private String descripcio;
    private String tipus;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    public Prova() {}

    public String getNom(){
        return nom;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public String getTipus() {
        return tipus;
    }

    public LocalDate getData() {
        return data;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Proves{" +
                "nom='" + nom + "\'" +
                ", descripcio='" + descripcio + "\'" +
                ", tipus='" + tipus + "\'" +
                ", data=" + data +
                "}";
    }
}
