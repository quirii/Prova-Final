package br.espm.cambio;

import java.time.LocalDate;
import java.util.UUID;

public class Cotacao {

    private UUID id;
    private UUID idMoeda;
    private LocalDate dtData;
    private Double valor;

    public Cotacao(){
    }

    public Cotacao(Double valor){
        this.valor = valor;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getIdMoeda() {
        return idMoeda;
    }

    public void setIdMoeda(UUID idMoeda) {
        this.idMoeda = idMoeda;
    }

    public LocalDate getDtData() {
        return dtData;
    }

    public void setDtData(LocalDate data) {
        this.dtData = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    
}