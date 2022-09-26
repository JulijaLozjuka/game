package dev.julialoz.game.javarushgame.domain;

import dev.julialoz.game.javarushgame.model.Location;
import dev.julialoz.game.javarushgame.model.LocationName;

import java.util.ArrayList;
import java.util.List;

public class LocationBuilder {
    LocationBuilder() {
    }

    public List<Location> generateLocations() {
        List<Location> result = new ArrayList<>();

        // start location
        result.add(Location.builder()
                .id(1L)
                .level(1)
                .name(LocationName.SMALL_GRASS)
                .description("Starting peaceful zone")
                .imageName("small_grass.png")
                .npcs(List.of(1L)) // 1 ladybug
                .enemyList(null)
                .key(null)
                .items(List.of(1L)) // 1 health potion
                .neighbouringLocations(List.of(2L, 3L, 4L)) // 2 stump, 3 forest, 4 flower field
                .build());

        result.add(Location.builder()
                .id(2L)
                .level(1)
                .name(LocationName.STUMP)
                .description("Lonely stump at the edge of the forest,there once was a tall tree, but now the ants have made their home here")
                .imageName("stump.png")
                .npcs(null)
                .enemyList(null)
                .key(null)
                .items(List.of(2L, 5L)) // 2 stick, 5 small hat
                .neighbouringLocations(List.of(1L, 5L)) // 1 small grass 5 wasp nest
                .build());

        result.add(Location.builder()
                .id(3L)
                .level(1)
                .name(LocationName.FOREST)
                .description("A narrow path leads through the forest, a slight breeze blows and the songs of birds are heard")
                .imageName("forest.png")
                .npcs(null) //
                .enemyList(null)
                .key(null)
                .items(null) //
                .neighbouringLocations(List.of(1L,6L,7L,8L)) // 1-small grass, 6-berry bush, 7-stream, 8-dark forest
                .build());

        result.add(Location.builder()
                .id(4L)
                .level(1)
                .name(LocationName.FLOWER_FIELD)
                .description("There is a pleasant strong smell of flowers, butterflies have gathered here in large numbers to collect nectar")
                .imageName("flower_field.png")
                .npcs(null) //
                .enemyList(null)
                .key(null)
                .items(null) //
                .neighbouringLocations(List.of(1L)) //1 small grass
                .build());

        result.add(Location.builder()
                .id(5L)
                .level(1)
                .name(LocationName.WASP_NEST)
                .description("A place with many intricate passages, where you can get lost or stuck")
                .imageName("wasp_nest.png")
                .npcs(null) //
                .enemyList(null)
                .key(null)
                .items(null) //
                .neighbouringLocations(List.of(2L)) //2 stump small grass
                .build());

        result.add(Location.builder()
                .id(6L)
                .level(1)
                .name(LocationName.BERRY_BUSH)
                .description("Some berries hanging on a bush and they seem to be very delicious")
                .imageName("berry_bush.png")
                .npcs(null) //
                .enemyList(null)
                .key(null)
                .items(null) //
                .neighbouringLocations(List.of(3L)) //3 forest
                .build());

        result.add(Location.builder()
                .id(7L)
                .level(1)
                .name(LocationName.STREAM)
                .description("This place exudes freshness, the water looks crystal clear, the sound of bubbling water is heard")
                .imageName("stream.png")
                .npcs(null) //
                .enemyList(null)
                .key(null)
                .items(null) //
                .neighbouringLocations(List.of(3L, 9L, 10L)) //3 forest, 9- pond, 10- swamp
                .build());

        result.add(Location.builder()
                .id(8L)
                .level(1)
                .name(LocationName.DARK_FOREST)
                .description("The forest is getting darker and darker, cobwebs have begun to appear on the trees in large numbers, strange sounds are heard")
                .imageName("dark_forest.png")
                .npcs(null) //
                .enemyList(null)
                .key(null)
                .items(null) //
                .neighbouringLocations(List.of(3L, 11L, 12L)) //3-forest, 11-rotten mushrooms, 12-spider liar
                .build());

        result.add(Location.builder()
                .id(9L)
                .level(1)
                .name(LocationName.POND)
                .description("It is very wet and noisy, there are many water lilies and croaking frogs.")
                .imageName("pond.png")
                .npcs(null) //
                .enemyList(null)
                .key(null)
                .items(null) //
                .neighbouringLocations(List.of(7L)) //7-stream
                .build());

        result.add(Location.builder()
                .id(10L)
                .level(1)
                .name(LocationName.SWAMP)
                .description("Quite a quiet place, dusk has come and it becomes difficult to distinguish the details," +
                        "but fireflies fly in large numbers, it is not clear why they have gathered here ..")
                .imageName("swamp.png")
                .npcs(List.of(2L)) // 2 firefly
                .enemyList(null)
                .key(null)
                .items(List.of(3L, 4L)) // 3 bottle cap, 4 water
                .neighbouringLocations(List.of(7L)) //7-stream
                .build());



        result.add(Location.builder()
                .id(11L)
                .level(1)
                .name(LocationName.ROTTEN_MUSHROOMS)
                .description("There is a strong unpleasant odor, not the best place to stay.. ")
                .imageName("rotten_mushrooms.png")
                .npcs(null) //
                .enemyList(null)
                .key(null)
                .items(null) //
                .neighbouringLocations(List.of(8L)) //8 -dark forest
                .build());

        result.add(Location.builder()
                .id(12L)
                .level(1)
                .name(LocationName.SPIDER_LAIR)
                .description("It seems that someone is watching and following you..")
                .imageName("spider_lair.png")
                .npcs(null) //
                .enemyList(null)
                .key(null)
                .items(null) //
                .neighbouringLocations(List.of(8L)) //8 - dark forest
                .build());

        return result;
    }
}
