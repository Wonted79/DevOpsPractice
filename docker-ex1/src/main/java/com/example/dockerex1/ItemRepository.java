package com.example.dockerex1;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface ItemRepository {
    Item save(Item item);
    Optional<Item> findById(Long id);
    List<Item> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}
