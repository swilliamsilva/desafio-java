package com.kipho.AppRestauranteDJ;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCriarPedido() {
        Pedido pedido = new Pedido();
        pedido.setUsuarioId(1L);
        pedido.setValorTotal(20.0);
        pedido.setStatus("Pendente");

        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        Pedido novoPedido = pedidoService.criarPedido(pedido);

        assertNotNull(novoPedido);
        assertEquals(pedido.getUsuarioId(), novoPedido.getUsuarioId());
        assertEquals(pedido.getValorTotal(), novoPedido.getValorTotal());
        assertEquals(pedido.getStatus(), novoPedido.getStatus());
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

        Pedido pedidoObtido = pedidoService.obterPedidoPorId(id);

        assertNotNull(pedidoObtido);
        assertEquals(id, pedidoObtido.getId());
        assertEquals(pedido.getUsuarioId(), pedidoObtido.getUsuarioId());
        assertEquals(pedido.getValorTotal(), pedidoObtido.getValorTotal());
        assertEquals(pedido.getStatus(), pedidoObtido.getStatus());
    }

    @Test
    public void testAtualizarPedido() {
        // Crie um pedido existente para atualização
        Long id = 1L;
        Pedido pedidoExistente = new Pedido();
        pedidoExistente.setId(id);
        pedidoExistente.setUsuarioId(1L);
        pedidoExistente.setValorTotal(20.0);
        pedidoExistente.setStatus("Pendente");

        // Crie um pedido atualizado com novos valores
        Pedido pedidoAtualizado = new Pedido();
        pedidoAtualizado.setId(id);
        pedidoAtualizado.setUsuarioId(2L);
        pedidoAtualizado.setValorTotal(30.0);
        pedidoAtualizado.setStatus("Entregue");

        // Simule o comportamento do método findById do PedidoRepository
        when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedidoExistente));

        // Simule o comportamento do método save do PedidoRepository
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedidoAtualizado);

        // Chame o método atualizarPedido de PedidoService
        Pedido pedido = pedidoService.atualizarPedido(id, pedidoAtualizado);

        // Verifique se o pedido foi atualizado corretamente
        assertNotNull(pedido);
        assertEquals(pedidoAtualizado.getId(), pedido.getId());
        assertEquals(pedidoAtualizado.getUsuarioId(), pedido.getUsuarioId());
        assertEquals(pedidoAtualizado.getValorTotal(), pedido.getValorTotal());
        assertEquals(pedidoAtualizado.getStatus(), pedido.getStatus());
    }

    @Test
    public void testExcluirPedido() {
        // Crie um ID de pedido para exclusão
        Long id = 1L;

        // Simule o comportamento do método findById do PedidoRepository
        when(pedidoRepository.findById(id)).thenReturn(Optional.of(new Pedido()));

        // Chame o método excluirPedido de PedidoService
        pedidoService.excluirPedido(id);

        // Verifique se o método deleteById do PedidoRepository foi chamado
        verify(pedidoRepository, times(1)).deleteById(id);
    }

    @Test
    public void testListarPedidos() {
        // Crie uma lista de pedidos simulados
        List<Pedido> pedidosSimulados = new ArrayList<>();
        pedidosSimulados.add(new Pedido(1L, 1L, 20.0, "Pendente"));
        pedidosSimulados.add(new Pedido(2L, 2L, 30.0, "Entregue"));

        // Simule o comportamento do método findAll do PedidoRepository
        when(pedidoRepository.findAll()).thenReturn(pedidosSimulados);

        // Chame o método listarPedidos de PedidoService
        List<Pedido> pedidos = pedidoService.listarPedidos();

        // Verifique se a lista retornada não é nula e possui os pedidos simulados
        assertNotNull(pedidos);
        assertEquals(pedidosSimulados.size(), pedidos.size());
        assertEquals(pedidosSimulados, pedidos);
    }

}
