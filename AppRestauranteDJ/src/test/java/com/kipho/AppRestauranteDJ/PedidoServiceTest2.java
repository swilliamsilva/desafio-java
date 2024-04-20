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
import Entidades.repository.PedidoRepository;
import Entidades.services.PedidoService;

public class PedidoServiceTest2 {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

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

    // Implemente testes para os outros métodos (atualizarPedido, excluirPedido, listarPedidos)

    // Teste para atualizar um pedido
    @Test
    public void test2AtualizarPedido() {
        Long id = 1L;
        Pedido pedido = new Pedido();
        pedido.setId(id);
        pedido.setUsuarioId(1L);
        pedido.setValorTotal(30.0);
        pedido.setStatus("Entregue");

        Pedido pedidoAtualizado = new Pedido();
        pedidoAtualizado.setId(id);
        pedidoAtualizado.setUsuarioId(1L);
        pedidoAtualizado.setValorTotal(40.0);
        pedidoAtualizado.setStatus("Pendente");

        when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(pedidoAtualizado)).thenReturn(pedidoAtualizado);

        Pedido pedidoAlterado = pedidoService.atualizarPedido(id, pedidoAtualizado);

        assertNotNull(pedidoAlterado);
        assertEquals(id, pedidoAlterado.getId());
        assertEquals(pedidoAtualizado.getUsuarioId(), pedidoAlterado.getUsuarioId());
        assertEquals(pedidoAtualizado.getValorTotal(), pedidoAlterado.getValorTotal());
        assertEquals(pedidoAtualizado.getStatus(), pedidoAlterado.getStatus());
    }

    // Teste para excluir um pedido
    @Test
    public void test2ExcluirPedido() {
        Long id = 1L;

        pedidoService.excluirPedido(id);

        verify(pedidoRepository, times(1)).deleteById(id);
    }

    // Teste para listar todos os pedidos
    @Test
    public void test2ListarPedidos() {
        List<Pedido> listaPedidos = new ArrayList<>();
  //      listaPedidos.add(new Pedido(1L, 1L, 20.0, "Pendente"));
  //      listaPedidos.add(new Pedido(2L, 2L, 30.0, "Entregue"));

        when(pedidoRepository.findAll()).thenReturn(listaPedidos);

        List<Pedido> pedidosRetornados = pedidoService.listarPedidos();

        assertNotNull(pedidosRetornados);
        assertEquals(listaPedidos.size(), pedidosRetornados.size());
    }
}
