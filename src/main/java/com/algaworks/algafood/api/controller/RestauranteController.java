package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.RestauranteModelAssembler;
import com.algaworks.algafood.api.disassembler.ModelRestauranteDisassembler;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.api.model.input.CozinhaIdInput;
import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.core.validation.ValidacaoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @Autowired
    private SmartValidator smartValidator;

    @Autowired
    private RestauranteModelAssembler restauranteModelAssembler;

    @Autowired
    private ModelRestauranteDisassembler modelRestauranteDisassembler;

    @GetMapping
    public List<RestauranteModel> listar() {

        return restauranteModelAssembler.toCollectionModel(restauranteRepository.findAll());
    }

    @GetMapping("/{restauranteId}")
    public RestauranteModel buscar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

        return restauranteModelAssembler.toModel(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteModel adicionar(@RequestBody
                @Valid RestauranteInput restauranteInput) {
        try {
            Restaurante restaurante = modelRestauranteDisassembler.toDomainObject(restauranteInput);

            return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restaurante));
        } catch (EntidadeNaoEncontradaException e){
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public RestauranteModel atualizar(@PathVariable Long restauranteId,
                                      @RequestBody @Valid RestauranteInput restauranteInput) {
        try {
            Restaurante restaurante = modelRestauranteDisassembler.toDomainObject(restauranteInput);
            Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);
            BeanUtils.copyProperties(restaurante, restauranteAtual,
                        "id", "formasPagamento", "endereco", "dataCadastro", "produtos");
            return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restauranteAtual));
        } catch (EntidadeNaoEncontradaException e){
            throw new NegocioException(e.getMessage());
        }
    }

    @PatchMapping("/{restauranteId}")
    public RestauranteModel atualizarParcial(@PathVariable Long restauranteId,
                                        @RequestBody Map<String, Object> campos, HttpServletRequest httpServletRequest){

        Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);

        merge(campos, restauranteAtual, httpServletRequest);

        validate(restauranteAtual, "restaurante");

        RestauranteInput restauranteInput = restauranteModelAssembler.toInputModel(restauranteAtual);

        return atualizar(restauranteId, restauranteInput);
    }



    private void validate(Restaurante restaurante, String objectName) {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);
        smartValidator.validate(restaurante, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ValidacaoException(bindingResult);
        }

    }

    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino, HttpServletRequest httpServletRequest) {

        ServletServerHttpRequest servletServerHttpRequest = new ServletServerHttpRequest(httpServletRequest);

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

            Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

            dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
                Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
                field.setAccessible(true);

                Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
                ReflectionUtils.setField(field, restauranteDestino, novoValor);
            });
        } catch (IllegalArgumentException e) {
//            Throwable rootCause = ExceptionUtils.getRootCause(e);
            Throwable rootCause = e.getCause();
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, servletServerHttpRequest);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{restauranteId}")
    public void remover(@PathVariable Long restauranteId) {
        cadastroRestaurante.excluir(restauranteId);
    }


}
