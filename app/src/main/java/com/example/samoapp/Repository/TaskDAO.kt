package com.example.samoapp.Repository

import androidx.room.*
import com.example.samoapp.Entity.Task
import com.example.samoapp.Entity.UserWithTasks

@Dao
interface TaskDAO {

    @Insert
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)

    @Query("SELECT * FROM tasks WHERE id = :id")
    fun findById(id: Int): Task

    @Transaction
    @Query("SELECT * FROM users WHERE id = :userId")
    fun findByUserId(userId: Int): UserWithTasks

    @Query("SELECT * FROM tasks")
    fun findAll(): List<Task>

}