package com.anabeatriz.todosimple.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name = TarefasAvaliativas.TABLE_NAME)
public class TarefasAvaliativas extends Tarefas {

    public static final String TABLE_NAME = "tarefas_avaliativas";
    @Column(name = "data_avaliacao", nullable = false)
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date dataAvaliacao;
    @Column(name = "professor", length = 100, nullable = false)
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 100)
    private String professor;
    @Column(name = "nota", nullable = false)
    @NotNull
    private double nota;

    public Date getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(Date dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}