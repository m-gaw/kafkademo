package m.gaw.kafkademo.abstraction;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class MessageReceiver<I> {

    protected final ValidationService<I,?,?> validationService;

    public void processReceivedMessage(I message){
        validationService.process(message);
    }

}
