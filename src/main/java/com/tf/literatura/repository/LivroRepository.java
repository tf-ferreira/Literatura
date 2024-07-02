package com.tf.literatura.repository;

import com.tf.literatura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro,Long> {
    @Override
    List<Livro> findAll();
}
