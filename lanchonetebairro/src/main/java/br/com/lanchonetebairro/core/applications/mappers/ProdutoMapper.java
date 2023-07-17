package br.com.lanchonetebairro.core.applications.mappers;

import br.com.lanchonetebairro.adapter.driver.api.dto.CriacaoProdutoDTO;
import br.com.lanchonetebairro.adapter.driver.api.dto.EdicaoProdutoDTO;
import br.com.lanchonetebairro.adapter.driver.api.dto.ProdutoResponseDTO;
import br.com.lanchonetebairro.core.domain.entities.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    @Mapping(target = "id", ignore = true)
    Produto toEntity(CriacaoProdutoDTO dto);

    default void updateNonNullFields(EdicaoProdutoDTO dto, @MappingTarget Produto entity) {
        if (dto.getNome() != null) {
            entity.setNome(dto.getNome());
        }
        if (dto.getCategoria() != null) {
            entity.setCategoria(dto.getCategoria());
        }
        if (dto.getPreco() != null) {
            entity.setPreco(dto.getPreco());
        }
        if (dto.getDescricao() != null) {
            entity.setDescricao(dto.getDescricao());
        }
        if (dto.getImagem() != null) {
            entity.setImagem(dto.getImagem());
        }
    }

    ProdutoResponseDTO toDtoResponse(Produto produto);
}
