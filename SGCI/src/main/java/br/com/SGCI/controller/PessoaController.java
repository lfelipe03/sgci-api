package br.com.SGCI.controller;

import br.com.SGCI.controller.schema.*;
import br.com.SGCI.model.Pessoa;
import br.com.SGCI.service.PessoaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pessoa::Pessoa")
@RestController
@RequestMapping("pessoas")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@PostMapping
	public ResponseEntity<Long> createPessoa(@Valid @RequestBody PessoaDTO dto) {
		Pessoa pessoa = pessoaService.createPessoa(dto);
		return ResponseEntity.ok(pessoa.getId());
	}


	@GetMapping
	public ResponseEntity<ResponsePagedCommom<PessoaResponse>> findAll(@Valid PessoaFilter filtros) {
		return ResponseEntity.ok(pessoaService.findAll(filtros));
	}

	@GetMapping(path = {"{idPessoa}"})
	public ResponseEntity<PessoaResponse> findById(@PathVariable (required = true) Long idPessoa) {
		return ResponseEntity.ok(pessoaService.findById(idPessoa));
	}


	@PutMapping(path = {"{idPessoa}"})
	public ResponseEntity<Long> updatePessoa(@Valid
											 @PathVariable (required = true) Long idPessoa ,
											 @RequestBody PessoaUpd upd) {
		Pessoa pessoa = pessoaService.updatePessoa(idPessoa, upd);
		return ResponseEntity.ok(pessoa.getId());
	}

	@DeleteMapping(path = {"{idPessoa}"})
	public ResponseEntity<Long> deletePessoa(@PathVariable Long idPessoa) {
		pessoaService.deletePessoa(idPessoa);
		return ResponseEntity.ok().build();
	}


	
}
