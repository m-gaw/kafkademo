package m.gaw.kafkademo.implementation;

import lombok.extern.log4j.Log4j2;
import m.gaw.kafkademo.abstraction.MessageReceiver;
import m.gaw.kafkademo.abstraction.ValidationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class KafkaConsumer extends MessageReceiver<String> {

    public KafkaConsumer(ValidationService<String, ?, ?> validationService) {
        super(validationService);
    }

    @KafkaListener(topics = "${spring.kafka.topic.input}", groupId = "m_gaw")
    public void consume(String message) {
        log.info("Received message: '{}'", message);
        super.processReceivedMessage(message);
    }

}
