package br.com.food_city.presentation.controller;

import br.com.food_city.application.dto.TipoUsuarioInput;
import br.com.food_city.application.dto.UsuarioInput;
import br.com.food_city.application.usecase.cadastro.SalvarCadastroUseCase;
import br.com.food_city.config.CadastroProperties;
import br.com.food_city.exception.GlobalNotFoundException;
import br.com.food_city.presentation.dto.RestauranteComercioRequest;
import br.com.food_city.presentation.dto.CadastroRequest;
import br.com.food_city.application.dto.CadastroInput;
import br.com.food_city.application.dto.EnderecoInput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/cadastrar")
@RequiredArgsConstructor
public class CadastroController {

    private final SalvarCadastroUseCase useCase;
    private final CadastroProperties properties;

    @PostMapping("/proprietario")
    public ResponseEntity<Void> salvarCadastroComercio(@RequestBody CadastroRequest dto) throws GlobalNotFoundException {
        useCase.created(toInput(dto), properties.getProprietario());
        return ResponseEntity.status(200).build();
    }

    @PostMapping("/cliente")
    public ResponseEntity<Void> salvarCadastroCliente(@RequestBody CadastroRequest dto) throws GlobalNotFoundException {
        useCase.created(toInput(dto), properties.getCliente());
        return ResponseEntity.status(200).build();
    }

    @PostMapping("/funcionario")
    public ResponseEntity<Void> salvarCadastroFuncionario(@RequestBody CadastroRequest dto) throws GlobalNotFoundException {
        useCase.created(toInput(dto), properties.getFuncionario());
        return ResponseEntity.status(200).build();
    }

    private CadastroInput toInput(CadastroRequest dto){
        var usuario = dto.usuario();
        var usuarioInput = new UsuarioInput(usuario.login(), usuario.senha());
        var enderecoDto = dto.enderecoDTO();
        var enderecoInput = new EnderecoInput(enderecoDto.logradouro(), enderecoDto.numero(), enderecoDto.bairro(), enderecoDto.municipio(), enderecoDto.estado());
        return new CadastroInput(dto.nome(), dto.email(), dto.numeroDocumento(), usuarioInput, dto.dataNascimento(),enderecoInput);
    }
}
