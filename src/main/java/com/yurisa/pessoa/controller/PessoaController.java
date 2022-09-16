package com.yurisa.pessoa.controller;

import com.yurisa.pessoa.controller.dto.PessoaDto;
import com.yurisa.pessoa.domain.pessoa.Pessoa;
import com.yurisa.pessoa.domain.pessoa.PessoaMapper;
import com.yurisa.pessoa.domain.pessoa.PessoaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;
    private final PessoaMapper pessoaMapper;

    public PessoaController(PessoaService pessoaService, PessoaMapper pessoaMapper) {
        this.pessoaService = pessoaService;
        this.pessoaMapper = pessoaMapper;
    }

    @PostMapping
    public PessoaDto criar(@RequestBody PessoaDto pessoaDto) {
        Pessoa pessoa = this.pessoaMapper.toEntity(pessoaDto);
        pessoa = this.pessoaService.salvar(pessoa);
        return this.pessoaMapper.toDto(pessoa);
    }

}
