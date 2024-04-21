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
        // Verifica se o pedido com o ID especificado existe no banco de dados
        Pedido pedidoExistente = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado: " + id));

        // Atualiza os campos do pedido existente com os valores do pedido atualizado
        pedidoExistente.setUsuarioId(pedidoAtualizado.getUsuarioId());
        pedidoExistente.setValorTotal(pedidoAtualizado.getValorTotal());
        pedidoExistente.setStatus(pedidoAtualizado.getStatus());

        // Salva e retorna o pedido atualizado
        return pedidoRepository.save(pedidoExistente);
    }

    // Excluir um pedido
    public void excluirPedido(Long id) {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(id);
        if (optionalPedido.isPresent()) {
            pedidoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Pedido não encontrado: " + id);
        }
    }

    // Listar todos os pedidos
    public List<Pedido> listarPedidos() {
        // Retorna todos os pedidos do banco de dados
        return pedidoRepository.findAll();
    }
}
