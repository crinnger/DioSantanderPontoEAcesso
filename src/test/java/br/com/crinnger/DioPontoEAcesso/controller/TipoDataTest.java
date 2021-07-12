package br.com.crinnger.DioPontoEAcesso.controller;

import br.com.crinnger.DioPontoEAcesso.model.TipoData;
import br.com.crinnger.DioPontoEAcesso.repository.TipoDataRepository;
import br.com.crinnger.DioPontoEAcesso.service.TipoDataService;
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

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@WebMvcTest(TipoDataController.class)
@OverrideAutoConfiguration(enabled=true)
public class TipoDataTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TipoDataService service;

    @MockBean
    TipoDataRepository repository;

    private final String host = "/tipoData";

    private final TipoData expected = new TipoData(1L,"teste2");

    @TestConfiguration
    static class serviceImplTestContextConfiguration {
        @Bean
        public TipoDataService service() {
            return new TipoDataService();
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

        given(repository.save(any(TipoData.class))).willReturn(expected);

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

        given(repository.save(any(TipoData.class))).willReturn(expected);

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
