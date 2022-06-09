package com.example.samoapp.Repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.samoapp.Entity.User

@Database(entities = [User::class], version = 3)
abstract class TaskDatabase : RoomDatabase(){
    abstract fun getUserDAO(): UserDAO
}