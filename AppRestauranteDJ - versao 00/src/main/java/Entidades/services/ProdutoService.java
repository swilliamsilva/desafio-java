package Entidades.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Entidades.Produto;
import Entidades.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    // Criar um novo produto
    public Produto criarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    // Obter um produto pelo ID
    public Produto obterProdutoPorId(Long id) {
        Optional<Produto> optionalProduto = produtoRepository.findById(id);
        return optionalProduto.orElse(null);
    }

    // Atualizar um produto existente
    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        Optional<Produto> optionalProduto = produtoRepository.findById(id);
        if (optionalProduto.isPresent()) {
            Produto produtoExistente = optionalProduto.get();
            produtoExistente.setNome(produtoAtualizado.getNome());
            produtoExistente.setPreco(produtoAtualizado.getPreco());
            produtoExistente.setCategoria(produtoAtualizado.getCategoria());
            return produtoRepository.save(produtoExistente);
        } else {
            return null;
        }
    }

    // Excluir um produto
    public void excluirProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    // Listar todos os produtos
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }
}