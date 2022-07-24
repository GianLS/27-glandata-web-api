package br.com.glandata.web.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.glandata.web.model.Cliente;
import br.com.glandata.web.repository.ClienteRepository;

@Controller
@RequestMapping("clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	/**
	 * Método que retorna a página com a listagem de todos os clientes.
	 * 
	 * @return Página com listagem de todos os clientes.
	 */
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("cliente/index");

		List<Cliente> clientes = clienteRepository.findAll();

		model.addObject("clientes", clientes);

		return model;
	}

	/**
	 * Método para carregar a tela de cadastrar um novo cliente.
	 * 
	 * @param cliente Objeto Cliente que será preenchido para ser incluído.
	 * @return Página para o cadastro de um novo cliente.
	 */
	@GetMapping("cadastrar")
	@Secured({"ROLE_CADASTROS", "ROLE_ADMIN"})
	public ModelAndView getIncluir(Cliente cliente) {
		return new ModelAndView("cliente/cadastrar");
	}

	/**
	 * Método para incluir um novo Cliente no banco de dados
	 * 
	 * @param cliente  Objeto Cliente para ser gravado.
	 * @param result   Objeto resultante da validação feita no Modelo.
	 * @param redirect Objeto que permite o redirecionamento de página.
	 * @return
	 */
	@PostMapping("cadastrar")
	public ModelAndView postIncluir(@Valid Cliente cliente, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return new ModelAndView("cliente/cadastrar");
		}

		clienteRepository.save(cliente);

		redirect.addFlashAttribute("mensagem", "Cliente cadastrado com sucesso!!");

		return new ModelAndView("redirect:/clientes");
	}

	/**
	 * Método busca os dados de um cliente a partir do seu ID
	 * 
	 * @param id ID do cliente.
	 * @return Retorna um formulário com os dados do cliente informado pelo ID.
	 */
	@GetMapping("{id}/editar")
	public ModelAndView getEditar(@PathVariable Long id) {
		ModelAndView model = new ModelAndView("cliente/editar");

		Optional<Cliente> cliente = clienteRepository.findById(id);
		
		if (cliente.isEmpty()) {
			return new ModelAndView("home/pages-404");
		}

		model.addObject("cliente", cliente.get());

		return model;
	}

	/**
	 * Realiza a gravação dos dados de um cliente já existe, a partir do objeto
	 * recebido.
	 * 
	 * @param cliente Objeto do tipo Cliente.
	 * @return Retorna para a página de listagem de clientes, exibindo a mensagem de
	 *         confirmação.
	 */
	@PostMapping("editar")
	public ModelAndView postEditar(@Valid Cliente cliente, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return new ModelAndView("cliente/editar");
		}

		clienteRepository.save(cliente);

		redirect.addFlashAttribute("mensagem", "Cliente alterado com sucesso!!");

		return new ModelAndView("redirect:/clientes");
	}

	/**
	 * Método para deletar um cliente a partir do ID informado.
	 * 
	 * @param id ID do cliente.
	 * @return Retorna para a página de listagem de clientes, exibindo a mensagem de
	 *         confirmação
	 */
	@PostMapping("deletar")
	public ModelAndView postDeletar(Long id, RedirectAttributes redirect) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		
		if (cliente.isEmpty()) {
			return new ModelAndView("redirect:/erro404");
		}
		
		clienteRepository.delete(cliente.get());
		
		redirect.addFlashAttribute("mensagem", "Cliente excluído com sucesso!!");

		return new ModelAndView("redirect:/clientes");
	}
}
