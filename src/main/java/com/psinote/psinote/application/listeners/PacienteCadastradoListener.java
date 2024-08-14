package com.psinote.psinote.application.listeners;
import com.psinote.psinote.domain.events.PacienteCadastradoEvent;
import com.psinote.psinote.domain.entity.Paciente;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PacienteCadastradoListener {
    @EventListener
    public void handle(PacienteCadastradoEvent event) {
        Paciente paciente = event.getPaciente();
        System.out.println("Paciente cadastrado: " + paciente.getNome());
    }
}