package com.algaworks.algafood.core.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.http.MediaType;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FileSizeContentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile> {

    private List<String> allowedContenttype;

    @Override
    public void initialize(FileContentType constraintAnnotation) {
        this.allowedContenttype = Arrays.asList(constraintAnnotation.allowed());

    }

    @Override
    public boolean isValid(MultipartFile value , ConstraintValidatorContext context) {
        return value == null
                || this.allowedContenttype.contains(value.getContentType());
    }


}
