package com.kipho.AppRestauranteDJ;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entidades.Produto;
import Entidades.services.ProdutoService;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
public class ProdutoTest {
    
    private static final Logger LOGGER = Logger.getLogger(ProdutoTest.class.getName());

    @PersistenceContext
    private EntityManager em;

    @BeforeEach
    void setUp() {
        LOGGER.info("Configurando o ambiente para os testes de Produto...");
    }

    @AfterEach
    void tearDown() {
        LOGGER.info("Limpando o ambiente após os testes de Produto...");
    }

    @Test
    void testInclusaoProduto() {
        LOGGER.info("Iniciando o teste de inclusão de Produto...");

        // Inclusão de um novo produto
        Produto novoProduto = criarProduto("Bolo de Chocolate", 15.00, "Sobremesa");
        em.persist(novoProduto);

        LOGGER.info("Produto inserido no banco de dados com sucesso.");

        // Consulta para verificar se o produto foi incluído corretamente
        Produto produtoInserido = em.find(Produto.class, novoProduto.getId());
        assertNotNull(produtoInserido, "O produto não foi incluído corretamente no banco de dados.");
        assertEquals("Bolo de Chocolate", produtoInserido.getNome(), "O nome do produto não corresponde ao esperado.");

        LOGGER.info("Teste de inclusão de Produto concluído com sucesso.");
    }

    // Método auxiliar para criar um produto
    private Produto criarProduto(String nome, double preco, String categoria) {
        LOGGER.info("Criando novo Produto com nome: " + nome + ", preco: " + preco + ", categoria: " + categoria);
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setPreco(preco);
        produto.setCategoria(categoria);
        return produto;
    }
}