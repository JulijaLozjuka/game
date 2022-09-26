package dev.julialoz.game.javarushgame.domain;

import dev.julialoz.game.javarushgame.model.npc.Answer;

import java.util.ArrayList;
import java.util.List;

public class AnswerBuilder {

    AnswerBuilder() {
    }

    public List<Answer> generateAnswers() {
        List<Answer> answers = new ArrayList<>();
        //ladybug answers
        // message 1
        answers.add(Answer.builder()
                .id(1l)
                .text("OMG what big bug, don't hurt me please!")
                .nextMessage(2L)
                .build());
        // message 2
        answers.add(Answer.builder()
                .id(2l)
                .text("Oh sorry, can you help me ? ")
                .nextMessage(3L)
                .build());
        answers.add(Answer.builder()
                .id(3l)
                .text("OoooH! (run away)")
                .nextMessage(null)
                .build());
        // message 3
        answers.add(Answer.builder()
                .id(4l)
                .text("Okay let's do it!")
                .nextMessage(null)
                .questId(1L)
                .build());
        answers.add(Answer.builder()
                .id(5l)
                .text("No thank you it sounds too difficult.")
                .nextMessage(null)
                .build());


        // firefly dialog

        // message 1

        answers.add(Answer.builder()
                .id(6l)
                .text("You look cool. What are you doing here ?")
                .nextMessage(5L)
                .build());
        answers.add(Answer.builder()
                .id(7l)
                .text("You are a big bug, need some help ?")
                .nextMessage(5L)
                .build());

        // message 2
        answers.add(Answer.builder()
                .id(8l)
                .text("Nope. (walk away)")
                .nextMessage(null)
                .build());
        answers.add(Answer.builder()
                .id(9l)
                .text("Yes, but not at the swamp some other place let me go get it for you ?")
                .questId(2L)
                .nextMessage(null)
                .build());

        return answers;
    }
}
