package com.tf.literatura.main;

<<<<<<< HEAD
public class Main {
    public void menu(){
        System.out.println("Hi!");
=======
import com.tf.literatura.model.DadosLivros;
import com.tf.literatura.service.ConsumoApi;
import com.tf.literatura.service.ConverteDados;

public class Main {
    public void  menu(){

        ConsumoApi api = new ConsumoApi();
        String json = api.obterDados("https://gutendex.com/books/?ids=11");
        ConverteDados conversor = new ConverteDados();
        DadosLivros dadosLivros = conversor.obterDados(json, DadosLivros.class);
        System.out.println(dadosLivros);
>>>>>>> cd5e0f3 (fixes dependencies and connects Api)
    }
}
