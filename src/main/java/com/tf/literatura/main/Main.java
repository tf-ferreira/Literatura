package com.tf.literatura.main;


import com.tf.literatura.model.DadosBusca;
import com.tf.literatura.service.ConsumoApi;
import com.tf.literatura.service.ConverteDados;

import java.util.Scanner;

public class Main {
    private final String ENDERECO = "https://gutendex.com/books/?search=";
    Scanner leitor = new Scanner(System.in);

    public void  menu(){

        ConsumoApi api = new ConsumoApi();
        System.out.println("Digite o nome do livro: ");
        String nomeTitulo = leitor.nextLine();
        nomeTitulo = nomeTitulo.replace(" ","%20");
        String json = api.obterDados(ENDERECO+nomeTitulo);
        ConverteDados conversor = new ConverteDados();
        DadosBusca dadosBusca = conversor.obterDados(json, DadosBusca.class);
        if(dadosBusca.count().equals("1")){
            System.out.println(dadosBusca.livros());
        }else{
            System.out.println("Livro não encontrado");
        }
    }
}
