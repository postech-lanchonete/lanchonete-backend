package br.com.lanchonetebairro.interfaceadapters.adapter;

import br.com.lanchonetebairro.enterpriserules.entities.Produto;
import br.com.lanchonetebairro.interfaceadapters.dto.CriacaoProdutoDTO;
import br.com.lanchonetebairro.interfaceadapters.dto.EdicaoProdutoDTO;
import br.com.lanchonetebairro.interfaceadapters.dto.ProdutoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProdutoAdapter {

    @Mapping(target = "id", ignore = true)
    Produto toEntity(CriacaoProdutoDTO dto);

    Produto toEntity(Long id, EdicaoProdutoDTO dto);

    ProdutoResponseDTO toDtoResponse(Produto produto);
}
