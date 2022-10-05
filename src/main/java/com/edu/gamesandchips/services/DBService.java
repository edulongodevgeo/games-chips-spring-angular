package com.edu.gamesandchips.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.gamesandchips.domain.Chamado;
import com.edu.gamesandchips.domain.Cliente;
import com.edu.gamesandchips.domain.Tecnico;
import com.edu.gamesandchips.domain.enums.Perfil;
import com.edu.gamesandchips.domain.enums.Prioridade;
import com.edu.gamesandchips.domain.enums.Status;
import com.edu.gamesandchips.repositories.ChamadoRepository;
import com.edu.gamesandchips.repositories.ClienteRepository;
import com.edu.gamesandchips.repositories.TecnicoRepository;

@Service
public class DBService {
	
	//Injeção de dependência pelo Spring
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	public void instanciaDB() {
		Tecnico tec1 = new Tecnico(null, "Fulano de tal", "573.543.860-30", "fulano@email.com", "123"); // Criou uma pessoa tecnico
		tec1.addPerfil(Perfil.ADMIN); // Setou ela realmente como tecnico
		
		Tecnico tec2 = new Tecnico(null, "Edu", "600.433.720-07", "edu@email.com", "123"); // Criou uma pessoa tecnico
		tec2.addPerfil(Perfil.ADMIN); // Setou ela realmente como tecnico
		
		Cliente cli1 = new Cliente(null, "Ciclano", "429.385.850-47", "ciclano@email.com", "123"); // Criou uma pessoa cliente
		//cli1.addPerfil(Perfil.CLIENTE);
		
		Chamado cham1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1); // Cria um chamado
		Chamado cham2 = new Chamado(null, Prioridade.ALTA, Status.ANDAMENTO, "Chamado 02", "Segundo chamado", tec1, cli1); // Cria outro chamado

		
		
		//Salvando as instâncias que foram criadas acima...
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		tecnicoRepository.saveAll(Arrays.asList(tec2));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(cham1));
		chamadoRepository.saveAll(Arrays.asList(cham2));
	}

}
