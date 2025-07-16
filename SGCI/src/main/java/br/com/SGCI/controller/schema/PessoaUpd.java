package br.com.SGCI.controller.schema;

import br.com.SGCI.model.EstadoCivilEnum;
import br.com.SGCI.model.TipoPessoaEnum;
import jakarta.validation.constraints.NotNull;

public record PessoaUpd(
        @NotNull
        String nome,

        @NotNull
        EnderecoUpd endereco,

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
