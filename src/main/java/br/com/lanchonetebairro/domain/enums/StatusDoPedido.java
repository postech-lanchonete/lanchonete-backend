package br.com.lanchonetebairro.domain.enums;

import br.com.lanchonetebairro.domain.exceptions.BadRequestException;

import java.util.Arrays;

public enum StatusDoPedido {
    RECEBIDO, EM_PREPARACAO, PRONTO, FINALIZADO;

    public static StatusDoPedido encontrarEnumPorString(String valor) {
        return Arrays.stream(StatusDoPedido.values())
                .filter(enumValue -> enumValue.name().equals(valor.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new BadRequestException(String.format("StatusDoPedido não encontrado para o valor: %s", valor)));
    }
}