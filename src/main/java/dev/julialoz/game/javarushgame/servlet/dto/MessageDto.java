package dev.julialoz.game.javarushgame.servlet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MessageDto {
    private String text;
    private AnswerDto answer1;
    private AnswerDto answer2;

}
