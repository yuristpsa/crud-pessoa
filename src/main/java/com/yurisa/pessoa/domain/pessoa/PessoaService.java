package com.yurisa.pessoa.domain.pessoa;

import com.yurisa.pessoa.base.AbstractService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class PessoaService extends AbstractService<Pessoa, PessoaRepository> {

    public PessoaService(PessoaRepository repo) {
        super(repo);
    }

    public Pessoa salvar(Pessoa pessoa) {
        if (Objects.isNull(pessoa.getTipoIdentificador())) {
            pessoa.setTipoIdentificador(calcularTipoIdentificador(pessoa.getIdentificador()));
        }

        return super.save(pessoa);
    }

    private TipoIdentificador calcularTipoIdentificador(String identificador) {
        if (Objects.equals(identificador.length(), 14)) {
            return TipoIdentificador.CNPJ;
        }

        if (Objects.equals(identificador.length(), 11)) {
            return TipoIdentificador.CPF;
        }

        return TipoIdentificador.NAO_RECONHECIDO;
    }

    public Optional<Pessoa> buscarPorIdentificador(String identificador) {
        return repo.findByIdentificador(identificador);
    }
}
