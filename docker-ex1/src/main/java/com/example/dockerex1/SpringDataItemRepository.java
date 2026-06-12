package com.example.dockerex1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SpringDataItemRepository extends JpaRepository<Item, Long> {
}
