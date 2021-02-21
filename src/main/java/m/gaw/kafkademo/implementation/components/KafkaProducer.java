package m.gaw.kafkademo.implementation.components;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Log4j2
@Component
@RequiredArgsConstructor
public class KafkaProducer implements BiConsumer<String,Boolean> {

    @Value("${spring.kafka.topic.valid}")
    private String validObjectsTopic;

    @Value("${spring.kafka.topic.invalid}")
    private String invalidObjectsTopic;

    private final KafkaTemplate<String,String> kafkaTemplate;

    public void accept(String message, Boolean isValid) {
        String topic = isValid ? validObjectsTopic : invalidObjectsTopic;
        log.info("Message '{}' passed to topic {}", message, topic);
        kafkaTemplate.send(topic, message);
    }

}
