package br.com.crinnger.DioPontoEAcesso.controller;

import br.com.crinnger.DioPontoEAcesso.model.Calendario;
import br.com.crinnger.DioPontoEAcesso.model.TipoData;
import br.com.crinnger.DioPontoEAcesso.repository.CalendarioRepository;
import br.com.crinnger.DioPontoEAcesso.service.CalendarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@WebMvcTest(CalendarioController.class)
@OverrideAutoConfiguration(enabled=true)
public class CalendarioTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CalendarioService service;

    @MockBean
    CalendarioRepository repository;

    private final String host = "/calendario";

    @TestConfiguration
    static class CalendarioServiceImplTestContextConfiguration {
        @Bean
        public CalendarioService service() {
            return new CalendarioService();
        }
    }

    @Test
    public void testGetById() throws Exception{
        Calendario expected =new Calendario(1L,"teste2", LocalDate.now(),new TipoData());

        given(repository.findById(any())).willReturn(Optional.of(expected));

        String json=this.objectMapper.writeValueAsString(expected);

        this.mockMvc.perform(MockMvcRequestBuilders.get(host.concat("/{calendarioId}"), "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(json));
    }

    @Test
    public void testFindAll() throws Exception{
        Calendario expected =new Calendario(1L,"teste2", LocalDate.now(),new TipoData());
        String json=this.objectMapper.writeValueAsString(Collections.singletonList(expected));

        given(repository.findAll()).willReturn(Collections.singletonList(expected));

        this.mockMvc.perform(MockMvcRequestBuilders.get(host)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(json));
    }

    @Test
    public void testCreate() throws Exception {
        Calendario expected =new Calendario(1L,"teste2", LocalDate.now(),new TipoData());

        given(repository.save(any(Calendario.class))).willReturn(expected);
        //given(service.save(any(Calendario.class))).willReturn(expected);

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
        Calendario expected =new Calendario(1L,"teste2", LocalDate.now(),new TipoData());

        given(repository.save(any(Calendario.class))).willReturn(expected);

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
        Calendario expected =new Calendario(1L,"teste2", LocalDate.now(),new TipoData());
        given(repository.findById(any())).willReturn(Optional.of(expected));
        this.mockMvc.perform(MockMvcRequestBuilders.delete(host.concat("/{calendarioId}"),"1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
