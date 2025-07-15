package br.com.SGCI.controller.schema;

import jakarta.validation.constraints.NotNull;

public record EnderecoDTO(
        String cep,

        @NotNull
        String estado,

        @NotNull
        String cidade,

        String rua,
        String bairro,
        Integer numero
) {
}
