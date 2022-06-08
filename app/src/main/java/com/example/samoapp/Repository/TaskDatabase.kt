package com.example.samoapp.Repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.samoapp.Entity.Task
import com.example.samoapp.Entity.User

@Database(entities = [User::class, Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase(){
    abstract fun getUserDAO(): UserDAO
    abstract fun getTaskDAO(): TaskDAO
}