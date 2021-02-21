package m.gaw.kafkademo.implementation;

import m.gaw.kafkademo.abstraction.components.MessageConverter;
import m.gaw.kafkademo.abstraction.ValidationService;
import m.gaw.kafkademo.implementation.model.Triangle;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;
import java.util.function.Predicate;

@Service
public class TriangleValidationService extends ValidationService<String,Triangle,String> {

    public TriangleValidationService(MessageConverter<String,Triangle,String> triangleMessageConverter, Predicate<Triangle> triangleValidator, BiConsumer<String,Boolean> sender) {
        super(triangleMessageConverter, triangleValidator, sender);
    }

}
