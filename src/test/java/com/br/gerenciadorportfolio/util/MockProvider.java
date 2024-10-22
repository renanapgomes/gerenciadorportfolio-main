package com.br.gerenciadorportfolio.util;

import com.br.gerenciadorportfolio.entity.Pessoa;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MockProvider {

    public static final Long ID = 1L;

    public static Pessoa generateMockPessoa(){
        return Pessoa.builder()
                .idPessoa(ID)
                .nome("nomeTest")
                .build();
    }
}
