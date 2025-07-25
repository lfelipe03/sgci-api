package br.com.SGCI.factory.entity;

import br.com.SGCI.controller.schema.*;
import br.com.SGCI.model.Endereco;
import br.com.SGCI.model.EstadoCivilEnum;
import br.com.SGCI.model.Pessoa;
import br.com.SGCI.model.TipoPessoaEnum;
import org.springframework.data.domain.Sort;

import java.util.Random;

public class PessoaFactory {

    public static Pessoa getPessoa(Endereco endereco) {
        return new Pessoa(endereco, "Lorem Impsum", TipoPessoaEnum.PESSOA_FISICA, gerarCpfAleatorio(), "Programador", EstadoCivilEnum.SOLTEIRO);
    }

    public static PessoaDTO getPessoaDTO(EnderecoDTO enderecoDTO, String documento) {
        return new PessoaDTO("Lorem Impsum", enderecoDTO,TipoPessoaEnum.PESSOA_FISICA, documento, "Programador", EstadoCivilEnum.SOLTEIRO);
    }

    public static PessoaUpd getPessoaUpd(EnderecoUpd enderecoUpd) {
        return new PessoaUpd("Lorem Impsum atualizado", enderecoUpd, TipoPessoaEnum.PESSOA_JURIDICA, gerarCpfAleatorio(), "Desenvolvedor Java", EstadoCivilEnum.CASADO);
    }

    private static String gerarCpfAleatorio() {
        Random random = new Random();

        int numeroNF = (random.nextInt(900000000) + 1000000000);
        return String.valueOf(numeroNF);
    }

    public static PessoaFilter getPessoaFilter(String cep) {
        PessoaFilter filtros = new PessoaFilter();

        filtros.setPage(0);
        filtros.setSize(1);
        filtros.setDirection(Sort.Direction.ASC);
        filtros.setOrdenarPor("nome");
        filtros.setCep(cep);

        return filtros;
    }

}
