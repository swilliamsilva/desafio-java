package com.kipho.AppRestauranteDJ;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kipho.AppRestauranteDJ.models.Produto;

@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final Logger logger = LoggerFactory.getLogger(ProdutoControllerTest.class);

    private Produto sampleProduto;

    @BeforeEach
    public void setUp() {
        sampleProduto = new Produto("Produto Teste", 10.99, "Categoria Teste");
        logger.info("Produto de teste inicializado: {}", sampleProduto);
    }

    @Test
    public void testCriarProduto() throws Exception {
        logger.info("Iniciando teste de criação de produto");
        
        String produtoJson = objectMapper.writeValueAsString(sampleProduto);

        MvcResult result = mockMvc.perform(
            post("/api/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(produtoJson)
        ).andExpect(status().isOk())
         .andReturn();

        Produto createdProduto = objectMapper.readValue(
            result.getResponse().getContentAsString(),
            Produto.class
        );

        assertNotNull(createdProduto.getId(), "O ID do produto criado não deveria ser nulo");
        assertEquals("Produto Teste", createdProduto.getNome(), "O nome do produto não corresponde");
        assertEquals(10.99, createdProduto.getPreco(), 0.01, "O preço do produto não corresponde");
        assertEquals("Categoria Teste", createdProduto.getCategoria(), "A categoria do produto não corresponde");

        logger.info("Produto criado com sucesso: {}", createdProduto);
    }

    @Test
    public void testObterProdutoPorId() throws Exception {
        logger.info("Testando a obtenção de produto por ID");

        // Criar um produto para buscar
        String produtoJson = objectMapper.writeValueAsString(sampleProduto);

        MvcResult createResult = mockMvc.perform(
            post("/api/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(produtoJson)
        ).andExpect(status().isOk())
         .andReturn();

        Produto createdProduto = objectMapper.readValue(
            createResult.getResponse().getContentAsString(),
            Produto.class
        );

        // Obter o produto pelo ID
        MvcResult getResult = mockMvc.perform(
            get("/api/produtos/" + createdProduto.getId())
        ).andExpect(status().isOk())
         .andReturn();

        Produto fetchedProduto = objectMapper.readValue(
            getResult.getResponse().getContentAsString(),
            Produto.class
        );

        assertNotNull(fetchedProduto, "O produto obtido não deveria ser nulo");
        assertEquals(createdProduto.getId(), fetchedProduto.getId(), "O ID do produto obtido não corresponde ao criado");

        logger.info("Produto obtido com sucesso pelo ID: {}", createdProduto.getId());
    }

    @Test
    public void testProdutoNaoEncontrado() throws Exception {
        long idInexistente = 999L; // Um ID que não existe

        mockMvc.perform(
            get("/api/produtos/" + idInexistente)
        ).andExpect(status().isNotFound());

        logger.info("Tentativa de obter produto com ID inexistente retornou status 404.");
    }

    @Test
    public void testCriarProdutoComDadosInvalidos() throws Exception {
        Produto produtoInvalido = new Produto(); // Produto sem atributos obrigatórios
        String produtoInvalidoJson = objectMapper.writeValueAsString(produtoInvalido);

        mockMvc.perform(
            post("/api/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(produtoInvalidoJson)
        ).andExpect(status().isBadRequest());

        logger.info("Criação de produto com dados inválidos retornou status 400.");
    }

    @Test
    public void testAtualizarProduto() throws Exception {
        logger.info("Testando a atualização de produto");
        
        // Criar um produto para atualizar
        String produtoJson = objectMapper.writeValueAsString(sampleProduto);

        MvcResult createResult = mockMvc.perform(
            post("/api/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(produtoJson)
        ).andExpect(status().isOk())
         .andReturn();

        Produto createdProduto = objectMapper.readValue(
            createResult.getResponse().getContentAsString(),
            Produto.class
        );

        // Atualizar produto
        createdProduto.setNome("Produto Atualizado");
        createdProduto.setPreco(20.99);
        createdProduto.setCategoria("Categoria Atualizada");

        String updatedProdutoJson = objectMapper.writeValueAsString(createdProduto);

        mockMvc.perform(
            put("/api/produtos/" + createdProduto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedProdutoJson)
        ).andExpect(status().isOk())
         .andExpect(jsonPath("$.nome").value("Produto Atualizado"))
         .andExpect(jsonPath("$.preco").value(20.99))
         .andExpect(jsonPath("$.categoria").value("Categoria Atualizada"));

        logger.info("Produto atualizado com sucesso: {}", createdProduto);
    }

    @Test
    public void testExcluirProduto() throws Exception {
        logger.info("Testando a exclusão de produto");
        
        // Criar um produto para excluir
        String produtoJson = objectMapper.writeValueAsString(sampleProduto);

        MvcResult createResult = mockMvc.perform(
            post("/api/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(produtoJson)
        ).andExpect(status().isOk())
         .andReturn();

        Produto createdProduto = objectMapper.readValue(
            createResult.getResponse().getContentAsString(),
            Produto.class
        );

        // Excluir produto
        mockMvc.perform(
            delete("/api/produtos/" + createdProduto.getId())
        ).andExpect(status().isNoContent());

        // Verificar se o produto realmente foi excluído
        mockMvc.perform(
            get("/api/produtos/" + createdProduto.getId())
        ).andExpect(status().isNotFound());

        logger.info("Produto excluído com sucesso: {}", createdProduto.getId());
    }
}
