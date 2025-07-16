package br.com.SGCI.controller.schema;

import br.com.SGCI.model.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EnderecoMapper {
    EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);

    EnderecoResponse toEnderecoResponse(Endereco endereco);
}
