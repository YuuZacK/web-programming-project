package com.lucasmartins.projetoweb.model.repository;

import com.lucasmartins.projetoweb.model.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer> {
    Optional<Turma> findByNome(String nome);
    List<Turma> findByPeriodo(String periodo);
    List<Turma> findByCurso(String curso);
}
