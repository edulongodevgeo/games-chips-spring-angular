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
		Tecnico tec1 = new Tecnico(null, "Fulano de tal", "12345678900", "fulano@email.com", "123"); // Criou uma pessoa tecnico
		tec1.addPerfil(Perfil.ADMIN); // Setou ela realmente como tecnico
		
		Tecnico tec2 = new Tecnico(null, "Edu", "12345678911", "edu@email.com", "123"); // Criou uma pessoa tecnico
		tec2.addPerfil(Perfil.ADMIN); // Setou ela realmente como tecnico
		
		Cliente cli1 = new Cliente(null, "Ciclano", "98765432100", "ciclano@email.com", "123"); // Criou uma pessoa cliente
		//cli1.addPerfil(Perfil.CLIENTE);
		
		Chamado cham1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1); // Cria um chamado
	
		//Salvando as instâncias que foram criadas acima...
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		tecnicoRepository.saveAll(Arrays.asList(tec2));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(cham1));
	}

}
