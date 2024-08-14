package com.psinote.psinote.application.dto;

import com.psinote.psinote.domain.entity.Paciente;
import com.psinote.psinote.domain.valueobject.Cpf;
import com.psinote.psinote.domain.valueobject.Endereco;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PacienteDTO {
    private Long id;
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    @Size(max = 100, message = "Email não pode ter mais de 100 caracteres")
    private String email;
    private Cpf cpf;
    private Endereco endereco;

    public Paciente toDomain() {
        return new Paciente(this.id,this.nome,this.email,this.cpf,this.endereco);
    }
}
