package br.espm.cambio;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MoedaRepository extends CrudRepository<MoedaModel, String>{
    
    @Override
    Iterable<MoedaModel> findAll();

    @Override
    Optional<MoedaModel> findById(String id);

    // JQL - Java Query Language
    @Query("SELECT m from MoedaModel m WHERE UPPER(m.txSimbolo) = UPPER(:simbolo)")
    Optional<MoedaModel> findBySimbolo(@Param("simbolo") String simbolo);

    @Override
    void deleteById(String id);

    @Transactional
    @Modifying
    @Query("DELETE FROM MoedaModel m WHERE UPPER(m.txSimbolo) = UPPER(:simbolo)")
    void deleteBySimbolo(@Param("simbolo") String simbolo);
    
}