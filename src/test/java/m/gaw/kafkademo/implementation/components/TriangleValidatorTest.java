package m.gaw.kafkademo.implementation.components;

import m.gaw.kafkademo.implementation.model.Triangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TriangleValidatorTest {

    private final TriangleValidator triangleValidator = new TriangleValidator();

    @Test
    void shouldMarkTriangleWithNegativeSideAsInvalid(){
        //given
        Triangle triangle = new Triangle(-1,2,3);

        //when
        final boolean validationResult = triangleValidator.test(triangle);

        //then
        assertFalse(validationResult);
    }

    @Test
    void shouldMarkTriangleWithTooLongSideAsInvalid(){
        //given
        Triangle triangle = new Triangle(7,2,3);

        //when
        final boolean validationResult = triangleValidator.test(triangle);

        //then
        assertFalse(validationResult);
    }

    @Test
    void shouldMarkNullTriangleAsInvalid(){
        //given
        Triangle triangle = null;

        //when
        final boolean validationResult = triangleValidator.test(triangle);

        //then
        assertFalse(validationResult);
    }

    @Test
    void shouldMarkCorrectTriangleAsValid(){
        //given
        Triangle triangle = new Triangle(4,3,5);

        //when
        final boolean validationResult = triangleValidator.test(triangle);

        //then
        assertTrue(validationResult);
    }

}
