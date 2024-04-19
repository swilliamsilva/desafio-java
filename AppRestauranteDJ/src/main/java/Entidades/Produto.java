package Entidades;

import javax.persistence.*;

@Entity
@Table(name = "produtos")
public class Produto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;
  private double preco;
  private String categoria;

  // Construtor com argumentos para inicializar as propriedades
  public Produto(String nome, double preco, String categoria) {
    this.nome = nome;
    this.preco = preco;
    this.categoria = categoria;
  }

  // Construtor vazio (opcional)
  public Produto() {
  }

  // Getters e Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public double getPreco() {
    return preco;
  }

  public void setPreco(double preco) {
    this.preco = preco;
  }

  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }
}