package br.com.SGCI.controller.schema;

import jakarta.validation.constraints.NotNull;

public record EnderecoResponse(
        String cep,

        String estado,

        String cidade,

        String rua,
        String bairro,
        Integer numero
) {
}
