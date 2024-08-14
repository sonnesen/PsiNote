package com.psinote.psinote.application.usecases;

import com.psinote.psinote.application.dto.PacienteDTO;
import com.psinote.psinote.domain.entity.Paciente;
import com.psinote.psinote.infra.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastrarPacienteUseCase {

    private final PacienteRepository pacienteRepository;

    @Autowired
    public CadastrarPacienteUseCase(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente execute(Paciente paciente) {
           Paciente savedPaciente = pacienteRepository.save(paciente);
        return savedPaciente;
    }
}

