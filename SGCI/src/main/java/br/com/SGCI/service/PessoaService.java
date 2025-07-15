package br.com.SGCI.service;

import br.com.SGCI.controller.schema.PessoaDTO;
import br.com.SGCI.model.Endereco;
import br.com.SGCI.model.Pessoa;
import br.com.SGCI.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional
    public Pessoa createPessoa(PessoaDTO dto) {

        Endereco endereco = new Endereco
                (dto.endereco().cep(),
                 dto.endereco().estado(),
                 dto.endereco().cidade(),
                 dto.endereco().rua(),
                 dto.endereco().bairro(),
                 dto.endereco().numero()
                );
        Pessoa pessoa = new Pessoa(
                endereco, dto.nome(),
                dto.tipo(), dto.documento(),
                dto.profissao(),
                dto.estadoCivil());

        return pessoaRepository.save(pessoa);
    }

    @Transactional
    public void deletePessoa(Long idPessoa) {
        Pessoa pessoa = pessoaRepository.findById(idPessoa).orElseThrow();
        pessoaRepository.delete(pessoa);

    }
}
