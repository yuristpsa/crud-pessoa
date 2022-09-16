package com.yurisa.pessoa.controller.dto;

import com.yurisa.pessoa.base.BaseEntity;
import com.yurisa.pessoa.domain.pessoa.TipoIdentificador;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PessoaDto implements BaseEntity {
    private Long id;
    private String nome;
    private String identificador;
    private TipoIdentificador tipoIdentificador;
}
