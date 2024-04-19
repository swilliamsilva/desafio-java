package Entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Entity
@Table(name = "pedidos")
public class Pedido {
    private static final Logger LOGGER = Logger.getLogger(Pedido.class.getName());

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId; // Alterado de clienteId para usuarioId

    @Column(name = "valor_total", nullable = false)
    private Double valorTotal;

    @Column(name = "status", nullable = false)
    private String status;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

    public Pedido() {
        LOGGER.info("Criando novo pedido...");
    }

    public Pedido(Long usuarioId, Double valorTotal, String status) {
        this.usuarioId = usuarioId;
        this.valorTotal = valorTotal;
        this.status = status;
        LOGGER.info("Criando novo pedido com usuarioId: " + usuarioId + ", valorTotal: " + valorTotal + ", status: " + status);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        LOGGER.info("Definindo id do pedido como: " + id);
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
        LOGGER.info("Definindo usuarioId do pedido como: " + usuarioId);
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
        LOGGER.info("Definindo valorTotal do pedido como: " + valorTotal);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        LOGGER.info("Definindo status do pedido como: " + status);
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
        LOGGER.info("Definindo itens do pedido: " + itens);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", usuarioId=" + usuarioId +
                ", valorTotal=" + valorTotal +
                ", status='" + status + '\'' +
                '}';
    }
}

