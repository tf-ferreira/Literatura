package com.tf.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivros(
        Integer id,
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DadosAutor> autor
) { }
