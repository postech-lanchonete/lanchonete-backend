package br.com.lanchonetebairro.domain.mappers;

import br.com.lanchonetebairro.api.dto.CriacaoProdutoDTO;
import br.com.lanchonetebairro.api.dto.EdicaoProdutoDTO;
import br.com.lanchonetebairro.api.dto.ProdutoResponseDTO;
import br.com.lanchonetebairro.infraestructure.entities.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

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
