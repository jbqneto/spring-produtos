package io.jbqneto.produtos.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Produtos")
@CrossOrigin(origins = "*")
public class ProdutoResource {
	
	@Autowired
	ProdutoRepository produtoRepo;
	
	@GetMapping("/produtos")
	@ApiOperation(value = "Retorna uma lista de produtos")
	public List<Produto> getProdutos() {
		return produtoRepo.findAll();
	}
	
	@GetMapping("/produtos/{id}")
	@ApiOperation(value = "Retorna um determinado produto conforme o 'ID' passado")
	public  Produto getProduto(@PathVariable(value="id") long id) {
		return produtoRepo.findById(id);
	}
	
	@PostMapping("/produtos")
	@ApiOperation(value = "Cadastra um produto novo")
	public Produto addProduto(@RequestBody Produto produto) {
		Produto produtoExiste = produtoRepo.findByNome(produto.getNome());
		if (produtoExiste == null) {
			return produtoRepo.save(produto);
		} else {
			return produtoExiste;
		}
	}
	
	@DeleteMapping("/produtos")
	@ApiOperation(value = "Deleta um produto com base no conteúdo do 'body'")
	public void deleteProduto(@RequestBody Produto produto) {
		produtoRepo.delete(produto);
	}
	
	@PutMapping("/produtos")
	@ApiOperation(value = "Atualiza o produto passado no 'body'")
	public Produto updateProduto(@RequestBody Produto produto) {
		return produtoRepo.save(produto);
	}

}
