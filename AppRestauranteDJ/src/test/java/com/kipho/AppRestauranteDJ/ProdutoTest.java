package com.kipho.AppRestauranteDJ;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kipho.AppRestauranteDJ.models.Produto;

import static org.junit.jupiter.api.Assertions.*;

public class ProdutoTest {

    private static final Logger logger = LoggerFactory.getLogger(ProdutoTest.class);
    private Produto produto;

    @BeforeEach
    public void setUp() {
        logger.info("Configurando teste...");
        produto = new Produto("Arroz", 10.0, "Alimentos");
    }

    @Test
    public void testCreateProdutoWithConstructor() {
        logger.info("Testando construtor com argumentos...");
        assertNotNull(produto);
        assertEquals("Arroz", produto.getNome());
        assertEquals(10.0, produto.getPreco());
        assertEquals("Alimentos", produto.getCategoria());
    }

    @Test
    public void testCreateProdutoWithoutConstructor() {
        logger.info("Testando construtor vazio...");
        Produto emptyProduto = new Produto();
        assertNotNull(emptyProduto);

        emptyProduto.setNome("Feijão");
        emptyProduto.setPreco(15.0);
        emptyProduto.setCategoria("Alimentos");

        assertEquals("Feijão", emptyProduto.getNome());
        assertEquals(15.0, emptyProduto.getPreco());
        assertEquals("Alimentos", emptyProduto.getCategoria());
    }

    @Test
    public void testGettersAndSetters() {
        logger.info("Testando getters e setters...");
        produto.setId(1L);
        produto.setNome("Milho");
        produto.setPreco(20.0);
        produto.setCategoria("Grãos");

        assertEquals(1L, produto.getId());
        assertEquals("Milho", produto.getNome());
        assertEquals(20.0, produto.getPreco());
        assertEquals("Grãos", produto.getCategoria());
    }

    @Test
    public void testProdutoToString() {
        logger.info("Testando o método toString...");
        String expected = "Produto{id=null, nome='Arroz', preco=10.0, categoria='Alimentos'}";
        String actual = produto.toString();
        assertEquals(expected, actual);
    }
}

