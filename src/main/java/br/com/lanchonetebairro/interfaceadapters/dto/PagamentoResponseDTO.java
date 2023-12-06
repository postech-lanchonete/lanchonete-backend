package br.com.lanchonetebairro.interfaceadapters.dto;

import br.com.lanchonetebairro.enterpriserules.enums.StatusPagamento;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("unused")
public class PagamentoResponseDTO {
    private StatusPagamento status;

}
