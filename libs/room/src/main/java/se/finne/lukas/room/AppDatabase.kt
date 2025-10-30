package se.finne.lukas.room

import androidx.room.Database
import androidx.room.RoomDatabase
import se.finne.lukas.room.dao.UserDao
import se.finne.lukas.room.entities.User
import se.finne.lukas.room.entities.workouts.Workout

@Database(entities = [User::class, Workout::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
