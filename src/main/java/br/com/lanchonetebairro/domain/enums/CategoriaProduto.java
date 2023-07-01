package br.com.lanchonetebairro.domain.enums;

import java.util.Arrays;

public enum CategoriaProduto {

    LANCHE, ACOMPANHAMENTO, BEBIDA, SOBREMESA;

    public static CategoriaProduto encontrarEnumPorString(String valor) {
        return Arrays.stream(CategoriaProduto.values())
                .filter(enumValue -> enumValue.name().equals(valor.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("CategoriaProduto não encontrado para o valor: " + valor));
    }
}
