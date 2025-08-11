package com.example.TaskAPIwithJPA.Repo;

import com.example.TaskAPIwithJPA.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository<Task,Long> {
}
