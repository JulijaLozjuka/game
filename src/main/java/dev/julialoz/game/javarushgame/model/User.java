package dev.julialoz.game.javarushgame.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String name;
    private int currentHealth;
    private int maxHealth;
    private int currentRoomId;
}
