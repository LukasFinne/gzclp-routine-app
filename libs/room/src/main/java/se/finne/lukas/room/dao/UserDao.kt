package se.finne.lukas.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import se.finne.lukas.room.entities.User
import se.finne.lukas.room.entities.relationships.UserAndWorkout
import se.finne.lukas.room.entities.workouts.Workout


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUsers(users: User): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWorkout(
        workout: Workout
    )


    @Transaction
    @Query("UPDATE Workout SET tierTwoSet = :newSets, tierTwoRep = :newReps, weight = :weight WHERE id = :workoutId and userCreatorId = :userCreatorId")
    suspend fun updateWorkoutTierTwoSetAndRep(
        workoutId: Int,
        userCreatorId: Int,
        weight: Double,
        newSets: Int,
        newReps: Int
    )

    @Transaction
    @Query("UPDATE Workout SET tierOneSet = :newSets, tierOneRep = :newReps, weight = :weight WHERE id = :workoutId and userCreatorId = :userCreatorId")
    suspend fun updateWorkoutTierOneSetAndRep(
        workoutId: Int,
        userCreatorId: Int,
        weight: Double,
        newSets: Int,
        newReps: Int
    )
    @Transaction
    @Query("UPDATE Workout SET weight = :newWeight WHERE id = :workoutId and userCreatorId = :userCreatorId")
    suspend fun updateWorkoutWeight(
        workoutId: Int,
        userCreatorId: Int,
        newWeight: Double
    )
    @Query("SELECT * FROM User WHERE id = :id")
    fun getUsersById(id: Int): Flow<User>

    @Transaction
    @Query("SELECT * FROM User")
    fun getUsersWithWorkouts():Flow<List<UserAndWorkout>>

    @Query("SELECT * FROM Workout WHERE workoutName = :name AND userCreatorId = :userCreatorId LIMIT 1")
    fun getWorkoutByNameAndUserId(name: String, userCreatorId: Int): Flow<Workout>
 }
