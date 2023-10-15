package br.com.lanchonetebairro.frameworksdrivers.web;

import br.com.lanchonetebairro.interfaceadapters.dto.CriacaoPedidoDTO;
import br.com.lanchonetebairro.interfaceadapters.dto.PedidoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/v1/pedidos")
@Tag(name = "Pedidos", description = "Todas as operações referentes aos pedidos")
public interface PedidoAPI {

    @Operation(
            summary = "Busca pedidos",
            description = "Busca todos os pedidos."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PedidoResponseDTO.class)))
            }),
    })
    List<PedidoResponseDTO> buscarTodos(@Parameter(description = "Status do pedido, podendo ser 'RECEBIDO', 'EM_PREPARACAO', 'PRONTO' ou 'FINALIZADO'", example = "RECEBIDO", schema = @Schema(type = "string", allowableValues = {"RECEBIDO", "EM_PREPARACAO", "PRONTO", "FINALIZADO"}))
                                        @RequestParam(required = false, name = "status") String status);

    @Operation(
            summary = "Cria um novo pedido",
            description = "Ao receber uma lista de produtos e o id de um cliente, cria um novo pedido."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pedido criado." ),
            @ApiResponse(responseCode = "404", description = "O usuário, ou os produtos não foram encontrados.", content = { @Content(schema = @Schema()) })
    })
    PedidoResponseDTO criar(@Valid @RequestBody CriacaoPedidoDTO pedido);


    @Operation(
            summary = "Mudar status de um pedido um novo pedido",
            description = "Muda o pedido, seguindo o fluxo: recebido > em preparação > pronto > finalizado."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Pedido alterado." ),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado.", content = { @Content(schema = @Schema()) })
    })
    PedidoResponseDTO mudarStatus(@NotNull(message = "ID do pedido é obrigatório") Long id);

}
