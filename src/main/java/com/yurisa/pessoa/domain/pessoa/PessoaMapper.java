package com.yurisa.pessoa.domain.pessoa;

import com.yurisa.pessoa.controller.dto.PessoaDto;
import org.springframework.stereotype.Component;

@Component
public class PessoaMapper {

    public Pessoa toEntity(PessoaDto pessoaDto) {
        return Pessoa.builder()
                .id(pessoaDto.getId())
                .nome(pessoaDto.getNome())
                .identificador(pessoaDto.getIdentificador())
                .tipoIdentificador(pessoaDto.getTipoIdentificador())
                .build();
    }

    public PessoaDto toDto(Pessoa pessoa) {
        return PessoaDto.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .identificador(pessoa.getIdentificador())
                .tipoIdentificador(pessoa.getTipoIdentificador())
                .build();
    }

}
