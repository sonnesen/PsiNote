package com.psinote.psinote.domain.events.paciente;

import com.psinote.psinote.domain.entity.Paciente;
import org.springframework.context.ApplicationEvent;

public class PacienteCadastradoEvent extends ApplicationEvent {

    private final Paciente paciente;

    public PacienteCadastradoEvent(Object source, Paciente paciente) {
        super(source);
        this.paciente = paciente;
    }

    public Paciente getPaciente() {
        return paciente;
    }
}
