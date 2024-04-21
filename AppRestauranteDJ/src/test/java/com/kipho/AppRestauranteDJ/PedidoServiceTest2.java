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
        Pedido pedido = new Pedido(null, null, null);
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
        Pedido pedido = new Pedido(1L, 20.0, "Pendente");
        pedido.setId(id);

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
        Long id = 1L;
        Pedido pedido = new Pedido(1L, null, null, null); // Alteração aqui para criar pedido com os campos nulos
        pedido.setId(id);

        Pedido pedidoAtualizado = new Pedido(1L, 40.0, "Pendente");
        pedidoAtualizado.setId(id);

        when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(pedidoAtualizado)).thenReturn(pedidoAtualizado);

        Pedido pedidoAlterado = pedidoService.atualizarPedido(id, pedidoAtualizado);

        assertNotNull(pedidoAlterado);
        assertEquals(id, pedidoAlterado.getId());
        assertEquals(pedidoAtualizado.getUsuarioId(), pedidoAlterado.getUsuarioId());
        assertEquals(pedidoAtualizado.getValorTotal(), pedidoAlterado.getValorTotal());
        assertEquals(pedidoAtualizado.getStatus(), pedidoAlterado.getStatus());
    }

    @Test
    public void testExcluirPedido() {
        Long id = 1L;

        // Simula o retorno de um pedido ao buscar pelo ID
        when(pedidoRepository.findById(id)).thenReturn(Optional.of(new Pedido()));

        pedidoService.excluirPedido(id);

        verify(pedidoRepository, times(1)).deleteById(id);
    }

    @Test
    public void testListarPedidos() {
        List<Pedido> listaPedidos = new ArrayList<>();
        listaPedidos.add(new Pedido(1L, 20.0, "Pendente"));
        listaPedidos.add(new Pedido(2L, 30.0, "Entregue"));

        when(pedidoRepository.findAll()).thenReturn(listaPedidos);

        List<Pedido> pedidosRetornados = pedidoService.listarPedidos();

        assertNotNull(pedidosRetornados);
        assertEquals(listaPedidos.size(), pedidosRetornados.size());
        assertEquals(listaPedidos, pedidosRetornados);
    }
}
