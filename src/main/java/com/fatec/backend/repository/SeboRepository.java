package com.fatec.backend.repository;

import com.fatec.backend.model.Sebo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeboRepository extends JpaRepository<Sebo, Long> {
    List<Sebo> findByCidade(String cidade);
}
