package com.psinote.psinote.application.controller;

import com.psinote.psinote.application.dto.PacienteDTO;
import com.psinote.psinote.application.usecases.paciente.*;
import com.psinote.psinote.domain.entity.Paciente;

import com.psinote.psinote.infra.repository.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    CadastrarPacienteUseCase cadastrarPacienteUseCase;
    @Autowired
    AtualizarPacienteUseCase atualizarPacienteUseCase;
    @Autowired
    ExcluirPacienteUseCase excluirPacienteUseCase;
    @Autowired
    PacienteRepository pacienteRepository;

    @GetMapping()
    public ResponseEntity<List<PacienteDTO>> buscarTodosPacientes() {
        var pacientes = pacienteRepository.findAll();

        if (pacientes == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        var pacientesDTO = pacientes.stream().map(paciente -> {
            var pacienteDTO = new PacienteDTO();
            pacienteDTO.setId(paciente.getId());
            pacienteDTO.setNome(paciente.getNome());
            pacienteDTO.setEmail(paciente.getEmail());
            pacienteDTO.setCpf(paciente.getCpf());
            pacienteDTO.setEndereco(paciente.getEndereco());
            return pacienteDTO;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(pacientesDTO);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> buscarPacientePorId(@PathVariable Long id) {
        var paciente = pacienteRepository.findById(id);

        if (paciente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        var pacienteDTO = new PacienteDTO();
        pacienteDTO.setId(paciente.get().getId());
        pacienteDTO.setNome(paciente.get().getNome());
        pacienteDTO.setEmail(paciente.get().getEmail());
        pacienteDTO.setCpf(paciente.get().getCpf());
        pacienteDTO.setEndereco(paciente.get().getEndereco());

        return ResponseEntity.ok(pacienteDTO);
    }
    @PostMapping
    public ResponseEntity<Paciente> cadastrarPaciente(@RequestBody @Valid PacienteDTO pacienteDTO) {
        var paciente=pacienteDTO.toDomain();
        var novoPaciente = cadastrarPacienteUseCase.execute(paciente);
        if (novoPaciente == null)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoPaciente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarPaciente(
            @PathVariable Long id,
            @RequestBody PacienteDTO pacienteDTO) {
        Paciente paciente = new Paciente();
        paciente.setNome(pacienteDTO.getNome());
        paciente.setCpf(pacienteDTO.getCpf());
        paciente.setEmail(pacienteDTO.getEmail());
        paciente.setEndereco(pacienteDTO.getEndereco());

        Paciente pacienteAtualizado = atualizarPacienteUseCase.execute(id, paciente);
        return ResponseEntity.ok(pacienteAtualizado);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPaciente(@PathVariable Long id) {
        boolean excluido = excluirPacienteUseCase.execute(id);

        if (!excluido) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}