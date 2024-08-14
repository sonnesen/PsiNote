package com.psinote.psinote.application.usecases.paciente;

import com.psinote.psinote.domain.events.paciente.PacienteExcluidoEvent;
import com.psinote.psinote.infra.repository.PacienteRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class ExcluirPacienteUseCase {

    private final PacienteRepository pacienteRepository;
    private final ApplicationEventPublisher eventPublisher;

    public ExcluirPacienteUseCase(PacienteRepository pacienteRepository, ApplicationEventPublisher eventPublisher) {
        this.pacienteRepository = pacienteRepository;
        this.eventPublisher = eventPublisher;
    }

    public boolean execute(Long id) {
        if (!pacienteRepository.existsById(id))
            return false;
        pacienteRepository.deleteById(id);
        eventPublisher.publishEvent(new PacienteExcluidoEvent(this, id));
        return true;
    }
}