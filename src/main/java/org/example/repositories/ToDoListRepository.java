package org.example.repositories;

import org.example.entities.ToDoListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoListRepository extends JpaRepository<ToDoListEntity, Integer> {
    // Ви можете додавати власні методи запитів, якщо потрібно
}
