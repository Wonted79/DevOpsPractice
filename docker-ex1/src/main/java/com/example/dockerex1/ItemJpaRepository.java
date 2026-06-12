package com.example.dockerex1;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Primary
@Repository
@RequiredArgsConstructor
public class ItemJpaRepository implements ItemRepository {

    private final SpringDataItemRepository jpaRepository;

    @Override
    public Item save(Item item) {
        return jpaRepository.save(item);
    }

    @Override
    public Optional<Item> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<Item> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }
}
