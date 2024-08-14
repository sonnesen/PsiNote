package com.psinote.psinote.domain.services;

import com.psinote.psinote.infra.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

//    public Paciente cadastrarPaciente(Paciente paciente) {
//        return pacienteRepository.save(paciente);
//    }

}
