package Entidades.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Entidades.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
