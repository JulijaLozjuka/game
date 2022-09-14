package dev.julialoz.game.javarushgame.business.repository;

import java.util.*;

public class Repository <T> {
    Map<Integer,T> repository = new HashMap<Integer, T>();
     Integer currentId = 1;

     List<T> findAll(){
        return new ArrayList<T>(repository.values());
    }

     <S extends T> S save(S entity){
        repository.put(currentId++,entity);
        return entity;
    }

     Optional<T> findById(int id){
        return Optional.ofNullable(repository.get(id));
    }

     boolean existsById (int id){
        return repository.containsKey(id);
     }

     void deleteById(int id){
         repository.remove(id);
     }



}
