package com.yurisa.pessoa.domain.pessoa;

import com.yurisa.pessoa.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "pessoa")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pessoa implements BaseEntity {

    @Id
    @GeneratedValue(generator = "pessoa", strategy = GenerationType.TABLE)
    @TableGenerator(name = "pessoa", table = "sequence")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "identificador")
    private String identificador;

    @Column(name = "tipo_identificador")
    private TipoIdentificador tipoIdentificador;

}
