package com.teste.foursys.controller;

import com.teste.foursys.models.CepDTO;
import com.teste.foursys.models.ErrorResponseDTO;
import com.teste.foursys.service.CepService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.mockito.Mockito.when;

class CepControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CepService cepService;

    @InjectMocks
    private CepController cepController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cepController).build();
    }

    @Test
    void buscarCep_Success() throws Exception {
        // Simula o retorno do serviço
        CepDTO cepDTO = new CepDTO();
        cepDTO.setCep("12345678");
        cepDTO.setLogradouro("Rua Fictícia");
        cepDTO.setEstado("EX");
        cepDTO.setCode(0);
        cepDTO.setMessage("Sucesso");

        when(cepService.getCep("12345678")).thenReturn(cepDTO);

        mockMvc.perform(get("/buscar-cep/12345678"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cep").value("12345678"))
                .andExpect(jsonPath("$.logradouro").value("Rua Fictícia"))
                .andExpect(jsonPath("$.estado").value("EX"));
    }

    @Test
    void buscarCep_Error() throws Exception {
        // Simula o retorno de um erro no serviço
        ErrorResponseDTO errorResponse = new ErrorResponseDTO("CEP não encontrado");

        when(cepService.getCep("12345678")).thenReturn(
                new CepDTO( "CEP não encontrado",404,null));

        mockMvc.perform(get("/buscar-cep/12345678"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("CEP não encontrado"));
    }
}