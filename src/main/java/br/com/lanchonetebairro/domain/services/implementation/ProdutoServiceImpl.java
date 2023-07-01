package br.com.lanchonetebairro.domain.services.implementation;

import br.com.lanchonetebairro.api.dto.CriacaoProdutoDTO;
import br.com.lanchonetebairro.api.dto.EdicaoProdutoDTO;
import br.com.lanchonetebairro.api.dto.ProdutoResponseDTO;
import br.com.lanchonetebairro.domain.enums.CategoriaProduto;
import br.com.lanchonetebairro.domain.exceptions.NotFoundException;
import br.com.lanchonetebairro.domain.mappers.ProdutoMapper;
import br.com.lanchonetebairro.domain.services.ProdutoService;
import br.com.lanchonetebairro.infraestructure.entities.Produto;
import br.com.lanchonetebairro.infraestructure.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoServiceImpl implements ProdutoService {


    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository, ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }

    @Override
    public ProdutoResponseDTO criar(CriacaoProdutoDTO produtoDTO) {
        Produto produto = produtoMapper.toEntity(produtoDTO);
        produtoRepository.save(produto);
        return produtoMapper.toDtoResponse(produto);
    }

    @Override
    public ProdutoResponseDTO editar(Long id, EdicaoProdutoDTO produtoDTO) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Produto não encontrado com o id %d", id)));
        produtoMapper.updateNonNullFields(produtoDTO, produto);
        produtoRepository.save(produto);
        return produtoMapper.toDtoResponse(produto);
    }

    @Override
    public ProdutoResponseDTO buscarPorId(Long id) {
        return produtoMapper.toDtoResponse(
                produtoRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(String.format("Produto não encontrado com o id %d", id)))
        );
    }

    @Override
    public List<ProdutoResponseDTO> buscarPorCategoria(CategoriaProduto categoria) {
        return produtoRepository.findByCategoria(categoria)
                .stream()
                .map(produtoMapper::toDtoResponse)
                .collect(Collectors.toList());
    }
}
