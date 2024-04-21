package com.kipho.AppRestauranteDJ;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
@AutoConfigureMockMvc
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final Logger LOGGER = LoggerFactory.getLogger(PedidoControllerTest.class);

    @Test
    void testCriarPedido() throws Exception {
        LOGGER.info("Testando criação de um novo pedido");

        // Simular uma solicitação POST para criar um pedido
        ResultActions result = mockMvc.perform(post("/api/pedidos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"usuarioId\": 1, \"valorTotal\": 100.0, \"status\": \"Em andamento\"}"));

        // Verificar se a resposta é 201 Created
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.usuarioId").value(1))
                .andExpect(jsonPath("$.valorTotal").value(100.0))
                .andExpect(jsonPath("$.status").value("Em andamento"));

        LOGGER.info("Pedido criado com sucesso");
    }

    @Test
    void testObterPedidoPorId() throws Exception {
        LOGGER.info("Testando obter pedido por ID");

        // Simular uma solicitação GET para obter um pedido por ID
        ResultActions result = mockMvc.perform(get("/api/pedidos/1"));

        // Verificar se a resposta é 200 OK
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.usuarioId").value(1))
                .andExpect(jsonPath("$.valorTotal").value(100.0))
                .andExpect(jsonPath("$.status").value("Em andamento"));

        LOGGER.info("Pedido obtido com sucesso");
    }

    @Test
    void testAtualizarPedido() throws Exception {
        LOGGER.info("Testando atualização de um pedido");

        // Simular uma solicitação PUT para atualizar um pedido existente
        ResultActions result = mockMvc.perform(put("/api/pedidos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"usuarioId\": 1, \"valorTotal\": 150.0, \"status\": \"Concluído\"}"));

        // Verificar se a resposta é 200 OK
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.valorTotal").value(150.0))
                .andExpect(jsonPath("$.status").value("Concluído"));

        LOGGER.info("Pedido atualizado com sucesso");
    }

    @Test
    void testExcluirPedido() throws Exception {
        LOGGER.info("Testando exclusão de um pedido");

        // Simular uma solicitação DELETE para excluir um pedido existente
        ResultActions result = mockMvc.perform(delete("/api/pedidos/1"));

        // Verificar se a resposta é 204 No Content
        result.andExpect(status().isNoContent());

        LOGGER.info("Pedido excluído com sucesso");
    }

    @Test
    void testPedidoNaoEncontrado() throws Exception {
        LOGGER.info("Testando pedido não encontrado");

        // Tentar obter um pedido que não existe
        ResultActions result = mockMvc.perform(get("/api/pedidos/999"));

        // Verificar se a resposta é 404 Not Found
        result.andExpect(status().isNotFound());

        LOGGER.info("Pedido com ID inexistente retornou status 404.");
    }

    @Test
    void testCriarPedidoComDadosInvalidos() throws Exception {
        LOGGER.info("Testando criação de pedido com dados inválidos");

        // Simular uma solicitação POST com dados inválidos
        ResultActions result = mockMvc.perform(post("/api/pedidos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"usuarioId\": \"invalido\", \"valorTotal\": \"abc\", \"status\": \"\"}"));

        // Verificar se a resposta é 400 Bad Request
        result.andExpect(status().isBadRequest());

        LOGGER.info("Criação de pedido com dados inválidos retornou status 400.");
    }
}
