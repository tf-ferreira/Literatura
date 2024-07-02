package com.tf.literatura.repository;

import com.tf.literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor,Long> {
}
