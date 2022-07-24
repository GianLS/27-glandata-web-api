package br.com.glandata.web.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.glandata.web.model.Alerta;
import br.com.glandata.web.model.Categoria;
import br.com.glandata.web.repository.CategoriaRepository;

@Controller
@RequestMapping("categorias")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;

	/**
	 * Método que retorna a página com a listagem de todos os categorias.
	 * 
	 * @return Página com listagem de todos os categorias.
	 */
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("categoria/index");

		List<Categoria> categorias = categoriaRepository.findAll();

		model.addObject("categorias", categorias);

		return model;
	}

	/**
	 * Método para carregar a tela de cadastrar um novo categoria.
	 * 
	 * @param categoria Objeto Categoria que será preenchido para ser incluído.
	 * @return Página para o cadastro de um novo categoria.
	 */
	@GetMapping("cadastrar")
	public ModelAndView getIncluir(Categoria categoria) {
		return new ModelAndView("categoria/cadastrar");
	}

	/**
	 * Método para incluir um novo Categoria no banco de dados
	 * 
	 * @param categoria Objeto Categoria para ser gravado.
	 * @param result    Objeto resultante da validação feita no Modelo.
	 * @param redirect  Objeto que permite o redirecionamento de página.
	 * @return
	 */
	@PostMapping("cadastrar")
	public ModelAndView postIncluir(@Valid Categoria categoria, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return new ModelAndView("categoria/cadastrar");
		}

		categoriaRepository.save(categoria);

		redirect.addFlashAttribute("mensagem", new Alerta("alert-success", "Categoria cadastrada com sucesso!!", "fas fa-check-circle"));

		return new ModelAndView("redirect:/categorias");
	}

	/**
	 * Método busca os dados de um categoria a partir do seu ID
	 * 
	 * @param id ID do categoria.
	 * @return Retorna um formulário com os dados do categoria informado pelo ID.
	 */
	@GetMapping("{id}/editar")
	public ModelAndView getEditar(@PathVariable Long id) {
		ModelAndView model = new ModelAndView("categoria/editar");

		Optional<Categoria> categoria = categoriaRepository.findById(id);

		if (categoria.isEmpty()) {
			return new ModelAndView("home/pages-404");
		}

		model.addObject("categoria", categoria.get());

		return model;
	}

	/**
	 * Realiza a gravação dos dados de um categoria já existe, a partir do objeto
	 * recebido.
	 * 
	 * @param categoria Objeto do tipo Categoria.
	 * @return Retorna para a página de listagem de categorias, exibindo a mensagem
	 *         de confirmação.
	 */
	@PostMapping("editar")
	public ModelAndView postEditar(@Valid Categoria categoria, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return new ModelAndView("categoria/editar");
		}

		categoriaRepository.save(categoria);

		redirect.addFlashAttribute("mensagem", new Alerta("alert-success", "Categoria alterada com sucesso!!", "fas fa-check-circle"));

		return new ModelAndView("redirect:/categorias");
	}

	/**
	 * Método para deletar um categoria a partir do ID informado.
	 * 
	 * @param id ID do categoria. de confirmação
	 */
	@PostMapping("deletar")
	public ModelAndView postDeletar(Long id, RedirectAttributes redirect) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);

		if (categoria.isEmpty()) {
			return new ModelAndView("redirect:/erro404");
			
		} else if (!categoria.get().getProdutos().isEmpty()) {
			redirect.addFlashAttribute("mensagem", new Alerta("alert-danger", "Categoria não pôde ser excluída pois está sendo utilizada em algum produto!!", "fas fa-exclamation-circle"));
		
		} else {
			categoriaRepository.delete(categoria.get());
			redirect.addFlashAttribute("mensagem", new Alerta("alert-success", "Categoria excluída com sucesso!!", "fas fa-check-circle"));
		}

		return new ModelAndView("redirect:/categorias");
	}
}
