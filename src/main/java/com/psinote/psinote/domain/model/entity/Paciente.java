package com.psinote.psinote.domain.model.entity;

import com.psinote.psinote.domain.model.value_object.Cpf;
import com.psinote.psinote.domain.model.value_object.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "paciente")
@Entity
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;

    @Embedded
    private Cpf cpf;

    @Embedded
    private Endereco endereco;
}
