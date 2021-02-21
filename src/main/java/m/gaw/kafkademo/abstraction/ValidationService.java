package m.gaw.kafkademo.abstraction;

import lombok.AllArgsConstructor;
import m.gaw.kafkademo.abstraction.components.MessageConverter;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

@AllArgsConstructor
public abstract class ValidationService<I,T,O> {

    protected MessageConverter<I,T,O> messageConverter;
    protected Predicate<T> validator;
    protected BiConsumer<O,Boolean> sender;

    public void process(I input) {
        final Optional<T> deserializedObject = messageConverter.deserialize(input);

        if (deserializedObject.isPresent()) {
            final boolean isValid = validator.test(deserializedObject.get());
            messageConverter.serialize(deserializedObject.get())
                    .ifPresent(outputMessage -> sender.accept(outputMessage, isValid));

        } else {
            sender.accept(messageConverter.convertErrorInput(input), false);

        }
    }

}
