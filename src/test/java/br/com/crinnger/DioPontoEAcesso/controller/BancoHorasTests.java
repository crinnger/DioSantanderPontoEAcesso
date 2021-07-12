package br.com.crinnger.DioPontoEAcesso.controller;

import br.com.crinnger.DioPontoEAcesso.model.BancoHoras;
import br.com.crinnger.DioPontoEAcesso.model.Movimentacao;
import br.com.crinnger.DioPontoEAcesso.model.Usuario;
import br.com.crinnger.DioPontoEAcesso.repository.BancoHorasRepository;
import br.com.crinnger.DioPontoEAcesso.repository.MovimentacaoRepository;
import br.com.crinnger.DioPontoEAcesso.repository.UsuarioRepository;
import br.com.crinnger.DioPontoEAcesso.service.BancoHorasService;
import br.com.crinnger.DioPontoEAcesso.service.CalendarioService;
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

@WebMvcTest(BancoHorasController.class)
@OverrideAutoConfiguration(enabled=true)
public class BancoHorasTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @TestConfiguration
    static class ServiceImplTestContextConfiguration {
        @Bean
        public BancoHorasService bancoHorasServiceService() {
            return new BancoHorasService();
        }
        @Bean
        public MovimentacaoService movimentacaoServiceService() {
            return new MovimentacaoService();
        }
        @Bean
        public UsuarioService usuarioService() {
            return new UsuarioService();
        }
    }

    @Autowired
    BancoHorasService service;

    @Autowired
    MovimentacaoService movimentacaoService;

    @Autowired
    UsuarioService usuarioService;

    @MockBean
    UsuarioRepository usuarioRepository;

    @MockBean
    BancoHorasRepository repository;

    @MockBean
    MovimentacaoRepository movimentacaoRepository;

    private final String host = "/bancoHoras";

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

    private Movimentacao expectedMovimentacao;

    private BancoHoras expected;


    private void mock() {
        expectedMovimentacao=new Movimentacao(expectedUsuario, 1L);
        expectedMovimentacao.setCalendario(null);
        expectedMovimentacao.setEntrada(LocalDate.now());
        expectedMovimentacao.setOcorrencia(null);
        expectedMovimentacao.setPeriodoPermanencia(1);
        expectedMovimentacao.setSaida(null);

        expected=new BancoHoras(expectedMovimentacao,1L);
        expected.setQuantidadeHoras(1);
        expected.setSaldoHoras(10);
        expected.setQuantidadeHoras(1);
    }
    @Test
    public void testGetById() throws Exception{
        mock();
        given(usuarioRepository.findById(any())).willReturn(Optional.of(expectedUsuario));
        given(movimentacaoRepository.findById(any())).willReturn(Optional.of(expectedMovimentacao));
        given(repository.findById(any())).willReturn(Optional.of(expected));
        String json=this.objectMapper.writeValueAsString(expected);
        this.mockMvc.perform(MockMvcRequestBuilders.get(host.concat("/{user}/{movimento}/{id}"), "1","1","1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(json));
    }

    @Test
    public void testFindAll() throws Exception{
        mock();
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
        given(repository.save(any(BancoHoras.class))).willReturn(expected);

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
        given(repository.save(any(BancoHoras.class))).willReturn(expected);

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
        given(movimentacaoRepository.findById(any())).willReturn(Optional.of(expectedMovimentacao));
        given(usuarioRepository.findById(any())).willReturn(Optional.of(expectedUsuario));
        this.mockMvc.perform(MockMvcRequestBuilders.delete(host.concat("/{user}/{movimento}/{id}"), "1","1","1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
