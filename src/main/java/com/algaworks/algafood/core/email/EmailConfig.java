package com.algaworks.algafood.core.email;

import com.algaworks.algafood.core.storage.StorageProperties;
import com.algaworks.algafood.domain.service.EnvioEmailService;
import com.algaworks.algafood.domain.service.FotoStorageService;
import com.algaworks.algafood.infrastructure.service.email.FakeEnvioEmailService;
import com.algaworks.algafood.infrastructure.service.email.SmtpEnvioEmailService;
import com.algaworks.algafood.infrastructure.service.storage.LocalFotoStorageService;
import com.algaworks.algafood.infrastructure.service.storage.S3FotoStorageService;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    @Autowired
    private EmailProperties emailProperties;

    @Bean
    public EnvioEmailService envioEmailService() {
        switch (emailProperties.getImpl()){
            case FAKE: return new FakeEnvioEmailService();
            case SMTP: return new SmtpEnvioEmailService();
            default: return null;
        }
    }

}