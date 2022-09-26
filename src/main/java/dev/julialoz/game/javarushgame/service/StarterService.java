package dev.julialoz.game.javarushgame.service;

import dev.julialoz.game.javarushgame.business.repository.Repository;
import dev.julialoz.game.javarushgame.model.User;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@AllArgsConstructor
public class StarterService {

    protected static final Logger log = LogManager.getLogger();

    private Repository<User> userRepository;

    public User loadUser(String name) {
        List<User> users = userRepository.findAll().stream()
                .filter(user1 -> name.equals(user1.getName()))
                .toList();
        if (!users.isEmpty()) {
            log.info("Loaded user from saved data.");
            return users.get(0);
        } else {
            log.info("New user created.");
            return userRepository.save(User.builder()
                    .name(name)
                    .level(1)
                    .currentLocationId(1L)
                    .maxHealth(100)
                    .currentHealth(100)
                    .avatar("avatar.png")
                    .build());
        }
    }
}
