package com.kipho.AppRestauranteDJ;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kipho.AppRestauranteDJ.business.ItemPedido;
import com.kipho.AppRestauranteDJ.models.Pedido;

import java.util.ArrayList;
import java.util.List;

public class PedidoTest {

    private static final Logger logger = LoggerFactory.getLogger(PedidoTest.class);
    private Pedido pedido;

    @BeforeEach
    void setUp() {
        // Configuração inicial antes de cada teste
        logger.debug("Configurando o teste com pedido inicial...");
        pedido = new Pedido(1L, 100.0, "Em andamento");
    }

    @Test
    void testCreatePedidoWithParameters() {
        // Testar a criação de um pedido com parâmetros
        logger.debug("Testando criação de pedido com parâmetros...");
        assertNotNull(pedido, "Pedido não deveria ser nulo");
        assertEquals(1L, pedido.getUsuarioId(), "ID do usuário deve ser 1");
        assertEquals(100.0, pedido.getValorTotal(), "Valor total deve ser 100");
        assertEquals("Em andamento", pedido.getStatus(), "Status deve ser 'Em andamento'");
    }

    @Test
    void testCreatePedidoWithoutParameters() {
        // Testar a criação de um pedido sem parâmetros
        logger.debug("Testando criação de pedido sem parâmetros...");
        Pedido emptyPedido = new Pedido();
        assertNotNull(emptyPedido, "Pedido sem parâmetros não deveria ser nulo");
    }

    @Test
    void testSettersAndGetters() {
        // Testar os métodos setters e getters
        logger.debug("Testando setters e getters...");
        pedido.setUsuarioId(2L);
        pedido.setValorTotal(200.0);
        pedido.setStatus("Concluído");

        assertEquals(2L, pedido.getUsuarioId(), "Novo ID de usuário deve ser 2");
        assertEquals(200.0, pedido.getValorTotal(), "Novo valor total deve ser 200");
        assertEquals("Concluído", pedido.getStatus(), "Novo status deve ser 'Concluído'");
    }

    @Test
    void testSetAndGetItens() {
        // Testar o método de adicionar itens ao pedido
        logger.debug("Testando a adição de itens ao pedido...");
        List<ItemPedido> itens = new ArrayList<>();
        ItemPedido item = new ItemPedido();  // Certifique-se de ter uma classe ItemPedido válida
        itens.add(item);
        pedido.setItens(itens);

        assertEquals(1, pedido.getItens().size(), "Deve haver um item no pedido");
        assertSame(item, pedido.getItens().get(0), "O item adicionado deve ser o mesmo recuperado");
    }

    @Test
    void testToString() {
        // Testar a saída do método toString()
        logger.debug("Testando o método toString() do pedido...");
        String expected = "Pedido{id=null, usuarioId=1, valorTotal=100.0, status='Em andamento'}";
        assertEquals(expected, pedido.toString(), "A representação em string do pedido deve corresponder ao esperado");
    }
}
