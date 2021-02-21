package m.gaw.kafkademo.abstraction.components;

import java.util.Optional;

public interface MessageConverter<I,T,O> {

    Optional<T> deserialize(I input);

    Optional<O> serialize(T object);

    O convertErrorInput(I errorInput);

}
