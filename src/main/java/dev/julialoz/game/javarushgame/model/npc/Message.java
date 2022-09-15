package dev.julialoz.game.javarushgame.model.npc;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {
    private Long id;
    private String text;
    private Long answer1;
    private Long answer2;
}
