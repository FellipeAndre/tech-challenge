package br.com.food_city.application.mapper;

import br.com.food_city.application.dto.EnderecoOutput;
import br.com.food_city.application.dto.RestauranteInput;
import br.com.food_city.application.dto.RestauranteOutput;
import br.com.food_city.domain.entities.EnderecoDomain;
import br.com.food_city.domain.entities.Restaurante;

import java.util.ArrayList;
import java.util.List;

public class RestauranteUsecaseMapper {

    public static Restaurante toDomain(RestauranteInput input) {

        var endereco = input.getEndereco();
        var endereoDomain =  new EnderecoDomain(endereco.getLogradouro(), endereco.getNumero(), endereco.getBairro(), endereco.getEstado(), endereco.getMunicipio());

        return new Restaurante(
                null,
                input.getNome(),
                endereoDomain,
                input.getTipoCozinha(),
                input.getHorarioAbertura(),
                input.getHorarioFechamento(),
                input.getDonoId()
        );
    }

    public static RestauranteOutput toOutput(Restaurante restaurante) {

        var endereco = restaurante.getEndereco();
        var enderecoOutput =  new EnderecoOutput(endereco.getLorgadouro(), endereco.getNumero(), endereco.getBairro(), endereco.getEstado(), endereco.getMunicipio());

        RestauranteOutput output = new RestauranteOutput();
        output.setId(restaurante.getId());
        output.setNome(restaurante.getNome());
        output.setEndereco(enderecoOutput);
        output.setTipoCozinha(restaurante.getTipoCozinha());
        output.setHorarioAbertura(restaurante.getHorarioAbertura());
        output.setHorarioFechamento(restaurante.getHorarioFechamento());
        output.setDonoId(restaurante.getDonoId());
        return output;
    }

    public static List<RestauranteOutput> toOutputList(List<Restaurante> listaDeRestaurantes) {

        List<RestauranteOutput> listOutPut = new ArrayList<>();

        for(Restaurante restaurante : listaDeRestaurantes){

           var outPut = toOutput(restaurante);

            listOutPut.add(outPut);
        }

        return listOutPut;
    }
}