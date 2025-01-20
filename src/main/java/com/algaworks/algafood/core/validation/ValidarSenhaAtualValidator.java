package com.algaworks.algafood.core.validation;

import com.algaworks.algafood.domain.service.CadastroUsuarioService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ValidationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class ValidarSenhaAtualValidator implements ConstraintValidator<ValidarSenhaAtual, Object> {

    private String id;
    private String senhaAtual;

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;


    @Override
    public void initialize(ValidarSenhaAtual constraintAnnotation) {
        this.senhaAtual = constraintAnnotation.senhaAtualField();
        this.id = constraintAnnotation.idField();
    }

    @Override
    public boolean isValid(Object objetoValidacao , ConstraintValidatorContext context) {
        boolean valido = true;
        try {
            Long valorId = (Long) BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), "idField")
                    .getReadMethod().invoke(objetoValidacao);
            String senha = (String) BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), "senhaAtualField")
                    .getReadMethod().invoke(objetoValidacao);
            var usuario = cadastroUsuarioService.buscarOuFalhar(valorId);
            if (usuario.getSenha() != senha)
                valido = false;

            return valido;
        } catch (Exception e){
            throw new ValidationException(e);
        }

    }

}
