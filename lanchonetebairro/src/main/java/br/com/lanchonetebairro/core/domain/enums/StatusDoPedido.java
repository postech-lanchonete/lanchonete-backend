package br.com.lanchonetebairro.core.domain.enums;

import br.com.lanchonetebairro.core.applications.exceptions.BadRequestException;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum StatusDoPedido {
    RECEBIDO, EM_PREPARACAO, PRONTO, FINALIZADO;

    public static StatusDoPedido encontrarEnumPorString(String valor) {
        return Arrays.stream(StatusDoPedido.values())
                .filter(enumValue -> enumValue.name().equals(valor.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new BadRequestException(
                        String.format("StatusDoPedido não encontrado para o valor: %s. Os valores permitidos são: %s",
                                        valor, String.join(",", Arrays.stream(values()).map(Enum::toString).collect(Collectors.joining(","))))));
    }
}