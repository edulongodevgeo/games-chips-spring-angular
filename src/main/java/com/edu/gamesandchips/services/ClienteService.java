package com.edu.gamesandchips.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.gamesandchips.domain.Cliente;
import com.edu.gamesandchips.domain.Pessoa;
import com.edu.gamesandchips.domain.DTOs.ClienteDTO;
import com.edu.gamesandchips.repositories.ClienteRepository;
import com.edu.gamesandchips.repositories.PessoaRepository;
import com.edu.gamesandchips.services.exceptions.DataIntegrityViolationException;
import com.edu.gamesandchips.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository tecnicoRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = tecnicoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
	}

	public List<Cliente> findAll() {
		return tecnicoRepository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {
		objDTO.setId(null); //Para segurar que o id irá nulo, pois tem um "automatizador" já no construtor...
		validaPorCpfEEmail(objDTO);
		Cliente newObj = new Cliente(objDTO);
		return tecnicoRepository.save(newObj);
	}
	
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		objDTO.setId(id);
		Cliente oldObj = findById(id);
		validaPorCpfEEmail(objDTO);
		oldObj = new Cliente(objDTO);
		return tecnicoRepository.save(oldObj);
	}
	
	public void delete(Integer id) {
		Cliente obj = findById(id);
		if(obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Cliente possui ordens de serviços, logo não pode ser deletado!");
		} else {
			tecnicoRepository.deleteById(id);
		}
	}

	private void validaPorCpfEEmail(ClienteDTO objDTO) {
		
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}
		
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
		}
	}



	
}
