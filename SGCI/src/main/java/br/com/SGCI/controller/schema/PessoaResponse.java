package br.com.SGCI.controller.schema;

import br.com.SGCI.model.EstadoCivilEnum;
import br.com.SGCI.model.TipoPessoaEnum;
import jakarta.validation.constraints.NotNull;

public record PessoaResponse(

        Long id,

        String nome,

        EnderecoResponse endereco,

        TipoPessoaEnum tipo,

        String documento,

        String profissao,

        EstadoCivilEnum estadoCivil

) {
}
