package com.tf.literatura;

import com.tf.literatura.main.Main;
import com.tf.literatura.repository.AutorRepository;
import com.tf.literatura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private AutorRepository autorRepository;


    public static void main(String[] args) {

        SpringApplication.run(LiteraturaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Main main = new Main();
        main.menu(livroRepository,autorRepository);
    }
}
