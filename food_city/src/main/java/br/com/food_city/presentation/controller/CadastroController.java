package br.com.food_city.presentation.controller;

import br.com.food_city.presentation.dto.CadastroRequest;
import br.com.food_city.application.usecase.SalvarDadosCadastradoUseCase;
import br.com.food_city.domain.entities.enumerable.TipoRoleEnum;
import br.com.food_city.application.dto.CadastroInput;
import br.com.food_city.application.dto.EnderecoInput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/cadastrar")
@RequiredArgsConstructor
public class CadastroController {

    private final SalvarDadosCadastradoUseCase useCase;

    @PostMapping
    public ResponseEntity<Void> salvarCadastro(@RequestBody CadastroRequest dto){
        useCase.created(toInput(dto));
        return ResponseEntity.status(200).build();
    }

    private CadastroInput toInput(CadastroRequest dto){
        var enderecoDto = dto.enderecoDTO();
        var enderecoInput = new EnderecoInput(enderecoDto.logradouro(), enderecoDto.numero(), enderecoDto.bairro(), enderecoDto.municipio(), enderecoDto.estado());
        return new CadastroInput(dto.nome(), dto.email(), dto.numeroDocumento(), dto.dataNascimento(),enderecoInput);
    }
}
