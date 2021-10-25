package br.com.compasso.avaliacao.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;

import br.com.compasso.avaliacao.modelo.Estado;
import br.com.compasso.avaliacao.repository.EstadoRepository;

public class EstadoForm {
	
	@NotBlank
	private String nome;
	@NotBlank
	private String regiao;
	@NonNull
	private long populacao;
	@NotBlank
	private String capital;
	@NotNull
	private Double area;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRegiao() {
		return regiao;
	}
	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}
	public long getPopulacao() {
		return populacao;
	}
	public void setPopulacao(long populacao) {
		this.populacao = populacao;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	public Estado converter() {
		return new Estado(nome, regiao, populacao, capital, area);
	}
	public Estado atualizar(Long id, EstadoRepository estadoRepository) {
		Estado estado = estadoRepository.getById(id);
		estado.setNome(this.nome);
		estado.setRegiao(this.regiao);
		estado.setPopulacao(this.populacao);
		estado.setCapital(this.capital);
		estado.setArea(this.area);
		
		return estado;
	}
	
	
}
