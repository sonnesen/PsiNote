package com.psinote.psinote.application.controller;

import com.psinote.psinote.application.dto.PacienteDTO;
import com.psinote.psinote.application.usecases.CadastrarPacienteUseCase;
import com.psinote.psinote.domain.entity.Paciente;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    CadastrarPacienteUseCase cadastrarPacienteUseCase;


    @PostMapping
    public ResponseEntity<Paciente> cadastrarPaciente(@RequestBody @Valid PacienteDTO pacienteDTO) {
        var paciente=pacienteDTO.toDomain();
        var novoPaciente = cadastrarPacienteUseCase.execute(paciente);
        if (novoPaciente == null)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoPaciente);
    }
}