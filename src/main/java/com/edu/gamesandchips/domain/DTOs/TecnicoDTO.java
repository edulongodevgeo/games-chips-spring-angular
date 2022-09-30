package com.edu.gamesandchips.domain.DTOs;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.edu.gamesandchips.domain.Tecnico;
import com.edu.gamesandchips.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;

public class TecnicoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Integer id;
	@NotBlank @NotNull(message = "O campo NOME é obrigatório!")
	protected String nome;
	@NotBlank @NotNull(message = "O campo CPF é obrigatório!")
	protected String cpf;
	@NotBlank @NotNull(message = "O campo E-MAIL é obrigatório!")
	protected String email;
	@NotBlank @NotNull(message = "O campo SENHA é obrigatório!")
	protected String senha;
	protected Set<Integer> perfis = new HashSet<>(); // Lista de perfis do tipo SET, ela tmb não deixa duplicar valores
	
	@JsonFormat(pattern = "dd/MM/yyyy") // Padroniza o formato da Data
	protected LocalDate dataCriacao = LocalDate.now(); // Pega a hora da criação, etc.
	
	public TecnicoDTO() {
		super();
		addPerfil(Perfil.TECNICO);
	}

	public TecnicoDTO(Tecnico obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.dataCriacao = obj.getDataCriacao();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		this.perfis.add(perfil.getCodigo());
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	

}
