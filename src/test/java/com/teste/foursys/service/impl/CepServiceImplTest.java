package com.teste.foursys.service.impl;

import com.teste.foursys.models.CepDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CepServiceImplTest {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CepServiceImpl cepService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCep_ValidCep() {
        String validCep = "12345-678";
        CepDTO mockedResponse = new CepDTO();
        mockedResponse.setCep("12345-678");
        mockedResponse.setLogradouro("Rua Teste");
        mockedResponse.setEstado("TE");
        mockedResponse.setErro(false);

        when(restTemplate.getForObject(any(String.class), any(Class.class))).thenReturn(mockedResponse);

        CepDTO result = cepService.getCep(validCep);

        assertEquals("12345-678", result.getCep());
        assertEquals("Rua Teste", result.getLogradouro());
        assertEquals("TE", result.getEstado());
        assertEquals(0, result.getCode());
        assertEquals(null, result.getMessage());
    }

    @Test
    void testGetCep_InvalidCep() {
        String invalidCep = null;
        CepDTO cepDTO = new CepDTO();
        cepDTO.setCep(invalidCep);

        cepDTO = cepService.getCep(invalidCep);

        assertEquals(400, cepDTO.getCode());
        assertEquals("CEP inválido: " + invalidCep, cepDTO.getMessage());
    }

    @Test
    void testGetCep_CepNotFound() {
        String cepNotFound = "00000-000";

        CepDTO mockedResponse = new CepDTO();
        mockedResponse.setErro(true);

        when(restTemplate.getForObject(any(String.class), any(Class.class))).thenReturn(mockedResponse);

        CepDTO result = cepService.getCep(cepNotFound);

        assertEquals(404, result.getCode());
        assertEquals("CEP não encontrado: " + cepNotFound, result.getMessage());
    }
}