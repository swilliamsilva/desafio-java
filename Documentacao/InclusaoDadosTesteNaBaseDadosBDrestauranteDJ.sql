-- Inserção de dados na tabela de Produtos
INSERT INTO produtos (nome, preco, categoria) VALUES
    ('Hambúrguer', 10.50, 'Prato Principal'),
    ('Coca-Cola', 5.00, 'Bebida'),
    ('Batata Frita', 7.00, 'Entrada'),
    ('Sorvete', 8.50, 'Sobremesa');

-- Inserção de dados na tabela de Usuários
INSERT INTO usuarios (username, password) VALUES
    ('joao123', 'senha123'),
    ('maria456', 'senha456');

-- Inserção de dados na tabela de Pedidos
INSERT INTO pedidos (usuario_id, valor_total, status) VALUES
    (1, 25.50, 'Pendente'),
    (2, 13.50, 'Entregue');

-- Inserção de dados na tabela de Itens do Pedido
INSERT INTO itens_pedido (pedido_id, produto_id, quantidade, preco_unitario) VALUES
    (1, 1, 2, 10.50),
    (1, 2, 1, 5.00),
    (2, 3, 1, 7.00),
    (2, 4, 1, 8.50);
