package br.com.SGCI.factory.entity;

import br.com.SGCI.controller.schema.EnderecoDTO;
import br.com.SGCI.controller.schema.EnderecoUpd;
import br.com.SGCI.controller.schema.PessoaDTO;
import br.com.SGCI.model.Endereco;
import br.com.SGCI.model.EstadoCivilEnum;
import br.com.SGCI.model.TipoPessoaEnum;

public class EnderecoFactory {

    public static Endereco getEndereco() {
        return new Endereco("78783211", "MS", "Campo Grande", "Avenida Afonso Pena", "Centro", 5000);
    }

    public static EnderecoDTO getEnderecoDTO() {
        return new EnderecoDTO("78783211", "MS", "Campo Grande", "Avenida Afonso Pena", "Centro", 5000);
    }

    public static EnderecoUpd getEnderecoUpd () {
        return new EnderecoUpd("79873123", "SP", "Araçatuba", "Av 13 de Junho", "Nova Parati", 143);
    }

}
