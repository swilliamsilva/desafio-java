package com.kipho.AppRestauranteDJ;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Entidades.Pedido;
import Entidades.PedidoRepository;
import Entidades.PedidoService;

public class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;
    
    private static final Logger LOGGER = Logger.getLogger(PedidoServiceTest.class.getName());

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        LOGGER.info("Configurando o ambiente para os testes do PedidoService...");
    }

    @Test
    public void testCriarPedido() {
        Pedido pedido = new Pedido();
        pedido.setUsuarioId(1L);
        pedido.setValorTotal(20.0);
        pedido.setStatus("Pendente");

        when(pedidoRepository.save(pedido)).thenReturn(pedido);
        
        LOGGER.info("Simulando criação de Pedido no banco de dados...");

        Pedido novoPedido = pedidoService.criarPedido(pedido);

        assertNotNull(novoPedido);
        assertEquals(pedido.getUsuarioId(), novoPedido.getUsuarioId());
        assertEquals(pedido.getValorTotal(), novoPedido.getValorTotal());
        assertEquals(pedido.getStatus(), novoPedido.getStatus());
        
        LOGGER.info("Teste de criação de Pedido concluído com sucesso.");
    }

    @Test
    public void testObterPedidoPorId() {
        Long id = 1L;
        Pedido pedido = new Pedido();
        pedido.setId(id);
        pedido.setUsuarioId(1L);
        pedido.setValorTotal(20.0);
        pedido.setStatus("Pendente");

        when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedido));
        
        LOGGER.info("Simulando busca de Pedido no banco de dados...");

        Pedido pedidoObtido = pedidoService.obterPedidoPorId(id);

        assertNotNull(pedidoObtido);
        assertEquals(id, pedidoObtido.getId());
        assertEquals(pedido.getUsuarioId(), pedidoObtido.getUsuarioId());
        assertEquals(pedido.getValorTotal(), pedidoObtido.getValorTotal());
        assertEquals(pedido.getStatus(), pedidoObtido.getStatus());
        
        LOGGER.info("Teste de busca de Pedido por ID concluído com sucesso.");
    }

    // Outros métodos de teste...

}
