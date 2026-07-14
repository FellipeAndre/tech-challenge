package br.com.food_city.presentation.controller;

import br.com.food_city.application.dto.EnderecoInput;
import br.com.food_city.application.dto.RestauranteInput;
import br.com.food_city.application.usecase.restaurante.*;
import br.com.food_city.exception.GlobalNotFoundException;
import br.com.food_city.exception.UsuarioNaoEncontradoException;
import br.com.food_city.presentation.dto.RestauranteComercioRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/restaurantes")
@RequiredArgsConstructor
public class RestauranteController {

    private final CadastrarRestauranteUseCase cadastrarUseCase;
    private final BuscarRestauranteUseCase buscarUseCase;
    private final ListarRestauranteUseCase listarUseCase;
    private final AtualizarRestauranteUseCase atualizarUseCase;
    private final RemoverRestauranteUseCase removerUseCase;


    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@Valid @RequestBody RestauranteComercioRequest request) throws Exception {
        var input = toInput(request);
        var output = cadastrarUseCase.executar(input);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(output);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable UUID id) throws Exception {
        var output = buscarUseCase.executar(id);
        return ResponseEntity.ok(output);
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(listarUseCase.executar());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable UUID id,
                                       @Valid @RequestBody RestauranteComercioRequest request) throws Exception {
        var input = toInput(request);
        var output = atualizarUseCase.executar(id, input);
        return ResponseEntity.ok(output);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable UUID id) throws Exception {
        removerUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }

    private RestauranteInput toInput(@Valid RestauranteComercioRequest request) {
        var requestEnd = request.getEndereco();
        var enderecoInput = new EnderecoInput(requestEnd.estado(), requestEnd.numero(), requestEnd.bairro(), requestEnd.municipio(), requestEnd.estado());
        return new RestauranteInput(request.getNome(), enderecoInput, request.getTipoCozinha(), request.getHorarioAbertura(), request.getHorarioFechamento(), request.getDonoId());
    }

}