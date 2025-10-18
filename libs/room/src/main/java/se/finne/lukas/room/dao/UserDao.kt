package se.finne.lukas.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import se.finne.lukas.room.entities.User
import se.finne.lukas.room.entities.relationships.UserAndBench
import se.finne.lukas.room.entities.relationships.UserAndLatPullDown
import se.finne.lukas.room.entities.relationships.UserAndSquat
import se.finne.lukas.room.entities.workouts.Bench
import se.finne.lukas.room.entities.workouts.LatPullDown
import se.finne.lukas.room.entities.workouts.Squat

@Dao
interface UserDao {
    @Upsert
    suspend fun insertUsers(users: User)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertA1(
        squat: Squat,
        bench: Bench,
        latPullDown: LatPullDown
    )

   @Query("SELECT * FROM User WHERE id = :id")
    fun getUsersById(id: Int): Flow<User>

   @Transaction
   @Query("SELECT * FROM User")
    fun getUsersAndSquat(): Flow<List<UserAndSquat>>

    @Transaction
    @Query("SELECT * FROM User")
    fun getUsersAndBench(): Flow<List<UserAndBench>>

    @Transaction
    @Query("SELECT * FROM User")
    fun getUsersAndLatPullDown(): Flow<List<UserAndLatPullDown>>
}