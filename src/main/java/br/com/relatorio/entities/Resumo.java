package br.com.relatorio.entities;

import io.quarkus.hibernate.orm.panache.common.ProjectedConstructor;

public record Resumo(String nomeCurso, Long total, Double media) {
    
    @ProjectedConstructor
    public Resumo(String nomeCurso, Long total, Double media) {
        this.nomeCurso = nomeCurso;
        this.total = total;
        this.media = media;
    }
}
