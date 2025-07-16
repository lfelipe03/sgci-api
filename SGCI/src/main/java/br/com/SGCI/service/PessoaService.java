package br.com.SGCI.service;

import br.com.SGCI.controller.schema.*;
import br.com.SGCI.model.Endereco;
import br.com.SGCI.model.Pessoa;
import br.com.SGCI.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

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

    public List<PessoaResponse> findAll() {
        List<PessoaResponse> listResponse = new ArrayList<PessoaResponse>();

        List<Pessoa> listPessoaBd = pessoaRepository.findAll();
        listPessoaBd.forEach(item -> {
            EnderecoResponse enderecoResponse = EnderecoMapper.INSTANCE.toEnderecoResponse(item.getEndereco());
            PessoaResponse pessoaResponse = PessoaMapper.INSTANCE.toPessoaResponse(item, enderecoResponse);
            listResponse.add(pessoaResponse);
        });

        return listResponse;
    }

    @Transactional
    public Pessoa updatePessoa(@Valid Long idPessoa, PessoaUpd upd) {
        Pessoa pessoa = pessoaRepository.findById(idPessoa).orElseThrow();

        pessoa.setDocumento(upd.documento());
        pessoa.setEstadoCivil(upd.estadoCivil());
        pessoa.setNome(upd.nome());
        pessoa.setProfissao(upd.profissao());
        pessoa.setTipo(upd.tipo());

        pessoa.getEndereco().setCep(upd.endereco().cep());
        pessoa.getEndereco().setEstado(upd.endereco().estado());
        pessoa.getEndereco().setCidade(upd.endereco().cidade());
        pessoa.getEndereco().setRua(upd.endereco().rua());
        pessoa.getEndereco().setBairro(upd.endereco().bairro());
        pessoa.getEndereco().setNumero(upd.endereco().numero());


        pessoaRepository.save(pessoa);

        return pessoa;
    }

    public PessoaResponse findById(Long idPessoa) {
        Pessoa pessoa = pessoaRepository.findById(idPessoa).orElseThrow();

        EnderecoResponse enderecoResponse = EnderecoMapper.INSTANCE.toEnderecoResponse(pessoa.getEndereco());
        PessoaResponse pessoaResponse = PessoaMapper.INSTANCE.toPessoaResponse(pessoa, enderecoResponse);

        return pessoaResponse;
    }
}
