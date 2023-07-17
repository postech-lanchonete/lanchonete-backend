package br.com.lanchonetebairro.adapter.driver.api;

import br.com.lanchonetebairro.adapter.driver.api.dto.ClienteResponseDTO;
import br.com.lanchonetebairro.adapter.driver.api.dto.CriacaoClienteDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Clientes", description = "Todas as operações referentes aos clientes")
public interface ClienteAPI {

    @Operation(
            summary = "Cria um novo cliente",
            description = "Recebe os dados do cliente e salva na base de dados."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Cliente criado." ),
            @ApiResponse(responseCode = "400", description = "Algum dos atributos está incorreto.", content = { @Content(schema = @Schema()) })
    })
    ClienteResponseDTO criar(@Valid CriacaoClienteDTO cliente);

    @Operation(
            summary = "Buscar cliente por CPF",
            description = "Busca o cliente registrado no sistema com o cpf informado."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente encontrado." ),
            @ApiResponse(responseCode = "400", description = "Não existe nenhum cliente registrado no sistema com este CPF informado.", content = { @Content(schema = @Schema()) })
    })
    ClienteResponseDTO buscarPorCPF(String cpf);

}
