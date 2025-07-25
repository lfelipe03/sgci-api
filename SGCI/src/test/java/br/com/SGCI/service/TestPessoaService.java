package br.com.SGCI.service;

import br.com.SGCI.controller.schema.*;
import br.com.SGCI.factory.entity.EnderecoFactory;
import br.com.SGCI.factory.entity.PessoaFactory;
import br.com.SGCI.model.Pessoa;
import br.com.SGCI.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest
public class TestPessoaService {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private PessoaRepository pessoaRepository;

    Pessoa pessoa = null;

    @BeforeEach
    void setUp () {
        pessoa = pessoaRepository.save(PessoaFactory.getPessoa(EnderecoFactory.getEndereco()));
    }

    @Test
    void  createPessoa() {

        EnderecoDTO enderecoDTO = EnderecoFactory.getEnderecoDTO();
        PessoaDTO dto = PessoaFactory.getPessoaDTO(enderecoDTO, "13211123");
        Pessoa pessoa = pessoaService.createPessoa(dto);

        assertTrue(pessoa != null && pessoa.getId() > 0);

    }

    @Test
    void findPessoaById() {

        PessoaResponse pessoaEncontrada = pessoaService.findById(pessoa.getId());
        assertTrue(pessoaEncontrada != null && pessoaEncontrada.documento().equals(pessoa.getDocumento()));

    }

    @Test
    void findAllPaged() {

        ResponsePagedCommom<PessoaResponse> retorno =  pessoaService.findAll(PessoaFactory.getPessoaFilter(pessoa.getEndereco().getCep()));

        assertTrue(!retorno.getData().isEmpty() && retorno.getTotalPages() > 0);

    }

    @Test
    void deletePessoaById() {

        pessoaService.deletePessoa(pessoa.getId());
        Optional<Pessoa> pessoaOpt = pessoaRepository.findById(pessoa.getId());
        assertTrue(pessoaOpt.isEmpty());

    }

    @Test
    void updatePessoaById() {
        PessoaUpd novosDados = PessoaFactory.getPessoaUpd(EnderecoFactory.getEnderecoUpd());
        Pessoa pessoaAtualizada = pessoaService.updatePessoa(pessoa.getId(), novosDados);

        assertTrue(pessoaAtualizada.getNome().equals(novosDados.nome()));
        assertTrue(pessoaAtualizada.getDocumento().equals(novosDados.documento()));
    }




}
