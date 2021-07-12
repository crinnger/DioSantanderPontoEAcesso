package br.com.crinnger.DioPontoEAcesso.controller;

import br.com.crinnger.DioPontoEAcesso.model.TipoData;
import br.com.crinnger.DioPontoEAcesso.model.Usuario;
import br.com.crinnger.DioPontoEAcesso.repository.TipoDataRepository;
import br.com.crinnger.DioPontoEAcesso.repository.UsuarioRepository;
import br.com.crinnger.DioPontoEAcesso.service.TipoDataService;
import br.com.crinnger.DioPontoEAcesso.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@WebMvcTest(UsuarioController.class)
@OverrideAutoConfiguration(enabled=true)
public class UsuarioTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UsuarioService service;

    @MockBean
    UsuarioRepository repository;

    private final String host = "/usuario";

    private final Usuario expected = Usuario.builder()
            .id(1L)
            .categoriaUsuario(null)
            .empresa(null)
            .inicioJornada(LocalDateTime.now())
            .finalJornada(null)
            .nome("teste")
            .nivelAcesso(null)
            .tolerancia(new BigDecimal(1))
            .build();

    @TestConfiguration
    static class serviceImplTestContextConfiguration {
        @Bean
        public UsuarioService service() {
            return new UsuarioService();
        }
    }

    @Test
    public void testGetById() throws Exception{
        given(repository.findById(any())).willReturn(Optional.of(expected));
        String json=this.objectMapper.writeValueAsString(expected);
        this.mockMvc.perform(MockMvcRequestBuilders.get(host.concat("/{id}"), "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(json));
    }

    @Test
    public void testFindAll() throws Exception{
        given(repository.findAll()).willReturn(Collections.singletonList(expected));
        String json=this.objectMapper.writeValueAsString(Collections.singletonList(expected));
        this.mockMvc.perform(MockMvcRequestBuilders.get(host)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(json));
    }

    @Test
    public void testCreate() throws Exception {

        given(repository.save(any(Usuario.class))).willReturn(expected);

        String json=this.objectMapper.writeValueAsString(expected);

        this.mockMvc.perform(MockMvcRequestBuilders.post(host)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(json));
    }

    @Test
    public void testUpdate()   throws Exception {

        given(repository.save(any(Usuario.class))).willReturn(expected);

        String json=this.objectMapper.writeValueAsString(expected);

        this.mockMvc.perform(MockMvcRequestBuilders.put(host)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(json));
    }

    @Test
    public void testDelete() throws Exception{
        given(repository.findById(any())).willReturn(Optional.of(expected));
        this.mockMvc.perform(MockMvcRequestBuilders.delete(host.concat("/{id}"),"1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
