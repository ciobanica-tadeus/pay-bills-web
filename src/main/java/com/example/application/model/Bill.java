package com.example.application.model;

import java.util.Date;

public class Bill {

    private int id_factura;
    private float sumaTotala;
    private float sumaDatorata;
    private float consum;
    private Date scadenta;
    private Date dataPlata;
    private String status;
    private String numeFurnizor;

    public Bill(int id_factura, float sumaTotala, float sumaDatorata, String status, String numeFurnizor, Date scadenta) {
        this.id_factura = id_factura;
        this.sumaTotala = sumaTotala;
        this.sumaDatorata = sumaDatorata;
        this.status = status;
        this.numeFurnizor = numeFurnizor;
        this.scadenta = scadenta;
    }

    public String getNumeFurnizor() {
        return numeFurnizor;
    }

    public void setNumeFurnizor(String numeFurnizor) {
        this.numeFurnizor = numeFurnizor;
    }

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public float getSumaTotala() {
        return sumaTotala;
    }

    public void setSumaTotala(float sumaTotala) {
        this.sumaTotala = sumaTotala;
    }

    public float getSumaDatorata() {
        return sumaDatorata;
    }

    public void setSumaDatorata(float sumaDatorata) {
        this.sumaDatorata = sumaDatorata;
    }

    public float getConsum() {
        return consum;
    }

    public void setConsum(float consum) {
        this.consum = consum;
    }

    public Date getScadenta() {
        return scadenta;
    }

    public void setScadenta(Date scadenta) {
        this.scadenta = scadenta;
    }

    public Date getDataPlata() {
        return dataPlata;
    }

    public void setDataPlata(Date dataPlata) {
        this.dataPlata = dataPlata;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
