-- Criação da tabela de Produtos
CREATE TABLE produtos (
    produto_id SERIAL PRIMARY KEY, -- ID único do produto, auto-incrementável
    nome VARCHAR(100) NOT NULL, -- Nome do produto (limite de 100 caracteres)
    preco DECIMAL(10, 2) NOT NULL, -- Preço do produto (10 dígitos, 2 casas decimais)
    categoria VARCHAR(50) NOT NULL -- Categoria do produto (limite de 50 caracteres)
);

-- Criação da tabela de Usuários
CREATE TABLE usuarios (
    usuario_id SERIAL PRIMARY KEY, -- ID único do usuário, auto-incrementável
    username VARCHAR(50) NOT NULL, -- Nome de usuário (limite de 50 caracteres)
    password VARCHAR(100) NOT NULL -- Senha do usuário (limite de 100 caracteres)
    -- Adicione outros campos relevantes sobre o usuário conforme necessário
);

-- Criação da tabela de Pedidos
CREATE TABLE pedidos (
    pedido_id SERIAL PRIMARY KEY, -- ID único do pedido, auto-incrementável
    usuario_id INT NOT NULL, -- ID do usuário que fez o pedido
    data_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Data e hora em que o pedido foi feito (padrão: data atual)
    valor_total DECIMAL(10, 2) NOT NULL, -- Valor total do pedido (10 dígitos, 2 casas decimais)
    status VARCHAR(50) NOT NULL, -- Status do pedido (limite de 50 caracteres)
    FOREIGN KEY (usuario_id) REFERENCES usuarios(usuario_id) -- Chave estrangeira para vincular o pedido ao usuário correspondente
);

-- Criação da tabela de Itens do Pedido
CREATE TABLE itens_pedido (
    item_id SERIAL PRIMARY KEY, -- ID único do item do pedido, auto-incrementável
    pedido_id INT NOT NULL, -- ID do pedido ao qual o item pertence
    produto_id INT NOT NULL, -- ID do produto que foi pedido
    quantidade INT NOT NULL, -- Quantidade do produto pedida
    preco_unitario DECIMAL(10, 2) NOT NULL, -- Preço unitário do produto no momento do pedido (10 dígitos, 2 casas decimais)
    FOREIGN KEY (pedido_id) REFERENCES pedidos(pedido_id), -- Chave estrangeira para vincular o item ao pedido correspondente
    FOREIGN KEY (produto_id) REFERENCES produtos(produto_id) -- Chave estrangeira para vincular o item ao produto correspondente
);