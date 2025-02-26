package com.algaworks.algafood.infrastructure.service.email;

import com.algaworks.algafood.core.email.EmailProperties;
import com.algaworks.algafood.domain.service.EnvioEmailService;
import freemarker.template.Configuration;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

public class SandBoxEnvioEmailService extends SmtpEnvioEmailService {

    @Autowired
    private EmailProperties emailProperties;

    @Override
    protected MimeMessage criarMimeMessage(Mensagem mensagem) throws MessagingException {
        var mimeMessage = super.criarMimeMessage(mensagem);

        var helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setTo(emailProperties.getSandbox().getDestinatario());

        return mimeMessage;
    }
}
