package nl.hu.bep.shopping.model;

import com.azure.communication.email.EmailAsyncClient;
import com.azure.communication.email.models.EmailMessage;
import com.azure.communication.email.models.EmailSendResult;
import com.azure.core.util.polling.LongRunningOperationStatus;
import com.azure.core.util.polling.PollerFlux;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private static EmailAsyncClient emailClient;

    @Value("${azure.communication.service.sender.email}")
    private static String senderEmail;

    public static void sendEmail(String toEmail) {

        EmailMessage message = new EmailMessage()
                .setSenderAddress(senderEmail) // Use configured sender email
                .setToRecipients(toEmail)
                .setSubject("Vragenlijst ingeleverd")
                .setBodyHtml("Dankuwel!");

        PollerFlux<EmailSendResult, EmailSendResult> poller = emailClient.beginSend(message);
        poller.subscribe(
                response -> {
                    if (response.getStatus() == LongRunningOperationStatus.SUCCESSFULLY_COMPLETED) {
                        logger.info("Successfully sent the email (operation id: {})", response.getValue().getId());
                    } else {
                        logger.info("Email send status: {}", response.getStatus());
                    }
                },
                error -> {
                    logger.error("Error occurred while sending email: {}", error.getMessage(), error);
                }
        );
    }

}
