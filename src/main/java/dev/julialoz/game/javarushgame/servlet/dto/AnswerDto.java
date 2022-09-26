package dev.julialoz.game.javarushgame.servlet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AnswerDto {
    private String text;
    private Long nextMessage;
    private Long questId;
}
