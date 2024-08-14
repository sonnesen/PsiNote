package com.psinote.psinote.domain.valueobject;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    @NotBlank(message = "Rua é obrigatória")
    @Size(max = 100, message = "Rua não pode ter mais de 100 caracteres")
    private String rua;

    @NotBlank(message = "Cidade é obrigatória")
    @Size(max = 50, message = "Cidade não pode ter mais de 50 caracteres")
    private String cidade;

    @NotBlank(message = "Estado é obrigatório")
    @Size(max = 2, message = "Estado deve conter 2 caracteres")
    private String estado;

    @NotBlank(message = "CEP é obrigatório")
    @Pattern(regexp = "\\d{8}", message = "CEP deve conter 8 dígitos numéricos")
    private String cep;

}
