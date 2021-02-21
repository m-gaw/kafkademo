package m.gaw.kafkademo;

import m.gaw.kafkademo.implementation.KafkaConsumer;
import m.gaw.kafkademo.implementation.components.KafkaProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class TriangleValidationIntegrationTest {

    @Autowired
    private KafkaConsumer consumer;

    @MockBean
    private KafkaProducer producer;


    @Test
    void shouldSendMessageWithIncorrectSyntaxToInvalidObjectTopic(){
        //given
        String input = "incorrect_syntax_input";

        //when
        consumer.processReceivedMessage(input);

        //then
        verify(producer).accept(input, false);
    }

    @Test
    void shouldSendTriangleWithNegativeSideToInvalidObjectTopic(){
        //given
        String input = asJson("{'a':-1,'b':2,'c':2}");

        //when
        consumer.processReceivedMessage(input);

        //then
        verify(producer).accept(input, false);
    }

    @Test
    void shouldSendTriangleWithTooLongSideToInvalidObjectTopic(){
        //given
        String input = asJson("{'a':11,'b':2,'c':2}");

        //when
        consumer.processReceivedMessage(input);

        //then
        verify(producer).accept(input, false);
    }

    @Test
    void shouldSendValidTriangleToValidObjectTopic(){
        //given
        String input = asJson("{'a':1,'b':2,'c':2}");

        //when
        consumer.processReceivedMessage(input);

        //then
        verify(producer).accept(input, true);
    }

    private String asJson(String pseudoJson){
        return pseudoJson.replace("'","\"");
    }

}
