package Entidades;

import javax.persistence.*;

@Entity
@Table(name = "itens_pedido")
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pedidoId;
    private Long produtoId;
    private int quantidade;
    private double precoUnitario;

    // Getters e Setters
}
