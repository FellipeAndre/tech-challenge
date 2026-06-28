package br.com.food_city.presentation.controller;

import br.com.food_city.presentation.dto.CadastroRequest;
import br.com.food_city.application.usecase.SalvarDadosCadastradoUseCase;
import br.com.food_city.domain.entities.enumerable.TipoRoleEnum;
import br.com.food_city.application.dto.CadastroInput;
import br.com.food_city.application.dto.EnderecoInput;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/cadastrar")
@RequiredArgsConstructor
public class CadastroController {

    private final SalvarDadosCadastradoUseCase useCase;

    @PostMapping("/comercio")
    public void salvarProprietario(@RequestBody CadastroRequest dto){
        var role = TipoRoleEnum.DONO.name();
        criarCadastro(dto, role);
    }

    @PostMapping("/cliente")
    public void salvarCliente(@RequestBody CadastroRequest dto){
        var role = TipoRoleEnum.CLIENTE.name();
        criarCadastro(dto, role);
    }
    
    private void criarCadastro(CadastroRequest dto, String role){
        useCase.created(toInput(dto, role));
    }


    private CadastroInput toInput(CadastroRequest dto, String role){
        var enderecoDto = dto.enderecoDTO();
        var enderecoInput = new EnderecoInput(enderecoDto.logradouro(), enderecoDto.numero(), enderecoDto.bairro(), enderecoDto.municipio(), enderecoDto.estado());
        return new CadastroInput(dto.nome(), dto.email(), dto.numeroDocumento(), dto.dataNascimento(), role, enderecoInput);
    }
}
