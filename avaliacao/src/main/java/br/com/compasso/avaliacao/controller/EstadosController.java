package br.com.compasso.avaliacao.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.avaliacao.controller.dto.EstadoDto;
import br.com.compasso.avaliacao.controller.form.EstadoForm;
import br.com.compasso.avaliacao.modelo.Estado;
import br.com.compasso.avaliacao.repository.EstadoRepository;

@RestController
@RequestMapping("/api/states")
public class EstadosController {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@GetMapping
	public List<EstadoDto> lista(String regiao) {
		if (regiao == null) {
			List<Estado> estados = estadoRepository.findAll();
			return EstadoDto.converter(estados);
		}else {
			List<Estado> estados = estadoRepository.findByRegiao(regiao);
			return EstadoDto.converter(estados);
		}
	}
	
	
	@GetMapping("/filtrarPopulacao")
	public List<EstadoDto> maiorPopulacao(Long populacao) {
		List<Estado> estados = estadoRepository.filtrarPopulacao(populacao);
		return EstadoDto.converter(estados);		
	}
	
	@GetMapping("/filtrarArea")
	public List<EstadoDto> maiorArea(Double area) {
		List<Estado> estados = estadoRepository.filtrarArea(area);
		return EstadoDto.converter(estados);		
	}
	

	
	@GetMapping("/{id}")
	public ResponseEntity<EstadoDto> detalhar(@PathVariable Long id) {
		Optional<Estado> estado = estadoRepository.findById(id);
		if(estado.isPresent()) {
			return ResponseEntity.ok(new EstadoDto(estado.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<EstadoDto> cadastrar(@RequestBody @Valid EstadoForm form, UriComponentsBuilder uriBuilder) {
		Estado estado = form.converter();
		estadoRepository.save(estado);
		
		URI uri = uriBuilder.path("/api/states/{id}").buildAndExpand(estado.getId()).toUri();
		return ResponseEntity.created(uri).body(new EstadoDto(estado));
	}
	
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<EstadoDto> atualizar(@PathVariable Long id, @RequestBody @Valid EstadoForm form){
		Optional<Estado> op = estadoRepository.findById(id);
		if(op.isPresent()) {
			Estado estado = form.atualizar(id, estadoRepository);
			return ResponseEntity.ok(new EstadoDto(estado));
		}
		return ResponseEntity.notFound().build();
	}
	
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Estado> op = estadoRepository.findById(id);
		if(op.isPresent()) {
			estadoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}















