package br.com.glandata.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.glandata.web.model.Cliente;
import br.com.glandata.web.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Optional<Cliente> buscarPorId(Long id) {
		return clienteRepository.findById(id);
	}

	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Cliente delete(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);

		clienteRepository.delete(cliente.get());

		return cliente.get();
	}

}
