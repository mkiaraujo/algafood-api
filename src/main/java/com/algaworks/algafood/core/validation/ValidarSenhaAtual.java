package com.algaworks.algafood.core.validation;


import jakarta.validation.Constraint;
import jakarta.validation.OverridesAttribute;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = { ValidarSenhaAtualValidator.class })
public @interface ValidarSenhaAtual {

    String message() default "{ValidarSenhaAtual.invalida}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String senhaAtualField();
    String idField();

}
