package com.edu.gamesandchips.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.gamesandchips.domain.Pessoa;

//Persistencia no banco, ele espera uma classe e o tipo promitivo do objeto identificar da classe
public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

	Optional<Pessoa> findByCpf(String cpf);
	Optional<Pessoa> findByEmail(String email);
}
