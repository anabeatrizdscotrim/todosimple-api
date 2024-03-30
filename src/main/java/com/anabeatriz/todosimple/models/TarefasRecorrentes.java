package com.anabeatriz.todosimple.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name = TarefasRecorrentes.TABLE_NAME)
public class TarefasRecorrentes extends Tarefas {
    public static final String TABLE_NAME = "tarefas_recorrentes";

    @Column(name = "data_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date dataInicio;
    @Column(name = "data_fim", nullable = false)
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date dataFim;

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
}