package com.psinote.psinote.application.listeners;
import com.psinote.psinote.domain.events.paciente.PacienteAtualizadoEvent;
import com.psinote.psinote.domain.events.paciente.PacienteCadastradoEvent;
import com.psinote.psinote.domain.entity.Paciente;
import com.psinote.psinote.domain.events.paciente.PacienteExcluidoEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PacienteEventListener {
    @EventListener
    public void handle(PacienteCadastradoEvent event) {
        Paciente paciente = event.getPaciente();
        System.out.println("Paciente cadastrado: " + paciente.getNome());
    }
    @EventListener
    public void handle(PacienteAtualizadoEvent event) {
        Paciente paciente = event.getPaciente();
        System.out.println("Paciente atualizado: " + paciente.getNome());
    }
    @EventListener
    public void handle(PacienteExcluidoEvent event) {
        System.out.println("Paciente excluido: " + event.getPacienteId());
    }
}