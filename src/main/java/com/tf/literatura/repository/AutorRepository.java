package com.tf.literatura.repository;

import com.tf.literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    Optional<Autor> findByNomeContainingIgnoreCase(String nomeAutor);
}
