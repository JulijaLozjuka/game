package dev.julialoz.game.javarushgame.domain;

import dev.julialoz.game.javarushgame.model.npc.Npc;

import java.util.ArrayList;
import java.util.List;

public class NpcBuilder {

    NpcBuilder() {
    }

    public List<Npc> generateNpcs() {
        List<Npc> npcs = new ArrayList<>();

        npcs.add(Npc.builder()
                .id(1L)
                .name("Ladybug")
                .questInProgressText("You are not done.")
                .questDoneText("Oh great you got it.")
                .questCompleteText("I got all the water I need.")
                .questId(1L) // get water quest
                .dialogStartingMessage(1L)
                .build());

        npcs.add(Npc.builder()
                .id(2L)
                .name("Firefly")
                .questId(2L) // get hat quest
                .questInProgressText("Did you find my hat?")
                .questInProgressText("Is that my hat?")
                .questCompleteText("I love hats.")
                .dialogStartingMessage(4L)
                .build());

        return npcs;
    }
}
