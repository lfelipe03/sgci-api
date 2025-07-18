package br.com.SGCI.service;

import br.com.SGCI.controller.schema.*;
import br.com.SGCI.model.Endereco;
import br.com.SGCI.model.Pessoa;
import br.com.SGCI.repository.PessoaRepository;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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

    public ResponsePagedCommom<PessoaResponse> findAll(@Valid PessoaFilter filtros) {




        Specification<Pessoa> filtrosCustomizados = (root, query, cb) -> {

            List<Predicate> condicoes = new ArrayList<>();

            if(filtros.getNome() != null) {
                condicoes.add(cb.like(root.get("nome"), "%" + filtros.getNome() + "%"));
            }

            if(filtros.getCep() != null) {
                condicoes.add(cb.equal(root.get("endereco").get("cep"),  filtros.getCep()));
            }

            if(filtros.getEstado() != null) {
                condicoes.add(cb.equal(root.get("endereco").get("estado"),  filtros.getEstado()));
            }

            if(filtros.getCidade() != null) {
                condicoes.add(cb.equal(root.get("endereco").get("cidade"),  filtros.getCidade()));
            }

            if(filtros.getTipo() != null) {
                condicoes.add(cb.equal(root.get("tipo"),  filtros.getTipo()));
            }

            if(filtros.getDocumento() != null) {
                condicoes.add(cb.equal(root.get("documento"), filtros.getDocumento()));
            }

            return cb.and(condicoes.toArray(Predicate[]::new));
        };


        Page<Pessoa> listPessoaBd = pessoaRepository.findAll(
                filtrosCustomizados,
                PageRequest.of(filtros.getPage(), filtros.getSize(), Sort.by(filtros.getDirection(), filtros.getOrdenarPor()))
        );

        List<PessoaResponse> listResponse = new ArrayList<PessoaResponse>();

        listPessoaBd.forEach(item -> {
            EnderecoResponse enderecoResponse = EnderecoMapper.INSTANCE.toEnderecoResponse(item.getEndereco());
            PessoaResponse pessoaResponse = PessoaMapper.INSTANCE.toPessoaResponse(item, enderecoResponse);
            listResponse.add(pessoaResponse);
        });

        return new ResponsePagedCommom<PessoaResponse>(listResponse,  listPessoaBd.getTotalElements(), listPessoaBd.getTotalPages(), listPessoaBd.getSize());
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
