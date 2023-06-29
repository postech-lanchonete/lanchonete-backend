package br.com.lanchonetebairro.business.implementation;

import br.com.lanchonetebairro.api.dto.ClienteResponseDTO;
import br.com.lanchonetebairro.api.dto.CriacaoClienteDTO;
import br.com.lanchonetebairro.business.ClienteService;
import br.com.lanchonetebairro.infraestructure.entities.Cliente;
import br.com.lanchonetebairro.infraestructure.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ClienteResponseDTO buscarPorCPF(String cpf) {
        Cliente cliente = clienteRepository.findByCpf(cpf).orElseThrow(RuntimeException::new);
        ClienteResponseDTO response = new ClienteResponseDTO();
        response.setCpf(cliente.getCpf());
        response.setNome(cliente.getNome());
        response.setEmail(cliente.getEmail());
        response.setSobrenome(cliente.getSobrenome());
        return response;
    }

    @Override
    public ClienteResponseDTO criarCliente(CriacaoClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setSobrenome(clienteDTO.getSobrenome());
        cliente.setSenha(clienteDTO.getSenha());
        clienteRepository.save(cliente);

        ClienteResponseDTO response = new ClienteResponseDTO();
        response.setCpf(clienteDTO.getCpf());
        response.setNome(clienteDTO.getNome());
        response.setEmail(clienteDTO.getEmail());
        response.setSobrenome(clienteDTO.getSobrenome());
        return response;
    }
}
