package com.edu.gamesandchips.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.gamesandchips.domain.Tecnico;

//Persistencia no banco, ele espera uma classe e o tipo promitivo do objeto identificar da classe
public interface TecnicoRepository extends JpaRepository<Tecnico, Integer>{

}
