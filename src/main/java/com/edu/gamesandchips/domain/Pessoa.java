package com.edu.gamesandchips.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.br.CPF;

import com.edu.gamesandchips.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;

//PODERIA utilizar o LOMBOK, mas por didática do estudo mantive sem.

//Construção da nossa classe-mãe (SUPER), abstrata...
@Entity
public abstract class Pessoa implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // Geração automática de ID
	protected Integer id;
	protected String nome;
	
	@CPF
	@Column(unique = true)
	protected String cpf;
	
	@Column(unique = true)
	protected String email;	
	protected String senha;
	
	@ElementCollection(fetch=FetchType.EAGER) //Informa que essa é uma coleção que quando der um GET para buscar usuário, essa lista de PERFIS virá junto com o usuário
	@CollectionTable(name="PERFIS") // Cria então no DB uma tabela com este nome
	protected Set<Integer> perfis = new HashSet<>(); //Lista de perfis do tipo SET, ela tmb não deixa duplicar valores iguais.
	
	@JsonFormat(pattern = "dd/MM/yyyy") // Padroniza o formato da Data
	protected LocalDate dataCriacao = LocalDate.now(); //Pega a hora da criação, etc.
	
	public Pessoa() {
		super();
		addPerfil(Perfil.CLIENTE);
	}

	public Pessoa(Integer id, String nome, String cpf, String email, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		addPerfil(Perfil.CLIENTE);

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
		//Ou seja, ele vai em 'perfis', mapeia o x dos enumerados, e coleta o que for pedido.
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

	@Override
	public int hashCode() {
		return Objects.hash(cpf, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(id, other.id);
	}
	
	
	
}
