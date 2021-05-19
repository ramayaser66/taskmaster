package com.example.taskmaster;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TasksDao {


    @Query("SELECT * FROM tasks")
    List<Tasks> getAll();

    @Query("SELECT * FROM tasks WHERE id IN (:tasksIds)")
    List<Tasks> loadAllByIds(int[] tasksIds);

    @Query("SELECT * FROM tasks WHERE title LIKE :title LIMIT 1")
    Tasks findByTitle(String title);

//    (onConflict = OnConflictStrategy.IGNORE)
    @Insert
    void insertAll(Tasks... tasks);

    @Insert
    void  insertTasks(Tasks tasksObj);


    @Delete
    void delete(Tasks tasks);
    @Query("DELETE FROM tasks")
    void deleteAll();

    @Query("DELETE FROM tasks WHERE id = :id")
    void deleteById(int id);
}
