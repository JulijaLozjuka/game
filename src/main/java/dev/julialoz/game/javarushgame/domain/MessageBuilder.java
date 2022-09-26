package dev.julialoz.game.javarushgame.domain;

import dev.julialoz.game.javarushgame.model.npc.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageBuilder {

    MessageBuilder() {
    }

    public List<Message> generateMessages() {
        List<Message> messages = new ArrayList<>();

        ladyBugDialog(messages);
        fireFlyDialog(messages);

        return messages;
    }

    private void fireFlyDialog(List<Message> messages) {
        messages.add(Message.builder()
                .id(4L)
                .answer1(6L)
                .answer2(7L)
                .text("Bzhzhzh, Hi there!")
                .build());

        messages.add(Message.builder()
                .id(5L)
                .answer1(8L)
                .answer2(9L)
                .text("Bzhzhzh I'm looking for a hat, have you seen it ?")
                .build());
    }

    private void ladyBugDialog(List<Message> messages) {
        messages.add(Message.builder()
                .id(1L)
                .answer1(1L)
                .answer2(null)
                .text("Who are you? I have never seen something like you.")
                .build());
        messages.add(Message.builder()
                .id(2L)
                .answer1(2L)
                .answer2(3L)
                .text("Oh I won't hurt you, but you nearly broke my house.")
                .build());
        messages.add(Message.builder()
                .id(3L)
                .answer1(4L)
                .answer2(5L)
                .text("Yes. I can help you to get a weapon for these dangerous parts. You must go and get a drop of water from the swamp.")
                .build());
    }
}
