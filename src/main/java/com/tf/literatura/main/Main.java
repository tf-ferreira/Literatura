package com.tf.literatura.main;


import com.tf.literatura.model.Autor;
import com.tf.literatura.model.DadosBusca;
import com.tf.literatura.model.Livro;
import com.tf.literatura.repository.AutorRepository;
import com.tf.literatura.repository.LivroRepository;
import com.tf.literatura.service.ConsumoApi;
import com.tf.literatura.service.ConverteDados;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    private final String ENDERECO = "https://gutendex.com/books/?search=";
    ConsumoApi api = new ConsumoApi();
    LivroRepository livroRepository;
    AutorRepository autorRepository;

    public Main(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void  menu(){
        Scanner leitor = new Scanner(System.in);

        int opcaoMenu = -1;

        do{
            System.out.println("\n\n");
            System.out.println("*******************************************************");
            System.out.println("            (1) Buscar um Livro");
            System.out.println("            (2) Buscar um Autor");
            System.out.println("            (0) Sair");
            System.out.println("*******************************************************");

            opcaoMenu = leitor.nextInt();
            leitor.nextLine();

            switch(opcaoMenu){
                case 1:
                    buscaLivro();
                    break;
                case 2:
                    buscaAutor();
                    break;
            }
        }while(opcaoMenu != 0);
    }

    public void buscaLivro(){
        Scanner leitor = new Scanner(System.in);

        System.out.println("Digite o nome do livro: ");
        String nomeTitulo = leitor.nextLine();
        nomeTitulo = nomeTitulo.replace(" ","%20");
        String json = api.obterDados(ENDERECO+nomeTitulo);
        ConverteDados conversor = new ConverteDados();
        DadosBusca dadosBusca = conversor.obterDados(json, DadosBusca.class);
        if(dadosBusca.count().equals("1")){
            System.out.println(dadosBusca.livros());
            System.out.println("Deseja inserir no banco? ");
            System.out.println("(1)Sim\n(0)Não");
            int opcaoInserir = leitor.nextInt();
            leitor.nextLine();
            if(opcaoInserir==1){
                Livro livro  = new Livro(dadosBusca.livros().get(0));
                String nomeAutor =  dadosBusca.livros().get(0).autor().get(0).nome();
                Autor autor = null;

                Optional<Autor> autorBuscado= autorRepository.findByNomeContainingIgnoreCase(nomeAutor);
                if(autorBuscado.isPresent()){
                    autor = autorBuscado.get();
                    livro.setAutor(autor);
                    livroRepository.save(livro);
                }else{
                    autor = new Autor(dadosBusca.livros().get(0).autor());
                    autor.setLivros(livro);
                    livro.setAutor(autor);
                    autorRepository.save(autor);
                }
            }
        }else{
            System.out.println("Livro não encontrado");
        }
    }

    private void buscaAutor() {
        Scanner leitor = new Scanner(System.in);

        System.out.println("Digite o nome do Autor: ");
        String nomeAutor = leitor.nextLine();
        Optional<Autor> autorBuscado= autorRepository.findByNomeContainingIgnoreCase(nomeAutor);
        if(autorBuscado.isPresent()){
            Autor autor = autorBuscado.get();
            System.out.println(autor.getNome());
        }else{
            System.out.println("Autor nao encontrado");
        }
    }
}
