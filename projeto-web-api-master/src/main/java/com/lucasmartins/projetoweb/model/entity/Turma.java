package com.lucasmartins.projetoweb.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "turmas")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String curso;

    @Column(nullable = false)
    private String periodo;

    public Turma() {}

    public Turma(Integer id, String nome, String curso, String periodo) {
        this.id = id;
        this.nome = nome;
        this.curso = curso;
        this.periodo = periodo;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }

    public String getPeriodo() { return periodo; }
    public void setPeriodo(String periodo) { this.periodo = periodo; }
}
