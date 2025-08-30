package com.lucasmartins.projetoweb.service;

import com.lucasmartins.projetoweb.controller.dto.TurmaDto;
import com.lucasmartins.projetoweb.exception.CursoNotFoundException;
import com.lucasmartins.projetoweb.exception.PeriodoNotFoundException;
import com.lucasmartins.projetoweb.exception.TurmaNotFoundException;
import com.lucasmartins.projetoweb.model.entity.Turma;
import com.lucasmartins.projetoweb.model.repository.TurmaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;

    public TurmaService(TurmaRepository turmaRepository) {
        this.turmaRepository = turmaRepository;
    }

    public TurmaDto cadastrarTurma(TurmaDto dto) {
        if (dto.getNome() == null || dto.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome da turma é obrigatório");
        }
        Turma turma = new Turma();
        turma.setNome(dto.getNome());
        turma.setCurso(dto.getCurso());
        turma.setPeriodo(dto.getPeriodo());
        Turma saved = turmaRepository.save(turma);
        return toDto(saved);
    }

    public TurmaDto findById(Integer id) {
        Turma turma = turmaRepository.findById(id)
                .orElseThrow(() -> new TurmaNotFoundException("Turma não encontrada com ID " + id));
        return toDto(turma);
    }

    public List<TurmaDto> listarTodas() {
        List<Turma> turmas = turmaRepository.findAll();
        if (turmas.isEmpty()) throw new TurmaNotFoundException("Nenhuma turma cadastrada");
        return turmas.stream().map(this::toDto).collect(Collectors.toList());
    }

    public TurmaDto buscarPorNome(String nome) {
        Turma turma = turmaRepository.findByNome(nome)
                .orElseThrow(() -> new TurmaNotFoundException("Nenhuma turma encontrada com nome " + nome));
        return toDto(turma);
    }

    public List<TurmaDto> buscarPorPeriodo(String periodo) {
        List<Turma> turmas = turmaRepository.findByPeriodo(periodo);
        if (turmas.isEmpty()) throw new PeriodoNotFoundException("Nenhuma turma encontrada no período " + periodo);
        return turmas.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<TurmaDto> buscarPorCurso(String curso) {
        List<Turma> turmas = turmaRepository.findByCurso(curso);
        if (turmas.isEmpty()) throw new CursoNotFoundException("Nenhuma turma encontrada para o curso " + curso);
        return turmas.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void deletarPorId(Integer id) {
        if (!turmaRepository.existsById(id)) {
            throw new TurmaNotFoundException("Não existe turma com id " + id);
        }
        turmaRepository.deleteById(id);
    }

    private TurmaDto toDto(Turma t) {
        TurmaDto dto = new TurmaDto();
        dto.setId(t.getId());
        dto.setNome(t.getNome());
        dto.setCurso(t.getCurso());
        dto.setPeriodo(t.getPeriodo());
        return dto;
    }
}
