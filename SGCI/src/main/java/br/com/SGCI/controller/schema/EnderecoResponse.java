package br.com.SGCI.controller.schema;

import jakarta.validation.constraints.NotNull;

public record EnderecoResponse(

        Long id,
        String cep,

        String estado,

        String cidade,

        String rua,
        String bairro,
        Integer numero
) {
}
