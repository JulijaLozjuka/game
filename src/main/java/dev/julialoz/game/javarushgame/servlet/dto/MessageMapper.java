package dev.julialoz.game.javarushgame.servlet.dto;

import dev.julialoz.game.javarushgame.model.npc.Answer;
import dev.julialoz.game.javarushgame.model.npc.Message;

public class MessageMapper {

    public static MessageDto mapToMessageDto(Message message, Answer answer1, Answer answer2) {
        return MessageDto.builder()
                .text(message.getText())
                .answer1(mapToAnswerDto(answer1))
                .answer2(mapToAnswerDto(answer2))
                .build();
    }

    public static AnswerDto mapToAnswerDto(Answer answer) {
        return answer == null
                ? null
                : AnswerDto.builder()
                .text(answer.getText())
                .nextMessage(answer.getNextMessage())
                .questId(answer.getQuestId())
                .build();
    }
}
