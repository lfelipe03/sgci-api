package br.com.SGCI.controller.schema;

import br.com.SGCI.model.EstadoCivilEnum;
import br.com.SGCI.model.TipoPessoaEnum;
import jakarta.validation.constraints.NotNull;

public record PessoaDTO(
        @NotNull
        String nome,

        @NotNull
        EnderecoDTO endereco,

        @NotNull
        TipoPessoaEnum tipo,

        @NotNull
        String documento,

        @NotNull
        String profissao,

        @NotNull
        EstadoCivilEnum estadoCivil

) {
}
