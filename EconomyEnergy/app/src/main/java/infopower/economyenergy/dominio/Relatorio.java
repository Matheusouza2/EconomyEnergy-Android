package infopower.economyenergy.dominio;

import java.io.Serializable;

/**
 * Created by Mathe on 20/11/2016.
 */

public class Relatorio implements Serializable {
    private int id;
    private String data;
    private double consumoRs;
    private double consumoDia;
    private double consumoMes;

    public Relatorio(){}

    public Relatorio(int id, String data, double consumoRs, double consumoDia, double consumoMes) {
        this.id = id;
        this.data = data;
        this.consumoRs = consumoRs;
        this.consumoDia = consumoDia;
        this.consumoMes = consumoMes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getConsumoRs() {
        return consumoRs;
    }

    public void setConsumoRs(double consumoRs) {
        this.consumoRs = consumoRs;
    }

    public double getConsumoDia() {
        return consumoDia;
    }

    public void setConsumoDia(double consumoDia) {
        this.consumoDia = consumoDia;
    }

    public double getConsumoMes() {
        return consumoMes;
    }

    public void setConsumoMes(double consumoMes) {
        this.consumoMes = consumoMes;
    }
}