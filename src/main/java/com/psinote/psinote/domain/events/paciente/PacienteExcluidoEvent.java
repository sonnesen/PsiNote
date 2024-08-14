package com.psinote.psinote.domain.events.paciente;

import org.springframework.context.ApplicationEvent;

public class PacienteExcluidoEvent extends ApplicationEvent {

    private final Long pacienteId;

    public PacienteExcluidoEvent(Object source, Long pacienteId) {
        super(source);
        this.pacienteId = pacienteId;
    }

    public Long getPacienteId() {
        return pacienteId;
    }
}