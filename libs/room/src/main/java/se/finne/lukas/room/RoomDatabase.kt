package se.finne.lukas.room

import androidx.room.Database
import androidx.room.RoomDatabase
import se.finne.lukas.room.dao.UserDao
import se.finne.lukas.room.entities.User
import se.finne.lukas.room.entities.workouts.Workout

@Database(
    entities = [
        User::class,
        Workout::class,
    ],
    version = 1,
)
abstract class RoomDatabase : RoomDatabase(){
    abstract val dao: UserDao
}