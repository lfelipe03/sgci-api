package br.com.SGCI.controller.schema;

import br.com.SGCI.model.Pessoa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PessoaMapper {
    PessoaMapper INSTANCE = Mappers.getMapper(PessoaMapper.class);

    @Mapping(source = "enderecoResponse", target = "endereco")
    PessoaResponse toPessoaResponse(Pessoa pessoa, EnderecoResponse enderecoResponse);
}
