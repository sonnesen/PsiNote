package com.psinote.psinote.application.usecases.paciente;

import com.psinote.psinote.domain.entity.Paciente;
import com.psinote.psinote.domain.events.paciente.PacienteAtualizadoEvent;
import com.psinote.psinote.infra.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AtualizarPacienteUseCase {

    private final PacienteRepository pacienteRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public AtualizarPacienteUseCase(PacienteRepository pacienteRepository, ApplicationEventPublisher eventPublisher) {
        this.pacienteRepository = pacienteRepository;
        this.eventPublisher = eventPublisher;
    }

    public Paciente execute(Long pacienteId, Paciente pacienteAtualizado) {
        Optional<Paciente> pacienteExistenteOpt = pacienteRepository.findById(pacienteId);

        if (!pacienteExistenteOpt.isPresent())
            throw new RuntimeException("Paciente não encontrado com o ID: " + pacienteId);

        Paciente pacienteExistente = pacienteExistenteOpt.get();

        pacienteExistente.setNome(pacienteAtualizado.getNome());
        pacienteExistente.setCpf(pacienteAtualizado.getCpf());
        pacienteExistente.setEmail(pacienteAtualizado.getEmail());
        pacienteExistente.setEndereco(pacienteAtualizado.getEndereco());

        Paciente pacienteSalvo = pacienteRepository.save(pacienteExistente);

        PacienteAtualizadoEvent event = new PacienteAtualizadoEvent(this, pacienteSalvo);
        eventPublisher.publishEvent(event);

        return pacienteSalvo;
    }
}

