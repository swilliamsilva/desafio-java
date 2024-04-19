package com.kipho.AppRestauranteDJ;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import Entidades.ItemPedido;
import Entidades.Pedido;
import Entidades.PedidoRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PedidoRepositoryTest {

    private static final Logger LOGGER = Logger.getLogger(PedidoRepositoryTest.class.getName());

    @Autowired
    private PedidoRepository pedidoRepository;

    @Test
    public void testSalvarPedido() {
        LOGGER.info("Iniciando o teste de salvar pedido...");

        Pedido pedido = new Pedido();
        pedido.setUsuarioId(1L);
        pedido.setValorTotal(50.0);
        pedido.setStatus("Em andamento");
        LOGGER.info("Pedido criado com sucesso.");

        ItemPedido item1 = new ItemPedido();
        item1.setQuantidade(2);
        item1.setPrecoUnitario(10.0);
        item1.setPedido(pedido); // Correção aqui
        pedido.getItens().add(item1);
        LOGGER.info("Item 1 adicionado ao pedido.");

        ItemPedido item2 = new ItemPedido();
        item2.setQuantidade(1);
        item2.setPrecoUnitario(30.0);
        item2.setPedido(pedido); // Correção aqui
        pedido.getItens().add(item2);
        LOGGER.info("Item 2 adicionado ao pedido.");

        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        LOGGER.info("Pedido salvo com sucesso.");

        assertNotNull(pedidoSalvo.getId(), "O ID do pedido não deve ser nulo após salvar.");
        assertEquals(2, pedidoSalvo.getItens().size(), "O número de itens do pedido deve ser 2.");

        List<ItemPedido> itensDoPedidoSalvo = pedidoSalvo.getItens();
        assertEquals(2, itensDoPedidoSalvo.get(0).getQuantidade(), "A quantidade do primeiro item deve ser 2.");
        assertEquals(1, itensDoPedidoSalvo.get(1).getQuantidade(), "A quantidade do segundo item deve ser 1.");

        LOGGER.info("Teste de salvar pedido concluído com sucesso.");
    }
}





