package io.jbqneto.produtos.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jbqneto.produtos.models.Produto;
import io.jbqneto.produtos.repository.ProdutoRepository;

@RestController
@RequestMapping(value = "/api")
public class ProdutoResource {
	
	@Autowired
	ProdutoRepository produtoRepo;
	
	@GetMapping("/produtos")
	public List<Produto> getProdutos() {
		return produtoRepo.findAll();
	}
	
	@GetMapping("/produtos/{id}")
	public  Produto getProduto(@PathVariable(value="id") long id) {
		return produtoRepo.findById(id);
	}
	
	@PostMapping("/produtos")
	public Produto addProduto(@RequestBody Produto produto) {
		Produto produtoExiste = produtoRepo.findByNome(produto.getNome());
		if (produtoExiste == null) {
			return produtoRepo.save(produto);
		} else {
			return produtoExiste;
		}
	}
	
	@DeleteMapping("/produtos")
	public void deleteProduto(@RequestBody Produto produto) {
		produtoRepo.delete(produto);
	}
	
	@PutMapping("/produtos")
	public Produto updateProduto(@RequestBody Produto produto) {
		return produtoRepo.save(produto);
	}

}
