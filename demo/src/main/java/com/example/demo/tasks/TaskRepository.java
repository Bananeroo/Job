package com.example.demo.tasks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query("SELECT u FROM Task u WHERE u.programmerId = ?1")
    List<Task> findTasksByProgrammer(Long id);

    @Query("SELECT u FROM Task u WHERE u.programmerId IS NULL")
    List<Task> findFreeTask();

    @Transactional
    @Modifying
    @Query("UPDATE Task u set u.programmerId = ?1 where u.id = ?2 ")
    int assignTaskToProgrammer(Long programmerId,Long taskId );

}
