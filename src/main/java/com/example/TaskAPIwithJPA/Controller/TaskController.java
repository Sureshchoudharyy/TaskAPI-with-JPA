package com.example.TaskAPIwithJPA.Controller;

import com.example.TaskAPIwithJPA.Entity.Task;
import com.example.TaskAPIwithJPA.Repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskRepo taskRepo;

    @PostMapping
    public Task createTask(@RequestBody Task task){
        return taskRepo.save(task);
    }

    @GetMapping
    public List<Task> getAllTask(){
        return taskRepo.findAll();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id){
        return taskRepo.findById(id).orElseThrow(()->new RuntimeException("Task not found"));
    }

    @PutMapping
    public Task updateTask(@RequestParam Long id,@RequestBody Task task){
        Task t = taskRepo.findById(id).orElseThrow(()->new RuntimeException("Task not found!"));
        t.setTitle(task.getTitle());
        t.setDescription(task.getDescription());
        t.setStatus(task.getStatus());
        t.setDueDate(task.getDueDate());
        return taskRepo.save(t);
    }

    @PatchMapping
    public Task partialUpdate(@RequestParam Long id, @RequestParam String status){
        Task t = taskRepo.findById(id).orElseThrow(()->new RuntimeException("Task not found!"));
        t.setStatus(status.toString());
        return taskRepo.save(t);
    }

    @DeleteMapping
    public Task deleteTask(@RequestParam Long id){
        Task t = taskRepo.findById(id).orElseThrow(()->new RuntimeException("Task not found!"));
        taskRepo.deleteById(id);
        return t;
    }

    @GetMapping("/filter")
    public List<Task> filter(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueBefore){
        return taskRepo.findAll().stream().filter(task -> task.getDueDate().isBefore(dueBefore)).toList();
    }
}
