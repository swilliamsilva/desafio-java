package com.kipho.AppRestauranteDJ;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kipho.AppRestauranteDJ.business.ItemPedido;
import com.kipho.AppRestauranteDJ.models.Pedido;
import com.kipho.AppRestauranteDJ.models.Produto;

public class ItemPedidoTest {

    private static final Logger logger = LoggerFactory.getLogger(ItemPedidoTest.class);
    private ItemPedido itemPedido;
    private Pedido pedido;
    private Produto produto;

    @BeforeEach
    void setUp() {
        logger.debug("Configurando o teste para ItemPedido...");
        pedido = new Pedido(1L, 100.0, "Em andamento"); // Criação de um pedido para referência
        produto = new Produto("Hamburger", 10.0, "Comida"); // Produto de exemplo

        itemPedido = new ItemPedido();
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(2);
        itemPedido.setPrecoUnitario(10.0);

        logger.debug("ItemPedido configurado com Pedido, Produto, quantidade e preço unitário.");
    }

    @Test
    void testItemPedidoCreation() {
        logger.debug("Testando criação de ItemPedido...");
        assertNotNull(itemPedido, "ItemPedido não deveria ser nulo");
        assertEquals(2, itemPedido.getQuantidade(), "A quantidade deve ser 2");
        assertEquals(10.0, itemPedido.getPrecoUnitario(), "O preço unitário deve ser 10.0");
    }

    @Test
    void testSettersAndGetters() {
        logger.debug("Testando setters e getters de ItemPedido...");
        itemPedido.setQuantidade(3);
        itemPedido.setPrecoUnitario(12.0);

        assertEquals(3, itemPedido.getQuantidade(), "Nova quantidade deve ser 3");
        assertEquals(12.0, itemPedido.getPrecoUnitario(), "Novo preço unitário deve ser 12.0");
    }

    @Test
    void testGetPedidoAndProduto() {
        logger.debug("Testando métodos de obtenção de Pedido e Produto...");
        assertSame(pedido, itemPedido.getPedido(), "O Pedido deve ser o mesmo definido anteriormente");
        assertSame(produto, itemPedido.getProduto(), "O Produto deve ser o mesmo definido anteriormente");
    }

    @Test
    void testValidationConstraints() {
        logger.debug("Testando validações para quantidade mínima...");
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> itemPedido.setQuantidade(0),
            "Deve lançar uma exceção se a quantidade for menor que 1"
        );
        assertTrue(exception.getMessage().contains("A quantidade deve ser maior ou igual a 1"));
    }
}

