package dev.julialoz.game.javarushgame.business.repository;

import java.util.*;

public class Repository<T> {
    private final Map<Long, T> data = new HashMap<>();
    private Long currentId = 1L;

    public List<T> findAll() {
        return new ArrayList<>(data.values());
    }

    public <S extends T> S save(S entity) {
        data.put(currentId++, entity);
        return entity;
    }

    public <S extends T> S save(Long id, S entity) {
        data.put(id, entity);
        return entity;
    }

    public Optional<T> findById(Long id) {
        return Optional.ofNullable(data.get(id));
    }

    public boolean existsById(Long id) {
        return data.containsKey(id);
    }

    public void deleteById(Long id) {
        data.remove(id);
    }

}
