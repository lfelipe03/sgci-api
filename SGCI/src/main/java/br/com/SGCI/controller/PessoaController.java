package br.com.SGCI.controller;

import br.com.SGCI.controller.schema.PessoaDTO;
import br.com.SGCI.model.Pessoa;
import br.com.SGCI.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


	//@GetMapping
	//public ResponseEntity<String> findAll() {
	//	return ResponseEntity.ok("Olá mundo!");}


	//@PutMapping


	@DeleteMapping(path = {"{idPessoa}"})
	public ResponseEntity<Long> deletePessoa(@PathVariable Long idPessoa) {
		pessoaService.deletePessoa(idPessoa);
		return ResponseEntity.ok().build();
	}


	
}
