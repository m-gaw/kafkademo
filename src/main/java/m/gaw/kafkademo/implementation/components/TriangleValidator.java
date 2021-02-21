package m.gaw.kafkademo.implementation.components;

import m.gaw.kafkademo.implementation.model.Triangle;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

import static java.util.Objects.nonNull;

@Component
public class TriangleValidator implements Predicate<Triangle> {

    public boolean test(Triangle triangle) {
        return nonNull(triangle) && triangle.canBeConstructed();
    }

}
