package com.yurisa.pessoa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yurisa.pessoa.controller.dto.PessoaDto;
import com.yurisa.pessoa.domain.pessoa.PessoaService;
import com.yurisa.pessoa.domain.pessoa.TipoIdentificador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@WebAppConfiguration
@SpringBootTest
class PessoaControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PessoaService pessoaService;

    @BeforeEach
    void setUp() {
        this.mockMvc = webAppContextSetup(this.context).build();
    }

    @Nested
    class Dado_uma_pessoa_com_identificador_contendo_11_digitos_e_tipo_identificador_nulo {

        private PessoaDto pessoaDto;

        @BeforeEach
        private void setup() throws Exception {
            pessoaDto = PessoaDto.builder()
                    .nome("Yuri Stapassoli de Sá")
                    .identificador("05590050910")
                    .build();
        }

        @Nested
        class Quando_salvar {

            @BeforeEach
            private void setup() throws Exception {
                var json = objectMapper.writeValueAsString(pessoaDto);

                String response = mockMvc.perform(post("/pessoas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                        .andExpect(status().isOk())
                        .andReturn().getResponse().getContentAsString();

                pessoaDto = objectMapper.readValue(response, PessoaDto.class);
            }

            @Test
            void Entao_tipo_identificador_CPF() {
                assertNotNull(pessoaDto.getId());
                assertEquals(TipoIdentificador.CPF, pessoaDto.getTipoIdentificador());
            }
        }
    }

    @Nested
    class Dado_uma_pessoa_com_identificador_contendo_14_digitos_e_tipo_identificador_nulo {

        private PessoaDto pessoaDto;

        @BeforeEach
        private void setup() throws Exception {
            pessoaDto = PessoaDto.builder()
                    .nome("Nome Fantasia")
                    .identificador("26917686000173")
                    .build();
        }

        @Nested
        class Quando_salvar {

            @BeforeEach
            private void setup() throws Exception {
                var json = objectMapper.writeValueAsString(pessoaDto);

                String response = mockMvc.perform(post("/pessoas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                        .andExpect(status().isOk())
                        .andReturn().getResponse().getContentAsString();

                pessoaDto = objectMapper.readValue(response, PessoaDto.class);
            }

            @Test
            void Entao_tipo_identificador_CNPJ() {
                assertNotNull(pessoaDto.getId());
                assertEquals(TipoIdentificador.CNPJ, pessoaDto.getTipoIdentificador());
            }
        }
    }

    @Nested
    class Dado_uma_pessoa_com_tipo_identificador_preenchido {

        private PessoaDto pessoaDto;

        @BeforeEach
        private void setup() throws Exception {
            pessoaDto = PessoaDto.builder()
                    .nome("Jean Giraud")
                    .identificador("99999999999")
                    .tipoIdentificador(TipoIdentificador.NAO_RECONHECIDO)
                    .build();
        }

        @Nested
        class Quando_salvar {

            @BeforeEach
            private void setup() throws Exception {
                var json = objectMapper.writeValueAsString(pessoaDto);

                String response = mockMvc.perform(post("/pessoas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                        .andExpect(status().isOk())
                        .andReturn().getResponse().getContentAsString();

                pessoaDto = objectMapper.readValue(response, PessoaDto.class);
            }

            @Test
            void Entao_deve_manter_o_que_foi_informado_no_cadastro() {
                assertNotNull(pessoaDto.getId());
                assertEquals(TipoIdentificador.NAO_RECONHECIDO, pessoaDto.getTipoIdentificador());
            }
        }
    }
}
