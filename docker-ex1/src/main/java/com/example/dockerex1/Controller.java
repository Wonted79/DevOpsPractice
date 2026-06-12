package com.example.dockerex1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class Controller {

    private final ItemRepository itemRepository;

    @GetMapping("/api/items")
    public List<Item> findAll() {
        log.info("GET /api/items");
        List<Item> items = itemRepository.findAll();
        log.info("GET /api/items → {}건 반환", items.size());
        return items;
    }

    @GetMapping("/api/items/{id}")
    public ResponseEntity<Item> findById(@PathVariable Long id) {
        log.info("GET /api/items/{}", id);
        return itemRepository.findById(id)
                .map(item -> {
                    log.info("GET /api/items/{} → 조회 성공: {}", id, item);
                    return ResponseEntity.ok(item);
                })
                .orElseGet(() -> {
                    log.warn("GET /api/items/{} → 존재하지 않는 아이템", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping("/api/items")
    public Item create(@RequestBody Item item) {
        log.info("POST /api/items → 요청: {}", item);
        Item saved = itemRepository.save(item);
        log.info("POST /api/items → 생성 완료: {}", saved);
        return saved;
    }

    @PutMapping("/api/items/{id}")
    public ResponseEntity<Item> update(@PathVariable Long id, @RequestBody Item item) {
        log.info("PUT /api/items/{} → 요청: {}", id, item);
        if (!itemRepository.existsById(id)) {
            log.warn("PUT /api/items/{} → 존재하지 않는 아이템", id);
            return ResponseEntity.notFound().build();
        }
        item.setId(id);
        Item updated = itemRepository.save(item);
        log.info("PUT /api/items/{} → 수정 완료: {}", id, updated);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/api/items/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("DELETE /api/items/{}", id);
        if (!itemRepository.existsById(id)) {
            log.warn("DELETE /api/items/{} → 존재하지 않는 아이템", id);
            return ResponseEntity.notFound().build();
        }
        itemRepository.deleteById(id);
        log.info("DELETE /api/items/{} → 삭제 완료", id);
        return ResponseEntity.noContent().build();
    }
}
