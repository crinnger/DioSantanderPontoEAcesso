package br.com.crinnger.DioPontoEAcesso.controller;

import br.com.crinnger.DioPontoEAcesso.model.Empresa;
import br.com.crinnger.DioPontoEAcesso.repository.EmpresaRepository;
import br.com.crinnger.DioPontoEAcesso.service.EmpresaService;
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

@WebMvcTest(EmpresaController.class)
@OverrideAutoConfiguration(enabled=true)
public class EmpresaTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    EmpresaService service;

    @MockBean
    EmpresaRepository repository;

    private final String host = "/empresa";

    @TestConfiguration
    static class serviceImplTestContextConfiguration {
        @Bean
        public EmpresaService service() {
            return new EmpresaService();
        }
    }

    @Test
    public void testGetById() throws Exception{
        Empresa expected=new Empresa(1L,"teste2","1","2","3","4","5","6");
        given(repository.findById(any())).willReturn(Optional.of(expected));
        String json=this.objectMapper.writeValueAsString(expected);
        this.mockMvc.perform(MockMvcRequestBuilders.get(host.concat("/{empresaId}"), "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(json));
    }

    @Test
    public void testFindAll() throws Exception{
        Empresa expected=new Empresa(1L,"teste2","1","2","3","4","5","6");
        given(repository.findAll()).willReturn(Collections.singletonList(expected));
        String json=this.objectMapper.writeValueAsString(Collections.singletonList(expected));
        this.mockMvc.perform(MockMvcRequestBuilders.get(host)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(json));
    }

    @Test
    public void testCreate() throws Exception {
        Empresa expected=new Empresa(1L,"teste2","1","2","3","4","5","6");
        given(repository.save(any(Empresa.class))).willReturn(expected);

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
        Empresa expected=new Empresa(1L,"teste2","1","2","3","4","5","6");
        given(repository.save(any(Empresa.class))).willReturn(expected);

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
        given(repository.findById(any())).willReturn(Optional.of(new Empresa()));
        this.mockMvc.perform(MockMvcRequestBuilders.delete(host.concat("/{empresaId}"),"1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
