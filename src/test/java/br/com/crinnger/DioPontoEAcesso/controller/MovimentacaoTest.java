package br.com.crinnger.DioPontoEAcesso.controller;

import br.com.crinnger.DioPontoEAcesso.model.Movimentacao;
import br.com.crinnger.DioPontoEAcesso.model.Usuario;
import br.com.crinnger.DioPontoEAcesso.repository.MovimentacaoRepository;
import br.com.crinnger.DioPontoEAcesso.repository.UsuarioRepository;
import br.com.crinnger.DioPontoEAcesso.service.MovimentacaoService;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@WebMvcTest(MovimentacaoController.class)
@OverrideAutoConfiguration(enabled=true)
public class MovimentacaoTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MovimentacaoService service;

    @Autowired
    UsuarioService usuarioService;

    @MockBean
    UsuarioRepository usuarioRepository;

    @MockBean
    MovimentacaoRepository repository;

    private final String host = "/movimentacao";

    private final Usuario expectedUsuario = Usuario.builder()
            .id(1L)
            .categoriaUsuario(null)
            .empresa(null)
            .inicioJornada(LocalDateTime.now())
            .finalJornada(null)
            .nome("teste")
            .nivelAcesso(null)
            .tolerancia(new BigDecimal(1))
            .build();

    private Movimentacao expected;

    @TestConfiguration
    static class serviceImplTestContextConfiguration {
        @Bean
        public MovimentacaoService service() {
            return new MovimentacaoService();
        }
        @Bean
        public UsuarioService service2() {
            return new UsuarioService();
        }
    }


    private void mock() {
        expected=new Movimentacao(expectedUsuario, 1L);
        expected.setCalendario(null);
        expected.setEntrada(LocalDate.now());
        expected.setOcorrencia(null);
        expected.setPeriodoPermanencia(1);
        expected.setSaida(null);
    }
    @Test
    public void testGetById() throws Exception{
        mock();
        given(usuarioRepository.findById(any())).willReturn(Optional.of(expectedUsuario));
        given(repository.findById(any())).willReturn(Optional.of(expected));
        String json=this.objectMapper.writeValueAsString(expected);
        this.mockMvc.perform(MockMvcRequestBuilders.get(host.concat("/{id}/{user}"), "1","1")
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
        mock();
        given(repository.save(any(Movimentacao.class))).willReturn(expected);

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
        mock();
        given(repository.save(any(Movimentacao.class))).willReturn(expected);

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
        mock();
        given(repository.findById(any())).willReturn(Optional.of(expected));
        given(usuarioRepository.findById(any())).willReturn(Optional.of(expectedUsuario));
        this.mockMvc.perform(MockMvcRequestBuilders.delete(host.concat("/{id}/{user}"), "1","1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
