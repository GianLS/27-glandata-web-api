package br.com.glandata.web.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.glandata.web.dto.ClienteDto;
import br.com.glandata.web.model.Cliente;
import br.com.glandata.web.service.ClienteService;

@RestController
@RequestMapping("api/clientes")
public class ClienteRestController {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("")
	@Cacheable(value = "listaDeClientes")
	public List<ClienteDto> listarClientes() {
		return clienteService.findAll().stream().map(c -> modelMapper.map(c, ClienteDto.class)).collect(Collectors.toList());

	}

	@GetMapping("{id}")
	public ResponseEntity<ClienteDto> buscarPorId(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteService.buscarPorId(id);

		if (cliente.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<ClienteDto>(modelMapper.map(cliente.get(), ClienteDto.class), HttpStatus.OK);
	}

	@CacheEvict(value = "listaDeClientes", allEntries = true)
	@PostMapping("")
	public ResponseEntity<ClienteDto> postCliente(@RequestBody Cliente cliente) {
		return ResponseEntity.ok(new ClienteDto(clienteService.save(cliente)));
	}

	@CacheEvict(value = "listaDeClientes", allEntries = true)
	@PutMapping("")
	public ResponseEntity<ClienteDto> putCliente(@RequestBody Cliente cliente) {
		return ResponseEntity.ok(new ClienteDto(clienteService.save(cliente)));
	}

	@CacheEvict(value = "listaDeClientes", allEntries = true)
	@DeleteMapping("{id}")
	public ResponseEntity<ClienteDto> deleteCliente(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteService.buscarPorId(id);

		if (cliente.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(new ClienteDto(clienteService.delete(id)));
	}
}
