package Entidades.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Entidades.Pedido;
import Entidades.repository.PedidoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    // Criar um novo pedido
    public Pedido criarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    // Obter um pedido pelo ID
    public Pedido obterPedidoPorId(Long id) {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(id);
        return optionalPedido.orElse(null);
    }

    // Atualizar um pedido existente
    public Pedido atualizarPedido(Long id, Pedido pedidoAtualizado) {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(id);
        if (optionalPedido.isPresent()) {
            Pedido pedidoExistente = optionalPedido.get();
            pedidoExistente.setUsuarioId(pedidoAtualizado.getUsuarioId());
            pedidoExistente.setValorTotal(pedidoAtualizado.getValorTotal());
            pedidoExistente.setStatus(pedidoAtualizado.getStatus());
            return pedidoRepository.save(pedidoExistente);
        } else {
            return null;
        }
    }

    // Excluir um pedido
    public void excluirPedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    // Listar todos os pedidos
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }
}
