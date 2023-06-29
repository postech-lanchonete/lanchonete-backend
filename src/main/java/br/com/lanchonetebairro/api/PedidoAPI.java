package br.com.lanchonetebairro.api;

import br.com.lanchonetebairro.api.dto.CriacaoPedidoDTO;
import br.com.lanchonetebairro.api.dto.PedidoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Pedidos", description = "Todas as operações referentes aos pedidos")
public interface PedidoAPI {

    @Operation(
            summary = "Cria um novo pedido",
            description = "Ao receber uma lista de produtos e o id de um cliente, cria um novo pedido."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Pedido criado." ),
            @ApiResponse(responseCode = "422", description = "O usuário, ou os produtos não foram encontrados.", content = { @Content(schema = @Schema()) })
    })
    ResponseEntity<Void> criar(CriacaoPedidoDTO pedido);

    @Operation(
            summary = "Busca pedidos por estado",
            description = "Busca todos os pedidos de um determinado status."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CriacaoPedidoDTO.class)))
            }),
            @ApiResponse(responseCode = "400", description = "O status solicitado não existe.", content = { @Content(schema = @Schema()) })
    })
    List<PedidoResponseDTO> buscarPorStatus(@Parameter(description = "Status do pedido.", required = true) String statusDoPedido);
}
