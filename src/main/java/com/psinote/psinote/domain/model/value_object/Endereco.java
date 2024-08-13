package com.psinote.psinote.domain.model.value_object;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String rua;
    private String cidade;
    private String estado;
    private String cep;

}
