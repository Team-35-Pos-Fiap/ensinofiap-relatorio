package br.com.relatorio.entities;

import java.math.BigDecimal;
import java.math.BigInteger;

import io.quarkus.hibernate.orm.panache.common.ProjectedConstructor;
import io.quarkus.hibernate.orm.panache.common.ProjectedFieldName;
import io.quarkus.runtime.annotations.RegisterForReflection;

//@RegisterForReflection
public record Resumo(String nomeCurso, Long total, Double media) {
    
    @ProjectedConstructor
    public Resumo(String nomeCurso, Long total, Double media) {
        this.nomeCurso = nomeCurso;
        this.total = total;
        this.media = media;
    }
}

