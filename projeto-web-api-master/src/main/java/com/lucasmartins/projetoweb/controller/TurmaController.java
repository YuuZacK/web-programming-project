package com.lucasmartins.projetoweb.controller;

import com.lucasmartins.projetoweb.controller.dto.TurmaDto;
import com.lucasmartins.projetoweb.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaService turmaService;

    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @PostMapping
    public ResponseEntity<TurmaDto> cadastrar(@Valid @RequestBody TurmaDto dto) {
        TurmaDto created = turmaService.cadastrarTurma(dto);
        return ResponseEntity.created(URI.create("/turmas/" + created.getId())).body(created);
    }

    @GetMapping
    public List<TurmaDto> listarTodas() {
        return turmaService.listarTodas();
    }

    @GetMapping("/{id}")
    public TurmaDto buscarPorId(@PathVariable Integer id) {
        return turmaService.findById(id);
    }

    @GetMapping("/nome/{nome}")
    public TurmaDto buscarPorNome(@PathVariable String nome) {
        return turmaService.buscarPorNome(nome);
    }

    @GetMapping("/periodo/{periodo}")
    public List<TurmaDto> buscarPorPeriodo(@PathVariable String periodo) {
        return turmaService.buscarPorPeriodo(periodo);
    }

    @GetMapping("/curso/{curso}")
    public List<TurmaDto> buscarPorCurso(@PathVariable String curso) {
        return turmaService.buscarPorCurso(curso);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
        turmaService.deletarPorId(id);
    }
}
