package com.bankproject.minibank;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Konto {
    private String imie;
    private String nazwisko;
    private String haslo;

    @Id
    private String nrKonta;

    private BigDecimal stanKonta;

    public Konto() {
        this.stanKonta = BigDecimal.valueOf(0);
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getHaslo() {
        return haslo;
    }

    public String getNrKonta() {
        return nrKonta;
    }

    public BigDecimal getStanKonta() {
        return stanKonta;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public void setNrKonta(String nrKonta) {
        this.nrKonta = nrKonta;
    }

    public void wplac(BigDecimal kwota){
        if (kwota.compareTo(BigDecimal.ZERO) > 0){
            this.stanKonta = this.stanKonta.add(kwota);
        }
    }

    public void wyplac(BigDecimal kwota){
        if (this.stanKonta.compareTo(kwota) >= 0){
            this.stanKonta = this.stanKonta.subtract(kwota);
        }
    }


}