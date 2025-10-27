package se.finne.lukas.room

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.turbineScope
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import se.finne.lukas.room.dao.UserDao
import se.finne.lukas.room.entities.User
import se.finne.lukas.room.entities.relationships.UserAndWorkout
import se.finne.lukas.room.entities.workouts.Workout
import java.io.IOException
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {
    private lateinit var userDao: UserDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context,
            AppDatabase::class.java).build()
        userDao = db.userDao()

    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndReadUser() = runTest {
        val user = User(username = "TestUser", currentWorkout = "A1")
        val userId = userDao.insertUsers(user)
        val users = userDao.getUsersById(userId.toInt())

        turbineScope {
            val user = users.testIn(backgroundScope)
            assertEquals(User(1, "TestUser", "A1"), user.awaitItem())
        }
    }

    @Test
    @Throws(Exception::class)
    fun insertAndReadUserWithWorkout() = runTest {
        val user = User(username = "TestUser", currentWorkout = "A1")
        userDao.insertUsers(user)
        userDao.insertWorkout(Workout(
            workoutName = "WorkoutA1",
            tierOneSet = 5,
            tierOneRep = 3,
            tierTwoSet = 3,
            tierTwoRep = 10,
            weight = 100,
            userCreatorId = 1
        ))
        val workoutFlow = userDao.getUsersWithWorkouts()
        turbineScope() {
            val userWithWorkoutsFlow = workoutFlow.testIn(backgroundScope)
            assertEquals(
                listOf<UserAndWorkout>(
                    UserAndWorkout(
                        user = User(1, "TestUser", "A1"),
                        workout = listOf(
                            Workout(
                                id = 1,
                                workoutName = "WorkoutA1",
                                tierOneSet = 5,
                                tierOneRep = 3,
                                tierTwoSet = 3,
                                tierTwoRep = 10,
                                weight = 100,
                                userCreatorId = 1
                            )
                        )
                    )
                ),
                userWithWorkoutsFlow.awaitItem()
            )
        }
    }

    @Test
    @Throws(Exception::class)
    fun getWorkoutByNameAndUserIdTest() = runTest {
        val user = User(username = "TestUser", currentWorkout = "A1")
        userDao.insertUsers(user)
        userDao.insertWorkout(Workout(
            workoutName = "WorkoutA1",
            tierOneSet = 5,
            tierOneRep = 3,
            tierTwoSet = 3,
            tierTwoRep = 10,
            weight = 100,
            userCreatorId = 1
        ))
        val workoutFlow = userDao.getWorkoutByNameAndUserId("WorkoutA1", 1)
        turbineScope() {
            val workout = workoutFlow.testIn(backgroundScope)
            assertEquals(
                Workout(
                    id = 1,
                    workoutName = "WorkoutA1",
                    tierOneSet = 5,
                    tierOneRep = 3,
                    tierTwoSet = 3,
                    tierTwoRep = 10,
                    weight = 100,
                    userCreatorId = 1
                ),
                workout.awaitItem()
            )
        }
    }

    @Test
    @Throws(Exception::class)
    fun updateWeightTest() = runTest {
        val user = User(username = "TestUser", currentWorkout = "A1")
        userDao.insertUsers(user)
        userDao.insertWorkout(Workout(
            workoutName = "WorkoutA1",
            tierOneSet = 5,
            tierOneRep = 3,
            tierTwoSet = 3,
            tierTwoRep = 10,
            weight = 100,
            userCreatorId = 1
        ))
        val workoutFlow = userDao.getWorkoutByNameAndUserId("WorkoutA1", 1)
        turbineScope() {
            val workout = workoutFlow.testIn(backgroundScope)
            assertEquals(
                Workout(
                    id = 1,
                    workoutName = "WorkoutA1",
                    tierOneSet = 5,
                    tierOneRep = 3,
                    tierTwoSet = 3,
                    tierTwoRep = 10,
                    weight = 100,
                    userCreatorId = 1
                ),
                workout.awaitItem()
            )
            userDao.updateWorkoutWeight(1, 1, 120)
            assertEquals(
                Workout(
                    id = 1,
                    workoutName = "WorkoutA1",
                    tierOneSet = 5,
                    tierOneRep = 3,
                    tierTwoSet = 3,
                    tierTwoRep = 10,
                    weight = 120,
                    userCreatorId = 1
                ),
                workout.awaitItem()
            )
        }
    }

}
