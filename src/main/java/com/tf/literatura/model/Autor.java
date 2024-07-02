package com.tf.literatura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer anoNascimento;
    private Integer anoMorte;

    //@OneToMany(mappedBy = "autor",cascade = CascadeType.ALL)
    @OneToMany(mappedBy = "autor")
    private List<Livro> livros = new ArrayList<>();



    public Autor(List<DadosAutor> dadosList) {
        DadosAutor dados = dadosList.get(0);

        this.nome = dados.nome();
        var str = dados.anoNascimento().replaceAll("\\D", "");
        if(!str.isEmpty()){
            this.anoNascimento = Integer.valueOf(str);
        }else{
            this.anoNascimento = 0;
        }
        str = dados.anoMorte().replaceAll("\\D", "");
        if(!str.isEmpty()){
            this.anoMorte = Integer.valueOf(str);
        }else{
            this.anoMorte = 0;
        }
    }

    public Autor() {
    }

    public void setLivros(Livro livro) {
        this.livros.add(livro);
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Nome do autor: " + nome + ".";
    }
}
