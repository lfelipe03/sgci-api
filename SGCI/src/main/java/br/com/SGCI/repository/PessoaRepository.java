package br.com.SGCI.repository;

import br.com.SGCI.model.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Page<Pessoa> findAll(Specification<Pessoa> filtrosCustomizados, Pageable pageable);
}
