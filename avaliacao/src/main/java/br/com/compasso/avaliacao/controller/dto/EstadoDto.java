package br.com.compasso.avaliacao.controller.dto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import br.com.compasso.avaliacao.modelo.Estado;

public class EstadoDto {
	
	private Long id;
	private String nome;
	private String regiao;
	private Long populacao;
	private String capital;
	private Double area;
	
	
	public EstadoDto() {
		
	}
	
	public EstadoDto(Estado estado) {
		this.id = estado.getId();
		this.nome = estado.getNome();
		this.regiao = estado.getRegiao();
		this.populacao = estado.getPopulacao();
		this.capital = estado.getCapital();
		this.area = estado.getArea();
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(area, capital, id, nome, populacao, regiao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstadoDto other = (EstadoDto) obj;
		return area == other.area && Objects.equals(capital, other.capital) && id == other.id
				&& Objects.equals(nome, other.nome) && populacao == other.populacao && regiao == other.regiao;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getRegiao() {
		return regiao;
	}

	public Long getPopulacao() {
		return populacao;
	}

	public String getCapital() {
		return capital;
	}

	public Double getArea() {
		return area;
	}

	public static List<EstadoDto> converter(List<Estado> estado) {
		return estado.stream().map(EstadoDto::new).collect(Collectors.toList());
	}
	
	
	
}

