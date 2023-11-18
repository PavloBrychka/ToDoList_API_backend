package org.example.service;

import org.example.entities.ToDoListEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.example.repositories.ToDoListRepository;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class ToDoListService {


    private List<ToDoListEntity> tasks;

    private final ToDoListRepository toDoListRepository;


    public ToDoListService(ToDoListRepository toDoListRepository) {
        this.toDoListRepository = toDoListRepository;
        this.tasks = toDoListRepository.findAll();
    }

//    public List<ToDoListEntity> getAllTasks() {
//        // Реалізуйте логіку отримання всіх задач (залежно від вашої конкретної реалізації)
//        return tasks;
//    }

//    public ToDoListEntity getTaskById(int id) {
//        // Реалізуйте логіку отримання задачі за ідентифікатором (залежно від вашої конкретної реалізації)
//        return null;
//    }

    public ToDoListEntity addTask(ToDoListEntity task) {
        if (task.getId() != 0) {
            // Якщо ідентифікатор задано, можливо, вам не потрібно створювати нову задачу
            throw new IllegalArgumentException("Cannot add a task with a specified ID");
        }

        // Реалізуйте додаткові перевірки, якщо потрібно

        return toDoListRepository.save(task);
    }

    public ToDoListEntity updateTask(int id, ToDoListEntity task) {
        int taskId = task.getId();

        // Перевірка, чи існує задача з вказаним ідентифікатором
        Optional<ToDoListEntity> existingTaskOptional = toDoListRepository.findById(taskId);

        if (existingTaskOptional.isPresent()) {
            // Задача існує, виконайте оновлення
            ToDoListEntity existingTask = existingTaskOptional.get();

            // Оновлення полів задачі за допомогою нових значень
            existingTask.setTask(task.getTask());
            // Додайте інші поля, якщо вони є

            // Використовуйте метод save для збереження оновленої задачі
            return toDoListRepository.save(existingTask);
        } else {
            // Задача не знайдена, можливо, ви можете викинути виключення або повернути null
            return null;
        }
    }


    public void deleteTask(int id) {
        // Видалення задачі за ідентифікатором
        toDoListRepository.deleteById(id);
    }
}
