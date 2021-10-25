package br.com.compasso.avaliacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.compasso.avaliacao.modelo.Estado;


public interface EstadoRepository extends JpaRepository<Estado, Long>{

	List<Estado> findByRegiao(String regiao);

	
	@Query(value = "SELECT * FROM Estado ORDER BY populacao DESC", nativeQuery=true)
	List<Estado> filtrarPopulacao(Long populacao);
	
	
	@Query(value = "SELECT * FROM Estado ORDER BY area DESC", nativeQuery=true)
	List<Estado> filtrarArea(Double area);
	
	
	
}
