package com.psinote.psinote.application.usecases;
import com.psinote.psinote.domain.entity.Paciente;
import com.psinote.psinote.domain.events.PacienteCadastradoEvent;
import com.psinote.psinote.infra.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class CadastrarPacienteUseCase {

    private final PacienteRepository pacienteRepository;
    private final ApplicationEventPublisher eventPublisher;


    @Autowired
    public CadastrarPacienteUseCase(PacienteRepository pacienteRepository, ApplicationEventPublisher eventPublisher) {
        this.pacienteRepository = pacienteRepository;
        this.eventPublisher = eventPublisher;
    }

    public Paciente execute(Paciente paciente) {
           Paciente savedPaciente = pacienteRepository.save(paciente);

        PacienteCadastradoEvent event = new PacienteCadastradoEvent(this, savedPaciente);
        eventPublisher.publishEvent(event);

        return savedPaciente;
    }
}

