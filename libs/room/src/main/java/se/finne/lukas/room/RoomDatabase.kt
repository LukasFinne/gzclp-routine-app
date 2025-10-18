package se.finne.lukas.room

import androidx.room.Database
import androidx.room.RoomDatabase
import se.finne.lukas.room.dao.UserDao
import se.finne.lukas.room.entities.User
import se.finne.lukas.room.entities.workouts.Bench
import se.finne.lukas.room.entities.workouts.LatPullDown
import se.finne.lukas.room.entities.workouts.Squat

@Database(
    entities = [
        User::class,
        Bench::class,
        Squat::class,
        LatPullDown::class
    ],
    version = 3,
)
abstract class RoomDatabase : RoomDatabase(){
    abstract val dao: UserDao
}