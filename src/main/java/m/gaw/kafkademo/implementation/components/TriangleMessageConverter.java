package m.gaw.kafkademo.implementation.components;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import m.gaw.kafkademo.abstraction.components.MessageConverter;
import m.gaw.kafkademo.implementation.model.Triangle;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Log4j2
@Component
public class TriangleMessageConverter implements MessageConverter<String, Triangle,String> {

    private final static ObjectMapper MAPPER = new ObjectMapper();

    public Optional<Triangle> deserialize(String input) {
        try {
            return Optional.of(MAPPER.readValue(input, Triangle.class));
        } catch (JsonProcessingException e) {
            log.warn("Message '{}' cannot be deserialized", input);
            return Optional.empty();
        }
    }

    public Optional<String> serialize(Triangle triangle) {
        try {
            return Optional.of(MAPPER.writeValueAsString(triangle));
        } catch (JsonProcessingException e) {
            log.error("Triangle '{}' cannot be serialized", triangle);
            return Optional.empty();
        }
    }

    public String convertErrorInput(String errorInput) {
        return errorInput;
    }
}
