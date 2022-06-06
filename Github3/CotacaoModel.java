package br.espm.cambio;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cotacao")
public class CotacaoModel {
    
    @Id
    @Column(name = "id_cotacao")
    private String idCotacao;

    @Column(name = "id_moeda")
    private String idMoeda;

    @Column(name = "dt_data")
    private LocalDate dtData;

    @Column(name = "vr_valor")
    private Double valor;

    public CotacaoModel(){
    }

    public CotacaoModel(Cotacao cotacao){
        this.idCotacao = cotacao.getId().toString();
        this.idMoeda = cotacao.getIdMoeda().toString();
        this.dtData = cotacao.getDtData();
        this.valor = cotacao.getValor();
    }

    public Cotacao to(){
        Cotacao cotacao = new Cotacao();
        cotacao.setId(UUID.fromString(this.idCotacao));
        cotacao.setIdMoeda(UUID.fromString(this.idMoeda));
        cotacao.setDtData(this.dtData); 
        cotacao.setValor(this.valor);
        return cotacao;
    }
}