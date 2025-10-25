package se.finne.lukas.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import se.finne.lukas.room.entities.User
import se.finne.lukas.room.entities.relationships.UserAndWorkout
import se.finne.lukas.room.entities.workouts.Workout


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUsers(users: User)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWorkout(
       workout: Workout
    )

   @Query("SELECT * FROM User WHERE id = :id")
    fun getUsersById(id: Int): Flow<User>

    @Transaction
    @Query("SELECT * FROM User")
    fun getUsersWithWorkouts(): Flow<List<UserAndWorkout>>

}