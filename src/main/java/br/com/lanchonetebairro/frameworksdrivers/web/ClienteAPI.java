package br.com.lanchonetebairro.frameworksdrivers.web;

import br.com.lanchonetebairro.interfaceadapters.dto.ClienteResponseDTO;
import br.com.lanchonetebairro.interfaceadapters.dto.CriacaoClienteDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/clientes")
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
    ClienteResponseDTO criar(CriacaoClienteDTO cliente);

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
