package com.tf.literatura.model;

import jakarta.persistence.*;

@Entity
@Table(name="livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @ManyToOne
    @JoinColumn(name="autor")
    private Autor autor;

    public Livro() {
    }

    public Livro(DadosLivros dados) {
        this.titulo = dados.titulo();
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
